package org.test.basedemo.collection.list;

import java.util.ArrayList;
/**
 * 1.内部使用Object[]数组存储
 * 2.容量最大值：Integer.MAX_VALUE （如果指定的值大于该值Integer.MAX_VALUE - 8，则使用）
 * 3.indexOf方法使用equals方法进行判断，lastIndexOf则是从后向前遍历
 * 4.toArray方法使用Arrays.copyOf实现
 * 5.add方法会调用ensureCapacityInternal方法（判断是否需要扩容），
 * 		如果创建ArrayList不指定初始大小，则会扩展DEFAULT_CAPACITY=10的大小
 * 		如果不是第一次使用add方法，并且又需要扩容时，查看grow函数，int newCapacity = oldCapacity + (oldCapacity >> 1)，新的容量增加原容量的0.5倍
 * 6.remove方法，定位待删除的元素位置，移动后面的对象。将最后一个一个数组元素的指向设置为null，从而可以实现GC回收对象。
 * 7.clear方法，for循环数组元素，为数组元素的引用复制null。
 * 注：remove和clear并非是单独将size大小改变，并且使用null赋值删除元素，从而防止内存泄漏
 */
public class ListDemo {
	public static void main(String[] args) {
		new ArrayList<String>();
	}
}
