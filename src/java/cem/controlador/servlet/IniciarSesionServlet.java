package cem.controlador.servlet;

import cem.controlador.servicio.UsuarioServicio;
import cem.modelo.entidad.Usuario;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "IniciarSesionServlet", urlPatterns = {"/ingresar"})
public class IniciarSesionServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private UsuarioServicio us;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje;
        String nombreUsuario = request.getParameter("nU");
        String contrasena = request.getParameter("c");

        try {
            contrasena = us.encriptar(contrasena);
            Usuario objUsuario = us.validarIngreso(nombreUsuario, contrasena);
            if (objUsuario.getNombre() != null) {
                if (objUsuario.getContrasena().compareTo(contrasena) == 0
                        && objUsuario.getNombre()
                                .compareTo(nombreUsuario) == 0) {
                    sesion = request.getSession(true);
                    sesion.setAttribute("usuarioActual", objUsuario);
                    mensaje = "Bienvenido(a) " + objUsuario.getNombre();
                    sesion.setAttribute("mensajeBienvenida", mensaje);
                    LOGGER.info("Ingreso exitoso.");
                    redirecionarPerfil(response, objUsuario.getPerfil());
                }
            } else {
                mensaje = "Sus credenciales no son válidas.";
                request.setAttribute("mensajeError", mensaje);
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            }
        } catch (NoSuchAlgorithmException ex) {
            // Controlar
        }
    }

    private void redirecionarPerfil(HttpServletResponse response, String perfil)
            throws ServletException, IOException {

        switch (perfil) {
            case "Administrador":
                response.sendRedirect("cem/menu-cem.jsp");
                break;
            case "CEL":
                response.sendRedirect("cel/menu-cel.jsp");
                break;
            case "Alumno":
                response.sendRedirect("alumno/menu-alumno.jsp");
                break;
            case "Familia":
                response.sendRedirect("familia/menu-familia.jsp");
                break;
            default:
                response.sendRedirect("error/no-autorizado.jsp");
        }
    }

}
