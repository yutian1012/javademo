package org.test.basedemo.collection.map;

import java.util.TreeMap;
/**
 * 第9讲 对比Hashtable、HashMap、TreeMap有什么不同(极客时间)
 * 
 * 1.TreeMap 则是基于红黑树的一种提供顺序访问的 Map， get、put、remove 之类操作都是 O（log(n)）的时间复杂度，
 * 2.具体顺序可以由指定的 Comparator 来决定，或者根据键的自然顺序来判断。
 *
 */
public class TreeMapDemo {
	public static void main(String[] args) {
		new TreeMap<>();
	}
}
