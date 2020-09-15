
//hacker earth solution for document chunking!
//Youtube explanation : https://www.youtube.com/channel/UC2jUUYFTA2byJXqmONRv8aw
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DocumentChunking {

    //calculating num of chunks required
    static int pow2(long N) {
        int ans = 0;
        for (int i = 0; i < 64; i++) {
            long x = 1;
            if ((N & (x << i)) > 0) ans++;
        }
        return ans;
    }

   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        //taking uploaded chunk input
        ArrayList<ArrayList<Integer>> list= new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            ArrayList<Integer>small = new ArrayList<>();
            small.add(start);
            small.add(end);
            list.add(small);
        }
        sc.close();
        

        //sorting the uploaded chunks
        Collections.sort(list , new Comparator<ArrayList<Integer>>(){
            public int compare(ArrayList<Integer> l1 ,ArrayList<Integer> l2){
                return l1.get(0)-l2.get(0);
            }
        });

        int lastChunkNum = 1;
        int ans = 0;
        //calculating length of un-uploaded items
        for (int i = 0; i < list.size(); i++) {
            int start = list.get(i).get(0);
            int end = list.get(i).get(m-1);
            ans+=pow2(start-lastChunkNum);
            lastChunkNum = end+1;
        }

        if(list.get(n-1).get(m-1)!=total){
            ans+=pow2(total - list.get(n-1).get(m-1));
        }
        System.out.println(ans);
    }
}
