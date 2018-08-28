package org.sjq.test.demo.lambda;

public class LambdaCaculatorDemo2 {
	private int c=123;//增加了一个成员变量
	public static void main(String[] args) {
		LambdaCaculatorDemo2 test=new LambdaCaculatorDemo2();
	    test.LambdaUse(12,23);
	}
 
	public void LambdaUse(int a,int b) {
		 ILambdaCaculator lambdaCaculator=(x,y)->x+y+c;//在lambda表达式中调用了这个成员变量
		 System.out.println("a+b+c="+lambdaCaculator.result(a, b));
	}

}
