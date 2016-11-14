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
public class Address {
    private long id;
    private String zipCode;
    private String city;
    private String street;
    private String house;
    private String flat;
    //{id=KeyGenerator.getRandomLong();}

    public Address(String zipCode, String city, String street, String house, String flat) {
        this.zipCode = zipCode;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public Address(String zipCode, String street, String house, String flat) {
        this(zipCode,"Chernivci",street,house,flat);
    }

    public Address() {
        this.zipCode = "";
        this.city = "";
        this.street = "";
        this.house = "";
        this.flat = "";
    }

    public long getId() {
        return id;
    }

    
    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
    
    public String toString(){
        return new Formatter().format(" %s, %s, %s, %s, %s ",
                this.zipCode,
                this.city,
                this.street,
                this.house,
                this.flat)
                               .toString();
    } 
 
  
    
    
    
    
}
