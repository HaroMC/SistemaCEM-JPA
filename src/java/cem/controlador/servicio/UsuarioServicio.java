package cem.controlador.servicio;

import cem.modelo.entidad.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioServicio {

    @PersistenceContext
    private EntityManager em;
    
    /**
     * Método que encripta la contraseña con el algoritmo MD5 para guardarla con
     * mayor seguridad en la base de datos.
     * @param contrasena La contraseña que será encriptada.
     * @return La nueva contraseña encriptada.
     * @throws NoSuchAlgorithmException 
     */
    public String encriptar(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(contrasena.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(
                    Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                            .substring(1)
            );
        }
        return sb.toString();
    }
    
    /**
     * Método que valida que la combinación de usuario y contraseña enviadas por
     * parámetro exista en la base de datos.
     * @param nombreUsuario Nombre de la cuenta de usuario.
     * @param contrasena Contraseña de la cuenta de usuario.
     * @return Un objeto <code>Usuario</code> si es que se encuentra.
     */
    public Usuario validarIngreso(String nombreUsuario, String contrasena) {
        Usuario objUsuario = new Usuario();
        try {
            objUsuario = em.createNamedQuery("Usuario.validarIngreso",
                    Usuario.class)
                    .setParameter("nombre", nombreUsuario)
                    .setParameter("contrasena", contrasena)
                    .getSingleResult();
        } catch (Exception ex) {
            // Controlar
        }
        return objUsuario;
    }
    
    /**
     * Método que retorna la cuenta de usuario asociada al rut enviado por
     * parámetro.
     * @param rut Identificador único de la persona dueña de la cuenta.
     * @return Un objeto <code>Usuario</code> con los datos de la cuenta.
     */
    public Usuario buscarCuentaPorRut(String rut) {
        return (Usuario) em.createNamedQuery(
                "Usuario.buscarCuentaPorRut", Usuario.class)
                .setParameter("rutPersona", rut);
    }
}
