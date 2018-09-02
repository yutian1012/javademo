package org.test.basedemo.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考：https://www.cnblogs.com/go2sea/p/5627539.html
 * ********************************ReentrantLock对象**********************************************************
 * ReentrantLock也提供了两种工作模式：公平模式&非公平模式，也是通过自定义两种同步器FairSync&NonfairSync来实现的
 * 
 * ********************************ReentrantLock对象的构造函数**********************************************************
 * 1）默认构造函数
 * 	sync = new NonfairSync();使用非公平锁来构造Sync对象（继承了AbstractQueuedSynchronizer类实现同步机制）
 * 	
 * 2）传递boolean类型可以指定待创建的同步类型：即创建公平锁和非公平锁
 * sync = fair ? new FairSync() : new NonfairSync();
 * 
 * ********************************ReentrantLock对象的常用函数**********************************************************
 * 1）lock方法
 * 	调用sync.lock()，该方法在Sync内部类中声明为抽象，在FairSync和NonfairSync中被子类覆盖重写
 *  
 *  FairSync类的lock实现：
 *  	内部仅仅调用了acquire(1);即AbstractQueuedSynchronizer类的acquire方法
 *  		AbstractQueuedSynchronizer类的acquire方法实现：
 *  			if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
 *  				selfInterrupt();
 *				注：tryAcquire试图获取锁（AQS的acquire方法调用了fairSync重写的tryAcquire方法）
 *
 *	FairSync类的tryAcquire实现
 *		成功获取锁的过程：
 *		FairSync初始对象时state为0，定义在AbstractQueuedSynchronizer类中。
 *  	调用hasQueuedPredecessors判断队列中是否已经存在等待的线程（因为是公平锁，需要先让已经在队列中的线程先执行）
 * 		调用compareAndSetState(0, acquires)，该方法定义在AbstractQueuedSynchronizer类中。
 * 		期望值为0，更新值为1，利用cas实现state属性的设置。可以看到设置时使用的是Unsafe类的compareAndSwapInt
 * 			unsafe.compareAndSwapInt(this, stateOffset, expect, update);传递this对象，stateOffset（state属性的内存偏移地址），expect（state的期望值，内存中存放的值），update（state待更新的值）
 * 		调用setExclusiveOwnerThread方法设置公平锁的排他线程（即当前线程），该方法位于AbstractOwnableSynchronizer类中，即AbstractQueuedSynchronizer的父类
 * 
 * 2）lockInterruptibly方法
 * 	调用sync.acquireInterruptibly(1);进而执行AbstractQueuedSynchronizer类中该方法的定义
 * 		方法的主要实现代码：if (!tryAcquire(arg))  doAcquireInterruptibly(arg);
 * 		注：即调用tryAcquire方法（查看上面公平锁的实现），如果没有成功获取锁，则执行doAcquireInterruptibly方法。
 * 		
 */
public class ReentrantLockDemo {
	public static void main(String[] args) {
		ReentrantLock reentrantLock=new ReentrantLock(true);
		reentrantLock.lock();
		try {
			reentrantLock.lockInterruptibly();
		} catch (InterruptedException e) {
			//处理中断逻辑，放置数据不一致问题
			e.printStackTrace();
		}
	}
}
