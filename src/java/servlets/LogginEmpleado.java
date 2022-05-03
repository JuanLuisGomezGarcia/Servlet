/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Empleado;
import static beans.FuncionesEmpleado.enviarEmpleado;
import static beans.FuncionesEmpleado.verificarEmpleado;
import static beans.FuncionesEmpleado.verificarPassword;
import static beans.FuncionesHistorial.generarHistorial;
import beans.Historial.*;
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
@WebServlet(name = "LogginEmpleado", urlPatterns = {"/LogginEmpleado"})
public class LogginEmpleado extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String usuario = request.getParameter("usuario");
            String contraseña = request.getParameter("contrasena");
            int claveUsuario = verificarEmpleado(usuario);
            int claveContraseña = verificarPassword(contraseña);
           if(claveContraseña == -1 || claveUsuario== -1){            
            out.println("<h1>Contraseña o usuario incorectos</h1>"
            +  "<form action=\"logginEmpleado.html\" method=\"POST\">"
            + "<p>Volver a intentar</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Aceptar\" />"
            + "</form>");
            out.println("<form action=\"menuEmpleados.html\" method=\"POST\">"
            + "<p>Volver al menu empleados</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Aceptar\" />"
            + "</form>");}
           else{Empleado empleado = enviarEmpleado(usuario);
            generarHistorial("I",empleado);
            out.println("<h1>Loggin realizado</h1>"
            + "<form action=\"menuEmpleados.html\" method=\"POST\">"
            + "<p>Loggin realizado</p>"
            + "<input type=\"submit\" name=\"volver\" value=\"Aceptar\" />"
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
