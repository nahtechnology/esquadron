package sesion;

import modelo.ProfesorModelo;
import tecolotl.profesor.entidad.ProfesorEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProfesorSesionBean {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ProfesorModelo> busca(){
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.busca", ProfesorEntidad.class);
        List<ProfesorEntidad> profesorEntidadLista = typedQuery.getResultList();
        return profesorEntidadLista.stream().map(ProfesorModelo::new).collect(Collectors.toList());
    }
}
