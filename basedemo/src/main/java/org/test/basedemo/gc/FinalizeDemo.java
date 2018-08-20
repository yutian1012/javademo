package org.test.basedemo.gc;

/**
 * https://blog.csdn.net/lixpjita39/article/details/79383957
 * 
 * 实现了finalize方法的对象，垃圾回收时会拖慢垃圾回收的速度
 * 
 * 存在内存溢出问题
 * Finalizer线程会和我们的主线程进行竞争，不过由于它的优先级较低，获取到的CPU时间较少，因此它永远也赶不上主线程的步伐。所以最后会发生OutOfMemoryError异常
 * 
 * https://www.cnblogs.com/Smina/p/7189427.html
 */
public class FinalizeDemo {

}
