package org.sjq.thread.demo.suspend;

public class ThreadSuspendDemo implements Runnable{

	private Object lock=null;
	
	public ThreadSuspendDemo(Object lock) {
		this.lock=lock;
	}
	
	@Override
	public void run() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName()+":helloworld");
			try {
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName()+":ending...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Object lock=new Object();
		
		Thread t=new Thread(new ThreadSuspendDemo(lock));
		Thread t2=new Thread(new ThreadSuspendDemo(lock));
		
		t.start();
		Thread.sleep(1000);
		//对t执行suspend方法，t2仍然不能执行
		t.suspend();
		t2.start();
		Thread.sleep(1000);
		t.resume();//唤醒t再次执行
	}
	
}
