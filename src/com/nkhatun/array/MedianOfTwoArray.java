package com.nkhatun.array;

import java.math.BigDecimal;
import java.util.Arrays;

public class MedianOfTwoArray {

	public double getMedian(int A[], int B[]) {
		int ALength = A.length;
		int BLength = B.length;
		if (ALength == 0 ) {
			return getMedian(B);
		} 
		else if (BLength == 0 ) {
			return getMedian(A);
		} 
		else if (ALength == 1 || BLength == 1 ) {
			int mergeArr[] = Arrays.copyOf(A, ALength + BLength);
			System.arraycopy(B, 0, mergeArr, ALength, BLength);
			Arrays.sort(mergeArr);
			return getMedian(mergeArr);
		} 
		else {
			if(ALength == 2 || BLength == 2) {
				// no more partition. merge both array
				int mergeArr[] = Arrays.copyOf(A, ALength + BLength);
				System.arraycopy(B, 0, mergeArr, ALength, BLength);
				Arrays.sort(mergeArr);
				return getMedian(mergeArr);
			}
			// calculate median
			double firstMedian = getMedian(A);
			double secondMedian = getMedian(B);
			if (firstMedian < secondMedian) { // check size
				// consider second part of first array and first part of second array
				int startIndex = getHiherPartitionStartIndex(ALength);
				int endIndex = getLowerPartitionEndIndex(BLength);			
				return getMedian(getPartitionArray(A,startIndex,ALength),getPartitionArray(B,0,endIndex+1));
			}
			else {
				// consider first part of first array and second part of second array
				int endIndex = getLowerPartitionEndIndex(ALength);
				int startIndex = getHiherPartitionStartIndex(BLength);
				return getMedian(getPartitionArray(B,startIndex,BLength),getPartitionArray(A,0,endIndex+1));
			}
		}
	}

	private int getHiherPartitionStartIndex(int len) {
		if (len%2 == 0) {
			return (len / 2) - 1;
		}
		else {
			return (len -1 )/ 2;
		}
	}

	private int getLowerPartitionEndIndex(int len) {
		if (len%2 == 0) {
			return len / 2;
		}
		else {
			return (len -1 )/ 2;
		}
	}

	private double getMedian(int arr[]) {
		int len = arr.length;
		int midIndex;
		if (len%2 == 0) {
			midIndex = len / 2;
			return ((BigDecimal.valueOf(arr[midIndex])
					.add(BigDecimal.valueOf(arr[midIndex - 1])))
							.divide(BigDecimal.valueOf(2))).doubleValue();
		} else {
			midIndex = (len - 1) / 2;
			return arr[midIndex];
		}
	}

	private int[] getPartitionArray(int arr[], int beginIndex, int endIndex) {
		int partArr[] = new int[endIndex - beginIndex];
		System.arraycopy(arr, beginIndex, partArr, 0, partArr.length);
		return partArr;
	}
	
	public double findMedianSortedArrays(int nums1[], int nums2[]) {
		int ALength = nums1.length;
		int BLength = nums2.length;
		int low = 0; int high = ALength + BLength;int mid;
		int resultArr[] = new int[ALength + BLength];
		int len = ALength + BLength;

		if (ALength == 0 ) {
			return getMedian(nums2);
		} 
		else if (BLength == 0 ) {
			return getMedian(nums1);
		}
		else {
			/*
			 * while(len <= resultArr.length) { if(nums1[low] < nums2[low] ) {
			 * resultArr[low] = nums1[low]; } else { resultArr[low] =
			 * nums2[low]; } low++; len --; }
			 */
			
		}
	
		
		
		
		return 0;
	}

}
