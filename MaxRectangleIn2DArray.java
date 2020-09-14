 /*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class Reader1 {
    BufferedReader br;
    StringTokenizer st;
 
    public Reader1() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
 
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
 
    public int nextInt() {
        return Integer.parseInt(next());
    }
 
    long nextLong() {
        return Long.parseLong(next());
    }
 
    double nextDouble() {
        return Double.parseDouble(next());
    }
 
    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
 
}

class MaxRectangleIn2DArray {
    
    public static int kadanes(int arr[]){
        int max = arr[0];
        int curr_max = arr[0];
        
        for(int i =1;i<arr.length;i++){
            curr_max = Math.max(curr_max+arr[i],arr[i]);
            max = Math.max(curr_max , max);
        }
        return max;
    }
    
    
    public static void main (String[] args) {
	    Reader1 sc = new Reader1();
	    
	    int t = sc.nextInt();
	    while(t-->0){
	        //taking row and col inp
	        int rows = sc.nextInt();
	        int cols= sc.nextInt();
	        
	        //ini the mat
	        int mat[][] = new int[rows][cols];
	        
	        //inputting the mat
	        for(int i = 0;i<rows;i++){
	            for(int j = 0;j<cols;j++){
	                mat[i][j]=sc.nextInt();
	            }
	        }
	        
	        int ans = 0;
	        //c1 and c2 
	        for(int c1 = 0;c1<cols;c1++){
	            
	            int rowSum [] = new int[rows];
	            for(int c2 = c1;c2<cols;c2++){
	                //filling row sum
	                for(int i = 0;i<rows;i++){
	                    rowSum[i]+= mat[i][c2];
	                }
	                ans = Math.max(ans , kadanes(rowSum));
	            }
	        }
	        
	        System.out.println(ans);
	        
	    }
	    
	}
}



