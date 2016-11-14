/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import com.mycompany.contaktbook.entitypackage.GroupEnum;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonStructure;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import servicepackage.interfaces.ServiceGroupEnum;
import utillitepackage.JDBCUtillite;

/**
 *
 * @author Roma
 */
public class GroupEnumService implements ServiceGroupEnum{
    @Override
public  GroupEnum buildFromConsole(){
    
    GroupEnum g;
        System.out.println("Enter DATA about PERSONS_GROUP:");
        System.out.println("Select Group:");
        
        
        for(int i=0; i<GroupEnum.values().length;i++){
            
            System.out.println(GroupEnum.values()[i]+ "--"+ i); 
            
        }
        Scanner sc=new Scanner(System.in);
        int sel=sc.nextInt();
        g=GroupEnum.values()[sel]; 
        
        return g;
}

    @Override
    public void outputToConsole(GroupEnum t) {
        String sf="%s ;";
        Object[] args=new Object[]{t.toString()};
        String  s=new Formatter().format(Locale.UK, sf, args).toString();
        System.out.println(s);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Node outputToXML(GroupEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public JsonStructure outputToJSON(GroupEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GroupEnum buildFromJDBC(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(GroupEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public GroupEnum buildFromXML(Element element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GroupEnum buildFromJson(JsonStructure js) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/GroupTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GroupEnum getById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(GroupEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
