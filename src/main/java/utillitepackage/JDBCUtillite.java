/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicepackage.rules.MapSQLCommand;

/**
 *
 * @author roma
 */
public class JDBCUtillite {
    public static Connection getConnection(){
        String url="jdbc:derby://localhost:1527/ContactBookDB";
        Connection con=null;
        try {
            con=DriverManager.getConnection(url);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    public static Statement getStatement(){
        Connection con=null;
        con=JDBCUtillite.getConnection();
        Statement stm=null;
        try {
            stm=con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stm;
        
    }
    public static String getCommand(String file){
        String command= "";
        Scanner sc =null;
        try {
            sc= new Scanner(new File(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JDBCUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(sc.hasNextLine()){
            command+=sc.nextLine();
        }
        
        return command;
   
    }
    
 

    public static ResultSet getResultSet(String fromtablename){
        Connection con=null;
        con=JDBCUtillite.getConnection();
        Statement stm=null;
        try {
            stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        String cmdfile;
        cmdfile=MapSQLCommand.SQLMAP.get(fromtablename);
        String sqlcmd=JDBCUtillite.getCommand(cmdfile);
        ResultSet rs=null;
        try {
            rs=stm.executeQuery(sqlcmd);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCUtillite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
        
        
        
    }
    
    
}
