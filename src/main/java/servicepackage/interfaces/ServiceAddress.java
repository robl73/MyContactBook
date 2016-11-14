/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.interfaces;

import com.mycompany.contaktbook.entitypackage.Address;

/**
 *
 * @author Roma
 */
public interface ServiceAddress extends Service<Address>{
long getIdBy(String city, String street, String house, String flat);    
}
