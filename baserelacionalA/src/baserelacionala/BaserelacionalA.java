/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author oracle
 */
public class BaserelacionalA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
       insireProducto("p1","parafusos",3);
       insireProducto("p2","cravos",4);
       insireProducto("p3","tachaas",6);
        actualizaPre(10,"p1");
         listaProdutos();
//       borrafila("p2");
       amosarFila("p3");
    }
    
    public static Connection connect() throws SQLException{
         String driver = "jdbc:oracle:thin:";
          String  host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
           String porto = "1521";
           String sid = "orcl";
           String usuario = "hr";
           String password = "hr";
           String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }
    
    public static void insireProducto(String codigo, String descricion, int prezo) throws SQLException{
        Connection conn;
        conn = connect();
        String sql= "INSERT into produtos VALUES('"+codigo+"','"+descricion+"',"+prezo+")";
        Statement st =conn.createStatement();
        st.executeUpdate(sql);
        
    }
    
    public static void listaProdutos() throws SQLException{
        Connection conn;
        conn = connect(); 
        String sql= "SELECT * from produtos";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            System.out.println(rs.getString("codigo")+", "+ rs.getString("descricion")+", "+rs.getInt("prezo"));
        }
    }
    public static void actualizaPre(int prezonovo, String codigo) throws SQLException{
      Connection conn;
        conn = connect();      
        String sql= "UPDATE produtos set prezo = ? where codigo = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, prezonovo);
        pst.setString(2, codigo);
        pst.executeUpdate();
    }
    
    public static void borrafila(String codigo) throws SQLException{
        Connection conn;
        conn = connect();      
        String sql= "delete produtos where codigo = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, codigo);
        pst.executeUpdate();
    }
    
    public static void amosarFila(String codigo) throws SQLException{
        Connection conn;
        conn = connect();      
        String sql= "SELECT * from produtos where codigo = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, codigo);
        ResultSet rs =pst.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("codigo")+", "+ rs.getString("descricion")+", "+rs.getInt("prezo"));
        }
    }
    
}
