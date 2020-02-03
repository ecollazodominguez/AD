/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Arquivos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //1) crear o directorio 'arquivosdir' na ruta '/home/oracle/NetBeansProjects/arquivos/' sempre e cando dito directorio non exista.
	// comprobar que a ruta foi creada ou existe mediante o comandos do sistema operativo
        File f = new File("/home/oracle/NetBeansProjects/arquivos/arquivosdir");
        if (!f.isDirectory() && !f.exists()){
            f.mkdir();
        }else{
            System.out.println("\n"+"Ya existe el directorio");
        }
        
        
        //2) crear  o arquivo Products1.txt no directorio mencionado anteriormente (arquivosdir) sempre e cando dito arquivo non exista. 
	 // comprobar que o arquivo foi creado ou existe mediante comandos do sistema operativo
        File f2 = new File("/home/oracle/NetBeansProjects/arquivos/arquivosdir/Products1.txt");
        if (!f2.isFile() && !f2.exists()){
            f2.createNewFile();
        }else{
            System.out.println("\n"+"Ya existe el archivo");
        }
        
      // 3) comprobar que a ruta foi creada mediante o metodo da clase File axeitado 
// comprobar que o arquivo foi creado mediante o metodo da clase File axeitado
        if (f.isDirectory() && f.exists()){
             System.out.println(f.getAbsolutePath());  
        }
        if (f2.isFile() && f2.exists()){
            System.out.println(f2.getAbsolutePath());
        }
        
        //4)crear o directorio 'subdir'  na ruta '/home/oracle/NetBeansProjects/arquivos/arquivosdir/' creada anteriormente
	  // crear o arquivo Products2.txt no directorio mencionado anteriormente (subdir) sempre e cando dito arquivo non exista. 
        
        File f3 = new File("/home/oracle/NetBeansProjects/arquivos/arquivosdir/subdir");
        if (!f3.isDirectory() && !f3.exists()){
            f3.mkdir();
        }else{
            System.out.println("\n"+"Ya existe el directorio2");
        }
        
        File f4 = new File("/home/oracle/NetBeansProjects/arquivos/arquivosdir/subdir/Products2.txt");
        if (!f4.isFile() && !f4.exists()){
            f4.createNewFile();
        }else{
            System.out.println("\n"+"Ya existe el archivo2");
        }
    
    
     	// 5)amosar arquivos e subdirectorios do directorio creado '/home/oracle/NetBeansProjects/arquivos/arquivosdir/'
	 //  utilizar para elo o metodo da clase File axeitado : debería verse o seguinte resultado:
    
        String[] hola = f.list();
        for (int i = 0; i < hola.length; i++) {
           System.out.println("\n"+hola[i]); 
        }
  
    
    	// 6)utilizando  os metodos axeitados amosar a ruta que corresponde  ao primeiro  directorio creado:

           System.out.println(f.getAbsolutePath());
           
        //7)utilizando  os metodos axeitados amosar a seguinte informacion sobre o primeiro obxecto arquivo creado:
	// nome
	// ruta
	// si e posible ou non escribir nel
	// si e posible ou non ler del
	// a sua lonxitude en bytes (para isto escribir no arquivo mediante calquerea editor de texto  ,  o texto 'ola')
           
           f2.getName();
           f2.getAbsolutePath();
           if (f2.canWrite()){
               System.out.println("Se puede escribir");
           } else{
               System.out.println("No se puede");
           }
           if (f2.canRead()){
               System.out.println("Se puede leer");
           } else{
               System.out.println("No se puede leer");
           }
           System.out.println(f2.length()+" bytes");
           
          // 8) forzar a que o mesmo arquivo referido no apartado anterior   sexa de so lectura
	//comprobar dende o sistema operativo que no se pode escribir na da en dito arquivo
           
           f2.setReadOnly();
           
         //  9)forzar a que o arquivo referido no apartado anterior pase de novo a ser de novo de  escritura
	//comprobar dendo o sistema operativo que se pode ler do arquivo
           
           f2.setWritable(true);
           
         //          10)borrar o arquivo referido no apartado anterior
	//comprobar dende o sistema operativo que o arquivo foi borrado
           //f2.delete();
           
        //11)borrar os o resto de arquivos e directorios creados anteriormente 
           //f4.delete();
           //f3.delete();
           //f.delete();
    }
}
