package org.sjq.sort.simple;

import java.util.Arrays;

/**
 * 希尔排序
 * 时间复杂度O(n*log2n)，最好情况O(n*log2n)，最坏情况O(n*log2n)，空间复杂度O(1)，不稳定排序
 * 
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序
 * 注：它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * 
 * 图片位置：/images/java_basic/algorithm/shellsort.png
 * 
 * 1）指定step步长，不断进行除2操作，直到步长为0.
 * 2）排序时，从step位置处开始比较（step前的元素，向前移动step为负值，没有意义），比较时需要比较移动步长位置处的元素。向前查找的过程即为插入排序的过程
 */
public class ShellSortDemo {
	public static void main(String[] args) {
		int[] a= {12,43,2,67,49,24,75,32,88,103};
		shellSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void shellSort(int[] arr) {
		if(null!=arr&&arr.length>0) {
			int step=arr.length/2;//初始步长
			while(step>0) {
				for(int i=step;i<arr.length;i++) {//从step位置处开始比较
					int current=arr[i];//待比较的元素
					int preIndex=i-step;
					while(preIndex>=0 && arr[preIndex]>current) {//step步长前的元素大于当前待比较的元素，则继续循环
						//移动大的数据
						arr[preIndex+step]=arr[preIndex];//将前一个元素与step后一个元素交换位置
						preIndex=preIndex-step;
					}
					arr[preIndex+step]=current;//将值插入到最终的位置处
				}
				
				step=step/2;//步长不断除2操作
			}
		}
	}
	
}
