/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exac;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author oracle
 */
public class ExaC {

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
    
    public static void inserirXerados() throws FileNotFoundException, IOException, SQLException{
                //ACCESO AL FICHERO DELIMITADO:
                FileReader fr = new FileReader("/home/oracle/Desktop/compartido/analisis.txt");
                BufferedReader br1 = new BufferedReader(fr);

                String[] arrayDatos;
                String linea;
                 Analisis ana = new Analisis();
                 
                //Datos a almacenar
                 String codigoA;
                 String nomeUva="";
                 int acidez;
                 int acidezMin =0;
                 int acidezMax =0;
                 int total;
                 String acidezTexto="";
                
                
                while((linea=br1.readLine())!=null){
                    
                  arrayDatos=linea.split(",");
                  
                  ana.setCodigoa(arrayDatos[0]);
                  ana.setAcidez(Integer.parseInt(arrayDatos[1]));
                  ana.setGrao(Integer.parseInt(arrayDatos[2]));
                  ana.setTaninos(Integer.parseInt(arrayDatos[3]));
                  ana.setTipoUva(arrayDatos[4]);
                  ana.setCantidade(Integer.parseInt(arrayDatos[5]));
                  ana.setDni(arrayDatos[6]);
                  
                    System.out.println(ana);
                    
                    //Tenemos código y acidez
                    codigoA= ana.getCodigoa();
                    acidez= ana.getAcidez();
                    
                    //Buscamos o nome da uva e a sua acidez minima e máxima
                    
                    PreparedStatement pst1 = conexion.prepareStatement("select nomeu,acidezmin,acidezmax from uvas where tipo = ?");
                    
                    pst1.setString(1, ana.getTipoUva());
                   
                    ResultSet rs1 = pst1.executeQuery();
                    
                    while(rs1.next()){
                        nomeUva=rs1.getString("nomeu");
                        acidezMin=rs1.getInt("acidezmin");
                        acidezMax=rs1.getInt("acidezmax");
                    }
                    
                    //Comprobamos acidez
                    if (acidez < acidezMin){
                        acidezTexto= "Subir acidez";
                    } else if (acidez > acidezMax){
                        acidezTexto= "Baixar acidez";
                    } else if (acidez >= acidezMin && acidez <= acidezMax){
                        acidezTexto= "Acidez correcta";
                    }
                    
                    //calculamos o total
                    
                    total= ana.getCantidade()*15;
                    
                    //Inxerimos os datos en XERADO
                    
            PreparedStatement pst2 = conexion.prepareStatement("insert into xerado values(?,?,?,?)");
            pst2.setString(1, codigoA);
            pst2.setString(2, nomeUva);
            pst2.setString(3, acidezTexto);
            pst2.setInt(4, total);

            pst2.executeQuery();

            System.out.println("CAMPO INSERTADO !");
            
            //APARTADO B: AUMENTAR EL NUMERO DE ANALISIS REALIZADOS EN TABLA CLIENTES
            //UPDATE DE LA TABLA CLIENTES:  (asumimos que la clave primaria es el dni)
            
            PreparedStatement pst3 = conexion.prepareStatement("UPDATE clientes SET NUMERODEANALISIS = (NUMERODEANALIS +1) WHERE DNI =(?)");
            
            pst3.setString(1, ana.getDni());
            
            pst3.executeUpdate();
                    
                }
                br1.close();
    }
    
    public static void main(String[] args) throws SQLException, IOException {
        getConexion();
        inserirXerados();
        closeConexion();
    }
    
}
