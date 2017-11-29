package test;

import cem.controlador.servicio.UsuarioServicio;
import cem.modelo.entidad.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

public class Test {

    public static void main(String[] args) {

        String nombreUsuario = "j.perez";
        String contrasena = "cecilia123";
        
        String contrasenaEncriptada = encriptar(contrasena);
        
        System.out.println("Contraseña original: " + contrasena);
        System.out.println("Largo contraseña: " + contrasena.length());
        System.out.println("");
        System.out.println("Contraseña encriptada: " + contrasenaEncriptada);
        System.out.println("Largo contraseña encriptada: " + contrasenaEncriptada.length());
    }

    public static String encriptar(String contrasena) {
        MessageDigest md;
        StringBuilder sb = new StringBuilder();
        String encriptado = " ";
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(contrasena.getBytes());
            byte byteData[] = md.digest();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer
                        .toString((byteData[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            encriptado = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioServicio.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        //return sb.toString();
        return encriptado;
    }
}
