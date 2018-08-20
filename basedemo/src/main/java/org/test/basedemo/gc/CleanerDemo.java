package org.test.basedemo.gc;

/**
 * 在java9中已经将finalize方法废弃
 * 占有非堆资源的对象实例，类应该提供一个方法以明确释放这些资源，如果合适的话他们也应该实现AutoCloseable接口。
 * java.lang.ref.Cleaner和java.lang.ref.PhantomReference提供更灵活和有效的方式，在对象无法再访问时释放资源。
 *
 */
public class CleanerDemo implements AutoCloseable {

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
    // A cleaner, preferably one shared within a library
   /* private static final Cleaner cleaner = <cleaner>;
    static class State implements Runnable { 
        State(...) {
            // initialize State needed for cleaning action
        }
        public void run() {
            // cleanup action accessing State, executed at most once
        }
    }
    private final State;
    private final Cleaner.Cleanable cleanable
    public CleaningExample() {
        this.state = new State(...);
        this.cleanable = cleaner.register(this, state);
    }
    public void close() {
        cleanable.clean();
    }*/

}
