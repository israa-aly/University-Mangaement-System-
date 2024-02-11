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
public class StudentDTO {
    private int id;
    private String name;
    private int age;
    private String city;
    private double gpa;
    private int semester;
    private String phone;
    private String fname;
   private String lname;
   private String email ;
   private String pass;
    private String dob;
 private   String dept2;
 private String deptname;
 private int deptid;
    public StudentDTO( int id,String fname, String lname,String email ,
         String phone,int deptid, String deptname,String city,String pass,String dob) {
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
    }
      public StudentDTO( int id,String fname, String lname,String email ,
         String phone,int deptid, String deptname,String city,String pass,String dob,double gpa) {
        this.id=id;
        this.fname = fname;
        this.lname=lname;
        this.email=email;
        this.pass=pass;
        this.deptid=deptid;
        this.deptname=deptname;
       this.dob=dob;
       this.gpa=gpa;
        this.phone = phone;
       this.city=city;
    }

    public StudentDTO(int id, String name, int age, String city, int gpa, int semester) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.gpa = gpa;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
     public String getPhone() {
        return phone;
    }
      public String getDeptname() {
        return deptname;
    }
      public int getDeptid() {
        return deptid;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public double getGpa() {
        return gpa;
    }

    public int getSemester() {
        return semester;
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
         public String getDept2() {
        return dept2;
    }
          public String getDob() {
        return dob;
    }
}

