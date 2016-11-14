/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicepackage.interfaces;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.json.JsonStructure;
/**
 *
 * @author Roma
 * @param <T>
 */
public interface Service<T> {
    
    T buildFromConsole();
    void outputToConsole(T t);
    
    Node outputToXML(T t);
    T buildFromXML(Element element);
    
    JsonStructure outputToJSON(T t);
    T buildFromJson(JsonStructure js);
    
    long outputToJDBC(T t);  
    T buildFromJDBC (long id);
    
    void createTable();
    T getById(long id);
   // void deleteById(T t,long id);
    
    long insert(T t);
   
};


    
    

