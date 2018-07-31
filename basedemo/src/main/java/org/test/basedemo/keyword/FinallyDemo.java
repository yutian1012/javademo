package org.test.basedemo.keyword;

public class FinallyDemo {
	/**
	 * try中return后，finally依然执行
	 */
	public void finalyAndReturn() {
		try {
			System.out.println("return语句执行后，fianlly依然执行");
			
			return;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("fianlly语句执行");
		}
	}
	/**
	 * 在finally中修改return的数值
	 * @return
	 */
	public Integer finallyChangeData() {
		
		Integer num=new Integer(1);
		try {
			System.out.println("return语句执行后，fianlly依然执行");
			
			return num;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("fianlly语句执行");
			num+=1;
		}
		
		return num;
	}
	/**
	 * finally中执行return语句，会覆盖掉之前的return
	 * @return
	 */
	@SuppressWarnings("finally")
	public Integer finallyCoveryReturnData() {
		Integer num=new Integer(1);
		try {
			System.out.println("return语句执行后，fianlly依然执行");
			
			return num;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("fianlly语句执行");
			num+=1;
			return num;
		}
	}
	
	public static void main(String[] args) {
		FinallyDemo demo=new FinallyDemo();
		System.out.println(demo.finallyCoveryReturnData());
	}
	
}
