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
 * @author Admin
 */
public class MapContactXML {
    
    public static final Map<String,String> XMLMAP=new HashMap<String,String>();
    /*
    public Map<String, String> getXmlmap() {
        return xmlmap;
    }

    public void setXmlmap(Map<String, String> xmlmap) {
        this.xmlmap = xmlmap;
    }
    */
    static{
        XMLMAP.put("person", "PERSON");
        XMLMAP.put("address", "ADDRESS");
        XMLMAP.put("email", "EMAILL");
        XMLMAP.put("homePhone", "HOMEPHONE");
        XMLMAP.put("workPhone", "WORKPHONE");
        XMLMAP.put("mobilePhones", "MOBILEPHONES");
    }
 
    
}
