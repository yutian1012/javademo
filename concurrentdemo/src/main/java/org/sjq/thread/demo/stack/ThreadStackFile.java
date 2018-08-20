package org.sjq.thread.demo.stack;

public class ThreadStackFile implements Runnable{
	@Override
	public void run() {
		while(true) {
			//防止程序退出
		}
	}
	public static void main(String[] args) {
		Thread t=new Thread(new ThreadStackFile(),"workThread");
		t.start();
		
	}
}
