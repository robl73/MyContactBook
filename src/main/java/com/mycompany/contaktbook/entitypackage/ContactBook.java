/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contaktbook.entitypackage;

import java.util.Collection;
import java.util.TreeSet;
import utillitepackage.ContactComparator;
import utillitepackage.KeyGenerator;

/**
 *
 * @author roma
 */
public class ContactBook{
private long id;
private Collection<Contact> contacts;
{id=KeyGenerator.getRandomLong();}

    public ContactBook(Collection<Contact> contacts) {
        this.contacts = contacts;       
    }

    public ContactBook() {
        this.contacts =new TreeSet<Contact>(new ContactComparator()); 
    }

    public long getId() {
        return id;
    }
    
    
    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }
    
    public String toString(){
       String s= this.getContacts().toString();
       return s;
    }
  /*  
    
    public void addContact(Contact c){
        
     //   if(!(this.contacts.contains(c))){
     //       this.contacts.add(c);
     //   }
        
        this.contacts.add(c);
    }
      
    public void delContact(Contact c){
         this.contacts.remove(c);
    }
    public void updateContact(Contact c){
        
    }
     
    
    public String toString(){
        return this.contacts.toString();
    }
            
   */
    
    
}
