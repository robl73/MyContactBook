/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.interfaces;

import com.mycompany.contaktbook.entitypackage.Phone;

/**
 *
 * @author Roma
 */
public interface ServicePhone extends Service<Phone>{
    long getIdBy(String cityCode,String number);
}
