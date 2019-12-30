
package hu.kg.main;

import java.util.Collection;

public class Util {

	
	public static int randomInclusive(int n){
		return (int) (Math.random()*n+1);
	}
	
	
	public static boolean[] convertBinaryNumber (int number, int digits) {
		boolean[] result = new boolean [digits];
		for (int i = digits-1; i >= 0; i--) {
			if (number%2==1) result[i] = true;
			number = number >> 1;
		}
		
		return result;	
	}


	public static int countTrue(boolean[] arr) {
		int result = 0;
		for(boolean b:arr){
			if(b) result++;
		}
		return result;
	}

	public static int[] convertToIntArray(Collection<Integer> result) {
		int[] r=new int[result.size()];
		int k=0;
		for(int i:result){
			r[k++] =i;
		}
		return r;
	}


	public static int randomFromToInclusive(int x, int y) {
		if(y<x) throw new RuntimeException("Bad interval");
		return x+(int) (Math.random()*(y-x));
	}
	
}
