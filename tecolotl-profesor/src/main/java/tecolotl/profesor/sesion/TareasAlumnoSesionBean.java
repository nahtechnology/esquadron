package tecolotl.profesor.sesion;

import tecolotl.profesor.entidad.ActividadEntidad;
import tecolotl.profesor.modelo.ActividadModelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TareasAlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ActividadModelo> busca(@NotNull Integer idGrupo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM profesor.busca_tareas(?)", ActividadEntidad.class);
        query.setParameter(1, idGrupo);
        return ((List<ActividadEntidad>)query.getResultList()).stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

}
