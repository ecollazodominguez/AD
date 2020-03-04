/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pvehiculos;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.bson.Document;

/**
 *
 * @author oracle
 */
public class Pvehiculos {

    public static Connection conexion = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            double id = 0d;
            String dni = "";
            String codveh = "";
            int pf = 0;
            Clientes cliente = new Clientes();
            Vehiculos vehiculo = new Vehiculos();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection collection = database.getCollection("vendas");
            getConexion();

            //Interacion vendas MONGODB
            MongoCursor<Document> cursor = (MongoCursor) collection.find().iterator();
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                id = doc.getDouble("_id");
                dni = doc.getString("dni");
                codveh = doc.getString("codveh");
                System.out.println("id: " + id + " dni: " + dni + " codveh: " + codveh);
                
                //OBJECTDB
                EntityManagerFactory emf
                        = Persistence.createEntityManagerFactory("$objectdb/db/vehicli.odb");
                EntityManager em = emf.createEntityManager();

                //Iteracion clientes para recoger nombre con DNI
                TypedQuery<Clientes> query
                        = em.createQuery("SELECT p FROM Clientes p where dni='" + dni + "'", Clientes.class);
                List<Clientes> results = query.getResultList();
                for (Clientes p : results) {
                    cliente.setDni(p.dni);
                    cliente.setNcompras(p.ncompras);
                    cliente.setNomec(p.nomec);
                    System.out.println(cliente.toString());
                }

                //Iteracion Vehiculos para recoger datos con CODVEH
                TypedQuery<Vehiculos> query2
                        = em.createQuery("SELECT p FROM Vehiculos p where codveh='" + codveh + "'", Vehiculos.class);
                List<Vehiculos> results2 = query2.getResultList();
                for (Vehiculos p : results2) {
                    vehiculo.setAnomatricula(p.anomatricula);
                    vehiculo.setCodveh(p.codveh);
                    vehiculo.setNomveh(p.nomveh);
                    vehiculo.setPrezoorixe(p.prezoorixe);
                    System.out.println(vehiculo.toString());
                }

                em.close();
                emf.close();
                //Calculamos o precio final
                if (cliente.getNcompras() == 0) {
                    pf = vehiculo.getPrezoorixe() - ((2019 - vehiculo.getAnomatricula()) * 500);
                } else {
                    pf = vehiculo.getPrezoorixe() - ((2019 - vehiculo.getAnomatricula()) * 500) - 500;
                }
                System.out.println(pf);

                //Inxerimos valores no base de datos ORACLE
                PreparedStatement pst = conexion.prepareStatement("insert into finalveh values(?,?,?,tipo_vehf(?,?))");
                pst.setInt(1, (int) id);
                pst.setString(2, dni);
                pst.setString(3, cliente.getNomec());
                pst.setString(4, vehiculo.getNomveh());
                pst.setInt(5, pf);
                pst.executeQuery();
                System.out.println("Insertado");

            }
            closeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Pvehiculos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

}
