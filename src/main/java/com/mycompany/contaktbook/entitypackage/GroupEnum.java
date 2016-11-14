/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contaktbook.entitypackage;

/**
 *
 * @author roma
 */
public enum GroupEnum {
    FAMILY(1),FRIENDS(2),WORK(3),OTHER(4);
    int val;
    GroupEnum(int val){
        this.val=val;
    }
}
