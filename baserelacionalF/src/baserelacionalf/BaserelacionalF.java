/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionalf;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author oracle
 */
public class BaserelacionalF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        procedemento(3,7);
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
    
    public static void procedemento(int entrada, int saida) throws SQLException{
                CallableStatement stmt = null;
        Connection conn;
        conn = connect();
			stmt = conn.prepareCall("{call pjavaprocoracle(?,?)}");
			stmt.setInt(1, entrada);
			stmt.setInt(2, saida);
                        
    //Registrar el parámetro de salida antes de llamar al procedimiento
			stmt.registerOutParameter(2, java.sql.Types.INTEGER);
			
			stmt.executeUpdate();
			
    //Leer el parámetro devuelto
			String result = stmt.getString(2);
                        System.out.println(result);
    }
}
