
package servlets;

import static beans.FuncionesEmpleado.eliminarEmpleado;
import static beans.FuncionesEmpleado.verificarEmpleado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EliminarEmpleado", urlPatterns = {"/EliminarEmpleado"})
public class EliminarEmpleado extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
            // Recogemos los datos de la web
            String usuario = request.getParameter("usuario");
            //Introducimos el usuario recogido para verificar que existe
            int claveUsuario = verificarEmpleado(usuario);
            //Introducimos el usuario recogido para verificar que existe
            if(claveUsuario== -1){            
            out.println("<h1>El usuario no existe</h1>"
            +  "<form action=\"eliminarEmpleado.html\" method=\"POST\">"
            + "<p>Volver a intentar</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Aceptar\" />"
            + "</form>");
            out.println("<form action=\"menuEmpleados.html\" method=\"POST\">"
            + "<p>Volver al menu empleados</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Aceptar\" />"
            + "</form>");}
            //En caso que el usuario exista realisamos el proceso
            else{
            eliminarEmpleado(usuario);
            out.println("<h1>Empleado eliminado</h1>"
            + "<form action=\"menuEmpleados.html\" method=\"POST\">"
            + "<p>volver al menu empleados</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Aceptar\" />"
            + "</form>");}}}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
