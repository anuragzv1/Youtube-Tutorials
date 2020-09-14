package factset;

import java.util.Scanner;

public class MinOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //inputting array size
        int n = sc.nextInt();
        int arr[] = new int[n];

        //array input
        for(int i = 0;i<n;i++)arr[i] = sc.nextInt();

        //smallest ele

        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i : arr){
            min = Math.min(min , i);
            sum+=i;
        }

        System.out.println(sum - n*min);
    }
}
