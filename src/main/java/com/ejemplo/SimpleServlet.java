package com.ejemplo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/simple")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Aplicación Simple para Jenkins y Tomcat</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>¡Hola desde mi aplicación Java desplegada con Jenkins en Tomcat!</h1>");
            out.println("<p>La fecha y hora actual es: " + new java.util.Date() + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}