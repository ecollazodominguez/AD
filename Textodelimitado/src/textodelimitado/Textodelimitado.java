/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textodelimitado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author oracle
 */
public class Textodelimitado {

    public static void main(String[] args) throws IOException {
       
        String[] cod={"p1","p2","p3"};
        String[] desc ={"parafusos","cravos","tachas"};
        Double[] prezo ={3d,4d,5d};
        
        PrintWriter pw = new PrintWriter(
                         new BufferedWriter(
                         new FileWriter("/home/oracle/Desktop/textodelimitado.txt")));
        
        BufferedReader br = new BufferedReader(
                            new FileReader("/home/oracle/Desktop/textodelimitado.txt"));
        
        for (int i = 0; i < 3; i++) {
            pw.print(cod[i] + "\t");
            pw.print(desc[i] + "\t");
            pw.println(prezo[i]);            
        }
        pw.close();
        
        String[] prod1 = br.readLine().split("\t");
        Product p1 = new Product(prod1[0],prod1[1],Double.parseDouble(prod1[2]));
        
        System.out.println(p1.toString());
        
        String[] prod2 = br.readLine().split("\t");
        Product p2 = new Product(prod2[0],prod2[1],Double.parseDouble(prod2[2]));
        
        System.out.println(p2.toString());
        
        String[] prod3 = br.readLine().split("\t");
        Product p3 = new Product(prod3[0],prod3[1],Double.parseDouble(prod3[2]));
        
        System.out.println(p3.toString());
        
        br.close();
    }
    
}
