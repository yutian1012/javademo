package org.test.basedemo.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 第9讲 对比Hashtable、HashMap、TreeMap有什么不同(极客时间)
 * 
 * 问题思考
 * 1. 从源码去分析 HashMap 的设计和实现要点，理解容量、负载因子等，为什么需要这些参数，如何影响 Map 的性能，实践中如何取舍等。
 * 2. 理解树化改造的相关原理和改进原因。
 * 3. HashMap的性能表现非常依赖于哈希码的有效性， 要满足hashCode 和 equals 的一些基本约定
 * 
 * 知识点
 * 1.以键值对的形式存储和操作数据的容器类型，
 * 2.与HashTable类似，主要区别在于 HashMap 不是同步的，支持 null 键和值
 * 3.hashMap的内部实现结构：以数组和链表的信息组织其结构，通过哈希值决定了键值对在这个数组的寻址；哈希值相同的键值对，则以链表形式存储
 * 4.每次put元素时，如果发生冲突则在链表的第一个位置处插入新添加的元素
 * 5.常见的常量
 * 		DEFAULT_INITIAL_CAPACITY = 1 << 4; // 16
 * 		MAXIMUM_CAPACITY = 1 << 30//最大容量
 * 		DEFAULT_LOAD_FACTOR = 0.75f;//装填因子，影响hashmap性能的因子
 * 		TREEIFY_THRESHOLD = 8;//树化的阈值
 * 
 * 6.hash函数，针对key为null的键，直接返回0，即key为null的对象会存储到数组的第一个位置处
 * 		hash函数 return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);//其中>>>表示无符号右移，忽略符号位，空位都以0补齐（运算结果高16位补0）
 * 		为什么这里需要将高位数据移位到低位进行异或运算呢?这是因为有些数据计算出的哈希值差异主要在高位，而 HashMap 里的哈希寻址是忽略容量以上的高位的，那么这种处理就可以有效避免类似情况下的哈希碰撞。
 * 7。tableSizeFor方法，最终移位操作后保证最高位为1的数据值，后面所有位也都为1。最后一个加1的操作，因此最终会是2的幂次方
 * 		https://blog.csdn.net/ymrfzr/article/details/51220673
 * 8.数值存放位置transient Node<K,V>[] table;
 * 9.无参构造函数只会设置，this.loadFactor = DEFAULT_LOAD_FACTOR;其他变量信息使用默认（table存放对象的数组此时为null） 
 * 		使用懒加载模式，只要使用put方法时，才会去调用resize方法初始化对象容量
 * 10.put方法，定位数组下标位置（p = tab[i = (n - 1) & hash]）。p表示table数组中存放的node对象，如果空表示该桶还未进行过赋值，即没有发生冲突碰撞
 * 		新节点被插入在了链表的尾部
 * 		通过该方法可以看到，当链表的长度大于8时，会将链表进行树化操作
 * 11.resize方法，
 * 		门限值等于（负载因子）x（容量），如果构建 HashMap 的时候没有指定它们，那么就是依据相应的默认常量值。
 * 		门限通常是以倍数进行调整 （newThr = oldThr << 1），当元素个数超过门限大小时，则调整 Map 大小。
 * 		扩容后，需要将老的数组中的元素重新放置到新的数组，这是扩容的一个主要开销来源。
 * 		重hash方法有所不同（jdk8）newTab[j + oldCap]，下标数组并
 */
public class HashMapDemo {
	public static void main(String[] args) {
		new HashMap<>().put("hello", "world");
		testResize();
	}
	/**
	 * 测试查看hashMap的resize方法
	 */
	private static void testResize() {
		Map<String,String> result=new HashMap<>(7);//查看数组table
		//resize的阈值为8×0.75=6，即当存入的数据大于6时，将进行resize方法的调用
		System.out.println("断点查看hashMap初始化");
		
		Random random=new Random();
		for(int i=0;i<6;i++) {
			result.put(random.nextInt(10000)+"", "hello");
		}

		System.out.println("断点查看table数组信息");
		result.put(random.nextInt(10000)+"", "world");//触发resize
	}
}
