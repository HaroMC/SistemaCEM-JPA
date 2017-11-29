package cem.controlador.servicio;

import cem.modelo.entidad.Programa;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProgramaServicio {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Programa> listarProgramas() {
        return em.createNamedQuery("Programa.findAll", Programa.class)
                .getResultList();
    }
    
    public List<Programa> listarProgramasPorEstado(String estado) {
        return em.createNamedQuery("Programa.findByEstado", Programa.class)
                .setParameter("estado", estado)
                .getResultList();
    }
    
    public BigDecimal codigoAutoIncremental() {
        return (BigDecimal) em.createNamedQuery(
                "Programa.ultimoCodigo", BigDecimal.class);
    }
    
    public void registrarPrograma(Programa objPrograma) {
        em.persist(objPrograma);
    }
    
    public void eliminarPrograma(BigDecimal codigo) {
        em.remove(buscarProgramaPorCodigo(codigo));
    }
    
    public Programa buscarProgramaPorCodigo(BigDecimal codigo) {
        return em.find(Programa.class, codigo);
    }
    
}
