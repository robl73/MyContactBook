/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import utillitepackage.Factory;
import com.mycompany.contaktbook.entitypackage.GroupEnum;
import com.mycompany.contaktbook.entitypackage.MobilePhone;
import servicepackage.interfaces.ServicePerson;
import servicepackage.rules.RulePersonValidator;
import utillitepackage.Validator;
import com.mycompany.contaktbook.entitypackage.Person;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.joda.time.LocalDate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import servicepackage.rules.MapContactXML;
import servicepackage.rules.MapPersonXML;
import servicepackage.rules.MapSQLCommand;
import utillitepackage.JDBCUtillite;
import utillitepackage.XMLUtillite;
import utillitepackage.KeyGenerator;

/**
 *
 * @author Roma
 */
public class PersonService implements ServicePerson{
    @Override
    public  Person buildFromConsole(){
   // System.out.println("Enter Data about PERSON:");
    String firstname,lastname,birthday;
    int year,mounth,day;
    LocalDate dtbirthday;
    GroupEnum group;
    
    int count=0;
    
    String pat;
    String message;
    
    System.out.println("Enter DATA for PERSON :");
    
Scanner sc=new Scanner(System.in);

 pat=new RulePersonValidator().getRegmap().get("firstName").getPatstr();
 message=new RulePersonValidator().getRegmap().get("firstName").getMessage();
    do{
        count++;
        System.out.println("Enter FirstName:");
        System.out.println(message);
        firstname=sc.nextLine();
   }
   while ((!Validator.validate(firstname,pat))&&count<=3);
   count=0;
   
   
   
 pat=new RulePersonValidator().getRegmap().get("lastName").getPatstr();
 message=new RulePersonValidator().getRegmap().get("lastName").getMessage();
    do{
        count++;
        System.out.println("Enter LastName:");
        System.out.println(message);
        lastname=sc.nextLine();
   }
   while ((!Validator.validate(lastname,pat))&&count<=3);
   count=0;
        

 pat=new RulePersonValidator().getRegmap().get("birthday").getPatstr();
 message=new RulePersonValidator().getRegmap().get("birthday").getMessage();
    do{
        count++;
        System.out.println("Enter Birthday:");
        System.out.println(message);
        birthday=sc.nextLine();
   }
   while ((!Validator.validate(birthday,pat))&&count<=3);
   count=0;
        //birthday.parseDateString();
        dtbirthday=new LocalDate(birthday);

    group=new GroupEnumService().buildFromConsole();

    Person p=Factory.createPerson(firstname, lastname,dtbirthday,group);
    
 return p;       
}

