
package beans;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.ejb.Stateless;
@Stateless
public class Utilidades {

        /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
    
        //Funcion para pedirr String
        public static String pedirString(){
        String cadenaRetorno="";
        double clave_pedirDouble;
        do{ clave_pedirDouble=0;
        try{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cadenaRetorno= br. readLine();
        clave_pedirDouble=0;
        }catch(Exception e){ clave_pedirDouble=-1; System.out.println("Intentelo de nuevo");}
        }while(! (clave_pedirDouble==0));
        return cadenaRetorno;}    

        /* -----------S-E-P-A-R-A-D-O-R---------((()))-------F-U-N-C-I-O-N-E-S---------------*/
        
        //Fucion parapedir int
        public static int pedirInt(){
        int numeroRetornar=0;
        int clave_pedirInt;
        do{ clave_pedirInt=0;
        try{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String brm = br.readLine();
        numeroRetornar = Integer.parseInt(brm);
        if(numeroRetornar<0){
            System.out.println("Introdusca un valor positivo por favor");
            clave_pedirInt=-1;
        }
        }catch(Exception e){ clave_pedirInt=-1;System.out.println("Introdusca un numero por favor");}
        }while(!(clave_pedirInt==0));
        return numeroRetornar;}   
}
