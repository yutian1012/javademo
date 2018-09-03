package org.sjq.thread.demo.stack;

public class ThreadRunnableStatus implements Runnable{

	@Override
	public void run() {
		while(true) {
			//保证线程一直处于运行状态
		}
	}
	
	public static void main(String[] args) {
		Thread t1=new Thread(new ThreadRunnableStatus(),"ThreadRunnableStatus");
		t1.start();
		printThreadInfo(t1);
	}
	
	private static void printThreadInfo(Thread t) {
		System.out.println(t.getId());
		System.out.println(t.getName());
		System.out.println(t.getPriority());
		for(StackTraceElement e:t.getStackTrace()) {
			System.out.print(e.getClassName()+" "+e.getMethodName() +" "+e.getLineNumber());
			System.out.print("<--");
		}
		System.out.println();
		System.out.println(t.getState());
	}
}
