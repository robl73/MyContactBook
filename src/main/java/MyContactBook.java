
import utillitepackage.Factory;
import com.mycompany.contaktbook.entitypackage.*;
import java.io.File;
import javax.json.JsonArray;
import org.joda.time.LocalDate;
import servicepackage.*;
import utillitepackage.JsonUtillite;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roma
 */
public class MyContactBook {

    /**
     * @param args the command line arguments
     */
    
     private static void createDBSchema(){
        //new GroupEnumService().createTable();
        new PersonService().createTable();
        
        new AddressService().createTable();
        
        //new PhoneTypeEnumService().createTable();
        new PhoneService().createTable();
        
       // new ContactBookService().createTable();
        new ContactService().createTable();
        new MobilePhoneService().createTable();
        
        new ContactBookService().createAllContactView();
         
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
ContactBook cb=Factory.initContactBook();
System.out.println(cb.toString());

/*
ContactBook cb1=new ContactBookService().buildFromConsole();
System.out.println(cb1.toString());
*/

/*
File f=new File("myxml.xml");
File xsltfile=new File("myxslt.xsl");
new ContactBookService().toXMLFile(cb, f, null);
*/

File file=new File("myxml.xml");
File xsltfile=new File("myxslt.xsl");
new ContactBookService().toXMLFile(cb, file, xsltfile);


ContactBook cb2= new ContactBookService().buildFromXML(null);
System.out.println(cb2.toString());


new ContactBookService().toJsonFile(cb2, "myjson.json");


JsonArray ja=(JsonArray)JsonUtillite.getJsonStructureFromJsonFile("myjson.json");
ContactBook cb3=new ContactBookService().buildFromJson(ja);
System.out.println(cb3.toString()); 


// Create and filling DB

//createDBSchema();
//new ContactBookService().outputToJDBC(cb3);
//Contact c5=Factory.initContact();
//new ContactService().insert(c5);

//------------------------------------------------------------
ContactBook cb4=new ContactBookService().buildFromJDBC(0);
System.out.println(cb4.toString()); 
//-------------------------------------------------------------

//=============================================================================



//Person p=Factory.createPerson("Vasia", "Pupkin", 1980, 7, 10, GroupEnum.WORK);
//long id =new PersonService().insert(p);
//System.out.println(id);


//Address a=Factory.createAddress("234543", "Lviv", "Kvitkova", "12", "6" );
//long id2 =new AddressService().insert(a);
//System.out.println(id2);


//Phone ph=Factory.createPhone("380", "234", "545454", PhoneTypeEnum.HOMEPHONE);
//long id3= new PhoneService().insert(ph);
//System.out.println(id3);


//MobilePhone mph=Factory.createMobilePhone("380", "99", "9545454");
//long id4= new MobilePhoneService().insert(mph);
//System.out.println(id4);

//MobilePhone mph2=Factory.createMobilePhone("380", "99", "9545455");
//long id5= new MobilePhoneService().outputToJDBC(mph2);
//System.out.println(id5);

//============================================================================


long l2=new ContactService().getIdBy("petro@meta.ua");
System.out.println(l2);
Contact c2=new ContactService().getById(new ContactService().getIdBy("petro@meta.ua"));
System.out.println(c2);

MobilePhone mph1=Factory.createMobilePhone("380", "50", "5345679");
long l3=new ContactService().getIdBy(mph1);
System.out.println(l3);
Contact c3=new ContactService().getById(new ContactService().getIdBy(mph1));
System.out.println(c3);

Person p1=Factory.createPerson("Petrenko", "Petro", new LocalDate("1985-05-15"), GroupEnum.WORK);
long l4=new ContactService().getIdBy(p1);
System.out.println(l4);
Contact c4=new ContactService().getById(new ContactService().getIdBy(p1));
System.out.println(c4);

//=========================================================================


// end of main method
}
    
    
// end of class
}

