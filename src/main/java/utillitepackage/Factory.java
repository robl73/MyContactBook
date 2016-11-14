/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;
import com.mycompany.contaktbook.entitypackage.*;
import java.util.ArrayList;
import java.util.Collection;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
/**
 *
 * @author Roma
 */
public class Factory {

//---------------------------------------------------------------------------

public static ContactBook initContactBook(){
    
ArrayList<MobilePhone> almph1,almph2,almph3,almph4; 
almph1=new ArrayList<MobilePhone>();
almph2=new ArrayList<MobilePhone>();
almph3=new ArrayList<MobilePhone>();
almph4=new ArrayList<MobilePhone>();

almph1.add(new MobilePhone("50","1234567"));
almph1.add(new MobilePhone("50","1234568"));

almph2.add(new MobilePhone("50","2234567"));
almph2.add(new MobilePhone("50","2234568"));

almph3.add(new MobilePhone("50","5345678"));
almph3.add(new MobilePhone("50","5345679"));

almph4.add(new MobilePhone("50","7345678"));
almph4.add(new MobilePhone("50","7345679"));

        Contact c1,c2,c3,c4;
        
       // Phone hph1= new Phone("123123",PhoneTypeEnum.HOMEPHONE);
        c1 = new Contact(
        new Person("Ivanenko_1","Ivan_1",1981,11,11,GroupEnum.FAMILY ),
        new Address("530001","Golovna","151","21"),       
        new String("ivan_1@meta.ua"),
        new Phone("123123",PhoneTypeEnum.HOMEPHONE),
        //hph1,        
        new Phone("123124",PhoneTypeEnum.WORKPHONE),
        almph1
              
);
        //Phone hph2=new Phone("123125",PhoneTypeEnum.HOMEPHONE);
        c2 = new Contact(
        new Person("Ivanenko_2","Ivan_2",1982,12,12,GroupEnum.FAMILY ),
        new Address("530001","Golovna","152","22"),
        new String("ivan_2@meta.ua"),
        new Phone("123125",PhoneTypeEnum.HOMEPHONE),
        //hph2,        
        new Phone("123126",PhoneTypeEnum.WORKPHONE),
        almph2
);
        //Phone hph3=new Phone("534567",PhoneTypeEnum.HOMEPHONE);
        c3 = new Contact(
        new Person("Petrenko","Petro",1985,5,15,GroupEnum.FRIENDS),
        new Address("530002","Gertcen","15","5"),
        new String("petro@meta.ua"),
        new Phone("534567",PhoneTypeEnum.HOMEPHONE),
        //hph3,        
        new Phone("534568",PhoneTypeEnum.WORKPHONE),
        almph3
);
        //Phone hph4=new Phone("734567",PhoneTypeEnum.HOMEPHONE); 
        c4 = new Contact(
        new Person("Sidorenko","Sidor",1987,7,7,GroupEnum.WORK),
        new Address("530003","Boyarko","17","7"),
        new String("sidor@meta.ua"),
        new Phone("734567",PhoneTypeEnum.HOMEPHONE),
        //hph4,        
        new Phone("734568",PhoneTypeEnum.WORKPHONE),
        almph4
);

ContactBook cb=Factory.createContactBook();
cb.getContacts().add(c4);
cb.getContacts().add(c3);
cb.getContacts().add(c2);
cb.getContacts().add(c1);

return cb;       
    }
    
public static Contact initContact(){
Contact c5=null;
ArrayList<MobilePhone> almph5; 
almph5=new ArrayList<MobilePhone>();
almph5.add(new MobilePhone("95","5345688"));
almph5.add(new MobilePhone("95","5345689"));

 c5 = new Contact(
        new Person("Sidorenko_5","Sidor_5",1987,7,7,GroupEnum.WORK),
        new Address("530003","Boyarko","57","5"),
        new String("sidor5@meta.ua"),
        new Phone("934567",PhoneTypeEnum.HOMEPHONE),
        //hph4,        
        new Phone("934568",PhoneTypeEnum.WORKPHONE),
        almph5);
         
return c5;
} 

//---------------------------------------------------------------------------    
public static Person createPerson(String firstname,String lastname, int year,int mounth,int day,GroupEnum group){
    Person p= new Person(firstname,lastname,year,mounth,day,group);
return p; 
}
public static Person createPerson(String firstname,String lastname,LocalDate birthday,GroupEnum group){
    Person p= new Person(firstname,lastname,birthday,group);
return p;
}
public static Address createAddress(String zipcode,String city,String street,String house,String flat){
    Address a=new Address(zipcode,city,street,house,flat);
return a;
}
public static Phone createPhone(String countryCode, String cityCode, String number,PhoneTypeEnum phoneType){
    Phone ph=new Phone(countryCode,cityCode,number,phoneType);
    return ph;
}   
public static Phone createPhone(String countryCode, String cityCode, String number){
    Phone ph=new Phone(countryCode,cityCode,number,PhoneTypeEnum.UNKNOWN);
    return ph;    
}
public static MobilePhone createMobilePhone(String countryCode, String providerCode, String number){
    MobilePhone mph=new MobilePhone(countryCode,providerCode,number);
    return mph;
}
public static Contact createContact(Person p,Address a,String e,Phone ph,Phone pw,Collection<MobilePhone> cmp){
    Contact c =new Contact(p,a,e,ph,pw,cmp);
    return c;
}
public static ContactBook createContactBook(){
    ContactBook cb =new ContactBook();
    return cb;
}
public static ContactBook createContactBook(Collection<Contact> c){
    ContactBook cb =new ContactBook(c);
    return cb;
}




}