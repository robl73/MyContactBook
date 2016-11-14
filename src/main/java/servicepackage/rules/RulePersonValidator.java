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
public class RulePersonValidator {
    
 private Map<String,Pair> regmap;

    public Map<String, Pair> getRegmap() {
        return regmap;
    }

    public void setRegmap(Map<String, Pair> regmap) {
        this.regmap = regmap;
    }
 

{
    
  regmap=new HashMap<String,Pair>();
  
  regmap.put("firstName",new Pair("^\\p{Upper}{1}(\\p{Lower})+","mesForfirstName"));
  regmap.put("lastName", new Pair("^\\p{Upper}{1}(\\p{Lower})+","mesForlastName"));
  regmap.put("birthday", new Pair(".*","In format YYYY-MM-DD :"));
  regmap.put("group",new Pair(".*","mesForgroup"));
};
    
}

