/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author oracle
 */
public class Serializacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        mclase m1 = new mclase("ola",-7,2.7E10);
        
        ObjectOutputStream oos = new ObjectOutputStream(
                                 new FileOutputStream("/home/oracle/Desktop/serializable.dat"));
        ObjectInputStream ois = new ObjectInputStream(
                                 new FileInputStream("/home/oracle/Desktop/serializable.dat"));
        
        oos.writeObject(m1);
        oos.close();
        
        mclase m2;
        m2= (mclase)ois.readObject();
        
        System.out.println(m2.toString());
        ois.close();
        
        
        
    }
    
}
