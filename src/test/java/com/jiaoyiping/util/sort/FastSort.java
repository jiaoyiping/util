package com.jiaoyiping.util.sort;

public class FastSort {
	public static void main(String[] args){
		
	}
	
	public void sort(int[] item){
		if(item.length == 0 || item.length == 1){
			return;
		}
		if(item.length == 2){
			int item0 = item[0];
 			int item1 = item[1];
 			//交换位置
 			if(item0<item1){
 				int tmp = item[0];
 				item[0] = item[1];
 				item[1] = tmp;
 			}
 			return;
		}
//		int flag = item.length/2+1;//中间位置
		for(int i = 0;i< item.length; i++){
//			for()
		}
		
		
		
		
	}
	
	public int[] getSubArray(int[] source,int flag){
		
		return null;
	}
}
