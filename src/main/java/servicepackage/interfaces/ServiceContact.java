/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.interfaces;

import com.mycompany.contaktbook.entitypackage.Contact;
import com.mycompany.contaktbook.entitypackage.MobilePhone;
import com.mycompany.contaktbook.entitypackage.Person;

/**
 *
 * @author Roma
 */
public interface ServiceContact extends Service<Contact> {

    long getIdBy(Person p);
    long getIdBy(String emaill);
    long getIdBy(MobilePhone mph);
}
