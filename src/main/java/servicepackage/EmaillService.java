/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonStructure;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import servicepackage.interfaces.ServiceEmaill;
import servicepackage.rules.RuleEmaillValidator;
import utillitepackage.Validator;
import utillitepackage.XMLUtillite;

/**
 *
 * @author Roma
 */
public class EmaillService implements ServiceEmaill{
    @Override
   public  String buildFromConsole(){
 
   String emaill;
    
   int count=0;
    
   String pat;
   String message;
   
   System.out.println("Enter DATA for EMAILL :");
    
   Scanner sc=new Scanner(System.in);
   
   
   pat=new RuleEmaillValidator().getRegmap().get("emaill").getPatstr();
   message=new RuleEmaillValidator().getRegmap().get("emaill").getMessage();
    do{
        count++;
        System.out.println("Enter Emaill:");
        System.out.println(message);
        emaill=sc.nextLine();
   }
   while ((!Validator.validate(emaill,pat))&&count<=3);
   count=0;

return emaill;
}    

    @Override
    public void outputToConsole(String t) {
        String sf="%s ;";
        Object[] args=new Object[]{t.toString()};
        String  s=new Formatter().format(Locale.UK, sf, args).toString();
        System.out.println(s);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Node outputToXML(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonStructure outputToJSON(String t) {
        JsonObject ejo= Json.createObjectBuilder()
                .add("emaill", t)
                .build();
        return ejo;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildFromJDBC(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String buildFromXML(Element element) {
        
        String eexpr= "."+"/text()";       
        String e=null;
        e=XMLUtillite.takeNodeData(eexpr, element);
        return e;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildFromJson(JsonStructure js) {
        JsonObject jo=(JsonObject)js;
        String emaill=jo.getString("emaill");
        return emaill;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   

    @Override
    public void createTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
    

