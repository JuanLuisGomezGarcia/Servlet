
package beans;

import static beans.FuncionesEmpleado.enviarEmpleado;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;


@Stateless
public class FuncionesHistorial {
     //(--------------S-E-P-A-R-A-D-O-R----------0-------------F-U-N-C-I-O-N-E-S----------------------)
    

    //ESTA FUNCION DEVUELVE EL RANKING DE HISTORIALES URGENTES
    public static List<Ranking> obtenerRanking(){
        EntityManager em =EntityManagerUtil.getEntityManager();
    String rankingString = "";

        Query q = em.createQuery("SELECT p FROM Empleado p");
        List results = q.getResultList();
        Iterator iterator = results.iterator();
    //Creamos un list donde meteremos las objetos del ranking objeto especifico para esta funcion
    List <Ranking> listaRanking = new ArrayList<>();
    int posicion = 0;
    //Recorremos cada empleado y sacamos su list de historiales el cual recorremos por un for
    //este for contiene un if y cada historial tipo U deja entrar al if aumentando una variable tipo
    //int que acabara siendo uno de los atributos de los objetos ranking
    while (iterator.hasNext()){
    Empleado empleado = (Empleado)iterator.next();
    String nombre = empleado.getNombreusuario();
    int cantidadHistoriales = 0;
    Collection listaHistoriales = empleado.getHistorialCollection();
    Iterator iterator2 = listaHistoriales.iterator();
    while(iterator2.hasNext()){
    Historial historial =(Historial)iterator2.next();    
    if(historial.getTipo().equals("U")){
    cantidadHistoriales++;}}
    Ranking ranking = new Ranking(nombre,cantidadHistoriales);
    //Estos objetos semeten en una LIST de objetos ranking
    listaRanking.add(ranking);}
    //para terminar usamos un collection el cual establecera los valores de mayor a menor
    //Segun su atributo int
    Collections.sort(listaRanking,new Comparator<Ranking>(){    
    public int compare(Ranking ranking1,Ranking ranking2){
    return ranking2.getCantidadHistoriales() - ranking1.getCantidadHistoriales();}});
    for(Ranking ranking : listaRanking){posicion++;
    rankingString = rankingString +"Posicion: " + posicion + " " + ranking.toString();}
    return listaRanking;}  
    
    //(--------------S-E-P-A-R-A-D-O-R----------0-------------F-U-N-C-I-O-N-E-S----------------------)
    
    //ESTA FUNCION MUESTRA EL HISTORIAL DE INICIO SESION MAS RECIENTE    
    public static String devolverRanking(List listaRanking){
    String rankingString = "";
    int posicion = 0;
    //para terminar usamos un collection el cual establecera los valores de mayor a menor
    //Segun su atributo int
    Collections.sort(listaRanking,new Comparator<Ranking>(){    
    public int compare(Ranking ranking1,Ranking ranking2){
    return ranking2.getCantidadHistoriales() - ranking1.getCantidadHistoriales();}});
    for(Object ranking : listaRanking){posicion++;
    rankingString = rankingString +" Posicion: " + posicion + " " + ranking.toString();}
    return rankingString;
    }

        /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    
    //ESTA FUNCION DEVUELVE POSICION EMPLEADO EN EL RANKING
    //Se crea un objeto object donde se almacena la consulta
    public static String posicionRanking(String usuario){
    String resultado = "";
    //Se obtiene el ranking que retorna la funcion anterior
    List<Ranking> listaRanking = obtenerRanking();
    //Se le pideal usuario el nombre de un usuario
    Empleado empleado = enviarEmpleado(usuario);
    int posicion = 0;
    //Este ranking se recorre y se compara con el nombre de usuario pedido
    for(Ranking ranking : listaRanking){
    posicion++;
    //Cuando el nombre introducido cuincida con el nombre de la Lista del ranking
    //devolvera la posiciony los datos
    if(empleado.getNombreusuario().equals(ranking.getNombre())){
    resultado ="Posicion: " + posicion + " " + ranking.toString(); }}
    return resultado;}
    
    //(--------------S-E-P-A-R-A-D-O-R----------0-------------F-U-N-C-I-O-N-E-S----------------------)
    
    //ESTA FUNCION MUESTRA EL HISTORIAL DE INICIO SESION MAS RECIENTE
    public static String mostrarHistorialReciente(String usuario){       
    String historialUltimo = "";
    //Pedimos al usuario que introdusca el nombre del ususario 
    Empleado empleado = enviarEmpleado(usuario); 
    Collection listaHistoriales = empleado.getHistorialCollection();
    Iterator iterator2 = listaHistoriales.iterator();
    while(iterator2.hasNext()){
    Historial historial =(Historial)iterator2.next();   
    if(historial.getTipo().equals("I")){
    historialUltimo = ""; 
    historialUltimo = historial.getFechahora();
    historialUltimo =historialUltimo + historial.getTipo();
    historialUltimo =historialUltimo + historial.getIdevento().toString();}}
    return historialUltimo;} 
    
        /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    //ESTA FUNCION GENERA EL ID
    public static int generarIdHistorial(){
    //Este int nos permite controlar la reaccion de los servlet    
    int clave = 1;
    //inicializamos la session
    EntityManager em =EntityManagerUtil.getEntityManager(); 
    //Sentencia query para seleccionar todas los empleados y poder comparar el nuevo
    //y metemos la consulta realizada en una lista y de hay al iterator
    String ordenSQL = "SELECT p FROM Historial p";
    Query query = em.createQuery(ordenSQL);
    List results = query.getResultList(); 
    // Comparamos el nombre recivido con los existentes
    Iterator iterator = results.iterator();
    while(iterator.hasNext()){   
    Historial historial =(Historial)iterator.next();  
    clave++;}
    em.close();
    return clave;}
    
    //ESTA FUNCION GENERA UN HISTORIA
    public static void generarHistorial(String tipo, Empleado usuario){
    //Una vez creada la incidencia se crea una variable que almacena la fecha y hora y se convierte a string
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String format = dtf.format(LocalDateTime.now());
    Integer id = generarIdHistorial();
    Historial historial = new Historial(id,tipo,format);
    historial.setEmpleado(usuario);
    EntityManager em =EntityManagerUtil.getEntityManager();
    em.getTransaction().begin();
    em.persist(historial);
    em.getTransaction().commit();
    em.close();}
    
    
}
