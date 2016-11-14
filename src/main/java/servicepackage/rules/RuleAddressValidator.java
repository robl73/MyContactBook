
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
public class RuleAddressValidator {
 private Map<String,Pair> regmap;

    public Map<String, Pair> getRegmap() {
        return regmap;
    }

    public void setRegmap(Map<String, Pair> regmap) {
        this.regmap = regmap;
    }

{
    
  regmap=new HashMap<String,Pair>();
  
  regmap.put("zipCode",new Pair("^\\p{Digit}{6}","mesForZipCode : XXXXXX"));
  regmap.put("city", new Pair("^\\p{Upper}{1}(\\p{Lower})+","mesForCity"));
  regmap.put("street", new Pair("^\\p{Upper}{1}(\\p{Lower})+","mesForStreet"));
  regmap.put("house",new Pair("^\\p{Digit}{1,3}(\\p{Alpha}?)","mesForHouse"));
  regmap.put("flat", new Pair("^\\p{Digit}{1,3}(\\p{Alpha}?)","mesForFlat"));
    
};    
}
