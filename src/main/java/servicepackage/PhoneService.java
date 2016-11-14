/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import utillitepackage.Factory;
import servicepackage.interfaces.ServicePhone;
import servicepackage.rules.RulePhoneValidator;
import utillitepackage.Validator;
import com.mycompany.contaktbook.entitypackage.Phone;
import com.mycompany.contaktbook.entitypackage.PhoneTypeEnum;
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
import servicepackage.rules.MapPhoneXML;
import utillitepackage.JDBCUtillite;
import utillitepackage.KeyGenerator;
import utillitepackage.XMLUtillite;

/**
 *
 * @author Roma
 */
public class PhoneService implements ServicePhone{
    @Override
    public  Phone buildFromConsole(){
 
   String countrycode,citycode,number;
    
   int count=0;
    
   String pat;
   String message; 
   
   System.out.println("Enter DATA for PHONE :");
    
   Scanner sc=new Scanner(System.in);
   
   
   pat=new RulePhoneValidator().getRegmap().get("countryCode").getPatstr();
   message=new RulePhoneValidator().getRegmap().get("countryCode").getMessage();
    do{
        count++;
        System.out.println("Enter CountryCode:");
        System.out.println(message);
        countrycode=sc.nextLine();
   }
   while ((!Validator.validate(countrycode,pat))&&count<=3);
   count=0;
   
   pat=new RulePhoneValidator().getRegmap().get("cityCode").getPatstr();
   message=new RulePhoneValidator().getRegmap().get("cityCode").getMessage();
    do{
        count++;
        System.out.println("Enter CityCode:");
        System.out.println(message);
        citycode=sc.nextLine();
   }
   while ((!Validator.validate(citycode,pat))&&count<=3);
   count=0;
   
  pat=new RulePhoneValidator().getRegmap().get("number").getPatstr();
   message=new RulePhoneValidator().getRegmap().get("number").getMessage();
    do{
        count++;
        System.out.println("Enter Number:");
        System.out.println(message);
        number=sc.nextLine();
   }
   while ((!Validator.validate(number,pat))&&count<=3);
   count=0; 
    
   Phone p=Factory.createPhone(countrycode, citycode, number);
   return p;
    
}
    @Override
    public void outputToConsole(Phone t) {
        String sf="%s ,%s ,%s : ";            
        Object[] args=new Object[]{t.getCountryCode(),t.getCityCode(),t.getNumber()};
        String  s=new Formatter().format(Locale.UK, sf, args).toString();
        System.out.println(s);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Node outputToXML(Phone t) {
        
        
        String s=null;
        Document d = null;
        d= XMLUtillite.DOC;
     
        Element ephone=d.createElement("PHONE");
        
        Element ecc=d.createElement(MapPhoneXML.XMLMAP.get("countryCode"));
        s="\n"+t.getCountryCode()+"\n";
        s=t.getCountryCode();
        Text tcc =d.createTextNode(s);
        
        ecc.appendChild(tcc);
        
        Element ectc=d.createElement(MapPhoneXML.XMLMAP.get("cityCode"));
        s="\n"+t.getCityCode().toString()+"\n";
        s=t.getCityCode().toString();      
        Text tctc =d.createTextNode(s);
        ectc.appendChild(tctc);
        
        Element en=d.createElement(MapPhoneXML.XMLMAP.get("number"));
        s="\n"+t.getNumber().toString()+"\n";
        s=t.getNumber().toString();
        Text tn =d.createTextNode(s);
        en.appendChild(tn);
        
        Element epht=d.createElement(MapPhoneXML.XMLMAP.get("phoneType"));
        s="\n"+t.getPhoneType().toString()+"\n";
        s=t.getPhoneType().toString();
        Text tpht =d.createTextNode(s);
        epht.appendChild(tpht);
        

        ephone.appendChild(ecc);
        ephone.appendChild(ectc);
        ephone.appendChild(en);
        ephone.appendChild(epht);
 
        return ephone;
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public JsonStructure outputToJSON(Phone t) {
        JsonObject phjo= Json.createObjectBuilder()
                .add("countryCode", t.getCountryCode())
                .add("cityCode", t.getCityCode())
                .add("number", t.getNumber())
                .add("phoneType", t.getPhoneType().toString())
                .build();
        return phjo;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Phone buildFromJDBC(long id) {
        Phone t = null;
        t=new PhoneService().getById(id);
        return t;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(Phone t) {
        Statement stm=JDBCUtillite.getStatement();
        long id=KeyGenerator.getRandomLong();
        String inscom=null;
            inscom="INSERT INTO PHONES(ID, COUNTRYCODE, CITYCODE, NUMB, PHONETYPE) "
                    +"VALUES "
                    +"("
                    //+t.getId()+","
                    +id+","
                    +"'"+t.getCountryCode()+"'"+", "
                    +"'"+t.getCityCode()+"'"+", "
                    +"'"+t.getNumber()+"'"+", "
                    +"'"+t.getPhoneType().toString()+"'"
                    +")";
         
        try {
            stm.executeUpdate(inscom, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(PhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Phone buildFromXML(Element element) {
        
        String ccexpr= "PHONE/"+MapPhoneXML.XMLMAP.get("countryCode")+"/text()";       
        String cc=null;
        cc=XMLUtillite.takeNodeData(ccexpr, element);
   
        String ctcexpr= "PHONE/"+MapPhoneXML.XMLMAP.get("cityCode")+"/text()";       
        String ctc=null;
        ctc=XMLUtillite.takeNodeData(ctcexpr, element);
    
        String nexpr= "PHONE/"+MapPhoneXML.XMLMAP.get("number")+"/text()";       
        String n=null;
        n=XMLUtillite.takeNodeData(nexpr, element);
        
        String phtexpr= "PHONE/"+MapPhoneXML.XMLMAP.get("phoneType")+"/text()";       
        String pht=null;
        pht=XMLUtillite.takeNodeData(phtexpr, element);
        PhoneTypeEnum phte=PhoneTypeEnum.valueOf(pht);
               
        Phone ph= Factory.createPhone(cc, ctc, n, phte);
        
        return ph;
 
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Phone buildFromJson(JsonStructure js) {
        JsonObject jo=(JsonObject)js;
        String countryCode=jo.getString("countryCode");
        String cityCode=jo.getString("cityCode");
        String number=jo.getString("number");
        String phoneType=jo.getString("phoneType");
        PhoneTypeEnum phte=PhoneTypeEnum.valueOf(phoneType);
        Phone ph=Factory.createPhone(countryCode, cityCode, number, phte);
        return ph;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/PhoneTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Phone getById(long id) {
 String cc = null,ctc = null,n = null,t = null;
 Statement stm= JDBCUtillite.getStatement();
 String selcmd="SELECT * FROM PHONES "
                + "WHERE PHONES.ID = "+id;
 ResultSet rs=null;
        try {
            rs=stm.executeQuery(selcmd);
        } catch (SQLException ex) {
            Logger.getLogger(PhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                cc=rs.getString("COUNTRYCODE");
                ctc=rs.getString("CITYCODE");
                n=rs.getString("NUMB");
                t=rs.getString("PHONETYPE");
            }       
        } catch (SQLException ex) {
            Logger.getLogger(PhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    PhoneTypeEnum phte=PhoneTypeEnum.valueOf(t);
    Phone ph=Factory.createPhone(cc, ctc, n, phte);
    return ph;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getIdBy(String cityCode, String number) {
        long id=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT ID FROM PHONES "
                        + " WHERE CITYCODE = "+"'"+cityCode+"'"+
                                 " NUMB = "+"'"+number+"'" ;
        
        ResultSet rs =null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(PhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){  
                id=rs.getLong("ID");
            }
                else System.out.println("no such Phone in DB");
        } catch (SQLException ex) {
            Logger.getLogger(PhoneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id; 
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(Phone t) {
        ResultSet rs=JDBCUtillite.getResultSet("PHONES");
        long id= KeyGenerator.getRandomLong();
      {
            try {
                rs.moveToInsertRow();
                rs.updateLong("ID", id);
                rs.updateString("COUNTRYCODE", t.getCountryCode());
                rs.updateString("CITYCODE", t.getCityCode());
                rs.updateString("NUMB", t.getNumber());
                rs.updateString("PHONETYPE", t.getPhoneType().toString());
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
