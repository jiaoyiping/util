package com.jiaoyiping.util.sort;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] score ={12,5,31,1,9,11,7,55,2,8};
		for(int i =0;i<score.length - 1; i++){//假设数组的长度为N 最多执行N-1次
			for(int j =0;j<score.length-i -1;j++){
				if(score[j]<score[j+1]){
					int tmp = score[j];
					score[j]=score[j+1];
					score[j+1]=tmp;
				}
				
			}
			System.out.println("第"+(i+1)+"次排序的结果：");
			for(int s:score){
				System.out.print(s+" ");
			}
			System.out.println();
			
		}
	}
}
