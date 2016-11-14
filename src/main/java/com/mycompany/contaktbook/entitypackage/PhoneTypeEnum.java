/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contaktbook.entitypackage;

/**
 *
 * @author Roma
 */
public enum PhoneTypeEnum {
    UNKNOWN(1),HOMEPHONE(2),WORKPHONE(3);
    int val;
    PhoneTypeEnum(int val){
        this.val=val;
    }
    
}
