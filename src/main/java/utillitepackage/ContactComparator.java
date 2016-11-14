/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;

import com.mycompany.contaktbook.entitypackage.Contact;
import java.util.Comparator;

/**
 *
 * @author Roma
 */
public class ContactComparator implements Comparator<Contact>{

    @Override
    public int compare(Contact o1, Contact o2) {
        String n1=o1.getPerson().getFirstName()+o1.getPerson().getLastName();
        String n2=o2.getPerson().getFirstName()+o2.getPerson().getLastName();
        int res=n1.compareToIgnoreCase(n2);
        return res;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
