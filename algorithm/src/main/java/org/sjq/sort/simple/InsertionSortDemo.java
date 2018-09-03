package org.sjq.sort.simple;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度O(n^2)，最好情况O(n)，最坏情况O(n^2)，空间复杂度O(1)，稳定排序
 * 
 * 工作原理：
 * 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
 * 
 * 问题：从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间
 * 
 */
public class InsertionSortDemo {
	public static void main(String[] args) {
		int[] a= {12,43,2,67,49,24,75,32,88,103};
		//insertionSort(a);
		insertionSort2(a);
		System.out.println(Arrays.toString(a));
	}
	/**
	 * 比较过程进行数据移动
	 * @param arr
	 */
	public static void insertionSort(int[] arr) {
		if(null!=arr&&arr.length>0) {
			for(int i=0;i<arr.length;i++) {//i的位置表示已经排序号的元素下标位置
				for(int j=i;j>0;j--) {//j从i开始比较
					if(arr[j]<arr[j-1]) {
						swap(arr,j,j-1);
					}else {
						break;
					}
				}
			}
		}
	}
	
	public static void insertionSort2(int[] arr) {
		int current; 
		for (int i = 0; i < arr.length-1; i++) {
			current = arr[i+1];//获取待插入的元素
			int preindex = i;//待插入元素的前一个元素下标
			while (preindex >=0 && current < arr[preindex]) {//不断向前查找，直到找到一个比当前值小的数据
				arr[preindex+1]=arr[preindex];
				preindex--;
			}
			arr[preindex+1] = current;
		}
	}
	
	/**
	 * 交换数据
	 * @param arr
	 * @param source
	 * @param dest
	 */
	public static void swap(int[] arr,int source,int dest) {
		int tmp=arr[dest];
		arr[dest]=arr[source];
		arr[source]=tmp;
	}
}
