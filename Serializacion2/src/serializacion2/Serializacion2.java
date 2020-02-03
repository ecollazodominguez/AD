/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author oracle
 */
public class Serializacion2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
         String[] cod={"p1","p2","p3"};
         String[] desc ={"parafusos","cravos ","tachas"};
        Double[] prezo ={3.0,4.0,5.0};
        
        
        ObjectOutputStream oos = new ObjectOutputStream(
                                 new FileOutputStream("/home/oracle/Desktop/serializable2.dat"));
        ObjectInputStream ois = new ObjectInputStream(
                                 new FileInputStream("/home/oracle/Desktop/serializable2.dat"));
        
        for (int i = 0; i < cod.length; i++) {
            mclase2 m = new mclase2(cod[i],desc[i],prezo[i]);
            oos.writeObject(m);           
        }
            mclase2 n = null;
            oos.writeObject(n);
            oos.close();
            
            n = (mclase2)ois.readObject();
        while(n != null){
            System.out.println(n.toString());
            n= (mclase2)ois.readObject();
            
        }   
        ois.close();
    }
    
}