    @Override
    public void outputToConsole(Person t) {
        String sf="%s ,%.4s , %tF , %s ;";
        Object[] args=new Object[]{t.getFirstName(),t.getLastName(),t.getBirthday().toDate(),t.getGroup()};
        String  s=new Formatter().format(Locale.UK, sf, args).toString();
        System.out.println(s);
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Node outputToXML(Person t){
        String s=null;
        Document d = null;
        d= XMLUtillite.DOC;
     
        Element epers=d.createElement(MapContactXML.XMLMAP.get("person"));
        
        Element efn=d.createElement(MapPersonXML.XMLMAP.get("firstName"));
        s="\n"+t.getFirstName()+"\n";
        s=t.getFirstName();
        Text tfn =d.createTextNode(s);
        
        efn.appendChild(tfn);
        
        Element eln=d.createElement(MapPersonXML.XMLMAP.get("lastName"));
        s="\n"+t.getLastName().toString()+"\n";
        s=t.getLastName().toString();      
        Text tln =d.createTextNode(s);
        eln.appendChild(tln);
        
        Element eb=d.createElement(MapPersonXML.XMLMAP.get("birthday"));
        s="\n"+t.getBirthday().toString()+"\n";
        s=t.getBirthday().toString();
        Text tb =d.createTextNode(s);
        eb.appendChild(tb);
        
        Element eg=d.createElement(MapPersonXML.XMLMAP.get("group"));
        s="\n"+t.getGroup().toString()+"\n";
        s=t.getGroup().toString();
        Text tg =d.createTextNode(s);
        eg.appendChild(tg);
               
        epers.appendChild(efn);
        epers.appendChild(eln);
        epers.appendChild(eb);
        epers.appendChild(eg);
      
        return epers;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public JsonStructure outputToJSON(Person t) {       
        JsonObject pjo= Json.createObjectBuilder()
                .add("firstName", t.getFirstName())
                .add("lastName", t.getLastName())
                .add("birthday", t.getBirthday().toString())
                .add("group", t.getGroup().toString())
                .build();
        return pjo;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person buildFromJDBC(long id) {
        Person t = null;
        t=new PersonService().getById(id);
        return t;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(Person t) {
        Statement stm=JDBCUtillite.getStatement();
        long id=KeyGenerator.getRandomLong();
        String inscom=null;
            inscom="INSERT INTO PERSONS (ID, FIRSTNAME, LASTNAME, BIRTHDAY, GROUPS) "
                    + "VALUES "
                    +"("
                    //+t.getId()+", "
                    +id+", "
                    +"'"+t.getFirstName()+"'"+", "
                    +"'"+t.getLastName()+"'"+", "
                    +"'"+t.getBirthday().toString()+"'"+", "
                    +"'"+t.getGroup().toString()+"' "
                    +")";
         
        try {
            stm.executeUpdate(inscom, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return  id;
    }
 
   

    @Override
    public Person buildFromXML(Element element) {
  
        String fnexpr= MapPersonXML.XMLMAP.get("firstName")+"/text()";       
        String fn=null;
        fn=XMLUtillite.takeNodeData(fnexpr, element);
   
        String lnexpr= MapPersonXML.XMLMAP.get("lastName")+"/text()";       
        String ln=null;
        ln=XMLUtillite.takeNodeData(lnexpr, element);
    
        String bexpr= MapPersonXML.XMLMAP.get("birthday")+"/text()";       
        String b=null;
        b=XMLUtillite.takeNodeData(bexpr, element); 
        LocalDate dtb= new LocalDate(b);       
        
        String gexpr= MapPersonXML.XMLMAP.get("group")+"/text()";       
        String g=null;
        g=XMLUtillite.takeNodeData(gexpr, element);
        GroupEnum ge= GroupEnum.valueOf(g);
        
        Person p= Factory.createPerson(fn, ln, dtb, ge);
        
        return p;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person buildFromJson(JsonStructure js) {
        JsonObject jo=(JsonObject)js;
        String firstName=jo.getString("firstName");
        String lastName=jo.getString("lastName");
        String birthday=jo.getString("birthday");
        LocalDate dtbirthday=new LocalDate(birthday);
        String group=jo.getString("group");
        GroupEnum ge=GroupEnum.valueOf(group);
        Person p=Factory.createPerson(firstName, lastName, dtbirthday, ge);
        return p;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/PersonTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Person getById(long id){
        
        String fn = null,ln = null,b = null,g=null;
        Statement stm= JDBCUtillite.getStatement();
        String selcmd="SELECT * FROM PERSONS "
                + "WHERE PERSONS.ID = "+id;
        ResultSet rs=null;
        try {
            rs=stm.executeQuery(selcmd);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(rs.next()){
                fn=rs.getString("FIRSTNAME");
                ln=rs.getString("LASTNAME");
                b=rs.getString("BIRTHDAY");
                g=rs.getString("GROUPS");
            }       
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        LocalDate dtbirthday=new LocalDate(b);
        GroupEnum eg=GroupEnum.valueOf(g);
    Person p=Factory.createPerson(fn, ln, dtbirthday, eg);
    return p;
}    

    @Override
    public long getIdBy(String firstName, String lastName, LocalDate birthday) {
        long id=0;
        Statement stm=JDBCUtillite.getStatement();
        String sqlcmd="SELECT ID FROM PERSONS "
                        + " WHERE FIRSTNAME = "+"'"+firstName+"'"+
                                 " LASTNAME = "+"'"+lastName+"'"+
                                 " BIRTHDAY = "+"'"+birthday.toString()+"'";
        
        ResultSet rs =null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(rs.next()){  
                id=rs.getLong("ID");
            }
                else System.out.println("no such Person in DB");
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;       
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(Person t) {
        ResultSet rs=JDBCUtillite.getResultSet("PERSONS");
        long id= KeyGenerator.getRandomLong();
      {
            try {
                rs.moveToInsertRow();
                rs.updateLong("ID", id);
                rs.updateString("FIRSTNAME", t.getFirstName());
                rs.updateString("LASTNAME", t.getLastName());
                rs.updateString("BIRTHDAY", t.getBirthday().toString());
                rs.updateString("GROUPS", t.getGroup().toString());
                rs.insertRow();
                rs.moveToCurrentRow();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
      }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<MobilePhone> getMobilePhones(Person p) {
        
        
         String cmdfile;
            cmdfile = MapSQLCommand.SQLMAP.get("MOBILEPHONEFORPERSON");
            String sqlcmd=JDBCUtillite.getCommand(cmdfile);
            
            PreparedStatement prpstm =null;
            ResultSet rs =null;
            String countrycode, providercode, number;
            MobilePhone mph=null;
            Collection<MobilePhone> cmph= new ArrayList<>();
            
            Connection con=JDBCUtillite.getConnection();
            try {
                prpstm=con.prepareStatement(sqlcmd, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                prpstm.setString(1, p.getFirstName());
                prpstm.setString(2, p.getLastName());
                prpstm.setString(3, p.getBirthday().toString());
                rs = prpstm.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try{
            while(rs.next()){
                countrycode=rs.getString("COUNTRYCODE");
                providercode=rs.getString("PROVIDERCODE");
                number=rs.getString("NUMB"); 
                mph=Factory.createMobilePhone(countrycode, providercode, number);
                cmph.add(mph);
            }
            }catch(SQLException ex){
                 Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);    
                    }
            return cmph;
        
        
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     
}
