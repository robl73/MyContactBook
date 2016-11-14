/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.interfaces;

import com.mycompany.contaktbook.entitypackage.MobilePhone;
import java.util.Collection;

/**
 *
 * @author Roma
 */
public interface ServiceMobilePhone extends Service<MobilePhone>{
     long outputToJDBC(MobilePhone t, long idcontact);
     Collection<MobilePhone> getByContactID(long id);
    
    long getIdBy(String providerCode,String number);
    long insert(MobilePhone t,long contactid);
}
