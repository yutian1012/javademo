package org.test.basedemo.gc.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
/**
 * https://www.cnblogs.com/dolphin0520/p/3784171.html
 * 要注意的是，虚引用必须和引用队列关联使用，
 * 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会把这个虚引用加入到与之 关联的引用队列中。
 * 程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。
 * 如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。
 */
public class PhantomReferenceDemo {
	public static void main(String[] args) {
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());
    }
}
