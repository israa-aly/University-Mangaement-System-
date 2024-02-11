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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class DeptDTO {
    int id;
    String name;
    int head;
    String name2;
    public DeptDTO (int id,String name,int head,String name2){
        this.id=id;
    this.name=name;
    this.head=head;
    this.name2=name2;
    }
    public String  getName(){
        return name;
    }
    public String  getName2(){
        return name2;
    }
    public int getHead(){
    return head;}
    public int getId(){
    return id;}
    
}
