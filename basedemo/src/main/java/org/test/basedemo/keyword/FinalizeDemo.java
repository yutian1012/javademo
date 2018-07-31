package org.test.basedemo.keyword;

public class FinalizeDemo {
	private static FinalizeDemo finalizeObj;
	
	public static void main(String[] args) throws InterruptedException {
		finalizeObj=new FinalizeDemo();//创建对象
		//清空对象
		finalizeObj=null;
		//调用gc方法，准备回收实例化的finalizeDemo对象
		System.gc();
		Thread.sleep(500);
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("execute finalize");
	}
}
