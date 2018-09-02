package org.test.basedemo.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
/**
 * *******************************成员变量及初始构造***********************************************************
 * 1.内部使用Objectp[]数组存放数据--final Object[] items;
 * 2.队列的相关指针及变量takeIndex，putIndex，count
 * 3.相关锁对象lock（ReentrantLock），notEmpty（Condition），notFull（Condition）
 * 4.传递capacity容量的构造函数，容量极为Object[]数组的大小
 * 5.构造函数内可传递公平锁，boolean类型的变量fair，同时初始化notEmpty和notFull变量（lock.newCondition()）
 * 
 * ********************************元素入队操作**********************************************************
 * 6.向队列中放入元素add方法，调用父类AbstractQueue类的add方法。调用offer方法。add方法抛出IllegalStateException异常（queue full）
 * 7.offer方法会使用lock对象的加锁操作（阻塞），判断队列是否已满，如果满了直接返回false，
 * 8.如果队列未满，则调用equeue方法
 * 
 * 9.使用put方法向队列中放入元素，调用lock.lockInterruptibly()，获取允许中断的锁
 * 10.如果队列已满，则调用notFull.await，使线程进入等待状态，循环判断条件是否条件while (count == items.length) notFull.await();等待队列不满条件，这种方式能够有效的检测队列容量问题
 * 11.如果队列有空闲，则调用enqueue方法添加对象到队列中
 * 
 * enqueue方法，将元素放入到队列中，将对象之赋值到数组对应的putIndex下标位置处。putIndex如果此时指向了数组的最后一个位置（即对象放置到了数组的最后位置处）。putIndex=0（即赋值为0）
 * 		enqueue方法放入元素，移动putIndex，count自增后，调用notEmpty.signal方法。通知等待notEmpty（队列不空），即调用notEmpty.await等待的线程会被唤醒
 * 
 * 注：add是利用offer实现的，因此入队操作本质上是offer和put操作，offer不会阻塞等待空闲空间，而put方法会调用相关对象的await操作，阻塞等待。
 * 注2：添加方法最终使用的是enqueue方法实现数据入队
 * 注3：两种方式的添加操作都需要加锁，lock.lock()，并在finally中unlock锁
 * 
 * ********************************设置等待时间的入队操作**********************************************************
 * 由上面的分析，可以看出offer方法不会等待队列有空闲位置后，再放入，即不进入等待状态。而put操作是会进入到线程等待状态。
 * 因此，针对offer方法可以设置一个超时时间，而put方法是不需要设置超时时间的，因为，它会一直等待，直到队列中用空闲位置，然后放入元素。
 * 分析offer方法设置超时时间的实现：
 * 
 * 思想：将offer方法设置的时间使用TimeUnit枚举类的toNanos方法转换成纳秒基本的long值。调用notFull.awaitNanos方法，设置等待的纳秒时间。
 * 
 * 定位notFull类的awaitNanos方法实现位置：
 * notFull是Condition的实例，是通过ReentrantLock类的newCondition方法创建的实例。通过查看newCondition方法，可以发现ReentrantLock内部是使用内部类Sync创建（Sync是一个提供同步实现机制）
 * 注：ReentrantLock类的newCondition实际是调用了sync.newCondition()完成Condition实例的创建。
 * 
 * 内部类ReentrantLock$Sync的同步实现机制：
 * 内部类Sync该类继承了AbstractQueuedSynchronizer。查看Sync的newCondition方法的实现，返回new ConditionObject()对象，该对象定义在AbstractQueuedSynchronizer类内部。到此，我们找到了notFull对象的实际类
 * 
 * 内部类AbstractQueuedSynchronizer$ConditionObject类的awaitNanos方法
 * 
 * 
 * 注：Sync是一个内部的抽象类，并不能直接使用，在ReentrantLock内部同时提供类该类的两个实现NonfairSync和FairSync
 * 
 * 参考：https://blog.csdn.net/zzti_erlie/article/details/80358564
 * 这里说一下Condition的大概实现，AbstractQueuedSynchronizer内部维护着一个同步队列（双向链表实现），多个条件队列（单向链表实现），
 * 条件队列由AbstractQueuedSynchronizer的内部类ConditionObject来维护，new一个ConditonObject ，则多一个条件队列，
 * 当一个线程执行await方法是，会把当线程包装成一个Node节点，放到执行await方法的ConditionObject的条件队列中，释放锁并被阻塞，
 * 当执行signal方式时，会把条件队列的第一个节点移除，并转移到同步队列中，获取到锁即可继续执行
 * signalAll和signal实现类似，区别如下，signal将等待队列中的一个非CANCELLED节点放到同步队列，而signalAll是将等待队列中的所有非CANCELLED节点放到同步队列中
 * 
 * ********************************元素出队操作**********************************************************
 * 12.take方法，类似put方法，会进行加锁操作,调用lock.lockInterruptibly()，获取允许中断的锁
 * 13.如果队列空，会等待while (count == 0) notEmpty.await();等待不空条件
 * 14.如果队列不空，调用dequeue方法取出队列元素
 * 
 * 13.poll方法，类似offer方法，不会阻塞等待队列有值
 * 14.如果队列空，则直接返回null
 * 15.如果队列不空，则调用dequeue方法取出队列元素。
 * 
 * 16.peek方法，
 *  
 * dequeue方法，从队列中获取队头元素（即takeIndex指向的数组下标元素，返回该元素，同时将引用置空，便于垃圾回收）。
 * 	takeIndex++，指向队列的下一个元素，如果元素长度为数组长度，则指向数组开头。count自减，维护队列元素数量。notFull.signal();标识队列不满，唤醒调用notFull.await方法等待的线程
 * 
 
 * 
 * ********************************问题：为什么调用await或signal时需要调用lock加锁，并在finally中unlock**********************************************************
 * 
 * 调用obj.wait()会立即释放锁，，以便其他线程可以执行obj.notify()，但是notify()不会立刻立刻释放sycronized（obj）中的obj锁，必须要等notify()所在线程执行完synchronized（obj）块中的所有代码才会释放这把锁.
 * 而 yield(),sleep()不会释放锁。
 * 
 */
public class ArrayBlockQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Object> blockingQueue=new ArrayBlockingQueue<>(5);//设置容量大小的队列
		blockingQueue.add(new Object());//添加，内部使用的是offer方法，当队列已满，返回false，add方法会抛出异常
		blockingQueue.put(new Object());//队列已满时会进入等待
		
		Object obj=blockingQueue.take();//从队列中获取队头元素，队列空时，进入线程等待状态
		blockingQueue.poll();//从队列中取出对头元素，队列空时返回null。
		blockingQueue.peek();//从队列中取出对头元素，不会移动对头指针takeIndex，即元素不出队列，调用itemAt方法实现
	}
}
