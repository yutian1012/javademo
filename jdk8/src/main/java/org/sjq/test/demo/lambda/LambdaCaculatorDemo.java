package org.sjq.test.demo.lambda;

public class LambdaCaculatorDemo {
	public static void main(String[] args) {
		//在静态方法中使用lambda表达式：(a,b)-> a+b，可以先简单的理解为，使用lambda构造了匿名内部类的方式实现方法调用
		System.out.println("add :"+LambdaCaculatorUse((a,b)-> a+b,12,14));
	}
	public static int LambdaCaculatorUse(ILambdaCaculator lambda,int a,int b){
		return lambda.result(a, b);
	}
}
