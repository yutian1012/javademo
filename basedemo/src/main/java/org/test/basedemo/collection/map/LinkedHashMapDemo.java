package org.test.basedemo.collection.map;

import java.util.LinkedHashMap;

/**
 * 第9讲 对比Hashtable、HashMap、TreeMap有什么不同(极客时间)
 * 
 * LinkedHashMap 通常提供的是遍历顺序符合插入顺序，它的实现是通过为条目（键值对）维护一个双向链表。在HashMap的基础上增加了双向链表的维护，效率上要低一些
 */
public class LinkedHashMapDemo {
	public static void main(String[] args) {
		new LinkedHashMap<>();
	}
}
