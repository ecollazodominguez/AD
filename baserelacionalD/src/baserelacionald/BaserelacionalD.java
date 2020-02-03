/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionald;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author oracle
 */
public class BaserelacionalD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
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
        String sql = "select * from produtos";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ResultSetMetaData rsm = rs.getMetaData();
        for(int i = 1 ; i <= rsm.getColumnCount() ; i++){
            System.out.println("Nombre: " + rsm.getColumnName(i) + " Tipo:  " + rsm.getColumnTypeName(i) + "longitud: " + rsm.getColumnDisplaySize(i));
        }
    }
}
