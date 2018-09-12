package org.test.basedemo.reference;

import java.lang.ref.SoftReference;
/**
 * 参考：https://www.cnblogs.com/fengbs/p/7019687.html
 * 软引用可以和一个引用队列（ReferenceQueue）联合使用，
 * 如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
 * 
 * 注：java中的软引用不一定能够保证获取对象值，如果获取不到，可添加相应的逻辑，如重新创建该对象，或再次重数据库中加载该对象
 */
public class SoftReferenceDemo {
	public static void main(String[] args) {
		String str=new String("abc");                                     // 强引用
		SoftReference<String> softRef=new SoftReference<String>(str);     // 软引用
		str=null;//清除强引用
		System.out.println(softRef.get());//依旧可以通过软引用获取
	}
}
