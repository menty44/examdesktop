/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exambr;

import java.util.Scanner;

/**
 *
 * @author MENTY
 */


public class studentgrades {
    
    public static int marks;
    
    public static void main (String args[]){
        
        Scanner myScanner = new Scanner(System.in);
        double subj1 = 0 ,subj2  = 0,subj3  = 0,subj4  = 0,subj5  = 0,subj6  = 0,subj7 = 0,subj8 = 0,subj9 = 10,subj10 = 0,subj11 = 0,subj12 = 0,subj13 = 0,average;
        char grade;
        
        System.out.println("ENTER THE MARKS OF THE 7 SUBJECTS");
        
        System.out.println("MATHS\n");
        subj1=myScanner.nextDouble();
        System.out.println("ENGLISH\n");
        subj2=myScanner.nextDouble();
        System.out.println("BIOLOGY\n");
        subj3=myScanner.nextDouble();
        System.out.println("PHYSICS\n");
        subj4=myScanner.nextDouble();
        System.out.println("CHEMISTRY\n");
        subj5=myScanner.nextDouble();
        System.out.println("HISTORY\n");
        subj6=myScanner.nextDouble();
        System.out.println("GEOGRAPHY\n");
        subj7=myScanner.nextDouble();
        
        average = (subj1 +subj2 + subj3 +subj4 +subj5 +subj6 +subj7  )/7.0;
        int marks = 0;
        
        if (marks>=0 && marks <= 100){
            
            if (average>=70)
            grade = 'A';
        else if (average>=60)
            grade = 'B';
        else if (average>=50)
            grade = 'C';
        else if (average>=40)
            grade = 'D';
        else  
            grade = 'E';
        
            
       System.out.println("maths :"+subj1);
       System.out.println("english :"+subj2);
       System.out.println("biology :"+subj3);
       System.out.println("physics :"+subj4);
       System.out.println("chem :"+subj5);
       System.out.println("history :"+subj6);
       System.out.println("geograpghy :"+subj7);
//       System.out.println("maths :"+subj8);
//       System.out.println("maths :"+subj9);
//       System.out.println("maths :"+subj10);
//       System.out.println("maths :"+subj12);
//       System.out.println("maths :"+subj12);
//       System.out.println("maths :"+subj13);
       System.out.println("\n the average mark is:  " +average);
       System.out.println("\n the grade obtained is:  " +grade);
            
            
        }
              else
            System.out.println(marks +"is an invalid mark\n");
            System.out.println(marks+ "is a valid mark\n");
   }
    
    
    
}
