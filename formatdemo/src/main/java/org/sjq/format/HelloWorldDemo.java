package org.sjq.format;

import java.util.Date;

public class HelloWorldDemo {
	public static void main(String[] args) {
		formatDate();
	}
	
	public static void replaceParameter() {
		//字符串替换功能，使用%s作为占位符
		String str=null;
        str=String.format("Hi,%s", "王力");
        System.out.println(str);
        //同时替换多个数据值
        str=String.format("Hi,%s:%s:%s", "王南","王力","sss","ddd");
        System.out.println(str);
	}
	
	public static void formatDate() {
	    String s2 = String.format("%1$tY-%1$tm-%1$te", new Date());  
	    System.out.println(s2);  
	}
}
