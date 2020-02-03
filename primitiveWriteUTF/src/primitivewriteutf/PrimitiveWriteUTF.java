/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitivewriteutf;

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
public class PrimitiveWriteUTF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /** consistirá grabar unha mesma cadea  de texto duas 
         * veces usando o metodo writeUTF  nun ficheiro denominado
         * texto3.txt para posteriormente recuperalos (voltalos a ler).
         * */
         
        DataInputStream dis = new DataInputStream(
                              new BufferedInputStream(
                              new FileInputStream("/home/oracle/Desktop/texto3.txt")));
        DataOutputStream dos = new DataOutputStream(
                              new BufferedOutputStream(
                              new FileOutputStream("/home/oracle/Desktop/texto3.txt")));

        dos.writeUTF("O tempo está xélido");
        System.out.println("writeUTF escribiu "+dos.size()+" bytes");
        dos.writeUTF("O tempo está xélida");
        System.out.println("writeUTF escribiu "+dos.size()+" bytes");
                dos.close();
        while (dis.available() > 0){
            System.out.println(dis.readUTF());
            System.out.println("Número de bytes leidos ?");
        }
        dis.close();
    }
}
