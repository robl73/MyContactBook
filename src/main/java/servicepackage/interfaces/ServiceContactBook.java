/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.interfaces;

import com.mycompany.contaktbook.entitypackage.ContactBook;
import java.io.File;

/**
 *
 * @author Roma
 */
public interface ServiceContactBook extends Service<ContactBook> {
   
   void toXMLFile(ContactBook cb,File f,File xsltfile );
   void toJsonFile(ContactBook cb,String file);     
   void createAllContactView();
}
