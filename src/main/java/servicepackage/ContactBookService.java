/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import utillitepackage.Factory;
import com.mycompany.contaktbook.entitypackage.Contact;
import servicepackage.interfaces.ServiceContactBook;
import com.mycompany.contaktbook.entitypackage.ContactBook;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import servicepackage.rules.MapContactBookXML;
import utillitepackage.JDBCUtillite;
import utillitepackage.JsonUtillite;
import utillitepackage.XMLUtillite;
/**
 *
 * @author Roma
 */
public class ContactBookService implements ServiceContactBook{
    @Override
   public ContactBook buildFromConsole(){
       
       ContactBook cb =Factory.createContactBook();
       String resp;
       System.out.println("Enter DATA for CONTACTBOOK :");
       do{ 
       cb.getContacts().add(new ContactService().buildFromConsole());
       System.out.println("Do you want enter Contact more : Y|N ");
       Scanner sc =new Scanner(System.in);
       resp=sc.nextLine();
       }
       while(resp.equalsIgnoreCase("Y"));
       
       return  cb;
}

    @Override
    public void outputToConsole(ContactBook t) {
        for(Contact c:t.getContacts()){
            new ContactService().outputToConsole(c);     
        }
            
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Node outputToXML(ContactBook t) {
        
        Document d=null;
        d=XMLUtillite.DOC;
//        
        Element ecb=d.createElement("CONTACTBOOK");
        Element ecs=d.createElement(MapContactBookXML.XMLMAP.get("contacts"));
        
        for (Contact c:t.getContacts()){
            Node econt=new ContactService().outputToXML(c);
            ecs.appendChild(econt); 
        }
        ecb.appendChild(ecs);
        return ecb;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public JsonStructure outputToJSON(ContactBook t) {
        JsonArrayBuilder jab=JsonUtillite.JSONBF.createArrayBuilder();
        
        for(Contact c:t.getContacts()){
            jab.add(new ContactService().outputToJSON(c));
        }
        JsonArray cbja=jab.build();
    return cbja;    
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public ContactBook buildFromJDBC(long id) {
    ContactBook t = null;    
    t=new ContactBookService().getById(id);
    return t;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
    }


    @Override
    public long outputToJDBC(ContactBook t) {
        long id=0;
        long idc=0;
       
        for(Contact c:t.getContacts()){
            idc=new ContactService().outputToJDBC(c);          
        }
        return id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toXMLFile(ContactBook cb, File f,File xsltfile) {
        
        Document d=null;
        d=XMLUtillite.DOC;

        Node cbelem=new ContactBookService().outputToXML(cb);
        d.appendChild(cbelem);
        
        Transformer t=null;
        if(xsltfile==null){
            t=XMLUtillite.getTransformer();  
        }
        else{
            t=XMLUtillite.getTransformer(xsltfile);    
        }
       DOMSource ds=new DOMSource(d);       
       StreamResult sr=new StreamResult(f); 
       try {
           t.transform(ds, sr);
       } catch (TransformerException ex) {
           Logger.getLogger(ContactBookService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return ;  
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public ContactBook buildFromXML(Element element) {
        Document d= XMLUtillite.DOCFF;
        
        Element nfc=null;
        Contact c=null;
        ContactBook cb =Factory.createContactBook();
       
       NodeList nl=d.getElementsByTagName("CONTACT");
       for(int i=0;i<nl.getLength();i++){
            nfc=(Element)nl.item(i);
            c= new ContactService().buildFromXML(nfc);
            cb.getContacts().add(c);    
       }
        return cb;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void toJsonFile(ContactBook cb, String file) {
        FileWriter fw=null;
        try {
            fw = new FileWriter(file);
        } catch (IOException ex) {
            Logger.getLogger(ContactBookService.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonStructure js=new ContactBookService().outputToJSON(cb);
        
        try(JsonWriter jsnwriter=Json.createWriter(fw);){
        jsnwriter.write(js);
        }
        return;
 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ContactBook buildFromJson(JsonStructure js) {
        ContactBook cb= Factory.createContactBook();
        Contact contact=null;
        JsonObject jscontact=null;
        JsonArray ja= (JsonArray)js;
        for(int i=0;i<ja.size();i++){
            jscontact=ja.getJsonObject(i);
            contact=new ContactService().buildFromJson(jscontact);
            cb.getContacts().add(contact);
        }
        return cb;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/ContactBookTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public ContactBook getById(long id) {
        ContactBook cb=Factory.createContactBook();
        Collection<Contact> cc=new ArrayList<>();
        Contact c=null;

        long cid=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT * FROM CONTACTS ";
        
        ResultSet rs=null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                cid=rs.getLong("ID");
                c=new ContactService().getById(cid);
                cc.add(c);      
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        cb.setContacts(cc);
        return cb;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(ContactBook t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createAllContactView() {
        
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/AllContactView.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContactBookService.class.getName()).log(Level.SEVERE, null, ex);
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
   
}
