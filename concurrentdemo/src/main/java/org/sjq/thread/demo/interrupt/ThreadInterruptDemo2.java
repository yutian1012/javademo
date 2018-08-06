package org.sjq.thread.demo.interrupt;

public class ThreadInterruptDemo2 implements Runnable {

	@Override
	public void run() {
		while(true) {
			System.out.println("helloworld");
			if(Thread.interrupted()) {//线程自身判断中断标志位
				break;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new ThreadInterruptDemo2(),"thread1");
		t.start();
		Thread.sleep(1000);
		//调用线程的中断
		t.interrupt();
		//其他线程判断某线程的中断标志位，多次运行会发现一个问题，有时候返回false有时候返回true
		System.out.println(t.getName()+" is interrupted "+t.isInterrupted());
	}
}
