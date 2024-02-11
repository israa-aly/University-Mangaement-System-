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
    int id;
    int id2;
    String name;
    int hours;
    int max;
    int mark;
      public CourseDTO (int id,int id2,int mark){
    this.id=id;
    this.id2=id2;
    this.mark=mark;}
    public CourseDTO (String name,int hours,int max){
    this.name=name;
    this.hours=hours;
    this.max=max;}
     public CourseDTO (String name){
    this.name=name;
    }
    
    public CourseDTO (int id,String name,int hours,int max){
        this.id=id;
    this.name=name;
     this.hours=hours;
    this.max=max;}
    public String  getName(){
        return name;
    }
    public int  getHours(){
        return hours;
    }
    public int getMax(){
    return max;}
    public int getId(){
    return id;}
    public int getId2(){
    return id2;}
    public int getMark(){
    return mark;}
    
}

