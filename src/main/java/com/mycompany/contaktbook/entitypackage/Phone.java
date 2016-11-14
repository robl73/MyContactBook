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
public class Phone{
    private long id;
    private String countryCode;
    private String cityCode;
    private String number;
    private PhoneTypeEnum phoneType;
    //{id=KeyGenerator.getRandomLong();}

    public Phone(String countryCode, String cityCode, String number, PhoneTypeEnum phoneType) {
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.number = number;
        this.phoneType = phoneType;
    }

    public Phone(String cityCode, String number) {
        this("380",cityCode,number,PhoneTypeEnum.UNKNOWN);
    }

    public Phone(String number) {
        this("380","372",number,PhoneTypeEnum.UNKNOWN);
    }
    public Phone(String number, PhoneTypeEnum phoneType) {
        this("380","372",number,phoneType);
    }

    public Phone() {
        this.countryCode = "";
        this.cityCode = "";
        this.number = "";
        this.phoneType = PhoneTypeEnum.UNKNOWN;
    }

    public long getId() {
        return id;
    }
    

    public String getCountryCode() {
        return countryCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getNumber() {
        return number;
    }

    public PhoneTypeEnum getPhoneType() {
        return phoneType;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPhoneType(PhoneTypeEnum phoneType) {
        this.phoneType = phoneType;
    }
    

    public String toString(){
        return new Formatter().format(" %s-%s-%s-%s ",
                this.countryCode,
                this.cityCode,
                this.number,
                this.phoneType.toString())
                              .toString();
        
    }
    
     
    }

    
    

