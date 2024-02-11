/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author DELL
 */
public class ProfessorDTO {
     private int id;
    private String name;
    private int age;
    private String city;
    private int salary;
    private String  phone;
    private String  degree;
    private int  dept;
     private String fname;
   private String lname;
   private String email ;
   private String pass;
    private String dob;

 private String deptname;
 private int deptid;

 public ProfessorDTO( int id,String fname, String lname,String email ,
         String phone,int deptid, String deptname,String city,String pass,String dob,int salary,String degree) {
        this.id=id;
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.deptid=deptid;
        this.deptname=deptname;
       this.dob=dob;
        this.phone = phone;
       this.city=city;
       this.salary=salary;
       this.degree=degree;
    }

    

    public ProfessorDTO(int id, String name, int age, String city, int salary, String phone,String degree,int dept) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.salary = salary;
        this.phone = phone;
        this.degree=degree;
        this.dept=dept;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public int getSalary() {
        return salary;
    }
    public String getFname() {
        return fname;
    }
      public String getLname() {
        return lname;
    }
       public String getEmail() {
        return email;
    }
        public String getPass() {
        return pass;
    }
    public String getDegree() {
        return degree;
    }
       public String getDeptname() {
        return deptname;
    }
      public int getDeptid() {
        return deptid;
    }
    
     public String getPhone() {
        return phone;
    }
        public String getDob() {
        return dob;
    }
    
      public int getDept() {
        return dept;
    }
}
