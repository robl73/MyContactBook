/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.rules;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author roma
 */
public class MapSQLCommand {
    public static final Map<String,String> SQLMAP=new HashMap<String,String>();
    /*
    public Map<String, String> getXmlmap() {
        return xmlmap;
    }

    public void setXmlmap(Map<String, String> xmlmap) {
        this.xmlmap = xmlmap;
    }
    */
    static{
        SQLMAP.put("PERSONS", "SQLscripts/CrudScripts/PersonGetAll.sql");
        SQLMAP.put("ADDRESSES", "SQLscripts/CrudScripts/AddressGetAll.sql");
        SQLMAP.put("PHONES", "SQLscripts/CrudScripts/PhoneGetAll.sql");
        SQLMAP.put("MOBILEPHONES", "SQLscripts/CrudScripts/MobilePhoneGetAll.sql");
        SQLMAP.put("CONTACTS", "SQLscripts/CrudScripts/ContactGetAll.sql");
        SQLMAP.put("MOBILEPHONEFORPERSON", "SQLscripts/CrudScripts/MobilePhoneForPerson.sql");
    }
 
    
}
