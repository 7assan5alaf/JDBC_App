package org.example.jdbccourse.model;

import org.example.jdbccourse.dao.EmployeeDao;
import org.example.jdbccourse.dao.EmployeeDaoImp;

import java.util.Date;
import java.util.Scanner;
public class First_App {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Chose from data:");
        System.out.println("1-Insert\n2-Update\n3-print all employee\n4-Find employee by id\n5-delete by id \n6-exit =0");
        EmployeeDao emp=new EmployeeDaoImp();
            int i=0,c=0;
            while (true){
                int n=input.nextInt();
                if(n==1){
                    i++;
                    System.out.print("Enter name employee insert "+i+": ");
                    String name=input.next();
                    System.out.print("Enter Gender employee insert "+i+": ");
                    Boolean b=input.nextBoolean();
                    System.out.print("Enter Salary employee insert "+i+": ");
                    double sal=input.nextDouble();
                    Employee  employee = new Employee(0, name, b, new Date(), sal);
                    emp.save(employee);
                    System.out.println("Done!");
                }else if(n==2){
                    c++;
                    System.out.print("Enter id employee update "+c+": ");
                    int id=input.nextInt();
                    System.out.print("Enter name employee update "+c+": ");
                    String name=input.next();
                    System.out.print("Enter Gender employee update "+c+": ");
                    Boolean b=input.nextBoolean();
                    System.out.print("Enter Salary employee update "+c+": ");
                    double sal=input.nextDouble();
                    Employee   employee =new Employee(id,name,b,new Date(),sal);
                    emp.save(employee);
                    System.out.println("Done!");
                }else if(n==3){
                    emp.findAll().forEach(System.out::println);
                }else  if(n==4){
                    System.out.print("Enter id employee  "+": ");
                    int id=input.nextInt();
                    Employee   employee=emp.findById(id);
                    System.out.println(employee);
                }else if(n==5){
                    System.out.print("Enter id employee  "+": ");
                    int id=input.nextInt();
                    emp.deleteById(id);
                }
                else if(n==0) {
                    break;
                }
            }


    }
}


