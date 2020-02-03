/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author oracle
 */
public class Aleatorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String[] codes = {"p1", "p2", "p3"};
        String[] descricion = {"parafusos", "cravos ", "tachas"};
        int[] prices = {3, 4, 5};
        Product p2 = new Product();

        RandomAccessFile raf = new RandomAccessFile("/home/oracle/Desktop/textoaleatorio.txt", "rw");

        for (int i = 0; i < 3; i++) {
            String cadea = String.format("%-" + 3 + "s", codes[i]) + String.format("%-" + 10 + "s", descricion[i]);
            raf.writeChars(cadea);
            raf.writeInt(prices[i]);

        }
        String cadena = "";
        raf.seek(30);

        for (int i = 0; i < 3; i++) {
            cadena = cadena + Character.toString(raf.readChar());
            p2.setCodigo(cadena.replace(" ", ""));
        }
        cadena = "";
        for (int i = 0; i < 10; i++) {
            cadena = cadena + Character.toString(raf.readChar());
            p2.setDescricion(cadena.replace(" ", ""));
        }
        p2.setPrezo((double) raf.readInt());

        System.out.print(p2.toString());

        raf.close();
    }

}
