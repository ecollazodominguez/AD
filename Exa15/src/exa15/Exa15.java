package exa15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Exa15 {

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

    public static int composicion(String codp) throws SQLException {
        int graxa;
        int grcomp;
        int grtotal = 0;

        //Consultamos la lista composicion a través del CODP recibido
        String sql = "SELECT CODC, PESO from composicion WHERE CODP = ?";
        PreparedStatement pst = conexion.prepareStatement(sql);
        pst.setString(1, codp);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            //obtenemos a graxa a traves del método COMPONENTES que recibe CODC de la consulta
            graxa = componentes(rs.getString("CODC"));
            //obtenemos a graxa do componente coa graxa o PESO obtenido da consulta /100
            grcomp = graxa * rs.getInt("PESO") / 100;
            //obtenemos a graxa total sumando a graxa dos componentes;
            grtotal += grcomp;

            //Mostramos valores
            System.out.println("Código do componente: " + rs.getString("CODC") + " -> graxa por cada 100g = " + graxa);
            System.out.println("Peso: " + rs.getInt("PESO"));
            System.out.println("Total de graxa por componente = " + grcomp + "\n");
        }
        System.out.println("TOTAL EN GRAXAS DO PLATO: " + grtotal + "\n");

        //devolvemos a graxa total para poder escribirla en el XML
        return grtotal;
    }

    public static int componentes(String codc) throws SQLException {
        int graxa = 0;
        //Consultamos a lista COMPONENTES a través del CODC recibido
        String sql = "SELECT GRAXA from componentes WHERE CODC = ?";
        PreparedStatement pst = conexion.prepareStatement(sql);
        pst.setString(1, codc);
        ResultSet rs = pst.executeQuery();

        //Metemos o valor de graxa recibido nunha variable é a devoltamos.
        while (rs.next()) {
            graxa = rs.getInt("GRAXA");
        }

        return graxa;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException, XMLStreamException {
        getConexion();
        Platos plat;
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("platoss"));
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = xof.createXMLStreamWriter(new FileWriter("/home/oracle/Desktop/totalgraxas.xml"));

        //Leemos el archivo y metemos nun objeto Platos os valores
        plat = (Platos) ois.readObject();

        xsw.writeStartDocument("1.0");
        xsw.writeStartElement("Platos");
        while (plat != null) {
            //Mostramos os Objetos Platos do archivo
            System.out.print(plat.toString());

            //empezamos a escribir o XML
            xsw.writeStartElement("Plato");
            //Collemos o codigo directamente do objeto
            xsw.writeAttribute("Codigop", plat.getCodigop());
                xsw.writeStartElement("nomep");
            //Collemos o nome directamente do objeto
                    xsw.writeCharacters(plat.getNomep());
                xsw.writeEndElement();
                xsw.writeStartElement("graxatotal");
            //Collemos a graxa total do metodo composicion, que a sua vez
            //fará todas as operacións de mostrado.
                    xsw.writeCharacters(String.valueOf(composicion(plat.getCodigop())));
                xsw.writeEndElement();
            xsw.writeEndElement();

            plat = (Platos) ois.readObject();
        }

        //cerramos todo
        xsw.writeEndElement();
        xsw.close();
        ois.close();
        closeConexion();

    }
}
