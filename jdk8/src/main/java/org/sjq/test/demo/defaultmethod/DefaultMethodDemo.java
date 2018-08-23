package org.sjq.test.demo.defaultmethod;

public class DefaultMethodDemo implements IDefautlMethodDemo{

	@Override
	public double calculate(int a) {
		return a*a;
	}
	public static void main(String[] args) {
		DefaultMethodDemo demo=new DefaultMethodDemo();
		System.out.println(demo.calculate(100));
		System.out.println(demo.sqrt(100));
	}
}
