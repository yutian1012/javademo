package org.test.basedemo.gc;

/**
 * 运行程序添加添加GC信息打印参数
 * 配置参数路径： run configurations --- arguments --vm arguments（运行参数）
 * -XX:+PrintGCDetails 打印GC详细信息
 * -XX:+PrintGCTimeStamps 打印CG发生的时间戳
 * -Xloggc:log/gc.log 指定GC log的位置
 * -XX:+PrintReferenceGC 引用GC的收集
 * @author chenyi
 *
 */
public class PrintGcDetailDemo {
	public static void main(String[] args) {
		System.out.println("hello world");
	}
}
