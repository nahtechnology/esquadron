package tecolotl.profesor.sesion;

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
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class GrupoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;


    /**
     * Inserta un nuevo Grupo.
     * @param grupoModelo datos para insertar el nuevo Grupo.
     */
    public void inserta(@NotNull GrupoModelo grupoModelo){
        logger.fine(grupoModelo.toString());
        GrupoEntidad grupoEntidad = new GrupoEntidad();
        grupoEntidad.setGrupo(grupoModelo.getGrupo());
        grupoEntidad.setGrado(grupoModelo.getGrado());
        grupoEntidad.setInicio(grupoModelo.getInicio());
        grupoEntidad.setFin(grupoModelo.getFin());
        grupoEntidad.setProfesorEntidad(new ProfesorEntidad(grupoModelo.getProfesorModelo().getId()));
        entityManager.persist(grupoEntidad);
    }

    /**
     * Busca un Grupo.
     * @return Lista de todos los Grupos.
     */
    public List<GrupoModelo> busca(){
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.busca", GrupoEntidad.class);
        List<GrupoEntidad> grupoEntidadLista = typedQuery.getResultList();
        logger.finer("Numero de grupos encontrados: ".concat(String.valueOf(grupoEntidadLista.size())));
        return grupoEntidadLista.stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    /**
     *  Actualiza la información de un Grupo.
     * @param grupoModelo  datos para poder actualizar el Grupo.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer actualiza(@NotNull GrupoModelo grupoModelo){
        validadorSessionBean.validacion(grupoModelo, GrupoProfesorValidacion.class);
        logger.fine(grupoModelo.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate criteriaUpdate = criteriaBuilder.createCriteriaUpdate(GrupoEntidad.class);
        Root<GrupoEntidad> root = criteriaUpdate.from(GrupoEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), grupoModelo.getId());
        criteriaUpdate.set(root.get("grupo"), grupoModelo.getGrupo());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * Elimina un Grupo.
     * @param idGrupo id del Grupo a eliminar.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer elimina(@NotNull Integer idGrupo){
        logger.fine(idGrupo.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete criteriaDelete = criteriaBuilder.createCriteriaDelete(GrupoEntidad.class);
        Root<GrupoEntidad> root = criteriaDelete.from(GrupoEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idGrupo));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
