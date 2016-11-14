/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import utillitepackage.Factory;
import servicepackage.interfaces.ServiceContact;
import com.mycompany.contaktbook.entitypackage.Address;
import com.mycompany.contaktbook.entitypackage.Contact;
import com.mycompany.contaktbook.entitypackage.ContactBook;
import com.mycompany.contaktbook.entitypackage.MobilePhone;
import com.mycompany.contaktbook.entitypackage.Person;
import com.mycompany.contaktbook.entitypackage.Phone;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonStructure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import servicepackage.rules.MapContactXML;
import utillitepackage.JDBCUtillite;
import utillitepackage.JsonUtillite;
import utillitepackage.XMLUtillite;
import utillitepackage.KeyGenerator;

/**
 *
 * @author Roma
 */
public class ContactService implements ServiceContact{
    
    @Override
    public  Contact buildFromConsole(){
    
   Person p;
   Address a;
   String e;
   Phone ph,pw;
   MobilePhone mph;
   ArrayList<MobilePhone> almph;
   
   System.out.println("Enter DATA for CONTACT :");
   
   p=new PersonService().buildFromConsole();
   a=new AddressService().buildFromConsole();
   e=new EmaillService().buildFromConsole();
   System.out.println("Enter DATA for HOME_PHONE :");
   ph=new PhoneService().buildFromConsole();
   System.out.println("Enter DATA for WORK_PHONE :");
   pw=new PhoneService().buildFromConsole();
   
   
   
        String s;
        almph=new ArrayList<MobilePhone>();
        
        Scanner sc=new Scanner(System.in);
        do{
        mph=new MobilePhoneService().buildFromConsole();
        almph.add(mph);
        System.out.println("Do you want enter mobilephone number more ? : Y/N" );
        s=sc.next();
        }
        while(s.equalsIgnoreCase("Y"));
        
    
 Contact c = Factory.createContact(p, a, e, ph, pw, almph);
 
 return c;  
}
   
//---------------------------------------------------------------------    

