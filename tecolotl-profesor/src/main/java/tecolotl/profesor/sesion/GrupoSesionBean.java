package tecolotl.profesor.sesion;

import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.entidad.CicloEscolarEntidad;
import tecolotl.profesor.entidad.CicloEscolarEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.validacion.GrupoLlavePrimariaValidacion;
import tecolotl.profesor.validacion.GrupoNuevoValidacion;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
     * Inserta un nuevo Grupo. La llave del nuevo grupo es pasada al modelo
     * @param grupoModelo datos para insertar el nuevo Grupo.
     */
    public void inserta(@NotNull GrupoModelo grupoModelo){
        logger.fine(grupoModelo.toString());
        validadorSessionBean.validacion(grupoModelo, GrupoNuevoValidacion.class);
        GrupoEntidad grupoEntidad = new GrupoEntidad();
        grupoEntidad.setGrado(grupoModelo.getGrado());
        grupoEntidad.setGrupo(grupoModelo.getGrupo());
        grupoEntidad.setProfesorEntidad(new ProfesorEntidad(grupoModelo.getIdProfesor()));
        entityManager.persist(grupoEntidad);
        grupoModelo.setId(grupoEntidad.getId());
    }

    /**
     * Busca todos los grupos.
     * @return Lista de todos los Grupos.
     */
    public List<GrupoModelo> busca(){
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.busca", GrupoEntidad.class);
        List<GrupoEntidad> grupoEntidadLista = typedQuery.getResultList();
        logger.finer("Numero de grupos encontrados: ".concat(String.valueOf(grupoEntidadLista.size())));
        return grupoEntidadLista.stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos los grupos por ciclo escolar
     * @param inicio
     * @param fin
     * @param claveCentroTrabajo
     * @return
     */
    public List<GrupoModelo> busca(@NotNull Date inicio, @NotNull Date fin, @NotNull String claveCentroTrabajo, @NotNull Integer idProfesor) {
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaCiclioEscolar", GrupoEntidad.class);
        typedQuery.setParameter("inicio", inicio).setParameter("fin", fin)
                .setParameter("claveCentroTrabajo", claveCentroTrabajo).setParameter("idProfesor", idProfesor);
        return typedQuery.getResultList().stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos los grupos perteneciente a un profesor.
     * @return Lista de todos los Grupos.
     */
    public List<GrupoModelo> busca(@NotNull Integer idProfesor){
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaProfesor", GrupoEntidad.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        List<GrupoEntidad> grupoEntidadLista = typedQuery.getResultList();
        logger.finer("Numero de grupos encontrados: ".concat(String.valueOf(grupoEntidadLista.size())));
        return grupoEntidadLista.stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    /**
     * Actualiza la información de un Grupo.
     * @param grupoModelo  datos para poder actualizar el Grupo.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer actualiza(@NotNull @Valid GrupoModelo grupoModelo){
        validadorSessionBean.validacion(grupoModelo, GrupoLlavePrimariaValidacion.class);
        logger.fine(grupoModelo.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<GrupoEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(GrupoEntidad.class);
        Root<GrupoEntidad> root = criteriaUpdate.from(GrupoEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), grupoModelo.getId());
        criteriaUpdate.set(root.get("grupo"), grupoModelo.getGrupo());
        criteriaUpdate.set(root.get("grado"), grupoModelo.getGrado());
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
