/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contaktbook.entitypackage;

import java.util.Formatter;
import utillitepackage.KeyGenerator;

/**
 *
 * @author Roma
 */
public class MobilePhone{
      private long id;
      private String countryCode;
      private String providerCode;
      private String number;
      //{id=KeyGenerator.getRandomLong();}

    public MobilePhone(String countryCode, String providerCode, String number) {
        this.countryCode = countryCode;
        this.providerCode = providerCode;
        this.number = number;
    }

    public MobilePhone(String providerCode, String number) {
        this("380",providerCode,number);
    }

    public MobilePhone() {
        this.countryCode = "";
        this.providerCode = "";
        this.number = "";    
    }

    public long getId() {
        return id;
    }
    
    public String getCountryCode() {
        return countryCode;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public String getNumber() {
        return number;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public void setNumber(String number) {
        this.number = number;
    }
      
    public String toString(){
        return new Formatter().format(" +%s-%s-%s ",
                this.countryCode,
                this.providerCode,
                this.number)
                              .toString();
    }
  


  
}
