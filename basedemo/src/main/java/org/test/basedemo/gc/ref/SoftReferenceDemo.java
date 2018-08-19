package org.test.basedemo.gc.ref;

import java.lang.ref.SoftReference;

/**
 * https://www.cnblogs.com/dolphin0520/p/3784171.html
 * 软引用是用来描述一些有用但并不是必需的对象，在Java中用java.lang.ref.SoftReference类来表示。对于软引用关联着的对象，只有在内存不足的时候JVM才会回收该对象。
 * 这个特性很适合用来实现缓存：比如网页缓存、图片缓存等
 *
 */
public class SoftReferenceDemo {
	public static void main(String[] args) {
        
        SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
    }
}
