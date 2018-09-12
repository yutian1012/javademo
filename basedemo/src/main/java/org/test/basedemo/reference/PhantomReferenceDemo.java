package org.test.basedemo.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用使用时必须结合引用队列
 * get方法始终返回null
 * 虚引用主要用来跟踪对象被垃圾回收器回收的活动。
 * 虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之 关联的引用队列中。
 */
public class PhantomReferenceDemo {
	private static ReferenceQueue<String> rq = new ReferenceQueue<String>();
	public static void main(String[] args) {
		String str=new String("abc");              
		PhantomReference<String> phantomRef=new PhantomReference<String>(str, rq);
		System.out.println(phantomRef.get());//get方法始终返回null
		System.out.println(phantomRef.isEnqueued());
		
		str=null;//赋值为null，使该对象仅保持虚引用
		System.gc();//把这个虚引用加入到与之 关联的引用队列中。
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//使当前线程睡眠
		System.out.println(phantomRef.isEnqueued());
		
		//能否通过队列访问虚引用对象呢?不能。
		//gc可以吗？
		System.out.println(rq.poll().get());
	}
}
