package org.test.basedemo.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 同步的实现是使用代理实现的
 * 
 * 1.同步list的创建，使用Collections内部静态类SynchronizedList实现
 * 2.SynchronizedList对象接收list对象作为构造函数的参数，是一个代理类的实现
 * 3.同步的实现，对所有的操作都进行加锁操作，synchronized (mutex)，mutex是一个锁信号量
 */
public class CollectionsListDemo {
	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		list=Collections.synchronizedList(list);
	}
}
