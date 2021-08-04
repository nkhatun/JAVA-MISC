package com.nkhatun;

import com.nkhatun.array.MedianOfTwoArray;

public class MiscMain {

	public static void main(String[] args) {
		System.out.println("Hello to misc..");
		MedianOfTwoArray median = new MedianOfTwoArray();
		/*
		 * int A[] = {1, 3, 5}; int B[] = {2,4,6};
		 */
		int A[] = {1,2,3};
		int B[] = {4,5,6,7,8};
		System.out.println("Median is:: "+median.findMedianSortedArrays(A, B));
	}

}
