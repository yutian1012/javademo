package org.sjq.thread.demo.interrupt;

public class ThreadInterruptDemo implements Runnable {

	@Override
	public void run() {
		while(true) {
			System.out.println("helloworld");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new ThreadInterruptDemo());
		t.start();
		Thread.sleep(1000);
		t.interrupt();//调用线程的中断，查看是否能够退出run方法的循环
	}
}
