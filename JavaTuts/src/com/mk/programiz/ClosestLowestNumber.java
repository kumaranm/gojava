package com.mk.programiz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClosestLowestNumber {

	public int[] closestLowestNumber(int[] arr) {

		int[] ret = new int[arr.length];
		ArrayDeque<Integer> stack = new ArrayDeque<>();

		for (int i = 0; i < arr.length; i++) {

			while (!stack.isEmpty() && stack.peek() >= arr[i]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				ret[i] = -1;
			} else {
				ret[i] = stack.peek();
			}

			stack.push(arr[i]);
		}
		return ret;

	}
	
	/**
	 * solution by interview candidate from right to left
	 * by storing the index in the stack
	 * @param arr
	 * @return
	 */
	static int[] solve(int[] arr){
	    if(arr == null) return null;
	    int n = arr.length;
	    int[] ans = new int[n];
	    if(n == 0) return ans;
	    ArrayDeque<Integer> stack = new ArrayDeque<>();
	    for(int i = n-1;i>=0;i--){
	       if(stack.isEmpty() || arr[stack.peek()]<=arr[i]){
	           stack.push(i);
	           continue;
	       }
	       while(!stack.isEmpty() && arr[stack.peek()]>arr[i]){
	           ans[stack.pop()] = arr[i];
	       }
	       stack.push(i);
	    }
	    while(!stack.isEmpty()){
	       ans[stack.pop()] = -1; 
	    }
	    return ans;
	}
	
	  /**
	 * @param listOfNumbers
	 * @return
	 */
	public List<Integer> getLowestNumberList(List<Integer> listOfNumbers){
		  
	      int min = listOfNumbers.get(0);
	      List<Integer> finalResult = new ArrayList<>();
	      finalResult.add(-1);
	      
	      for(int index=1;index<listOfNumbers.size();index++) {
	          if(listOfNumbers.get(index-1)<listOfNumbers.get(index)) {
	              min = listOfNumbers.get(index-1);
	              finalResult.add(min);
	          } else if(min < listOfNumbers.get(index)) {
	              finalResult.add(min);
	              
	          } else if(min >= listOfNumbers.get(index)) {
	              min = listOfNumbers.get(index);
	              finalResult.add(-1);
	          }
	      }
	      return finalResult;
	  }

	public static void main(String[] args) {

		ClosestLowestNumber n = new ClosestLowestNumber();
		System.out.println(Arrays.toString(n.closestLowestNumber(new int[] { 9, 22, 18, 16, 18, 7, 7, 27, 35 })));

		//[-1, 9, 9, 9, 16, -1, -1, 7, 27]
		//[-1, 8, 8, 8, 16, -1, -1, 7, 27, 7, 27, 7, -1, -1, 1, 1, 1, 1, 6, 16, 26, 26]
		
		System.out.println(Arrays.toString(n.closestLowestNumber(
				new int[] { 8, 23, 22, 16, 22, 7, 7, 27, 35, 27, 32, 20, 5, 1, 35, 28, 20, 6, 16, 26, 48, 34 })));
		
		System.out.println(Arrays.toString(solve(new int[] { 8, 23, 22, 16, 22, 7, 7, 27, 35, 27, 32, 20, 5, 1, 35, 28, 20, 6, 16, 26, 48, 34 })));
		
		System.out.println(n.getLowestNumberList(Arrays.asList( 8, 23, 22, 16, 22, 7, 7, 27, 35, 27, 32, 20, 5, 1, 35, 28, 20, 6, 16, 26, 48, 34 )));
	}

}
