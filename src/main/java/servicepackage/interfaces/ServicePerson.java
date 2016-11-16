/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.interfaces;

import com.mycompany.contaktbook.entitypackage.MobilePhone;
import com.mycompany.contaktbook.entitypackage.Person;
import java.util.Collection;
import org.joda.time.LocalDate;

/**
 *
 * @author Roma
 */
public interface ServicePerson extends Service<Person>{
long getIdBy(String firstName, String lastName, LocalDate birthday);
Collection<MobilePhone> getMobilePhones(Person p);
}
