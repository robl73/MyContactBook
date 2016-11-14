/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Roma
 */
public class Validator {
    public static boolean validate(String inp,String pat){
    Pattern p=Pattern.compile(pat);
    Matcher m=p.matcher(inp);
    return  m.matches();
    
}
}
