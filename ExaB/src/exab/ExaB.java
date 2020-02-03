/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author oracle
 */
public class ExaB {

        public static Connection conexion = null;

    public static Connection getConexion() throws SQLException {
        String usuario = "hr";
        String password = "hr";
        String host = "localhost";
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;

        conexion = DriverManager.getConnection(ulrjdbc);
        return conexion;
    }

    public static void closeConexion() throws SQLException {
        conexion.close();
    }
    
    public static void crearSerializado() throws FileNotFoundException, XMLStreamException, IOException, SQLException {

        //CREAMOS EL SERIALIZADO
        FileOutputStream fichR = new FileOutputStream("/home/oracle/Desktop/platosSerializados.txt");
        ObjectOutputStream fichrOOS = new ObjectOutputStream(fichR);

        //ACCESO AL XML
        File fich1 = new File("/home/oracle/Desktop/compartido/platos.xml");

        FileReader fich1FR = new FileReader(fich1);

        XMLInputFactory xmlIF = XMLInputFactory.newInstance();
        XMLStreamReader xmlSR = xmlIF.createXMLStreamReader(fich1FR);

        //DATOS A ESCRIBIR
        String codp = null;
        String nombreP = null;
        int grasa = 0;
        int grasaTotal = 0;

        //DATOS A RECOGER
        String codc = "";
        int peso = 0;

        while (xmlSR.hasNext()) {
            if (xmlSR.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (xmlSR.getLocalName().equals("Plato")) {
                    codp= xmlSR.getAttributeValue(0);
                } else if (xmlSR.getLocalName().equals("nomep")) {
                    nombreP= xmlSR.getElementText();
            }
            
        }
            
            //COMPROBAMOS SI YA ESTÁN PUESTAS TODAS LAS VARIABLES DEL XML
            //SI ES EL CASO, COMENZAMOS A BUSCAR LAS QUE QUEDAN PARA CREAR
            //EL OBJETO
            if ((codp != null) && (nombreP != null)) {

                //ACCESO AL FICHERO DELIMITADO:
                //OJO!! TENEMOS QUE LEER TODO EL FICHERO PARA CADA PLATO, POR ESO HACE FALTA CREAR UN BUFFEREDREADER
                //CADA VEZ QUE ENTRAMOS EN ESTE IF Y EN ESTE BUCLE !!!
                FileReader readFile1 = new FileReader("/home/oracle/Desktop/compartido/composicion.txt");
                BufferedReader bufferRead1 = new BufferedReader(readFile1);

                String[] arrayDatos;
                String linea;
                
                while ((linea=bufferRead1.readLine()) != null){
                    
                    arrayDatos = linea.split("#");
                    
                 if(arrayDatos[0].equals(codp)){
                     
                     codc= arrayDatos[1];
                     peso= Integer.parseInt(arrayDatos[2]);
                     
                     //YA TENEMOS CODP,NOMBRE, CODC Y Y PESO
                    //OJO, EN CONPOSICIÓN LA PRIMARYKEY SE COMPONE DE CODP Y CODC
                     //ACCESO A LA TABLA COMPONENTES:
                        PreparedStatement psmt = conexion.prepareStatement("select graxa from componentes where codc = ?");
                        
                        psmt.setString(1,codc);
                        
                        ResultSet rs1 = psmt.executeQuery();
                        
                         rs1.next();
                        //VAMOS ACUMULANDO LA GRASA TOTAL
                        grasa = rs1.getInt(1);

                        grasaTotal += (grasa * peso) / 100;

                    }
                    //UNA VEZ ACABADO CON ESTE CODP, HAY QUE COMPROBAR SI SIGUE HABIENDO LÍNEAS EN EL FICHERO
                    //QUE TENGAN ESE MISMO CODP
}

                //COMPROBACIÓN FINAL
                System.out.println("FINAL: " + codp + " " + nombreP + " " + " " + codc + " " + peso + " " + grasaTotal);

                //CUANDO ACABEMOS DE LEER TODO LO RELACIONADO CON ESE CODP, AÑADIMOS LOS OBJETOS AL FICHERO SERIALIZADO
                Platos objP = new Platos(codp, nombreP, grasaTotal);
                System.out.println(objP.toString());

                fichrOOS.writeObject(objP);
                //REINICIAR LAS VARIABLES UNA VEZ ACABEMOS DE LEER TODO UN CODP
                codp = null;
                nombreP = null;

                bufferRead1.close();
                readFile1.close();
            }

            xmlSR.next();

        }
        //PARA PODER LEER EL SERIALIZADO, METEMOS UN NULL AL FINAL

        fichrOOS.writeObject(null);

        fich1FR.close();
        fichR.close();
        fichrOOS.close();

    }

               
    
    public static void main(String[] args) throws SQLException, XMLStreamException, IOException {
        
        getConexion();
        crearSerializado();
        closeConexion();
        
    }
    
}
