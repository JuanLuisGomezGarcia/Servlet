
package beans;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
     
    public static EntityManager getEntityManager(){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PracticaFInalPU");
        EntityManager manager = emf.createEntityManager();
        
        return manager;
    }
//public static void main (String[] args){}
    



}
