package org.sjq.thread.demo.stop;

public class ThreadStopLockFreeDemo implements Runnable{
	
	private Object lockObj;
	
	public ThreadStopLockFreeDemo(Object lock) {
		this.lockObj=lock;
	}

	public Object getLockObj() {
		return lockObj;
	}
	public void setLockObj(Object lockObj) {
		this.lockObj = lockObj;
	}
	
	@Override
	public void run() {
		synchronized (lockObj) {
			try {
				System.out.println(Thread.currentThread().getName()+"acquired lock...");
				Thread.sleep(5000);//休眠5秒
				System.out.println(Thread.currentThread().getName()+"quiting...");
			} catch (Throwable e) {
				 System.out.println("抛出异常的线程："+Thread.currentThread().getName());
		         e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Object lock=new Object();//定义锁对象
		Thread t1=new Thread(new ThreadStopLockFreeDemo(lock),"ThreadStopLockFreeDemo1");
		Thread t2=new Thread(new ThreadStopLockFreeDemo(lock),"ThreadStopLockFreeDemo2");
		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		t1.stop();
	}

}
