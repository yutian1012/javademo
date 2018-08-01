package org.test.basedemo.other;

public class AddressDemo {
	
	public static void main(String[] args) {
		AddressDemo demo=new AddressDemo();
		
		System.out.println(demo);
		System.out.println(demo.hashCode());
		System.out.println(System.identityHashCode(demo));
	}
}
