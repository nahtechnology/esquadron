package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.vista.BuscaActividadesVistaEntidad;
import tecolotl.alumno.entidad.vista.TareasPendientesGrupoVistaEntidad;
import tecolotl.profesor.entidad.ActividadEntidad;
import tecolotl.profesor.modelo.ActividadModelo;
import tecolotl.profesor.modelo.TareasPendientesGrupoModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class TareasAlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    public List<ActividadModelo> busca(@NotNull UUID idGrupo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM profesor.busca_tareas(?)", ActividadEntidad.class);
        query.setParameter(1, idGrupo);
        return ((List<ActividadEntidad>)query.getResultList()).stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

    public List<ActividadModelo> buscaAlumno(@NotNull UUID idAlumno) {
        TypedQuery<BuscaActividadesVistaEntidad> typedQuery =
                entityManager.createNamedQuery("BuscaActividadesVistaEntidad.buscaAlumno", BuscaActividadesVistaEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        return typedQuery.getResultList().stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca tareas pendientes por grupo
     * @param igGrupo Identificador del grupo
     * @return Coleccion de tareas perdientes
     */
    public TareasPendientesGrupoModelo buscaTareasPendientes(@NotNull UUID igGrupo) {
        TypedQuery<TareasPendientesGrupoVistaEntidad> typedQuery =
                entityManager.createNamedQuery("TareasPendientesGrupoVistaEntidad.buscaPorGrupo", TareasPendientesGrupoVistaEntidad.class);
        typedQuery.setParameter("idGrupo", igGrupo);
        return new TareasPendientesGrupoModelo(typedQuery.getSingleResult());
    }

}
