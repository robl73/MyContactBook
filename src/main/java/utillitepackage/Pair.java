/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;

/**
 *
 * @author Roma
 */
public class Pair {
private String patstr;
private String message;

    public String getPatstr() {
        return patstr;
    }

    public String getMessage() {
        return message;
    }

    public void setPatstr(String patstr) {
        this.patstr = patstr;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pair(String patstr, String message) {
        this.patstr = patstr;
        this.message = message;
    }

    public Pair() {
        this.patstr = "";
        this.message = "";
    }

}
