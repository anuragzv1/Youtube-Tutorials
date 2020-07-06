
import java.util.*;
import java.lang.*;
import java.io.*;

class MissingNumber {
     public static int abs(int n ){
        return Math.abs(n);
    }
       public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // inputting the test cases
        int t = sc.nextInt();
        while (t-- > 0) {
            // input no. of elements in array
            int n = sc.nextInt();
            boolean allNeg = true;
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                // filling the array
                arr[i] = sc.nextInt();
                if (arr[i] > 0)
                    allNeg = false;
            }

            if (allNeg) {
                System.out.println("1");
                break;
            }

            int i = 0;
            int j = n - 1;
            // separating negatives and positives
            while (i < j) {
                if (arr[i] < 0)
                    i++;
                else if (arr[j] >= 0)
                    j--;
                else if (arr[i] >= 0 && arr[j] < 0) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }
            
            //get index of first +ve value
            for (int i1 = 0; i1 < n; i1++) {
                if (arr[i1] >= 0) {
                    i = i1;
                    break;
                }
            }
            
            // here variable i will now denote index of first positive
            //max_positive contains the max value of element that the
            //array can contain
            int max_positive = n - i;

            // setting the index negative if the element is found!
            for (int x = i; x < n; x++) {
                if (abs(arr[x]) > max_positive)continue;
                
                arr[i + abs(arr[x]) - 1] = -1 * abs(arr[i + abs(arr[x]) - 1]);
                
                if (arr[i + abs(arr[x]) - 1] == 0) arr[i + abs(arr[x]) - 1] = -10000001;
            }

            //if missing element if found in array then ansFound=true
            boolean ansFound = false;

            for (int z = i; z < n; z++) {
                if (arr[z] >= 0) {
                    System.out.println(z - i + 1);
                    ansFound = true;
                    break;
                }
            }
            //is ansFound == false that means all numbers are present and and = max+1
            if (!ansFound)
                System.out.println(max_positive + 1);

        }
    }
}
