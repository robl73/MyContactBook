/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.rules;

import utillitepackage.Pair;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roma
 */
public class RulePhoneValidator {
  private Map<String,Pair> regmap;

    public Map<String, Pair> getRegmap() {
        return regmap;
    }

    public void setRegmap(Map<String, Pair> regmap) {
        this.regmap = regmap;
    }
  
  

{
    
  regmap=new HashMap<String,Pair>();
  
  regmap.put("countryCode",new Pair("^\\p{Digit}{3}","mesForCountryCode : XXX"));
  regmap.put("cityCode", new Pair("^\\p{Digit}{3}","mesForCityCode : XXX"));
  regmap.put("number", new Pair("^\\p{Digit}{6}","mesForNumber : XXXXXX"));
 
    
};    
}
