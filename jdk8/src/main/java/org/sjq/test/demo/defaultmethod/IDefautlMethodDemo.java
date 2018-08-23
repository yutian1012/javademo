package org.sjq.test.demo.defaultmethod;

public interface IDefautlMethodDemo {
	double calculate(int a);

	//default方法可以在所有实现了该接口的方法中自动继承
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
