/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonStructure;

/**
 *
 * @author Admin
 */
public class JsonUtillite {
    public static final JsonBuilderFactory JSONBF;
    public static final JsonReaderFactory JSONRDRF;
    static {
        Map<String,String> config1= new HashMap<String,String>();
        JSONBF=Json.createBuilderFactory(config1);
        Map<String,String> config2= new HashMap<String,String>();
        JSONRDRF=Json.createReaderFactory(config2);
    }
    public static JsonStructure getJsonStructureFromJsonFile(String file){
        FileReader fr=null;
        JsonStructure js=null;
        JsonReader jsnrdr=null;
        try {
            jsnrdr=JsonUtillite.JSONRDRF.createReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        js=jsnrdr.read();
        return js;
    }
    
}
