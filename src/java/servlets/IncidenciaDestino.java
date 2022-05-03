/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Empleado;
import static beans.FuncionesEmpleado.enviarEmpleado;
import static beans.FuncionesEmpleado.verificarEmpleado;
import static beans.FuncionesHistorial.generarHistorial;
import static beans.FuncionesIncidencia.obtenerIncidenciaDestinoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Atenea
 */
@WebServlet(name = "IncidenciaDestino", urlPatterns = {"/IncidenciaDestino"})
public class IncidenciaDestino extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String usuario = request.getParameter("usuario");
            //Creamos un empleado con dichos datos y lo enviamos a la funcion que gestionara
            //su ingreso en la base de datos y si existen ya dichos datos
            int clave = verificarEmpleado(usuario);           
            //Este if genera dos opciones segun exista ya o no el usuario
            if (clave == -1){out.println("<h1>Usuario no encontrado</h1>"
            + "<form action=\"incidenciaDestino.html\" method=\"POST\">"
            + "<p>Volver a intentar</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Clik\" />"
            + "</form>");
            out.println("<form action=\"menuIncidencias.html\" method=\"POST\">"
            + "<p>Volver al menu de incidencias</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Clik\" />"
            + "</form>");}
            else{Empleado empleado = enviarEmpleado(usuario);
            generarHistorial("C",empleado);
            String incidenciaResultado = obtenerIncidenciaDestinoUsuario(usuario);
            out.println("<h1>Incidencia encontrada</h1>"
            + "<p>" + incidenciaResultado + "</p>"
            + "<form action=\"menuIncidencias.html\" method=\"POST\">"
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
