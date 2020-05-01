package tecolotl.profesor.sesion;

import tecolotl.profesor.entidad.ActividadEntidad;
import tecolotl.profesor.modelo.ActividadModelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class TareasAlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ActividadModelo> busca(@NotNull UUID idGrupo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM profesor.busca_tareas(?)", ActividadEntidad.class);
        query.setParameter(1, idGrupo);
        return ((List<ActividadEntidad>)query.getResultList()).stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

    public List<ActividadModelo> buscaAlumno(@NotNull UUID idAlumno, Short nivelLenguaje) {
        TypedQuery<tecolotl.alumno.entidad.ActividadEntidad> typedQuery =
                entityManager.createNamedQuery("ActividadEntidad.buscaNoAsigandasAlumno", tecolotl.alumno.entidad.ActividadEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        typedQuery.setParameter("nivelLenguaje", nivelLenguaje);
        return typedQuery.getResultList().stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

}
