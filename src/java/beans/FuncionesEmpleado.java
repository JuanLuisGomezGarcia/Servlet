
package beans;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
@Stateless
public class FuncionesEmpleado {

    
    //ESTA FUNCION DEVUELVE UNA LISTA DE LOS EMPLEADOS EXISTENTES
    public static String listaEmpleado(){
    
        String listaEmpleados = "";
        EntityManager em =EntityManagerUtil.getEntityManager();


        Query q = em.createQuery("SELECT p FROM Empleado p");
        List results = q.getResultList();
        Iterator iterator = results.iterator();
        while(iterator.hasNext()){   
        Empleado empleado =(Empleado)iterator.next();
        listaEmpleados = listaEmpleados + "\n" + empleado.getNombreusuario();}
        return listaEmpleados;}
  
    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    
    //ELIMINAR EMPLEADO
    public static void eliminarEmpleado(String nombreUsuario){
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager();
    //Generamos la sentencia query
    String ordenSQL = "FROM Empleado m WHERE m.nombreusuario=:nombreusuario";
    //y a traves de un set conseguimos el usuarios y traves del iteratos lo recuperamos
    //en forma de objeto y lo eliminamos con el metodo remove
    Query query = em.createQuery(ordenSQL);
    query.setParameter("nombreusuario", nombreUsuario);
    List results = query.getResultList(); 
    Iterator iterator = results.iterator();
    Empleado empleado =(Empleado)iterator.next();
    em.getTransaction().begin();
    em.remove(empleado);
    em.getTransaction().commit();
    em.close();}
    
    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    
    //MODIFICAR EMPLEADO
    public static void modificarEmpleado(String nombreUsuario, String nuevaContraseña, String nuevoTelefono, String nuevoNombreCompleto){
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager(); 
    //Generamos la sentencia query
    String ordenSQL = "FROM Empleado m WHERE m.nombreusuario=:nombreusuario";
    //y a traves de un set conseguimos el usuarios y traves del iteratos lo recuperamos
    //en forma de objeto le introducimos la nueva contraseña y lo pasamos a la base de datos
    Query query = em.createQuery(ordenSQL);
    query.setParameter("nombreusuario", nombreUsuario);
    List results = query.getResultList(); 
    Iterator iterator = results.iterator();
    Empleado empleado =(Empleado)iterator.next(); 
    empleado.setPassword(nuevaContraseña);
    empleado.setNombrecompleto(nuevoNombreCompleto);
    empleado.setTelefono(nuevoTelefono);
    em.getTransaction().begin();
    em.persist(empleado);
    em.getTransaction().commit();
    em.close();}
    
    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    
    //MODIFICAR PASSWORD
    public static void modificarPassword(String nombreUsuario, String nuevaContraseña){
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager(); 
    //Generamos la sentencia query
    String ordenSQL = "FROM Empleado m WHERE m.nombreusuario=:nombreusuario";
    //y a traves de un set conseguimos el usuarios y traves del iteratos lo recuperamos
    //en forma de bjeto le introducimos la nueva contraseña y lo pasamos a la base de datos
    Query query = em.createQuery(ordenSQL);
    query.setParameter("nombreusuario", nombreUsuario);
    List results = query.getResultList(); 
    Iterator iterator = results.iterator();
    Empleado empleado =(Empleado)iterator.next(); 
    empleado.setPassword(nuevaContraseña);
    em.getTransaction().begin();
    em.persist(empleado);
    em.getTransaction().commit();
    em.close();}

    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    //ESTA FUNCION DEVUELVE UN EMPLEADO
        //FUNCION QUE MUESTRA SI UN EMPLEADO EXISTE O NO
    public static Empleado enviarEmpleado(String nombreUsuarioSeleccinado){
    //Este int nos permite controlar la reaccion de los servlet    
    Empleado empleadoEnviar = new Empleado();
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager(); 
    //Sentencia query para seleccionar todas los empleados y poder comparar el nuevo
    //y metemos la consulta realizada en una lista y de hay al iterator
    String ordenSQL = "SELECT p FROM Empleado p";
    Query query = em.createQuery(ordenSQL);
    List results = query.getResultList(); 
    // Comparamos el nombre recivido con los existentes
    Iterator iterator = results.iterator();
    while(iterator.hasNext()){   
    Empleado empleado =(Empleado)iterator.next();  
    if(nombreUsuarioSeleccinado.equals(empleado.getNombreusuario())){ empleadoEnviar = empleado;}}
    em.close();
    return empleadoEnviar;}
    
    //FUNCION QUE MUESTRA SI UN EMPLEADO EXISTE O NO
    public static int verificarEmpleado(String nombreUsuarioSeleccinado){
    //Este int nos permite controlar la reaccion de los servlet    
    int clave = -1;

    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager(); 
    //Sentencia query para seleccionar todas los empleados y poder comparar el nuevo
    //y metemos la consulta realizada en una lista y de hay al iterator
    String ordenSQL = "SELECT p FROM Empleado p";
    Query query = em.createQuery(ordenSQL);
    List results = query.getResultList(); 
    // Comparamos el nombre recivido con los existentes
    Iterator iterator = results.iterator();
    while(iterator.hasNext()){   
    Empleado empleado =(Empleado)iterator.next();  
    if(nombreUsuarioSeleccinado.equals(empleado.getNombreusuario())){ clave = 0;}}
    em.close();
    return clave;}
   
    //FUNCION PARA VERIFICAR EL PASSWORD
    public static int verificarPassword(String contraseñaRecivida){
    //Este int nos permite controlar la reaccion de los servlet
    int clave = -1;
    if(contraseñaRecivida.isEmpty()){}else{
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager(); 
    //Sentencia query para seleccionar todas los empleados y poder comparar el nuevo
    //y metemos la consulta realizada en una lista y de hay al iterator
    String ordenSQL = "SELECT p FROM Empleado p";
    Query query = em.createQuery(ordenSQL);
    List results = query.getResultList(); 
    // Comparamos el nombre recivido con los existentes
    Iterator iterator = results.iterator();
    while(iterator.hasNext()){   
    Empleado empleado =(Empleado)iterator.next();  
    if(contraseñaRecivida.equals(empleado.getPassword())){clave = 0;}}
    em.close();}
    return clave;}  
   
    /*-----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
   
    //FUNCION PARA INSERTAR EMPLEADO
    public static int insertarEmpleado(Empleado empleado)throws Exception{
    //Este int nos permite controlar la reaccion de los servlet
    int clave = 0;
    //Comprovamos el usuario y password para que no cuincidan y si no cuinciden introducimos el empleado
    String nombreUsuario = empleado.getNombreusuario();
    int claveUsuario = verificarEmpleado(nombreUsuario);
    if (claveUsuario == 0){clave = 0;}
    else{
    //Inicialisamos la sesion y introducimos el nuevo empleado en la base de datos
    EntityManager em =EntityManagerUtil.getEntityManager();
    em.getTransaction().begin();
    em.persist(empleado);
    em.getTransaction().commit();
    em.close();clave=-1;}
    return clave;}
}
    
    
    
    



