package org.test.basedemo.reference;

import java.lang.ref.WeakReference;
/**
 * 参考：https://www.cnblogs.com/fengbs/p/7019687.html
 * 只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
 * 不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。 
 */
public class WeakReferenceDemo {
	public static void main(String[] args) {
		String str=new String("abc");    
		WeakReference<String> weakRef = new WeakReference<String>(str);
		str=null;
		System.out.println(weakRef.get());
	}
}
