package org.sjq.thread.demo.stop;

public class ThreadStopThreadDeathDemo implements Runnable{

	@Override
	public void run() {
		try {
			System.out.println("hellowlord");
			Thread.sleep(3000);
			System.out.println("shutdown");
		} catch (Throwable ex) {
			System.out.println("抛出异常的线程："+Thread.currentThread().getName());
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new ThreadStopThreadDeathDemo(),"ThreadStopThreadDeathDemo");
		t.start();
		Thread.sleep(1000);//休眠1秒
		t.stop();
	}
}