    @Override
    public void outputToConsole(Contact t) {
        new PersonService().outputToConsole(t.getPerson());
        new AddressService().outputToConsole(t.getAdress());
        new EmaillService().outputToConsole(t.getEmail());
        new PhoneService().outputToConsole(t.getHomePhone());
        new PhoneService().outputToConsole(t.getWorkPhone());
        for(MobilePhone mph:t.getMobilePhones()){
            new MobilePhoneService().outputToConsole(mph);
            }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Node outputToXML(Contact t) {
        
        Document d=null;
        d=XMLUtillite.DOC;
        
        Element ec=d.createElement("CONTACT");
            
        Person p=t.getPerson();
        Node epers=new PersonService().outputToXML(p);
        ec.appendChild(epers);
        /*
        ec.insertBefore(d.createTextNode("\n"),epers);
        */

        Address a=t.getAdress();
        Node eadr=new AddressService().outputToXML(a);
        ec.appendChild(eadr);
        
        
        String email=t.getEmail();
        Element eem=d.createElement(MapContactXML.XMLMAP.get("email"));
        Text te=d.createTextNode(t.getEmail().toString());
        eem.appendChild(te);
        ec.appendChild(eem);
        
        
        Element eehph=d.createElement(MapContactXML.XMLMAP.get("homePhone"));
        Phone hph=t.getHomePhone();
        Node ehph=new PhoneService().outputToXML(hph);
        
        eehph.appendChild(ehph);
        ec.appendChild(eehph);
        
        
        Element eewph=d.createElement(MapContactXML.XMLMAP.get("workPhone"));
        Phone wph =t.getWorkPhone();
        Node ewph=new PhoneService().outputToXML(wph);
        
        eewph.appendChild(ewph);
        ec.appendChild(eewph);
        
        Element emobphs=d.createElement(MapContactXML.XMLMAP.get("mobilePhones"));
        for(MobilePhone mobph:t.getMobilePhones()){
        Node emobph=new MobilePhoneService().outputToXML(mobph); 
        emobphs.appendChild(emobph);
        }
        ec.appendChild(emobphs);

        return ec;
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public JsonStructure outputToJSON(Contact t) {
        JsonArrayBuilder jab=JsonUtillite.JSONBF.createArrayBuilder();
        for (MobilePhone mph :t.getMobilePhones()){
            jab.add(new MobilePhoneService().outputToJSON(mph));       
        }
        JsonArray ja=jab.build();

        JsonObjectBuilder job =JsonUtillite.JSONBF.createObjectBuilder();
                 job
                .add("person", new PersonService().outputToJSON(t.getPerson()))
                .add("address", new AddressService().outputToJSON(t.getAdress()))
                .add("emaill", new EmaillService().outputToJSON(t.getEmail()))
                .add("homePhone", new PhoneService().outputToJSON(t.getHomePhone()))
                .add("workPhone", new PhoneService().outputToJSON(t.getWorkPhone()))
                .add("mobilePhones", ja);      
        JsonObject cjo=job.build();
        return cjo;
                
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact buildFromJDBC(long id) {
        Contact t = null;
        t=new ContactService().getById(id);
        return t;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact buildFromXML(Element element) {
        
        String snfp=MapContactXML.XMLMAP.get("person");        
        Element nfp= (Element)element.getElementsByTagName(snfp).item(0);
        Person p= new PersonService().buildFromXML(nfp);
        
        String snfa=MapContactXML.XMLMAP.get("address");        
        Element nfa= (Element)element.getElementsByTagName(snfa).item(0);
        Address a= new AddressService().buildFromXML(nfa);
        
        String snfe=MapContactXML.XMLMAP.get("email");        
        Element nfe= (Element)element.getElementsByTagName(snfe).item(0); 
        String e= new EmaillService().buildFromXML(nfe);
        
        String snfhph=MapContactXML.XMLMAP.get("homePhone");        
        Element nfhph= (Element)element.getElementsByTagName(snfhph).item(0);
        Phone hph= new PhoneService().buildFromXML(nfhph);
        
        String snfwph=MapContactXML.XMLMAP.get("workPhone");        
        Element nfwph= (Element)element.getElementsByTagName(snfwph).item(0);
        Phone wph= new PhoneService().buildFromXML(nfwph);
        
        Collection<MobilePhone> cmph= new ArrayList<MobilePhone>();
        
        String snfmph=MapContactXML.XMLMAP.get("mobilePhones");
        Element nfmphs= (Element)element.getElementsByTagName(snfmph).item(0);
        NodeList nlmph=nfmphs.getElementsByTagName("MOBILEPHONE");
        Element nfmph=null;
        for(int i=0;i<nlmph.getLength();i++){
            nfmph=(Element)nlmph.item(i);
            MobilePhone mph= new MobilePhoneService().buildFromXML(nfmph);
            cmph.add(mph);
        }        

     Contact c= Factory.createContact(p, a, e, hph, wph, cmph);
     return c;
     
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact buildFromJson(JsonStructure js) {
        JsonObject jscontact= (JsonObject)js;
        Contact c=null;
        Person p= null;
        Address a= null;
        String e= null;
        Phone hph= null;
        Phone wph= null;
        Collection<MobilePhone> mphc= new ArrayList<MobilePhone>();
        
        JsonObject jspers=jscontact.getJsonObject("person");
        p=new PersonService().buildFromJson(jspers);
        JsonObject jsadr=jscontact.getJsonObject("address");
        a=new AddressService().buildFromJson(jsadr);
        JsonObject jsemaill=jscontact.getJsonObject("emaill");
        e=new EmaillService().buildFromJson(jsemaill);
        JsonObject jshph=jscontact.getJsonObject("homePhone");
        hph=new PhoneService().buildFromJson(jshph);
        JsonObject jswph=jscontact.getJsonObject("workPhone");
        wph= new PhoneService().buildFromJson(jswph);
        
        JsonArray jsmobphs=jscontact.getJsonArray("mobilePhones");
        JsonObject jsmobph=null;
        MobilePhone mph=null;
        for(int i=0;i<jsmobphs.size();i++){
            jsmobph=jsmobphs.getJsonObject(i);
            mph=new MobilePhoneService().buildFromJson(jsmobph);
            mphc.add(mph);
        }
        c= Factory.createContact(p, a, e, hph, wph, mphc);
        return c;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/ContactTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(Contact t) {
        Statement stm=JDBCUtillite.getStatement();
        long id=KeyGenerator.getRandomLong();
        
        long idp=new PersonService().outputToJDBC(t.getPerson());
        long ida=new AddressService().outputToJDBC(t.getAdress());
        long idhph=new PhoneService().outputToJDBC(t.getHomePhone());
        long idwph=new PhoneService().outputToJDBC(t.getWorkPhone());
        String inscom=null;
            inscom="INSERT INTO CONTACTS(ID,PERSONID,ADDRESSID,EMAILL,HOMEPHONEID,WORKPHONEID) "
                    +"VALUES "
                    +"("
                    //+t.getId()+","
                    +id+","
                    +idp+","
                    +ida+","
                    +"'"+t.getEmail()+"'"+", "
                    +idhph+", "
                    +idwph
                    +")";
         
        try {
            stm.executeUpdate(inscom, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(MobilePhone mph : t.getMobilePhones()){
            new MobilePhoneService().outputToJDBC(mph, id);
        }
        
        return id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contact getById(long id) {
        Person p=null;
        Address a=null;
        Phone hph=null;
        Phone wph=null;
        Collection<MobilePhone> cmph=new ArrayList<>();
        ContactBook cb=null;
        Contact c=null;
        String emaill=null;
        long pid = 0,aid = 0,hphid = 0,wphid = 0,cbid;
        
 Statement stm= JDBCUtillite.getStatement();
 String selcmd="SELECT * FROM CONTACTS "
                + "WHERE CONTACTS.ID = "+id;
 ResultSet rs=null;
        try {
            rs=stm.executeQuery(selcmd);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                pid=rs.getLong("PERSONID");
                aid=rs.getLong("ADDRESSID");
                emaill=rs.getString("EMAILL");
                hphid=rs.getLong("HOMEPHONEID");
                wphid=rs.getLong("WORKPHONEID");
            }       
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    p=new PersonService().getById(pid);
    a=new AddressService().getById(aid);
    hph=new PhoneService().getById(hphid);
    wph=new PhoneService().getById(wphid);
    
    cmph=new MobilePhoneService().getByContactID(id);

    c=Factory.createContact(p, a, emaill, hph, wph, cmph);
    return c;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getIdBy(Person p) {
        long id=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT CONTACTS.ID FROM CONTACTS "
                        +" INNER JOIN "+" PERSONS "
                        +" ON "+" CONTACTS.PERSONID = PERSONS.ID "
                        + " WHERE "
                                + "     (PERSONS.FIRSTNAME = "+"'"+p.getFirstName()+"'"+" )"
                                + " AND (PERSONS.LASTNAME = "+"'"+p.getLastName()+"'"+" )"
                                + " AND (PERSONS.BIRTHDAY = "+"'"+p.getBirthday().toString()+"'"+" )";
        
        ResultSet rs =null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){  
                id=rs.getLong("ID");
            }
                else System.out.println("no such Contact in DB");
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getIdBy(String emaill) {
        long id=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT ID FROM CONTACTS "
                        + " WHERE EMAILL = "+"'"+emaill+"'" ;
        
        ResultSet rs =null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){  
                id=rs.getLong("ID");
            }
                else System.out.println("no such Contact in DB");
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getIdBy(MobilePhone mph) {
        long id=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT CONTACTS.ID FROM MOBILEPHONES "
                        +" INNER JOIN " + " CONTACTS "
                        +" ON "+ " MOBILEPHONES.CONTACTID = CONTACTS.ID "
                        + " WHERE "
                                + " (MOBILEPHONES.PROVIDERCODE = "+"'"+ mph.getProviderCode()+"'"+" )"
                                + " AND (MOBILEPHONES.NUMB = "+"'"+ mph.getNumber()+"'"+" )" ;

        ResultSet rs =null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){  
                id=rs.getLong("ID");
            }
                else System.out.println("no such Contact in DB");
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public long insert(Contact t){
        
        long pid = new PersonService().insert(t.getPerson());
        long aid = new AddressService().insert(t.getAdress());
        long hphid = new PhoneService().insert(t.getHomePhone());
        long wphid = new PhoneService().insert(t.getWorkPhone());
        
        ResultSet rs=JDBCUtillite.getResultSet("CONTACTS");
        long id= KeyGenerator.getRandomLong();
        
        try{
            rs.moveToInsertRow();
            
            rs.updateLong("ID", id);
            rs.updateLong("PERSONID", pid);
            rs.updateLong("ADDRESSID", aid);
            rs.updateString("EMAILL", t.getEmail());
            rs.updateLong("HOMEPHONEID", hphid);
            rs.updateLong("WORKPHONEID", wphid);
            
            rs.insertRow();
            rs.moveToCurrentRow();
            rs.close();
            
        } catch (SQLException ex) {
                Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
            }
        for(MobilePhone mph : t.getMobilePhones()){
            new MobilePhoneService().insert(mph, id);
        }
        
     return id;
     // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.   
        
    }

    
    
    
}
