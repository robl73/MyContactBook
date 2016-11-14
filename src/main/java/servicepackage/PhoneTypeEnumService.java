/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage;

import com.mycompany.contaktbook.entitypackage.PhoneTypeEnum;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonStructure;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import servicepackage.interfaces.ServicePhoneTypeEnum;
import utillitepackage.JDBCUtillite;

/**
 *
 * @author Roma
 */
public class PhoneTypeEnumService implements ServicePhoneTypeEnum{


    @Override
    public void outputToConsole(PhoneTypeEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node outputToXML(PhoneTypeEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhoneTypeEnum buildFromJson(JsonStructure js) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonStructure outputToJSON(PhoneTypeEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhoneTypeEnum buildFromJDBC(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long outputToJDBC(PhoneTypeEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhoneTypeEnum buildFromXML(Element element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhoneTypeEnum buildFromConsole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTable() {
        Statement stm= JDBCUtillite.getStatement();
        String sql= JDBCUtillite.getCommand("SQLscripts/PhoneTypeTable.sql");
        try {
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(PersonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhoneTypeEnum getById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insert(PhoneTypeEnum t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
