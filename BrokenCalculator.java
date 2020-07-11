/*
[Assuming each digit can be used only once]

You are given old touch smartphone numbers having dial pad and calculator app.
Aim: The goal is to type a number on the dial pad.
But as the phone is old, some of the numbers and some operations can’t be touched.
For eg. 2,3,5,9 keys are not responding, i.e you cannot use them
But you can always make a number using other numbers and operations in Calculator. There could be multiple ways of making a number
Calculator have 1-9 and +,-,\*,/,= as operations. Once you have made the number in Calculator you can copy the number and use it.
You have to find the minimum number of touches required to obtain a number.
Input:
There will be multiple Test cases.Each test case will consist of 4 lines
1) The first line will consist of N, M, O
N: no of keys working in Dialpad (out of 0,1,2,3,4,5,6,7,8,9)
M:types of operations supported (+,-,*,/)
O: Max no of touches allowed
2) second line of input contains the digits that are working e.g 0,2,3,4,6.
3) Third line contains the valued describing operations, 1(+),2(-),3(*),4(/)
4) fourth line contains the number that we want to make .
Output:
Output contains 1 line printing the number of touches required to make the number
Sample Test Case:
1                   // No of test cases
5 3 5            // N ,M, O
1 2 4 6 0     // digits that are working (total number of digits = N),
1 2 3         // describing operations allowed (1–> ‘+’, 2–> ‘-‘, 3–> ‘*’ , 4–> ‘/’ )(total number is equals to M)
5                 // number we want to make
Answer 3
How 4? 1+4=, “=” is also counted as a touch
2nd Sample Case
3                   // No of Test cases
6 4 5            // N ,M, O
1 2 4 6 9 8  // digits that are working (total number of digits = N),
1 2 3 4        // describing operations allowed (1–> +, 2–> -, 3–> , 4–>/)
91                // number we want to make
6 3 5 // 2nd test case
0 1 3 5 7 9
1 2 4 // +, -, / allowed here
28
5 2 10
1 2 6 7 8
2 3               // -, allowed
981
Output:
2        // 91 can be made by directly entering 91 as 1,9 digits are working, so only 2 operations
5            // 35-7=, other ways are 1+3*7=
INTEGER_MAX_VALUE          //No ways
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BrokenCalculator {

    public static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt(), o = sc.nextInt();
            ArrayList<Integer> workingNumber = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                int num = sc.nextInt();
                workingNumber.add(num);
            }

            int signs[] = new int[4];
            for (int i = 0; i < m; i++) {
                int num = sc.nextInt();
                signs[num - 1] = 1;
            }

            int target = sc.nextInt();
            getMinSteps(target, new HashMap<Integer, Integer>(), 1, workingNumber, o, signs);
            System.out.println(ans);
        }
        sc.close();
    }

    public static void getMinSteps(int target, HashMap<Integer, Integer> output, int cur_level,
            ArrayList<Integer> workingNumber, int o, int[] signs) {

        if (workingNumber.size() == 0) {
            // System.out.println(output);
            return;
        }


        // 2 3
        // [1] 
        for (int i = 0; i < workingNumber.size(); i++) {
            int cur_ele = workingNumber.get(i);
            // System.out.println("Cuurent Number :" + cur_ele);
            // System.out.println("Output :" + output);
            workingNumber.remove(i);
            HashMap<Integer, Integer> new_output = new HashMap<>();
            new_output.put(cur_ele, 1);
            for (Map.Entry<Integer, Integer> entry : output.entrySet()) {
                int val = entry.getKey();
                int level = entry.getValue(); //touches -> lvel

                // add operation
                if (signs[0] == 1) {
                    int newAdd = cur_ele + val;
                    int newFreq = level + 3;
                    // System.out.println("Adding" + cur_ele + " + " + val + " = " + newAdd + " freq :" + newFreq);
                    if (!new_output.containsKey(newAdd)) {
                        new_output.put(newAdd, newFreq);
                    } else if (new_output.get(newAdd) > newFreq) {
                        new_output.put(newAdd, newFreq);
                    }
                    if (newAdd == target && newFreq <= o) {
                        ans = Math.min(ans, newFreq);
                    }
                }
                // minus
                if (signs[1] == 1) {
                    int newAdd = Math.abs(cur_ele - val);
                    int newFreq = level + 3;
                    // System.out.println("Substracting" + cur_ele + " - " + val + " = " + newAdd + " freq :" + newFreq);
                    if (!new_output.containsKey(newAdd)) {
                        new_output.put(newAdd, newFreq);
                    } else if (new_output.get(newAdd) > newFreq) {
                        new_output.put(newAdd, newFreq);
                    }
                    if (newAdd == target && newFreq <= o) {
                        ans = Math.min(ans, newFreq);
                    }
                }
                // multiply
                if (signs[2] == 1) {
                    int newAdd = cur_ele * val;
                    int newFreq = level + 3;
                    // System.out.println("Multiplying" + cur_ele + " * " + val + " = " + newAdd + " freq :" + newFreq);
                    if (!new_output.containsKey(newAdd)) {
                        new_output.put(newAdd, newFreq);
                    } else if (new_output.get(newAdd) > newFreq) {
                        new_output.put(newAdd, newFreq);
                    }
                    if (newAdd == target && newFreq <= o) {
                        ans = Math.min(ans, newFreq);
                    }
                }
                // divide
                if (signs[3] == 1) {
                    // System.out.println("Dividing" + cur_ele + " + " + val);
                    if (val != 0 && cur_ele >= val) {
                        if (cur_ele % val == 0) {
                            int newAdd = cur_ele / val;
                            int newFreq = level + 3;
                            if (!new_output.containsKey(newAdd)) {
                                new_output.put(newAdd, newFreq);
                            } else if (new_output.get(newAdd) > newFreq) {
                                new_output.put(newAdd, newFreq);
                            }
                            if (newAdd == target && newFreq <= o) {
                                ans = Math.min(ans, newFreq);
                            }
                        }
                    } else {
                        if (cur_ele != 0 && val % cur_ele == 0) {
                            int newAdd = val / cur_ele;
                            int newFreq = level + 3;
                            if (!new_output.containsKey(newAdd)) {
                                new_output.put(newAdd, newFreq);
                            } else if (new_output.get(newAdd) > newFreq) {
                                new_output.put(newAdd, newFreq);
                            }
                            if (newAdd == target && newFreq <= o) {
                                ans = Math.min(ans, newFreq);
                            }
                        }
                    }
                }
                // adding the original string
                String one = Integer.toString(cur_ele);
                String two = Integer.toString(val);
                int newAdd = Integer.parseInt(one.concat(two));
                int newFreq = level + 1;
                // System.out.println("Concat" + cur_ele + " + " + val + " freq :" + newFreq);
                if (!new_output.containsKey(newAdd)) {
                    new_output.put(newAdd, newFreq);
                } else if (new_output.get(newAdd) > newFreq) {
                    new_output.put(newAdd, newFreq);
                }
                if (newAdd == target && newFreq <= o) {
                    ans = Math.min(ans, newFreq);
                }
                // adding the reverse string
                newAdd = Integer.parseInt(two.concat(one));
                if (!new_output.containsKey(newAdd)) {
                    new_output.put(newAdd, newFreq);
                } else if (new_output.get(newAdd) > newFreq) {
                    new_output.put(newAdd, newFreq);
                }
                if (newAdd == target && newFreq <= o) {
                    ans = Math.min(ans, newFreq);
                }
            }
            for (Map.Entry<Integer, Integer> entry : output.entrySet()) {
                int val = entry.getKey();
                int level = entry.getValue();
                if (!new_output.containsKey(val)) {
                    new_output.put(val, level);
                } else {
                    if (new_output.get(val) > level) {
                        new_output.put(val, level);
                    }
                }
            }
            getMinSteps(target, new_output, cur_level + 1, workingNumber, o, signs);
            workingNumber.add(i, cur_ele);
        }

    }

}


