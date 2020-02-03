/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionalb;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author oracle
 */
public class BaserelacionalB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        listaProdutos();
        actualizar();
        añadir();
        listaProdutos();
        borrar();
        listaProdutos();
    }
    
    public static Connection conexion() throws SQLException{
        
        
        String driver = "jdbc:oracle:thin:";
        String host = "localhost.localdomain";
        String porto = "1521";
        String sid = "orcl";
        String usuario = "hr";
        String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
    
    public static void listaProdutos() throws SQLException{
        Connection conn;
        conn = conexion();
        String sql = "SELECT * from produtos";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
            System.out.println("Codigo: " + rs.getString("CODIGO") + " Nome: " + rs.getString("DESCRICION") + " Prezo: " + rs.getInt("PREZO"));
        }
    }
    
    public static void actualizar() throws SQLException{
        Connection conn;
        conn = conexion();
        String sql = "SELECT produtos.* FROM produtos";
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
            if("p2".equals(rs.getString("CODIGO"))){
                rs.updateInt("PREZO", 8);
                rs.updateRow();
            }
        }
    }
    
    public static void añadir() throws SQLException{
       Connection conn;
       conn = conexion();
       String sql = "SELECT produtos.* FROM produtos";
       Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
       ResultSet rs = st.executeQuery(sql);
       rs.moveToInsertRow();
       rs.updateString("CODIGO", "p4");
       rs.updateString("DESCRICION", "martelo");
       rs.updateInt("PREZO", 20);
       rs.insertRow();
    }

    public static void borrar() throws SQLException{
        Connection conn;
        conn = conexion();
        String sql = "SELECT produtos.* FROM produtos";
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
            if("p3".equals(rs.getString("CODIGO"))){
                rs.deleteRow();
            }
        }
    }

}
