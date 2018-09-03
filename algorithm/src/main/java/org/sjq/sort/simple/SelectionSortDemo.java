package org.sjq.sort.simple;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度O(n^2)，最好情况O(n^2)，最坏情况O(n^2)，空间复杂度O(1)，不稳定排序
 *
 * 工作原理：
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 */
public class SelectionSortDemo {
	public static void main(String[] args) {
		int[] a= {12,43,2,67,49,24,75,32,88,103};
		//调用选择排序
		//maxSelectionSort(a);
		minSelectionSort(a);
		System.out.println(Arrays.toString(a));
	}
	/**
	 * 选择最大值放到后面
	 * @param arr
	 */
	public static void maxSelectionSort(int[] arr) {
		if(null!=arr&&arr.length>0) {
			for(int i=arr.length-1;i>0;i--) {//i最初指向最后一个元素，不断向前移动，直到指向第一个元素为止
				int maxIndex=0;
				for(int j=0;j<=i;j++) {//从0-i的集合中选出最大值，并记录最大值的下标
					if(arr[j]>arr[maxIndex]) {
						maxIndex=j;
					}
				}
				swap(arr,maxIndex,i);
			}
		}
	}
	/**
	 * 选取最小值放到前面
	 * @param arr
	 */
	public static void minSelectionSort(int[] arr) {
		if(null!=arr && arr.length>0) {
			for(int i=0;i<arr.length;i++) {//i最初指向第一个元素，不断向后移动，直到指向最后一个元素为止
				int minIndex=i;
				for(int j=i;j<arr.length;j++) {//从0-i的集合中选出最大值，并记录最大值的下标
					if(arr[j]<arr[minIndex]) {
						minIndex=j;
					}
				}
				swap(arr,minIndex,i);
			}
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
