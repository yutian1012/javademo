package org.test.basedemo.type.string;

/**
 * 创建时可指定容量，对象容量内容存储在AbstractStringBuilder类中的char[]数组中
 * 创建时如果传递初始化的字符串，则容量为字符串长度+16 --this(seq.length() + 16);
 * append方法，内部判断是否能够容纳追加的字符串，如果不能则使用Arrays.copyOf进行扩容操作，并且可以看到方法使用synchronized修饰
 */
public class StringBufferDemo {
	public static void main(String[] args) {
		new StringBuffer();
	}
}
