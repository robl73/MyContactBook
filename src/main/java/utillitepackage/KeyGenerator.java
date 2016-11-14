/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillitepackage;

import java.util.Random;

/**
 *
 * @author Roma
 */
public class KeyGenerator {
public static long getRandomLong(){
Random r =new Random(System.nanoTime());
long l= Math.abs(r.nextLong());
return l;
}
}
