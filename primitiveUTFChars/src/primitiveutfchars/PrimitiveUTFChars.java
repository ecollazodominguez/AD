/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitiveutfchars;

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
public class PrimitiveUTFChars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /* grabar unha mesma cadea  de texto
           tres veces usando dous metodos distintos
           (writeUTF e writeChars) nun ficheiro denominado
           text6.txt para posteriormente recuperalos.
         */
        
        DataInputStream dis = new DataInputStream(
                              new BufferedInputStream(
                              new FileInputStream("/home/oracle/Desktop/texto6.txt")));
        DataOutputStream dos = new DataOutputStream(
                              new BufferedOutputStream(
                              new FileOutputStream("/home/oracle/Desktop/texto6.txt")));

        dos.writeUTF("Está en casa");
        dos.writeChars("Está en case");
        dos.writeUTF("Está en caso");
        dos.close();
        
        //el CURSOR/ITERADOR empieza leyendo la 1º linea UTF
        System.out.println(dis.readUTF());
        //Continua leyendo la línea con unbucle con la longitud de la cadena como limite
            for (int i = 0; i < 12; i++) {
            System.out.print(dis.readChar());
        }
       //Termina leyendo la última linera UTF
       System.out.println("\n"+dis.readUTF());
            dis.close();
    }
}
    

