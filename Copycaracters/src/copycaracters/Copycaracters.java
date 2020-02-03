/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copycaracters;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Copycaracters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
            FileWriter fw = new FileWriter("/home/oracle/Desktop/texto11.txt");
        
        FileReader fr = new FileReader("/home/oracle/Desktop/texto10.txt");
        
       
        
        int c = fr.read();
          while(c > -1) {
              fw.write(c);
              c=fr.read();
              
    }
          fr.close();
          fw.close();
    }
    
}
