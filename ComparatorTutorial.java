import java.util.*;

// //this is our custom_comparator;
class custom_comparator implements Comparator<Student>{
    public int compare(Student s1, Student s2){
        //to solve in  ascending order
        return s1.rollno - s2.rollno;
    }
}


//this is our custom class 
class Student{
    int rollno;
    String name;
    //const
    Student(int roll , String name){
        rollno = roll;
        this.name = name;
    }
}

public class ComparatorTutorial{
    public static void main(String[] args) {
        Student arr [] = new Student[5];

        arr[0] = new Student(7, "Anurag");
        arr[1] = new Student(2, "ALok ");
        arr[2] = new Student(17, "Aditi");
        arr[3] = new Student(4, "Seema");
        arr[4] = new Student(9, "jack");

        //arrays.sort --> array Collections.sort();

        Arrays.sort(arr , new custom_comparator());


        System.out.println();
        for(int i = 0;i<5;i++){
            System.out.println(arr[i].rollno+" "+arr[i].name);
        }
    }
}
