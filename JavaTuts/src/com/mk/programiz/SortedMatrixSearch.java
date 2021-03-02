package com.mk.programiz;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SortedMatrixSearch {

	
	static int binarySearch(int[] data, int key ){
		
		int low = 0;
		int high = data.length - 1;
		
		while(low <= high){
			
			int mid = (low + high) / 2;
			
			if(data[mid] == key){
				return mid; 
			}
			else if(key < data[mid]){
				high = mid - 1;
			}
			else if(key > data[mid]){
				low = mid + 1;
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		int[][] data = { 
				{ 2, 4, 6, 8,10},
				{13,15,17,19,21},
				{24,26,27,28,29},
				{31,33,34,35,39},
				{41,43,44,45,48}
		};
	
		int key = 50;
		
		int[] array1d = Stream.of(data).flatMapToInt(IntStream::of).toArray();
		
		//Assuming all 2 d arrays are of equal length
		System.out.println(Arrays.toString(array1d));
		
		int found = binarySearch(array1d , key);
		if (found != -1)
		{
			System.out.println(found + " - " + found / data[0].length + "," + ((found % data[0].length)));
		}else{
			System.out.println("Not found");
		}
		
	}

}
