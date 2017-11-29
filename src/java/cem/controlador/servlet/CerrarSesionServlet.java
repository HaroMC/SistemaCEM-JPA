package cem.controlador.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "CerrarSesionServlet", urlPatterns = {"/salir"})
public class CerrarSesionServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().invalidate();
        LOGGER.info("Sesión finalizada.");
        response.sendRedirect("ingresar");
    }

}
