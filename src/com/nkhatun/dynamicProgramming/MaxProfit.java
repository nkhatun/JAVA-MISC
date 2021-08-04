package com.nkhatun.dynamicProgramming;

public class MaxProfit {
	//7 1 2 5
	//Brute force : O(n^2)
	public static int calculateMaxProfit1(int stocksArr[]) {
		int max_profit = -1;int len = stocksArr.length;
		for(int i=len-1;i>0;i--) {
			for(int j= i - 1;j>=0;j--) {
				if(stocksArr[i] > stocksArr[j]) { 
					max_profit = Math.max(max_profit, stocksArr[i] - stocksArr[j]);
				}
			}
		}		
		return max_profit;
	}
	//Recursive non-dp
	public static int calculateMaxProfit2(int stocksArr[]) {
		int max_profit = -1; int len = stocksArr.length;
		max_profit = Math.max(max_profit, maxProfitRecur(stocksArr,len-1,max_profit))	;
		return max_profit;
	}	
	public static int maxProfitRecur(int[] stocksArr, int end,int maxProfit) {
		if(end == 0) {
			return maxProfit;
		}
		int start = end -1;
		while(start >= 0) {
			if(stocksArr[end] > stocksArr[start]) {
				maxProfit = Math.max(maxProfit,stocksArr[end] - stocksArr[start]);
			}
			start--;
		}
		return maxProfitRecur(stocksArr,end-1,maxProfit);
	}
	
	public static void main(String args[]) {
		int arr[] = {7, 1, 2, 5};
		System.out.println("Out: "+calculateMaxProfit2(arr));
	}
}
