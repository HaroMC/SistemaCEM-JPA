package cem.controlador.servlet;

import cem.controlador.servicio.PersonasServicio;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "CemAlumnoServlet", urlPatterns = {"/cem-alumnos"})
public class CemAlumnoServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private PersonasServicio ps;
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        sesion = request.getSession();
        
        if (accion != null) {
            if (accion.compareTo("eliminar") == 0) {
                
            }
        }
        else {
            sesion.setAttribute("listadoAlumnos", request);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
}
