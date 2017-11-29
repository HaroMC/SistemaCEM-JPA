package cem.controlador.servicio;

import cem.modelo.entidad.Alumno;
import cem.modelo.entidad.CentroEstudiosLocal;
import cem.modelo.entidad.Docente;
import cem.modelo.entidad.FamiliaAnfitriona;
import cem.modelo.entidad.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonasServicio {
        
    @PersistenceContext
    private EntityManager em;
    
    /*public List<Persona> listarPersonasAlumnos() {
        Persona objPersona;
        em.
    }*/
    
    public List<Persona> listarPersonas() {
        return em.createNamedQuery("Persona.findAll", Persona.class)
                .getResultList();
    }
    
    public List<CentroEstudiosLocal> listarCel() {
        return em.createNamedQuery(
                "CentroEstudiosLocal.findAll", CentroEstudiosLocal.class)
                .getResultList();
    }
    
    public List<Alumno> listarAlumnos() {
        return em.createNamedQuery("Alumno.findAll", Alumno.class)
                .getResultList();
    }
    
    public List<Docente> listarDocentes() {
        return em.createNamedQuery("Docente.findAll", Docente.class)
                .getResultList();
    }
    
    public List<FamiliaAnfitriona> listarFamilias() {
        return em.createNamedQuery(
                "FamiliaAnfitriona.findAll", FamiliaAnfitriona.class)
                .getResultList();
    }
    
    public FamiliaAnfitriona buscarFamilia(String rut) {
        return em.find(FamiliaAnfitriona.class, rut);
    }
    
    public Docente buscarDocente(String rut) {
        return em.find(Docente.class, rut);
    }
    
    public CentroEstudiosLocal buscarCel(String rut) {
        return em.find(CentroEstudiosLocal.class, rut);
    }
    
    public Alumno buscarAlumno(String rut) {
        return em.find(Alumno.class, rut);
    }
    
    public Persona buscarPersona(String rut) {
        return em.find(Persona.class, rut);
    }
        
    public void eliminarPersona(String rut) {
        em.remove(buscarPersona(rut));
    }
    
}
