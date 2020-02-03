/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copybyimaxe2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Copybyimaxe2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
                
//          FileInputStream fis = new FileInputStream("/home/oracle/Desktop/foto.jpg");
//          FileOutputStream fos = new FileOutputStream("/home/oracle/Desktop/foto2.jpg");
//          BufferedInputStream bis = new BufferedInputStream(fis);
//          BufferedOutputStream bos = new BufferedOutputStream(fos);
//        int i= bis.read();
//        while (i != -1){
//        bos.write(i);
//        i = bis.read();
//        }
//                bis.close();
//                        bos.close();
                        
        /**b) executa por segunda vez a aplicacion para que se engada de novo a imaxe foto.jpg  á imaxe foto2.jpg. ¿que ocorre se abrimos a imaxe, vese a imaxe repetida? NON , Canto ocupa agora o arquivo foto2.jpg? O dobre
        */

        FileInputStream fis = new FileInputStream("/home/oracle/Desktop/foto.jpg");
        FileOutputStream fos = new FileOutputStream("/home/oracle/Desktop/foto2.jpg", true);
          BufferedInputStream bis = new BufferedInputStream(fis);
          BufferedOutputStream bos = new BufferedOutputStream(fos);
        int i= bis.read();
        while (i != -1){
        bos.write(i);
        i = bis.read();
        }
                bis.close();
                        bos.close();

    }
    
}
