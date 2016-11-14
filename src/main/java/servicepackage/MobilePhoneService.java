/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import utillitepackage.Factory;
import servicepackage.interfaces.ServiceMobilePhone;
import servicepackage.rules.RuleMobilePhoneValidator;
import utillitepackage.Validator;
import com.mycompany.contaktbook.entitypackage.MobilePhone;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
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
import servicepackage.rules.MapMobilePhoneXML;
import utillitepackage.JDBCUtillite;
import utillitepackage.XMLUtillite;
import utillitepackage.KeyGenerator;
/**
 *
 * @author Roma
 */
public class MobilePhoneService implements ServiceMobilePhone{
    @Override
public  MobilePhone buildFromConsole(){
 
   String countrycode,providercode,number;
    
   int count=0;
    
   String pat;
   String message; 
   
   System.out.println("Enter DATA for MOBILE_PHONE :");
   Scanner sc=new Scanner(System.in);
   
   
   pat=new RuleMobilePhoneValidator().getRegmap().get("countryCode").getPatstr();
   message=new RuleMobilePhoneValidator().getRegmap().get("countryCode").getMessage();
    do{
        count++;
        System.out.println("Enter CountryCode:");
        System.out.println(message);
        countrycode=sc.nextLine();
   }
   while ((!Validator.validate(countrycode,pat))&&count<=3);
   count=0;
   
   pat=new RuleMobilePhoneValidator().getRegmap().get("providerCode").getPatstr();
   message=new RuleMobilePhoneValidator().getRegmap().get("providerCode").getMessage();
    do{
        count++;
        System.out.println("Enter ProviderCode:");
        System.out.println(message);
        providercode=sc.nextLine();
   }
   while ((!Validator.validate(providercode,pat))&&count<=3);
   count=0;
   
  pat=new RuleMobilePhoneValidator().getRegmap().get("number").getPatstr();
   message=new RuleMobilePhoneValidator().getRegmap().get("number").getMessage();
    do{
        count++;
        System.out.println("Enter Number:");
        System.out.println(message);
        number=sc.nextLine();
   }
   while ((!Validator.validate(number,pat))&&count<=3);
   count=0; 
    
   MobilePhone mp=Factory.createMobilePhone(countrycode, providercode, number);
   return mp;
    
}
    @Override
    public void outputToConsole(MobilePhone t) {
        String sf="%s ,%s ,%s : ";            
        Object[] args=new Object[]{t.getCountryCode(),t.getProviderCode(),t.getNumber()};
        String  s=new Formatter().format(Locale.UK, sf, args).toString();
        System.out.println(s);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Node outputToXML(MobilePhone t) {
        
        String s=null;
        Document d = null;
        d= XMLUtillite.DOC;
     
        Element emobphone=d.createElement("MOBILEPHONE");
        
        Element ecc=d.createElement(MapMobilePhoneXML.XMLMAP.get("countryCode"));
        s="\n"+t.getCountryCode()+"\n";
        s=t.getCountryCode();
        Text tcc =d.createTextNode(s);
        
        ecc.appendChild(tcc);
        
        Element epvc=d.createElement(MapMobilePhoneXML.XMLMAP.get("providerCode"));
        s="\n"+t.getProviderCode().toString()+"\n";
        s=t.getProviderCode().toString();      
        Text tpvc =d.createTextNode(s);
        epvc.appendChild(tpvc);
        
        Element en=d.createElement(MapMobilePhoneXML.XMLMAP.get("number"));
        s="\n"+t.getNumber().toString()+"\n";
        s=t.getNumber().toString();
        Text tn =d.createTextNode(s);
        en.appendChild(tn);
        

        emobphone.appendChild(ecc);
        emobphone.appendChild(epvc);
        emobphone.appendChild(en);
       
/*
        ephone.insertBefore(d.createTextNode("\n"), ecc);
        ephone.insertBefore(d.createTextNode("\n"), ectc);
        ephone.insertBefore(d.createTextNode("\n"), en);
       
 */       

        return emobphone;
      
        //Sthrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public JsonStructure outputToJSON(MobilePhone t) {
        JsonObject mphjo= Json.createObjectBuilder()
                .add("countryCode", t.getCountryCode())
                .add("providerCode", t.getProviderCode())
                .add("number", t.getNumber())
                .build();
        return mphjo;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MobilePhone buildFromJDBC(long id) {
        MobilePhone t = null;
        t=new MobilePhoneService().getById(id);
        return t;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(MobilePhone t) {
        
         Statement stm=JDBCUtillite.getStatement();
        long id=KeyGenerator.getRandomLong();
        String inscom=null;
            inscom="INSERT INTO MOBILEPHONES(ID, COUNTRYCODE, PROVIDERCODE, NUMB) "
                    +"VALUES "
                    +"("
                    //+t.getId()+","
                    +id+", "
                    +"'"+t.getCountryCode()+"'"+", "
                    +"'"+t.getProviderCode()+"'"+", "
                    +"'"+t.getNumber()+"'"//+", "
            //        +idcontact
                    +")";
         
        try {
            stm.executeUpdate(inscom, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public MobilePhone buildFromXML(Element element) {
        
        String ccexpr= MapMobilePhoneXML.XMLMAP.get("countryCode")+"/text()";       
        String cc=null;
        cc=XMLUtillite.takeNodeData(ccexpr, element);
   
        String pvcexpr= MapMobilePhoneXML.XMLMAP.get("providerCode")+"/text()";       
        String pvc=null;
        pvc=XMLUtillite.takeNodeData(pvcexpr, element);
    
        String nexpr= MapMobilePhoneXML.XMLMAP.get("number")+"/text()";       
        String n=null;
        n=XMLUtillite.takeNodeData(nexpr, element); 
               
        MobilePhone mph= Factory.createMobilePhone(cc, pvc, n);
        
        return mph;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MobilePhone buildFromJson(JsonStructure js) {
        JsonObject jo=(JsonObject)js;
        String countryCode=jo.getString("countryCode");
        String providerCode=jo.getString("providerCode");
        String number=jo.getString("number");
        MobilePhone mph=Factory.createMobilePhone(countryCode, providerCode, number);
        return mph;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/MobilePhoneTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(MobilePhone t, long idcontact) {
        Statement stm=JDBCUtillite.getStatement();
        long id=KeyGenerator.getRandomLong();
        String inscom=null;
            inscom="INSERT INTO MOBILEPHONES(ID, COUNTRYCODE, PROVIDERCODE, NUMB, CONTACTID) "
                    +"VALUES "
                    +"("
                    //+t.getId()+","
                    +id+", "
                    +"'"+t.getCountryCode()+"'"+", "
                    +"'"+t.getProviderCode()+"'"+", "
                    +"'"+t.getNumber()+"'"+", "
                    +idcontact
                    +")";
         
        try {
            stm.executeUpdate(inscom, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MobilePhone getById(long id) {
        String cc = null,pc = null,n = null;
        MobilePhone mph=null;
 Statement stm= JDBCUtillite.getStatement();
 String selcmd="SELECT * FROM MOBILEPHONES "
                + "WHERE MOBILEPHONES.ID = "+id;
 ResultSet rs=null;
        try {
            rs=stm.executeQuery(selcmd);
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                cc=rs.getString("COUNTRYCODE");
                pc=rs.getString("PROVIDERCODE");
                n=rs.getString("NUMB");
            }       
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    mph=Factory.createMobilePhone(cc, pc, n );
    return mph;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MobilePhone> getByContactID(long id) {
        String cc = null,pc = null,n = null;
        MobilePhone mph=null;
        Collection<MobilePhone> cmph=new ArrayList<>();
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT * FROM MOBILEPHONES "
                + "WHERE MOBILEPHONES.CONTACTID = "+id;
        ResultSet rs=null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                cc=rs.getString("COUNTRYCODE");
                pc=rs.getString("PROVIDERCODE");
                n=rs.getString("NUMB");
                mph=Factory.createMobilePhone(cc, pc, n );
                cmph.add(mph);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cmph;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getIdBy(String providerCode, String number) {
        
        long id=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT ID FROM MOBILEPHONES "
                        + " WHERE PROVIDERCODE = "+"'"+providerCode+"'"+
                                 " NUMB = "+"'"+number+"'" ;
        
        ResultSet rs =null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){  
                id=rs.getLong("ID");
            }
                else System.out.println("no such MobilePhone in DB");
        } catch (SQLException ex) {
            Logger.getLogger(MobilePhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(MobilePhone t,long contactid) {
        ResultSet rs=JDBCUtillite.getResultSet("MOBILEPHONES");
        long id= KeyGenerator.getRandomLong();
      {
            try {
                rs.moveToInsertRow();
                rs.updateLong("ID", id);
                rs.updateString("COUNTRYCODE", t.getCountryCode());
                rs.updateString("PROVIDERCODE", t.getProviderCode());
                rs.updateString("NUMB", t.getNumber());
                rs.updateLong("CONTACTID", contactid);
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
    
    
    

    @Override
    public long insert(MobilePhone t) {
     
        ResultSet rs=JDBCUtillite.getResultSet("MOBILEPHONES");
        long id= KeyGenerator.getRandomLong();
      {
            try {
                rs.moveToInsertRow();
                rs.updateLong("ID", id);
                rs.updateString("COUNTRYCODE", t.getCountryCode());
                rs.updateString("PROVIDERCODE", t.getProviderCode());
                rs.updateString("NUMB", t.getNumber());
        //        rs.updateLong("CONTACTID", contactid);
                rs.insertRow();
                rs.moveToCurrentRow();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
    
}

