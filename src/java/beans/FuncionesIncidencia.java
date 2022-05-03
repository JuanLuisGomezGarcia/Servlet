
package beans;

import static beans.FuncionesEmpleado.enviarEmpleado;
import static beans.FuncionesEmpleado.verificarEmpleado;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;


@Stateless
public class FuncionesIncidencia {

    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/

    //ESTA FUNCION MUESTRA LA INCIDENCIA INDICADA POR EL ID
    public static String incidencaID(String idSelecionado){
    //Se genera el string a devolver
    String incidenciaString = "";
    //controlamos la exepcion que se provocara en caso de que no se introdusca un numero y devolveremos null
    try{
    int id = Integer.parseInt(idSelecionado);
    int clave = generarIdIncidencia();
    //y controlamos en caso de que no exista el id
    if(clave > id){
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager();
    //Generamos la sentencia query
    String ordenSQL = "FROM Incidencia m WHERE m.idincidencia=:idincidencia";
    //y a traves de un set conseguimos el usuarios y traves del iteratos lo recuperamos
    //en forma de objeto y lo eliminamos con el metodo remove
    Query query = em.createQuery(ordenSQL);
    query.setParameter("idincidencia", id); 
    List results = query.getResultList(); 
    Iterator iterator = results.iterator();
    Incidencia incidencia =(Incidencia)iterator.next();
    incidenciaString = "\n" + "Fecha hora: " + incidencia.getFechahora()
        + "\n" + "Tipo: " + incidencia.getTipo()
        + "\n" + "Origen: " + incidencia.getOrigen()
        + "\n" + "Destino: " + incidencia.getDestino()
        + "\n" + "Detalle: " + incidencia.getDetalle()
        + "\n" + "ID: " + incidencia.getIdincidencia();
    em.close();}else{incidenciaString = "null";}
    }catch(Exception e){incidenciaString = "null";}
    return incidenciaString;}
    
    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/

    //ESTA FUNCION MUESTRA LA LISTA DE INCIDENCIA
    public static String listaIncidencias(){
        //Generamos el string que acumulara las incidencias
        String listaIncidencias = "";
        //Iniciamos secion
        EntityManager em =EntityManagerUtil.getEntityManager();
        //Generamos la consulta que recorrera las incidencias
        //Y las recorremos y almacenamos en un list
        Query q = em.createQuery("SELECT p FROM Incidencia p");
        List results = q.getResultList();
        Iterator iterator = results.iterator();
        while(iterator.hasNext()){   
        Incidencia incidencia =(Incidencia)iterator.next();
        listaIncidencias = listaIncidencias + "\n" + "Fecha hora: " + incidencia.getFechahora()
        + "\n" + "Tipo: " + incidencia.getTipo()
        + "\n" + "Origen: " + incidencia.getOrigen()
        + "\n" + "Destino: " + incidencia.getDestino()
        + "\n" + "Detalle: " + incidencia.getDetalle()
        + "\n" + "ID: " + incidencia.getIdincidencia()
        + "-----------S-E-P-A-R-A-D-O-R---------((()))-------I-N-C-I-D-E-N-C-I-A-S---------------";}
        return listaIncidencias;}
    
    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/

    //ESTA FUNCION OBTIENE LAS INCIDENCIA DE ORIGEN USUARIO
    public static String obtenerIncidenciaOrigenUsuario(String nombreUsuario){
    //Conseguimos un objeto empleado
    Empleado empleado = enviarEmpleado(nombreUsuario);
    String listaIncidencias = "";
    //Sacamos su coleecion de incidencias destino la recorremos y la pasamos a un string
    Collection listaincidencias = empleado.getIncidenciaCollection();
    Iterator iterator2 = listaincidencias.iterator();
        while(iterator2.hasNext()){   
        Incidencia incidencia =(Incidencia)iterator2.next();
        listaIncidencias = listaIncidencias + "\n" + "Fecha hora: " + incidencia.getFechahora()
        + "\n" + "Tipo: " + incidencia.getTipo()
        + "\n" + "Origen: " + incidencia.getOrigen()
        + "\n" + "Destino: " + incidencia.getDestino()
        + "\n" + "Detalle: " + incidencia.getDetalle()
        + "\n" + "ID: " + incidencia.getIdincidencia()
        + "-----------S-E-P-A-R-A-D-O-R---------((()))-------I-N-C-I-D-E-N-C-I-A-S---------------";}
    return listaIncidencias;}
   
    //ESTA FUNCION OBTIENE LAS INCIDENCIA DE DESTINO USUARIO
    public static String obtenerIncidenciaDestinoUsuario(String nombreUsuario){
    //Conseguimos un objeto empleado
    Empleado empleado = enviarEmpleado(nombreUsuario);
    String listaIncidencias = "";
    //Sacamos su coleecion de incidencias destino la recorremos y la pasamos a un string
    Collection listaincidencias = empleado.getIncidenciaCollection1();
    Iterator iterator2 = listaincidencias.iterator();
        while(iterator2.hasNext()){   
        Incidencia incidencia =(Incidencia)iterator2.next();
        listaIncidencias = listaIncidencias + "\n" + "Fecha hora: " + incidencia.getFechahora()
        + "\n" + "Tipo: " + incidencia.getTipo()
        + "\n" + "Origen: " + incidencia.getOrigen()
        + "\n" + "Destino: " + incidencia.getDestino()
        + "\n" + "Detalle: " + incidencia.getDetalle()
        + "\n" + "ID: " + incidencia.getIdincidencia()
        + "-----------S-E-P-A-R-A-D-O-R---------((()))-------I-N-C-I-D-E-N-C-I-A-S---------------";}
    return listaIncidencias;}
    
    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    //ESTA FUNCION GENERA EL ID
    public static int generarIdIncidencia(){
    //Este int nos permite controlar la reaccion de los servlet    
    int clave = 1;
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager(); 
    //Sentencia query para seleccionar todas los empleados y poder comparar el nuevo
    //y metemos la consulta realizada en una lista y de hay al iterator
    String ordenSQL = "SELECT p FROM Incidencia p";
    Query query = em.createQuery(ordenSQL);
    List results = query.getResultList(); 
    // Comparamos el nombre recivido con los existentes
    Iterator iterator = results.iterator();
    while(iterator.hasNext()){   
    Incidencia incidencia =(Incidencia)iterator.next();  
    clave++;}
    em.close();
    return clave;}
   
    /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
   
    //ESTA FUNCION NOS PERMITE INSERTAR TODOS LOS DATOS DE LA INCIDENCIA
    public static int insertarIncidencia(Incidencia incidencia, String origen, String destino){
    //Este int nos permite controlar la reaccion de los servlet
    int clave = 0;
    Empleado empleadoOrigen = enviarEmpleado(origen);
    Empleado empleadoDestino = new Empleado(destino);
    //Comprovamos el usuario y password para que no cuincidan y si no cuinciden introducimos el empleado
    int claveOrigen = verificarEmpleado(origen);
    int claveDestino = verificarEmpleado(destino);
    if (claveOrigen == -1 || claveDestino == -1 ){clave = -1;}
    else{
        incidencia.setOrigen(empleadoOrigen);
        incidencia.setDestino(empleadoDestino);
    //Inicialisamos la sesion y introducimos el nuevo empleado en la base de datos
    EntityManager em =EntityManagerUtil.getEntityManager();
    em.getTransaction().begin();
    em.persist(incidencia);
    em.getTransaction().commit();
    em.close();}
    return clave;}

}