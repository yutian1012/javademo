package org.test.basedemo.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * 参考：https://www.jianshu.com/p/f86d3a43eec5
 * ReferenceQueue对象的内部结构及其左右
 * 下面的代码并不能冲弱引用中获取VeryBig对象，而只能获取赋值到VeryBigWeakReference中的id属性。
 * 
 * 我们在创建各种引用并关联到响应对象时，可以选择是否需要关联引用队列。
 * JVM 会在特定时机将引用 enqueue 到队列里，我们可以从队列里获取引用（remove 方法在这里实际是有获取的意思）进行相关后续逻辑。
 * 尤其是幻象引用，get 方法只返回 null，如果再不指定引用队列，基本就没有意义了。
 */
public class ReferenceQueueDemo {

    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

    public static void main(String args[]) {
        int size = 3;
        LinkedList<WeakReference<VeryBig>> weakList = new LinkedList<WeakReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            weakList.add(new VeryBigWeakReference(new VeryBig("Weak " + i), rq));
            System.out.println("Just created weak: " + weakList.getLast());
        }

        System.gc(); 
        try { // 下面休息几分钟，让上面的垃圾回收线程运行完成
            Thread.currentThread().sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //从weakList中尝试获取软引用对象
        for(WeakReference<VeryBig> ref:weakList) {
        	System.out.println(((VeryBigWeakReference) (ref)).id);
        }
        
        System.out.println("**************************queue********************************");
        checkQueue();
    }
    
    public static void checkQueue() {
        Reference<? extends VeryBig> ref = null;
        while ((ref = rq.poll()) != null) {
            if (ref != null) {
                System.out.println("In queue: "    + ((VeryBigWeakReference) (ref)).id);
            	//System.out.println("In queue: "    + ((VeryBigWeakReference) (ref)).get().id);
            }
        }
    }
}
class VeryBig {
    public String id;
    // 占用空间,让线程进行回收
    byte[] b = new byte[2 * 1024];

    public VeryBig(String id) {
        this.id = id;
    }

    protected void finalize() {
        System.out.println("Finalizing VeryBig " + id);
    }
}

class VeryBigWeakReference extends WeakReference<VeryBig> {
    public String id;

    public VeryBigWeakReference(VeryBig big, ReferenceQueue<VeryBig> rq) {
        super(big, rq);
        this.id = big.id;
    }
    protected void finalize() {
        System.out.println("Finalizing VeryBigWeakReference " + id);
    }
}