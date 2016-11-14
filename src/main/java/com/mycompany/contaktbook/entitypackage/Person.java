/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.contaktbook.entitypackage;

import java.util.Formatter;
import org.joda.time.*;
import utillitepackage.KeyGenerator;

/**
 *
 * @author Roma
 */
public class Person{
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private GroupEnum group;
   // {id=KeyGenerator.getRandomLong();}


    public Person(String firstName, String lastName, int year,int mounth,int day,GroupEnum group) {
        this.firstName = firstName;
        this.lastName = lastName;
        LocalDate gc =new LocalDate(year,mounth,day);
        this.birthday = gc;
        this.group=group;
    }
    public Person(String firstName, String lastName, LocalDate birthday,GroupEnum group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.group  =   group;
    }

    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.birthday = new LocalDate(0,1,1);
        this.group  =   GroupEnum.OTHER;
    }

    public long getId() {
        return id;
    }
    
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public GroupEnum getGroup() {
        return group;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }
    
   // %tF*
    public String toString(){
        return new Formatter().format("%s ,%s, %tF ,%s",
                                      this.firstName,
                                      this.lastName,
                                      this.birthday.toDate(),
                                      this.group
                                    )
                              .toString();
    } 
     
     
     
     
}

