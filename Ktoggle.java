import java.util.*;

//SIQ = Samsung interview question
public class SIQKToggle {   
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        //inputting variables
        int n = sc.nextInt();
        int m = sc.nextInt();
        int mat[][] = new int [n][m];
        int k = sc.nextInt();

        //inputting the matrix
        for(int i =0;i<n;i++){
            for(int j = 0;j<m;j++){
                mat[i][j] = sc.nextInt();
            }
        }
        sc.close();

        HashMap<String , Integer> map = new HashMap<>();

        //Storing frequency in map
        for(int i = 0;i<n;i++){
            String temp = "";
            for(int j = 0;j<m;j++){
                temp+=mat[i][j];
            }
            
            //if map already contains that string increase the freq
            if(map.containsKey(temp)){
                int oldFreq = map.get(temp);
                map.put(temp, oldFreq+1);
            }
            //if not insert that string and set freq to 1
            else{
                map.put(temp , 1);
            }
        }

        int ans = Integer.MIN_VALUE;

        for(Map.Entry<String,Integer>entry:map.entrySet()){
            //counting number of zeros in each row
            int num_zeros = 0;
            for(int i = 0 ;i<entry.getKey().length();i++){
                if(entry.getKey().charAt(i)=='0')num_zeros++;
            }
            if( num_zeros <=k && (k-num_zeros)%2 ==0 ){
                ans = Math.max(entry.getValue(), ans);
            }
        }

        System.out.println("Maximum Rows that can be 1 are :"+ ans);
    }

}
