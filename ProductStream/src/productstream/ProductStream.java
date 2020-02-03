/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class ProductStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        DataOutputStream dos = new DataOutputStream(
                              new BufferedOutputStream(
                              new FileOutputStream(("/home/oracle/Desktop/prod.txt"))));

        DataInputStream dis = new DataInputStream(
                              new BufferedInputStream(
                              new FileInputStream(("/home/oracle/Desktop/prod.txt"))));
        
        
        
        Product po1 = new Product("cod1", "parafusos", 3d);
        Product po2 = new Product("cod2", "cravos", 4d);
        
        dos.writeUTF(po1.getCodigo());
        dos.writeUTF(po1.getDescricion());
        dos.writeDouble(po1.getPrezo());
        
        dos.writeUTF(po2.getCodigo());
        dos.writeUTF(po2.getDescricion());
        dos.writeDouble(po2.getPrezo());
        
        dos.close();
        
        Product po3 = new Product();
        
        po3.setCodigo(dis.readUTF());
        po3.setDescricion(dis.readUTF());
        po3.setPrezo(dis.readDouble());
        
        System.out.println(po3.toString());
        
        po3.setCodigo(dis.readUTF());
        po3.setDescricion(dis.readUTF());
        po3.setPrezo(dis.readDouble());
        
        dis.close();
        
        System.out.println(po3.toString());
        
    }
    
}
