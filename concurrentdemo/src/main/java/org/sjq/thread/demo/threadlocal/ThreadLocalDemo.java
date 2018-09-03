package org.sjq.thread.demo.threadlocal;

/**
 * 参考：https://blog.csdn.net/cds86333774/article/details/51020819
 * 一般使用场景： 创建一个公共访问类工具，提供静态方法用于获取线程本地变量信息
 * 
 * ***********************************ThreadLocal数据结构***********************************
 * 内部数据存储使用内部类ThreadLocalMap（ThreadLocal$ThreadLocalMap）
 * 	该类内部又定义了Entry（ThreadLocal$ThreadLocalMap$Entry），Entry继承了WeakReference（弱引用）
 * 	Entry对象内部结构包括key和value，其中key类型为ThreadLocal对象，value为要存放的值	
 * 
 * ***********************************ThreadLocal常用方法***********************************
 * 1）set方法，用于放入线程的本地变量
 * 	getMap方法：通过线程对象获取ThreadLocalMap对象，ThreadLocalMap map = getMap(t);
 * 		getMap方法调用Thread类的threadLocals成员变量（访问类型为缺省访问类型，级别只高于private）
 * 		如果获取的Thread对象中的threadLocals为null，还未进行初始化操作，则调用ThreadLocal类的createMap方法
 * 		如果获取的Thread对象中的threadLocals不为null，则调用ThreadLocal$ThreadLocalMap的set方法设置值。
 * 	createMap方法：创建ThreadLocalMap对象，并设置值
 * 		方法实现：t.threadLocals = new ThreadLocalMap(this, firstValue);
 * 	放置数据：调用ThreadLocal$ThreadLocalMap的set方法设置值，key为TheadLocal对象，value为要设置的值。
 * 
 *  注：Thread对象中管理着ThreadLocal$ThreadLocalMap的数据，一个线程可使用多个ThreadLocal变量，向ThreadLocalMap中设置多个本地变量数据
 *  注2：线程中一个ThreadLocal只能存储一个值，再次存储时会覆盖。线程中使用多个ThreadLocal存储多个变量，多个变量都放置在Thread管理的ThreadLocalMap中
 */
public class ThreadLocalDemo {
	//用于不同线程获取其放置的本地变量数据
	private static ThreadLocal<Long> curRentId=new ThreadLocal<Long>();
	public static Long getThreadRentId(){
		return curRentId.get();
	}
	public static void setThreadRentId(Long rentId){
		curRentId.set(rentId);
	} 
	public static void removeThreadRentId(){
		curRentId.remove();
	}
	
	public static void main(String[] args) {
		
	}
}
