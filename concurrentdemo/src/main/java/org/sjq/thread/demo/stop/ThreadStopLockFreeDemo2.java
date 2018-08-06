package org.sjq.thread.demo.stop;

public class ThreadStopLockFreeDemo2 implements Runnable{
	
	private Object lockObj1;
	private Object lockObj2;
	
	public ThreadStopLockFreeDemo2(Object lock1,Object lock2) {
		this.lockObj1=lock2;
		this.lockObj2=lock2;
	}
	
	@Override
	public void run() {
		synchronized (lockObj1) {
			synchronized (lockObj2) {
				try {
					System.out.println(Thread.currentThread().getName()+"acquired lock...");
					Thread.sleep(5000);//休眠5秒
					System.out.println(Thread.currentThread().getName()+"quiting...");
				} catch (InterruptedException e) {//catch (Throwable e) {//想不起来try这个错误，就会将锁都释放掉
					 System.out.println("抛出异常的线程："+Thread.currentThread().getName());
			         e.printStackTrace();
				}
			}
			try {
				System.out.println("lockObj2 not free...");
				Thread.sleep(2000);
				System.out.println("lockObj2 freeing...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Object lock=new Object();//定义锁对象
		Object lock2=new Object();
		Thread t1=new Thread(new ThreadStopLockFreeDemo2(lock,lock2),"ThreadStopLockFreeDemo1");
		Thread t2=new Thread(new ThreadStopLockFreeDemo2(lock,lock2),"ThreadStopLockFreeDemo2");
		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		t1.stop();
	}

}
