/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contaktbook.entitypackage;

import java.util.ArrayList;
import java.util.Collection;
import utillitepackage.KeyGenerator;

/**
 *
 * @author Roma
 */
public class Contact{
    
    private long id;
    private Person person;
    private Address adress;
    private String email;
    private Phone homePhone;
    private Phone workPhone;        
    private Collection<MobilePhone> mobilePhones;
    //{id=KeyGenerator.getRandomLong();}
    
    
    public Contact(Person person, Address adress, String email, Phone homePhone, Phone workPhone,Collection<MobilePhone> mobilePhones) {
        this.person = person;
        this.adress = adress;
        this.email = email;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.mobilePhones= mobilePhones;
    }

    public Contact() {
        this.person = new Person();
        this.adress = new Address();
        this.email = "";
        this.homePhone = new Phone();
        this.workPhone = new Phone();
        this.mobilePhones= new ArrayList<MobilePhone>();
    }

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Phone getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(Phone homePhone) {
        this.homePhone = homePhone;
    }

    public Phone getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(Phone workPhone) {
        this.workPhone = workPhone;
    }

    public Collection<MobilePhone> getMobilePhones() {
        return mobilePhones;
    }

    public void setMobilePhones(Collection<MobilePhone> mobilePhones) {
        this.mobilePhones = mobilePhones;
    }


    
    public String toString(){
    String s="" +
            this.person.toString()+
            this.adress.toString()+
            this.email.toString()+
            this.homePhone.toString()+
            this.workPhone.toString()+
            this.mobilePhones.toString()+   
            "\n";
    return s;
    }
    
     
}
