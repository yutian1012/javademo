package org.test.basedemo.type.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
/**
 * 查看异常尽早抛出的区别
 */
public class ThrowEarly {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		getFileContent(null);
		System.out.println("****************************************************");
		getFileContentEarly(null);
	}
	
	public static void getFileContent(String fileName) throws FileNotFoundException, IOException {
		try(InputStream in = new FileInputStream(fileName)){
			
		} 
	}
	
	public static void getFileContentEarly(String fileName) throws FileNotFoundException, IOException {
		Objects.requireNonNull(fileName);
		try(InputStream in = new FileInputStream(fileName)){
			
		} 
	}
}
