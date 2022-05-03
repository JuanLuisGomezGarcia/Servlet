/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import beans.Empleado;
import static beans.FuncionesEmpleado.insertarEmpleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Atenea
 */
@WebServlet(name = "InsertarEmpleado", urlPatterns = {"/InsertarEmpleado"})
public class InsertarEmpleado extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            //Recogemos los datos de la web
            String usuario = request.getParameter("usuario");
            String nombreCompleto = request.getParameter("nombreCompleto");
            String contraseña = request.getParameter("contrasena");
            String telefono = request.getParameter("telefono");
            //Creamos un empleado con dichos datos y lo enviamos a la funcion que gestionara
            //su ingreso en la base de datos y si existen ya dichos datos
            Empleado empleado = new Empleado(usuario,contraseña,nombreCompleto,telefono);
            int clave = insertarEmpleado(empleado);
            //Este if genera dos opciones segun exista ya o no el usuario
            if (clave==0){out.println("<h1>Error al insertar nuevo usuario</h1>"
            + "<form action=\"insertarEmpleado.html\" method=\"POST\">"
            + "<p>Volver a intentar</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Clik\" />"
            + "</form>");
            out.println("<form action=\"menuEmpleados.html\" method=\"POST\">"
            + "<p>Volver al menu empleados</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Clik\" />"
            + "</form>");}
            else{out.println("<h1>Nuevo usuario insertado</h1>"
            + "<form action=\"menuEmpleados.html\" method=\"POST\">"
            + "<p>Volver a la pagina inicial</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Clik\" />"
            + "</form>");}
        }
    }

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(InsertarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
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
