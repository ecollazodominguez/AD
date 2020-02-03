package point;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Main {

    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());
        
        //AMOSAR OS ATRIBUTOS DO PUNTO CON ID =10:
        Query q3 = em.createQuery("SELECT p FROM Point p where id=10");
        System.out.println("Atributos: " + q3.getSingleResult());
        
        //Actualizar o punto de id =10 , coordenada y,  ao valor que ten
        //mais 2, e dicir se o atributo y do punto de id=10  valia 4 ,
        //debe pasar a valer 6.

        Point p2 = em.find(Point.class, 10);

        em.getTransaction().begin();
        p2.setY(p2.getY()+2);
        em.getTransaction().commit();
        System.out.println("Actualizando id 10");
        
        // eliminar punto de id=5 
        
        p2 = em.find(Point.class, 5);

        em.getTransaction().begin();
        em.remove(p2);
        em.getTransaction().commit();
        System.out.println("Borrando id 5");
        
        // actualizacion masiva selectiva : 
        //actualizar coordenada y ao valor 1000 para todos
        //os puntos que teñan un  valor de y  inferior
        //a un valor pasado por parametro (por
        //exemplo facelo para o valor  6)
        
       em.getTransaction().begin();
       Query q4 = em.createQuery(
      "UPDATE Point SET y = 1000 WHERE y < :p",Point.class);
       int updateCount = q4.setParameter("p", 6).executeUpdate();
        System.out.println("filas modificadas: "+updateCount);
       em.getTransaction().commit();
       
        //borrado masivo selectivo masivo(delete queries).
        //Eliminar todos os puntos cuxo valor do atributo 
        //y sexa inferior a un valor pasado por parametro
        //( por exemplo facelo para o valor 8 )
        
       em.getTransaction().begin();
       Query q5 = em.createQuery(
      "DELETE FROM Point WHERE y < :p");
  int deletedCount = q5.setParameter("p", 8).executeUpdate();
        System.out.println("filas eliminadas: "+deletedCount);
       em.getTransaction().commit();
        
        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query =
            em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        // Close the database connection:
        em.close();
        emf.close();
    }
}