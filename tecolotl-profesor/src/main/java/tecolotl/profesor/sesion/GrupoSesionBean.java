package tecolotl.profesor.sesion;

import com.sun.javafx.scene.EnteredExitedHandler;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class GrupoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private LoggerProducer logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    public void inserta(@NotNull GrupoModelo grupoModelo){
        GrupoEntidad grupoEntidad = new GrupoEntidad();
        grupoEntidad.setGrupo(grupoModelo.getGrupo());
        grupoEntidad.setGrado(grupoModelo.getGrado());
        grupoEntidad.setProfesorEntidad(new ProfesorEntidad(grupoModelo.getProfesorModelo().getId()));
        entityManager.persist(grupoEntidad);
    }

    public List<GrupoModelo> busca(){
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.busca", GrupoEntidad.class);
        List<GrupoEntidad> grupoEntidadLista = typedQuery.getResultList();
        return grupoEntidadLista.stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    public Integer actualiza(@NotNull GrupoModelo grupoModelo){
        validadorSessionBean.validacion(grupoModelo, GrupoProfesorValidacion.class);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate criteriaUpdate = criteriaBuilder.createCriteriaUpdate(GrupoEntidad.class);
        Root<GrupoEntidad> root = criteriaUpdate.from(GrupoEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), grupoModelo.getId());
        criteriaUpdate.set(root.get("grupo"), grupoModelo.getGrupo());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    public Integer elimina(@NotNull Integer idGrupo){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete criteriaDelete = criteriaBuilder.createCriteriaDelete(GrupoEntidad.class);
        Root<GrupoEntidad> root = criteriaDelete.from(GrupoEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idGrupo));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
