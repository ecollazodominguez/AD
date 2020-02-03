/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copybytestexto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Copybytestexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        /** 1)crea a man cun editor de texto simple (un editor de texto plano de Centos e “gedit” por exemplo) un pequeno ficheiro de texto chamado texto1.txt con tres lineas :
ola
adeus
cecais
* */
        
        /** 2_1)Desenvolve unha pequena aplicacion que usando somente estas duas clases e os metodos indicados  copie  byte a byte o contido do  ficheiro de texto chamado texto1.txt noutro ficheiro chamado texto2.txt
         * */
        
//        FileInputStream fis = new FileInputStream("/home/oracle/Desktop/texto1.txt");
//        FileOutputStream fos = new FileOutputStream("/home/oracle/Desktop/texto2.txt");
//        int i= fis.read();
//        while (i != -1){
//        fos.write(i);
//        i = fis.read();
//        }
//                fis.close();
//                        fos.close();
                        
        /**2_2) Fai a modifica necesaria na aplicacion anterior para que se engada de novo o texto do ficheiro texto1.txt ao ficheiro texto2.txt
         * */


        FileInputStream fis = new FileInputStream("/home/oracle/Desktop/texto1.txt");
        FileOutputStream fos = new FileOutputStream("/home/oracle/Desktop/texto2.txt");
        int i= fis.read();
        while (i != -1){
        fos.write(i);
            System.out.println(i);
        i = fis.read();
        }
                fis.close();
                        fos.close();

    }
    
}
