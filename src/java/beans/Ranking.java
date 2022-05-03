
package beans;

public class Ranking {
    //Creamos los atributos de la tabla
    String nombre;
    int cantidadHistoriales;
    
    //oCreamos el constructor vacio y otro con los atributos del objeto.
    public Ranking (String nombre, int cantidadHistoriales){
    this.nombre = nombre;
    this.cantidadHistoriales = cantidadHistoriales;}
    
    // Getter y Setter de la clase
    public String getNombre(){
    return nombre;}
    public void serNombre(String nombre){
    this.nombre = nombre;}
    public int getCantidadHistoriales(){
    return cantidadHistoriales;}
    public void setCantidadHistoriales(){
    this.cantidadHistoriales = cantidadHistoriales;}
    
    //Metodo toString
    public String toString(){
    return "Nombre: " + nombre + ". Numero de historiales urgentes: " + cantidadHistoriales;}}