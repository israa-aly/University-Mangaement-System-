/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class NotificationsDTO {
    String message;
    
    
    public NotificationsDTO(){};
    
    public NotificationsDTO(String message){
        this.message=message;
    };
    
    public String getMessage(){
    return message;
    }
     public void setMessage(String message){
    this.message=message;
    }
     

    
}




