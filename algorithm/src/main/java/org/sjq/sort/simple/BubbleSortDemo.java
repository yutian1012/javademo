package org.sjq.sort.simple;

import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度O(n^2)，最好情况O(n)，最坏情况O(n^2)，空间复杂度O(1)，稳定排序
 * 
 * 算法过程
 * 1）比较相邻的元素。如果第一个比第二个大，就交换它们两个
 * 2）对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；（完成第一次排序）
 * 3）针对所有的元素重复以上的步骤，除了最后一个；
 * 4）重复步骤1~3，直到排序完成。
 *
 */
public class BubbleSortDemo {
	public static void main(String[] args) {
		int[] a= {12,43,2,67,49,24,75,32,88,103};
		//冒泡排序
		bubbleSort(a);
		System.out.println(Arrays.toString(a));
		bestBubbleSort(a);
	}
	
	public static void bubbleSort(int[] arr) {
		if(null!=arr&&arr.length>0) {
			for(int i=1;i<arr.length;i++) {//外层循环控制循环次数
				for(int j=0;j<arr.length-i;j++) {//内存循环控制数据交换
					if(arr[j]>arr[j+1]) {//交换位置
						int tmp=arr[j];//需要借助的空间
						arr[j]=arr[j+1];
						arr[j+1]=tmp;
					}
				}
			}
		}
	}
	/**
	 * 最好情况--设置一个标识符，如果内层循环没有进行过数据交换，则证明已经有序了
	 * @param arr
	 */
	public static void bestBubbleSort(int[] arr) {
		boolean flag=false;
		if(null!=arr&&arr.length>0) {
			for(int i=1;i<arr.length;i++) {//外层循环控制循环次数
				System.out.println("第"+i+"次循环");
				flag=true;
				for(int j=0;j<arr.length-i;j++) {//内存循环控制数据交换
					if(arr[j]>arr[j+1]) {//交换位置
						int tmp=arr[j];//需要借助的空间
						arr[j]=arr[j+1];
						arr[j+1]=tmp;
						flag=false;//只要发生了交换操作就设置flag
					}
				}
				if(flag) {//如果没有发生过交换，则证明已经有序了
					break;
				}
			}
		}
	}
	
}
