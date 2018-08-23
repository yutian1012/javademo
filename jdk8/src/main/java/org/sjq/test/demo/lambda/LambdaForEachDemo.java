package org.sjq.test.demo.lambda;

import java.util.Arrays;
import java.util.List;

public class LambdaForEachDemo {
	public static void main(String[] args) {
		String[] atp = {"Rafael Nadal", "Novak Djokovic",  
			       "Stanislas Wawrinka",  
			       "David Ferrer","Roger Federer",  
			       "Andy Murray","Tomas Berdych",  
			       "Juan Martin Del Potro"};  
		List<String> players =  Arrays.asList(atp);  
		  
		// 以前的循环方式  
		for (String player : players) {  
		     System.out.print(player + "; ");  
		}  
		  
		// 使用 lambda 表达式以及函数操作(functional operation)，list集合作为参数
		//这里的forEach就是使用了iterable接口定义的default方法
		players.forEach((player) -> System.out.print(player + "; "));  
		   
		// 在 Java 8 中使用双冒号操作符(double colon operator)  
		players.forEach(System.out::println);  
	}
}
