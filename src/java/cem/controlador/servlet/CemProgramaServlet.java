package cem.controlador.servlet;

import cem.controlador.servicio.ProgramaServicio;
import cem.modelo.entidad.Programa;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "CemProgramaServlet", urlPatterns = {"/cem-programas"})
public class CemProgramaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private ProgramaServicio ps;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        sesion = request.getSession();

        if (accion != null) {
            if (accion.compareTo("eliminar") == 0) {

                LOGGER.info("Programa eliminado correctamente.");
                request.getRequestDispatcher("cem/ver-programas.jsp")
                        .forward(request, response);
            }
        } else {
            sesion.setAttribute("listadoProgramas",
                    ps.listarProgramas());
            LOGGER.info("Visualización de todos los programas registrados.");
            request.getRequestDispatcher("cem/ver-programas.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        Programa objPrograma;

        switch (accion) {

            case "agregar":
                objPrograma = definirPrograma(
                        request.getParameter("nombrePrograma"),
                        request.getParameter("tipoDuracion"),
                        request.getParameter("valor"),
                        request.getParameter("asignatura1"),
                        request.getParameter("asignatura2"),
                        request.getParameter("asignatura3")
                );
                if (objPrograma != null) {
                    ps.registrarPrograma(objPrograma);
                    LOGGER.info("Registro exitoso.");
                    request.setAttribute("resultado", "Programa registrado "
                            + "correctamente.");
                } else {
                    request.setAttribute("resultado", "Se ha producido un "
                            + "error al registrar.");
                }
                request.getRequestDispatcher("cem/agregar-programa.jsp")
                        .forward(request, response);
                break;

            case "modificar":

        }
    }

    private Programa definirPrograma(String nombrePrograma, String tipoDuracion,
            String valor, String asignatura1, String asignatura2,
            String asignatura3) {

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = Calendar.getInstance();

        Programa objPrograma = new Programa(
                ps.codigoAutoIncremental(),
                nombrePrograma,
                Integer.parseInt(valor),
                "Sin CEL asignado"
        );

        int dia, mes, ano;
        Date fechaInicio, fechaTermino;
        switch (tipoDuracion.toLowerCase()) {
            case "normal":
                try {
                    if (Calendar.MONTH >= Calendar.AUGUST) {
                        ano = calendario.get(Calendar.YEAR) + 1;
                    } else {
                        ano = calendario.get(Calendar.YEAR);
                    }
                    dia = 10;
                    mes = calendario.get(Calendar.AUGUST);
                    fechaInicio = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);
                    mes = calendario.get(Calendar.OCTOBER);
                    fechaTermino = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);
                    objPrograma.setFechaInicio(fechaInicio);
                    objPrograma.setFechaTermino(fechaTermino);
                } catch (ParseException ex) {
                    // Controlar
                }
                break;

            case "corto":
                try {
                    dia = 15;
                    mes = calendario.get(Calendar.JANUARY);
                    ano = calendario.get(Calendar.YEAR) + 1;
                    fechaInicio = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);
                    mes = calendario.get(Calendar.FEBRUARY);
                    fechaTermino = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);
                    objPrograma.setFechaInicio(fechaInicio);
                    objPrograma.setFechaTermino(fechaTermino);
                } catch (ParseException ex) {
                    // Controlar
                }
        }

        if (!asignatura1.isEmpty()) {

        }
        if (!asignatura2.isEmpty()) {

        }
        if (!asignatura3.isEmpty()) {

        }

        return objPrograma;
    }

}
