package org.test.basedemo.collection.map;

import java.util.Hashtable;

/**
 * 
 * 第9讲 对比Hashtable、HashMap、TreeMap有什么不同(极客时间)
 * 
 * 1.Hashtable 是早期 Java 类库提供的一个哈希表实现，
 * 2.本身是同步的，不支持 null 键和值，由于同步导致的性能开销，所以已经很少被推荐使用。
 * 3.扩展了 Dictionary 类的，类结构上与 HashMap 之类明显不同，hashMap继承AbstractMap
 */
public class HashtableDemo {
	public static void main(String[] args) {
		new Hashtable<>();
	}
}
