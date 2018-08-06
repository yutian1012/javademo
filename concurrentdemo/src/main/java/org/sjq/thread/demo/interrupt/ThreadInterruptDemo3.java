package org.sjq.thread.demo.interrupt;

public class ThreadInterruptDemo3 implements Runnable {

	@Override
	public void run() {
		try {// 通常将catch异常的代码放在while循环外层，这样InterruptedException通过异常直接就跳出来循环体
			while(true) {
				System.out.println("helloworld");
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t=new Thread(new ThreadInterruptDemo3(),"thread1");
		t.start();
		Thread.sleep(1000);
		//调用线程的中断
		t.interrupt();
		// 阻塞状态的线程无法被设置中断标志位
		System.out.println(t.getName()+" is interrupted "+t.isInterrupted());
	}
}
