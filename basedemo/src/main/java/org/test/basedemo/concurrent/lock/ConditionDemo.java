package org.test.basedemo.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 参考：https://www.cnblogs.com/go2sea/p/5630355.html
 * 
 * Condition的大概实现：
 * 	AbstractQueuedSynchronizer内部维护着一个同步队列（双向链表实现），多个条件队列（单向链表实现），
 * 		条件队列由AbstractQueuedSynchronizer的内部类ConditionObject来维护，new一个ConditonObject ，则多一个条件队列，
 * 		当一个线程执行await方法是，会把当线程包装成一个Node节点，放到执行await方法的ConditionObject的条件队列中，释放锁并被阻塞，
 * 		当执行signal方式时，会把条件队列的第一个节点移除，并转移到同步队列中，获取到锁即可继续执行
 * 注：要理解好同步队列和条件队列，同步队列使用AbstractQueuedSynchronizer的head和tail来指向链表的头和尾部，维护队列的链表信息通过Node节点的prev和next实现双向链表结构
 * 注2：条件队列是属于ConditionObject结构对象的，使用firstWaiter和lastWaiter分别执行链表的头和尾部，维护队列的链表信息是通过Node接的nextWaiter来实现的
 * 
 * 使用ReentrantLock对象生成Conditinon时
 * lock和unlock针对的是同步队列进行的锁操作及等待获取锁，而condition的wait和signal则是针对线程之间协作的实现（使用条件队列实现），释放和获取锁的操作则需要依赖同步队列实现
 * 
 * ********************************Condition对象**********************************************************
 * 1.Condition对象的获取，Condition必须被绑定到一个独占锁上使用，可通过ReentrantLock的newCondition方法获取
 * 2.ReentrantLock利用内部类ReentrantLock$Sync来同步实现机制，内部类Sync该类继承了AbstractQueuedSynchronizer
 * 3.Condition对象实际类型是AbstractQueuedSynchronizer$ConditionObject
 * 
 * 注：Condition实际上是AQS框架的内容
 * 
 * ********************************ConditionObject的内部结构**********************************************************
 * 两个指针指向等待队列的队头和队尾，即用来维护一个Condition等待队列
 * private transient Node firstWaiter;
 * private transient Node lastWaiter;
 * 注：队列中存放的节点是Node（也是有AbstractQueuedSynchronizer的内部类，即AbstractQueuedSynchronizer$Node）
 * 
 * ********************************Node的内部结构**********************************************************
 * Node对象中的nextWaiter成员、SHARED成员和EXCLUSIVE成员
 * 	static final Node SHARED = new Node();//SHARED为一个final的空节点，用来表示当前模式是共享模式
 *  static final Node EXCLUSIVE = null;//EXCLUSIVE成员是一个final的null，因此默认模式是独占模式
 *  Node nextWaiter;//默认情况下nextWaiter是null，在Condition队列中nextWaiter被用来指向队列里的下一个等待线程。
 * 注：nextWaiter在共享模式下，被设置为SHARED；在一个线程从Condition队列中被移除之后，nextWaiter被设置为空（EXCLUSIVE）。这再次表明：Condition必须被绑定在一个独占锁上使用。
 * 
 * 线程的等待状态waitStatus（volatile类型变量）：CANCELLED，SIGNAL，CONDITION，PROPAGATE
 * node链：prev和next（volatile变量）
 * Thread对象：入队列的Thread对象
 * 
 * ********************************Condition的await方法的工作流程**********************************************************
 * 1.await 在条件变量上等待，即调用Condition的await方法
 * 	Condition在调用await方法之前，必须先获取锁，注意，这个锁必须是一个独占锁。
 * 
 * 1）addConditionWaiter方法（将线程添加到Condition的等待队列上）
 * 	await方法内部会调用addConditionWaiter方法，实现将Thread对象封装成Node（状态为CONDITION）设置到条件对象的等待链上，即放置在队尾lastWaiter的nextWaiter上，并移动lastWaiter指针
 *  具体过程：首先，方法先检查一下队列尾节点是否还在等待Condition（如果被signal或者中断，waitStatus会被修改为0或者CANCELLED）。如果尾节点被取消或者中断，调用unlinkCancelledWaiters方法删除Condition队列中被cancel的节点。然后将当前线程封装在一个Node中，添加到Condition队列的尾部。这里由于我们在操纵Condition队列的时候已经获取了一个独占锁，因此不会发生竞争。
 * 
 * 2）unlinkCancelledWaiters方法（移除被cancel或被中断的Node节点）
 * unlinkCancelledWaiters方法很简单，从头到尾遍历Condition队列，移除被cancel或被中断的节点。
 * 注：由于这里我们在操纵Condition队列的时候已经获取了所绑定的独占锁，因此不用担心竞争的发生。
 * 
 * 3）fullyRelease方法（释放锁）
 * 
 * 注：在调用Condition对象的await等待方法后，会释放锁。
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 值得注意的是，Condition队列与Sync队列（锁等待队列）有几点不同：①Condition队列是一个单向链表，而Sync队列是一个双向链表；②Sync队列在初始化的时候，会在队列头部添加一个空的dummy节点，它不持有任何线程，而Condition队列初始化时，头结点就开始持有等待线程了。
 */
public class ConditionDemo {
	private static ReentrantLock reentrantLock=new ReentrantLock();
	private static Condition condition=reentrantLock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		
	}
	
	static class WaitThread implements Runnable{
		@Override
		public void run() {
			//awit方法前要先获取锁获取锁
			reentrantLock.lock();
			try {
				condition.await();//等待
				System.out.println("线程await被唤醒");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				reentrantLock.unlock();
			}
		}
	}
	
	static class NotifyThread implements Runnable{

		@Override
		public void run() {
			//条件signal前也需要获取锁对象
			reentrantLock.lock();
			try {
				condition.signal();//唤醒阻塞
			}finally{
				reentrantLock.unlock();
			}
		}
		
	}
}
