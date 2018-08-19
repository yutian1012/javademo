package org.test.basedemo.type.string;

/**
 * string类源码查看
 * 1。final修饰class类
 * 2.final修饰char[]数组
 * 3.new string传递byte数组，需要根据charset进行decode操作
 * 4.针对stringBuffer对象的处理需要添加synchronized关键字（加锁）
 * 5.startWith等函数直接使用char值的==方式比较
 * 6.substring方法，使用new方式创建新的对象，内部使用Arrays工具类调用copyOfRange方法
 * 7.concat方法，创建两个字符串长度相加的字符数组，然后将信息拷贝到新数组中
 * 8.split方法内部使用arrayList类获取分组集合，最后调用toArray方法返回，单个字符使使用内部逻辑，多个字符时使用Regex类处理
 */
public class StringDemo {
	public static void main(String[] args) {
		new String("string source file");
	}
	/**
	 * 利用javap反编译类查看，对于+操作，jvm如何在编译器进行优化的
	 * 
	 * javap -h 查看帮助命令信息
	 * 
	 * javap -v javap org.test.basedemo.type.string.StringDemo（不要添加.class后缀）--定位到target/classes目录下执行
	 */
	public static void javaP() {
		String test="hello"+"world";
		test.intern();//native方法
		String test2=new String("helloworld");
	}
	
	/**
	 * https://blog.csdn.net/xcliang9418/article/details/79786014
	 * 查看运行时常量池大小
	 * intern方法，会使jvm将字符串放置到常量池中，该方法是native方法，必须通过关联openJDK查看实现。
	 * 实现结构：字符串池是使用一个拥有固定容量的 HashMap 每个元素包含具有相同 hash 值的字符串列表。
	 * 
	 * 查看字符串常量池大小 -XX:+PrintStringTableStatistics 
	 * 设置大小：-XX:StringTableSize=1009 （查看Number of buckets的值）
	 */
	public static void stringConstantsPool() {
		String test="hello"+"world";
		test.intern();//native方法
	}
}
