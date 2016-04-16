package com;

public class Test {
	
	public static void main(String[] args) {
		int[] aa = new int[]{5,3,21,2};
		int temp;
		for(int i=0; i <aa.length; i++){
			 for(int j = i+1 ; j < aa.length ; j++){
				if(compare(aa[i],aa[j])<0){
					temp = aa[j];
				    aa[j] = aa[i];
				    aa[i] = temp;
				}
			}
		}
		String result = "";
		for(int c : aa){
			result += c+"";
		}
		System.out.println(result);
	}
	
	private static int compare(int a, int b){
		String aString = a + "";
		String bString = b + "";
		return Integer.parseInt(aString+ "" + bString) - Integer.parseInt(bString+""+aString);
	}
}
