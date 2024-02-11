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
public class CourseDTO {
    private int id;
    private String name;
    private int year;
    private int semester;
    private int mark;
    private String grade;
    private int hours;
    private int max;
    private int num;
    private int gpa;
    private String deptname;
   public CourseDTO( String name) {
        
        this.name = name;
        
    }
    public CourseDTO(int id, String name, int year, int semester, int mark, String grade,int max) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.mark = mark;
        this.grade = grade;
        this.max=max;
    }
     public CourseDTO(int id, String name, int year, int semester, String grade) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.semester = semester;
        
        this.grade = grade;
    }
      public CourseDTO(int id, String name, String deptname, int mark, String grade,int max) {
        this.id = id;
        this.name = name;
       this.mark=mark;
        this.deptname=deptname;
        this.max=max;
        
        this.grade = grade;
    }
     public CourseDTO(int id, String name, String grade) {
        this.id = id;
        this.name = name;
       
        this.grade = grade;
    }
     public CourseDTO(int id, String name, int hours, int max) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.max = max;
       
    }
       public CourseDTO(int id, String name, int hours, int max,int num,int gpa,String deptname) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.max = max;
        this.num=num;
        this.gpa=gpa;
        this.deptname=deptname;
       
    }
         public CourseDTO(int id, String name, int hours, int max,int num,int gpa) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.max = max;
        this.num=num;
        this.gpa=gpa;
       // this.deptname=deptname;
       
    }
         @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }
    public int getHours() {
        return hours;
    }
    public int getMax() {
        return max;
    }
    public int getNum() {
        return num;
    }
    public int getGpa() {
        return gpa;
    }
    public String getDeptname() {
        return deptname;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public int getMark() {
        return mark;
    }

    public String getGrade() {
        return grade;
    }
}
