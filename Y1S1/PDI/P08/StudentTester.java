import java.util.*;
import java.io.*;

public class StudentTester
{
    public static void main(String[]args)
    {
        Student student1 = new Student(" Justin",'e',20.0);
        Student student2= new Student("Justin",10000001 ,50.0);
        Student student3 = new Student(student2);
    
        System.out.println(student1.toString());
        System.out.println(student2.equals(student3));
        System.out.println(student3.toString());
        student3.setStudentID(15);
        System.out.println(student3.toString());
        System.out.println(student2.getGrade());
         
    }
}
        
