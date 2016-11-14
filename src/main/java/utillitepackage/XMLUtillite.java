/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import servicepackage.ContactBookService;

/**
 *
 * @author Admin
 */
public class XMLUtillite  {
    
    public static final Document DOC;
    public static final Document DOCFF;
    public static final XPath XPATH;
    
    static{
        DOC= XMLUtillite.getDocument();
        DOCFF=XMLUtillite.getDocumentFromFile("myxml.xml");
        XPATH=XMLUtillite.getXPath();
            }
    
    private static Document getDocument() /*throws ParserConfigurationException*/{
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db=null;
       
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document d=db.newDocument();
        //DocumentFragment df=d.createDocumentFragment();
        return d;  
    }
    
    private static Document getDocumentFromFile(String f) /*throws ParserConfigurationException*/{
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db=null;
       
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        Document d=null;
        try {
            d = db.parse(f);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtillite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;  
    }
    
    private static XPath getXPath(){
        XPathFactory xpthf=null;
        xpthf=XPathFactory.newInstance();
        XPath xpth=null;
        xpth=xpthf.newXPath();
        return xpth;  
    }
    
    public static Transformer getTransformer(){
        TransformerFactory tf=TransformerFactory.newInstance();
        Transformer t=null;
       try {
            t=tf.newTransformer();
       } catch (TransformerConfigurationException ex) {
           Logger.getLogger(ContactBookService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return t;
        
    }
    public static Transformer getTransformer(File xsltfile){
        TransformerFactory tf=TransformerFactory.newInstance();
        Transformer t=null;
        Source xslt=null;
        xslt=new StreamSource(xsltfile);
       try {
            t=tf.newTransformer(xslt);
       } catch (TransformerConfigurationException ex) {
           Logger.getLogger(ContactBookService.class.getName()).log(Level.SEVERE, null, ex);
       }
       return t;
        
    }
    
   
    public static String takeNodeData(String expr, Object context){
        XPath xpath= XMLUtillite.XPATH;
        String res=null;
        try {
            res= xpath.evaluate(expr, context);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
   
    
    
    
}
