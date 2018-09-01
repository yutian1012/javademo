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
 * 8.如果队列未满，则调用enqueue方法，将元素放入到队列中，将对象之赋值到数组对应的putIndex下标位置处。putIndex如果此时指向了数组的最后一个位置（即对象放置到了数组的最后位置处）。putIndex=0赋值为0
 * 		enqueue方法放入元素，移动putIndex，count自增后，调用notEmpty.signal方法。通知等待notEmpty（队列不空），即调用notEmpty.await等待的线程会被唤醒
 * 
 * 9.使用put方法向队列中放入元素，调用lock.lockInterruptibly()，获取允许中断的锁
 * 10.如果队列已满，则调用notFull.await，使线程进入等待状态，循环判断条件是否条件while (count == items.length)，这种方式能够有效的检测队列容量问题
 * 11.如果队列有空闲，则调用enqueue方法添加对象到队列中
 * 
 * 注：add是利用offer实现的，因此入队操作本质上是offer和put操作，offer不会阻塞等待空闲空间，而put方法会调用相关对象的await操作，阻塞等待。
 * 注2：添加方法最终使用的是enqueue方法实现数据入队
 * 注3：两种方式的添加操作都需要加锁，lock.lock()，并在finally中unlock锁
 * 
 * ********************************元素出队操作**********************************************************
 * 
 */
public class ArrayBlockQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Object> blockingQueue=new ArrayBlockingQueue<>(5);//设置容量大小的队列
		blockingQueue.add(new Object());//添加
		blockingQueue.put(new Object());
	}
}
