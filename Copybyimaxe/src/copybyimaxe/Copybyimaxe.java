/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copybyimaxe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Copybyimaxe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /** a) partindo do que aprendiches no exercicio anterior ( copybytestexto ) saberias copiar  o contido dun arquivo .jpg noutro ? . Comprobao copiando a  imaxe foto.jpg que se achega  nun arquivo que se denomine foto2.jpg . Canto ocupa foto2.jpg ? 2,5M ocupa o mesmo que a imaxe orixinal ? SI
        
//            FileInputStream fis = new FileInputStream("/home/oracle/Desktop/foto.jpg");
//          FileOutputStream fos = new FileOutputStream("/home/oracle/Desktop/foto2.jpg");
//        int i= fis.read();
//        while (i != -1){
//        fos.write(i);
//        i = fis.read();
//        }
//                fis.close();
//                        fos.close();
                        
        /**b) executa por segunda vez a aplicacion para que se engada de novo a imaxe foto.jpg  á imaxe foto2.jpg. ¿que ocorre se abrimos a imaxe, vese a imaxe repetida? NON , Canto ocupa agora o arquivo foto2.jpg? O dobre
        */

        FileInputStream fis = new FileInputStream("/home/oracle/Desktop/foto.jpg");
        FileOutputStream fos = new FileOutputStream("/home/oracle/Desktop/foto2.jpg", true);
        int i= fis.read();
        while (i != -1){
        fos.write(i);
        i = fis.read();
        }
                fis.close();
                        fos.close();

    }
    
}
