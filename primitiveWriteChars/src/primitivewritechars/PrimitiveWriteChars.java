/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivewritechars;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class PrimitiveWriteChars {

    /**
     * @param args the command line arguments
     */;
        
    
public static void main(String[] args) throws FileNotFoundException, IOException {
        /** cgrabar unha mesma cadea  de texto
         * duas veces usando o metodo 
         * writeChars(String) nun ficheiro
         * denominado text4.txt para posteriormente
         * recuperalas (voltalas a ler) mediante o
         * metodo readChar().
         * */
         
        DataInputStream dis = new DataInputStream(
                              new BufferedInputStream(
                              new FileInputStream("/home/oracle/Desktop/texto4.txt")));
        DataOutputStream dos = new DataOutputStream(
                              new BufferedOutputStream(
                              new FileOutputStream("/home/oracle/Desktop/texto4.txt")));

        dos.writeChars("O tempo está xélido");
        System.out.println("writeChars escribiu "+dos.size()+" bytes");
        dos.writeChars("O tempo está xélido");
        System.out.println("writeChars escribiu "+dos.size()+" bytes");
                dos.close();
        while (dis.available() > 0){
            if (dis.available() == 38){
                System.out.print(" ");
            }
            System.out.print(dis.readChar());
        }
        dis.close();
        
    }
    
}

