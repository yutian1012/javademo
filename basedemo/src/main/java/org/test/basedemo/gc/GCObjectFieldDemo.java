package org.test.basedemo.gc;
/**
 * 思考：如果一个对象的属性并赋予给了另一个对象，而该对象被设置为null后，该对象能否被回收
 *
 * 结论：能被回收，类对类成员的管理也只是保留了类成员的引用而已，而类成员并没有反向引用类。所以对类设置NULL后，没有指向该类的引用了，所以该类可以被回收。 
 */
public class GCObjectFieldDemo {

}
