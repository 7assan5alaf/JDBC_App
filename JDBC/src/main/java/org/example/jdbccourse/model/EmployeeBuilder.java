package org.example.jdbccourse.model;
import java.util.Date;
public class EmployeeBuilder {
    private int id;
    private String name;
    private Boolean gender;
    private Date birth_date;
    private double salary;
    public EmployeeBuilder id(int id){
        this.id=id;
        return this;
    }public EmployeeBuilder name(String name){
        this.name=name;
        return this;
    }public EmployeeBuilder birth_date(Date birth_date){
        this.birth_date=birth_date;
        return this;
    }public EmployeeBuilder gender(Boolean gender){
        this.gender=gender;
        return this;
    }public EmployeeBuilder salary(Double salary){
        this.salary=salary;
        return this;
    }
public Employee build(){
        return new Employee(id,name,gender,birth_date,salary);
}


}
