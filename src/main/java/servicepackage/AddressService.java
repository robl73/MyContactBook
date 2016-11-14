/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import utillitepackage.Factory;
import servicepackage.rules.RuleAddressValidator;
import utillitepackage.Validator;
import com.mycompany.contaktbook.entitypackage.Address;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import servicepackage.interfaces.ServiceAddress;
import servicepackage.rules.MapAddressXML;
import servicepackage.rules.MapContactXML;
import utillitepackage.JDBCUtillite;
import utillitepackage.XMLUtillite;
import utillitepackage.KeyGenerator;

/**
 *
 * @author Roma
 */
public class AddressService implements ServiceAddress {
@Override
public  Address buildFromConsole(){
   String zipcode,city,street,house,flat;
   int count=0;
    
   String pat;
   String message; 
   
    System.out.println("Enter DATA for ADDRESS :");
    
   Scanner sc=new Scanner(System.in);
     
    pat=new RuleAddressValidator().getRegmap().get("zipCode").getPatstr();
    message=new RuleAddressValidator().getRegmap().get("zipCode").getMessage();
    do{
        count++;
        System.out.println("Enter ZipCode:");
        System.out.println(message);
        zipcode=sc.nextLine();
   }
   while ((!Validator.validate(zipcode,pat))&&count<=3);
   count=0;
   
   pat=new RuleAddressValidator().getRegmap().get("city").getPatstr();
    message=new RuleAddressValidator().getRegmap().get("city").getMessage();
    do{
        count++;
        System.out.println("Enter City:");
        System.out.println(message);
        city=sc.nextLine();
   }
   while ((!Validator.validate(city,pat))&&count<=3);
   count=0;
   
   pat=new RuleAddressValidator().getRegmap().get("street").getPatstr();
   message=new RuleAddressValidator().getRegmap().get("street").getMessage();
    do{
        count++;
        System.out.println("Enter Street:");
        System.out.println(message);
        street=sc.nextLine();
   }
   while ((!Validator.validate(street,pat))&&count<=3);
   count=0;
   
   pat=new RuleAddressValidator().getRegmap().get("house").getPatstr();
   message=new RuleAddressValidator().getRegmap().get("house").getMessage();
    do{
        count++;
        System.out.println("Enter House:");
        System.out.println(message);
        house=sc.nextLine();
   }
   while ((!Validator.validate(house,pat))&&count<=3);
   count=0;
   
   pat=new RuleAddressValidator().getRegmap().get("flat").getPatstr();
   message=new RuleAddressValidator().getRegmap().get("flat").getMessage();
    do{
        count++;
        System.out.println("Enter Flat:");
        System.out.println(message);
        flat=sc.nextLine();
   }
   while ((!Validator.validate(flat,pat))&&count<=3);
   count=0;
   
   Address a=Factory.createAddress(zipcode, city, street, house, flat);
   return a;

} 

@Override
    public void outputToConsole(Address t){
        String sf="%s ,%s , %s , %s ,%s;";
        Object[] args=new Object[]{t.getZipCode(),t.getCity(),t.getStreet(),t.getHouse(),t.getFlat()};
        String  s=new Formatter().format(Locale.UK, sf, args).toString();
        System.out.println(s);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



@Override
    public Node outputToXML(Address t) {
        String s=null;
        Document d = null;
        d= XMLUtillite.DOC;
     
        Element eadr=d.createElement(MapContactXML.XMLMAP.get("address"));
        
        Element ezc=d.createElement(MapAddressXML.XMLMAP.get("zipCode"));
        s="\n"+t.getZipCode()+"\n";
        s=t.getZipCode();
        Text tzc =d.createTextNode(s);
        
        ezc.appendChild(tzc);
        
        Element ec=d.createElement(MapAddressXML.XMLMAP.get("city"));
        s="\n"+t.getCity().toString()+"\n";
        s=t.getCity().toString();      
        Text tc =d.createTextNode(s);
        ec.appendChild(tc);
        
        Element es=d.createElement(MapAddressXML.XMLMAP.get("street"));
        s="\n"+t.getStreet().toString()+"\n";
        s=t.getStreet().toString();
        Text ts =d.createTextNode(s);
        es.appendChild(ts);
        
        Element eh=d.createElement(MapAddressXML.XMLMAP.get("house"));
        s="\n"+t.getHouse().toString()+"\n";
        s=t.getHouse().toString();
        Text th =d.createTextNode(s);
        eh.appendChild(th);
        
        Element ef=d.createElement(MapAddressXML.XMLMAP.get("flat"));
        s="\n"+t.getFlat().toString()+"\n";
        s=t.getFlat().toString();
        Text tf =d.createTextNode(s);
        ef.appendChild(tf);
               
        eadr.appendChild(ezc);
        eadr.appendChild(ec);
        eadr.appendChild(es);
        eadr.appendChild(eh);
        eadr.appendChild(ef);

        return eadr;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


@Override
    public JsonStructure outputToJSON(Address t) {
        JsonObject ajo= Json.createObjectBuilder()
                .add("zipCode", t.getZipCode())
                .add("city", t.getCity())
                .add("street", t.getStreet())
                .add("house", t.getHouse())
                .add("flat", t.getFlat())
                .build();
        return ajo;
                       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

@Override
    public Address buildFromJDBC(long id) {
        Address t = null;
        t=new AddressService().getById(id);
        return t;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

@Override
    public long outputToJDBC(Address t) {
        
        Statement stm=JDBCUtillite.getStatement();
        long id=KeyGenerator.getRandomLong();
        String inscom=null;
            inscom="INSERT INTO ADDRESSES(ID, ZIPCODE, CITY, STREET, HOUSE, FLAT) "
                    +"VALUES "
                    +"("
                    //+t.getId()+","
                    +id+","
                    +"'"+t.getZipCode()+"'"+", "
                    +"'"+t.getCity()+"'"+", "
                    +"'"+t.getStreet()+"'"+", "
                    +"'"+t.getHouse()+"'"+", "
                    +"'"+t.getFlat()+"'"
                    +")";
         
        try {
            stm.executeUpdate(inscom, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(AddressService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


@Override
    public Address buildFromXML(Element element) {
        
        String zcexpr= MapAddressXML.XMLMAP.get("zipCode")+"/text()";       
        String zc=null;
        zc=XMLUtillite.takeNodeData(zcexpr, element);
   
        String cexpr= MapAddressXML.XMLMAP.get("city")+"/text()";       
        String c=null;
        c=XMLUtillite.takeNodeData(cexpr, element);
    
        String sexpr= MapAddressXML.XMLMAP.get("street")+"/text()";       
        String s=null;
        s=XMLUtillite.takeNodeData(sexpr, element);        
        
        String hexpr= MapAddressXML.XMLMAP.get("house")+"/text()";       
        String h=null;
        h=XMLUtillite.takeNodeData(hexpr, element);
        
        String fexpr= MapAddressXML.XMLMAP.get("flat")+"/text()";       
        String f=null;
        f=XMLUtillite.takeNodeData(fexpr, element);
        
        Address a= Factory.createAddress(zc, c, s, h, f);
        
        return a;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

@Override
    public Address buildFromJson(JsonStructure js) {
        JsonObject jo=(JsonObject)js;
        String zipCode=jo.getString("zipCode");
        String city=jo.getString("city");
        String street=jo.getString("street");
        String house=jo.getString("house");
        String flat=jo.getString("flat");
        Address a=Factory.createAddress(zipCode, city, street, house, flat);
        return a;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

@Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/AddressTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address getById(long id) {
    String zc = null,c = null,s = null,h = null,f = null;
    Statement stm= JDBCUtillite.getStatement();
    String selcmd="SELECT * FROM ADDRESSES "
                + "WHERE ADDRESSES.ID = "+id;
    ResultSet rs=null;
        try {
            rs=stm.executeQuery(selcmd);
        } catch (SQLException ex) {
            Logger.getLogger(AddressService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                zc=rs.getString("ZIPCODE");
                c=rs.getString("CITY");
                s=rs.getString("STREET");
                h=rs.getString("HOUSE");
                f=rs.getString("FLAT");
            }       
        } catch (SQLException ex) {
            Logger.getLogger(AddressService.class.getName()).log(Level.SEVERE, null, ex);
        }
    Address a=Factory.createAddress(zc, c, s, h, f);
    return a;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getIdBy(String city, String street, String house, String flat) {
        long id=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT ID FROM ADDRESSES "
                        + " WHERE CITY = "+"'"+city+"'"+
                                 " STREET = "+"'"+street+"'"+
                                 " HOUSE = "+"'"+house+"'"+
                                 " FLAT = "+"'"+flat+"'" ;
        
        ResultSet rs =null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(AddressService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){  
                id=rs.getLong("ID");
            }
                else System.out.println("no such Address in DB");
        } catch (SQLException ex) {
            Logger.getLogger(AddressService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(Address t) {
        ResultSet rs=JDBCUtillite.getResultSet("ADDRESSES");
        long id= KeyGenerator.getRandomLong();
      {
            try {
                rs.moveToInsertRow();
                rs.updateLong("ID", id);
                rs.updateString("ZIPCODE", t.getZipCode());
                rs.updateString("CITY", t.getCity());
                rs.updateString("STREET", t.getStreet());
                rs.updateString("HOUSE", t.getHouse());
                rs.updateString("FLAT", t.getFlat());
                rs.insertRow();
                rs.moveToCurrentRow();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
}
