/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdor5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class Bdor5 {

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

    /*
     a)metodo insireLinea que insira unha linea de pedido pasandolle como minimo os seguintes datos :
     ordnum, linum,item,cantidad,descuento
     probar a inserir unha linea de pedido para o pedido de numero de orden 4001 cos seguintes datos:
     linum: 48
     item : 2004
     cantidad: 20
     descuento: 10
     */
    public static void insertarLinea() {
     try {
         String sql= "INSERT INTO THE (SELECT P.pedido FROM pedido_tab P WHERE P.ordnum = 4001) SELECT 48, REF(S), 20, 10 FROM item_tab S WHERE S.itemnum = 2004";
         Statement st = conexion.createStatement();     
         st.executeUpdate(sql);
     } catch (SQLException ex) {
         Logger.getLogger(Bdor5.class.getName()).log(Level.SEVERE, null, ex);
     }

    }
    
    //    b)
//metodo modificaLinea  que modifique o nome dun cliente pasandolle como minimo o numero do cliente. 
//probar a modificar o nome del cliente 5 para que pase a chamarse'Alvaro Luna'
    
    public static void actualizaLinea(){      
     try {
         String sql= "update cliente_tab set clinomb = 'alvaro luna' where clinum= 5";
         Statement st = conexion.createStatement();
         st.executeUpdate(sql);
     } catch (SQLException ex) {
         Logger.getLogger(Bdor5.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
//    c) metodo borraLinea que pasandolle como minimo o numero de orde dun pedido e un numero de linea, borre dita linea de dito pedido  
//probar a borrar a linea de pedido (linum)  48 do pedido cuxo ordnum e igual a 4001
    
        public static void borraLinea(){      
     try {
         String sql= "delete from the(select p.pedido from pedido_tab p where p.ordnum=4001)where linum=48";
         Statement st = conexion.createStatement();
         st.executeUpdate(sql);
     } catch (SQLException ex) {
         Logger.getLogger(Bdor5.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    


    public static void main(String[] args) throws SQLException {
        getConexion();
        
        actualizaLinea();
//        insertarLinea();
//        borraLinea();
        
        closeConexion();


    }
    
}
