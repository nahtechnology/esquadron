package tecolotl.profesor.sesion;

import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.entidad.CicloEscolarEntidad;
import tecolotl.profesor.entidad.CicloEscolarEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.validacion.GrupoLlavePrimariaValidacion;
import tecolotl.profesor.validacion.GrupoNuevoValidacion;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class GrupoSesionBean implements Serializable {

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
        grupoEntidad.setCicloEscolarEntidad(new CicloEscolarEntidad(new CicloEscolarEntidadPK(
                grupoModelo.getCicloEscolarModelo().getInicio(),
                grupoModelo.getCicloEscolarModelo().getFin(),
                grupoModelo.getCicloEscolarModelo().getIdEscuela())));
        entityManager.persist(grupoEntidad);
        grupoModelo.setId(grupoEntidad.getId());
    }

    /**
     * Busca los datos de un grupo
     * @param idGrupo
     * @return
     */
    public GrupoModelo buscaId(@NotNull UUID idGrupo, @NotNull UUID idProfesor) {
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaGrupoPorProfesor", GrupoEntidad.class);
        typedQuery.setParameter("idGrupo", idGrupo);
        typedQuery.setParameter("idProfesor", idProfesor);
        List<GrupoEntidad> grupoEntidad = typedQuery.getResultList();
        if (!grupoEntidad.isEmpty()){
            GrupoModelo grupoModelo = new GrupoModelo();
            grupoModelo.setId(grupoEntidad.get(0).getId());
            grupoModelo.setGrado(grupoEntidad.get(0).getGrado());
            grupoModelo.setGrupo(grupoEntidad.get(0).getGrupo());
            grupoModelo.setIdProfesor(grupoEntidad.get(0).getProfesorEntidad().getId());
            grupoModelo.setCicloEscolarModelo(new CicloEscolarModelo(grupoEntidad.get(0).getCicloEscolarEntidad()));
            return grupoModelo;
        }else{
            return null;
        }
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
     * @param inicio Fecha inicio del ciclo escolar
     * @param fin Fecha final del ciclo escolar
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela
     * @return Colección de {@link GrupoModelo}
     */
    public List<GrupoModelo> busca(@NotNull Date inicio, @NotNull Date fin, @NotNull String claveCentroTrabajo, @NotNull UUID idProfesor) {
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaCiclioEscolar", GrupoEntidad.class);
        typedQuery.setParameter("inicio", inicio).setParameter("fin", fin)
                .setParameter("claveCentroTrabajo", claveCentroTrabajo).setParameter("idProfesor", idProfesor);
        return typedQuery.getResultList().stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos los grupos por ciclo escolar contando
     * @param inicio Fecha inicio del ciclo escolar
     * @param fin Fecha final del ciclo escolar
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela
     * @return Colección de {@link GrupoModelo}
     */
    public List<GrupoModelo> buscaTotalAlumno(@NotNull Date inicio, @NotNull Date fin, @NotNull String claveCentroTrabajo, @NotNull UUID idProfesor) {
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaCiclioEscolarTotalAlumno", GrupoEntidad.class);
        logger.info("");
        typedQuery.setParameter("inicio", inicio).setParameter("fin", fin)
                .setParameter("claveCentroTrabajo", claveCentroTrabajo).setParameter("idProfesor", idProfesor);
        List<GrupoEntidad> grupoEntidadLista = typedQuery.getResultList();
        List<GrupoModelo> grupoModeloLista = new ArrayList<>(grupoEntidadLista.size());
        for (GrupoEntidad grupoEntidad : grupoEntidadLista) {
            GrupoModelo grupoModelo = new GrupoModelo(grupoEntidad);
            grupoModelo.setTotalAlumno(grupoEntidad.getTotalAlumno());
            grupoModeloLista.add(grupoModelo);
        }
        return grupoModeloLista;
    }

    /**
     * Busca todos los grupos perteneciente a un profesor.
     * @return Lista de todos los Grupos.
     */
    public List<GrupoModelo> busca(@NotNull UUID idProfesor){
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaProfesor", GrupoEntidad.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        List<GrupoEntidad> grupoEntidadLista = typedQuery.getResultList();
        logger.finer("Numero de grupos encontrados: ".concat(String.valueOf(grupoEntidadLista.size())));
        return grupoEntidadLista.stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos los grupos perteneciente a un profesor.
     * @param cicloEscolarModelo ciclo escolar
     * @param idProfesor profesor
     * @return Lista de todos los Grupos.
     */
    public List<GrupoModelo> busca(@NotNull UUID idProfesor, @NotNull CicloEscolarModelo cicloEscolarModelo) {
        TypedQuery<GrupoEntidad> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaProfesorCicloEscolar", GrupoEntidad.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        typedQuery.setParameter("inicio", cicloEscolarModelo.getInicio());
        typedQuery.setParameter("fin", cicloEscolarModelo.getFin());
        typedQuery.setParameter("claveCentroTrabajo", cicloEscolarModelo.getIdEscuela());
        return typedQuery.getResultList().stream().map(GrupoModelo::new).collect(Collectors.toList());
    }

    /**
     * Valida si esciste el alumno dentro de una escuela
     * @param claveCentroTrabajo escuela
     * @param apodo apodo del alumno
     * @return
     */
    public boolean existeAlumno(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo,
                                @NotNull @Size(min = 4, max = 40) String apodo) {
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("GrupoEntidad.buscaAlumnoApodo", Long.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo).setParameter("apodo", apodo);
        return typedQuery.getSingleResult().intValue() == 1;
    }

    /**
     * Valida si existe el alumno o profesor
     * @param apodo Apodoa del profesor o del alumno.
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela.
     * @return true en case de ser xistir, false en cualquien otro caso
     */
    public boolean existeAlumnoProfesor(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo,
                                       @NotNull @Size(min = 4, max = 40) String apodo) {
        TypedQuery<Long> typedQuery = entityManager.createQuery(
                "SELECT count(p.id) + count(a.id) FROM GrupoEntidad g JOIN g.grupoAlumnoEntidad ga JOIN ga.grupoAlumnoEntidadPK.alumnoEntidad a JOIN " +
                "g.profesorEntidad p WHERE (p.apodo = :apodo OR a.apodo = :apodo) AND p.escuelaEntidad.claveCentroTrabajo = :claveCentroTrabajo", Long.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo).setParameter("apodo", apodo);
        return typedQuery.getSingleResult().intValue() >= 1;
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
    public Integer elimina(@NotNull UUID idGrupo){
        logger.fine(idGrupo.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete criteriaDelete = criteriaBuilder.createCriteriaDelete(GrupoEntidad.class);
        Root<GrupoEntidad> root = criteriaDelete.from(GrupoEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idGrupo));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    /**
     * Busca si el profesor tiene asigando el grupo.
     * @param idProfesor Identificador del profesor.
     * @param idGrupo Identificador del grupo.
     * @return True en caso de que concidan, false en cualquier otro caso.
     */
    public boolean pertenece(@NotNull UUID idProfesor, @NotNull UUID idGrupo) {
        TypedQuery<UUID> typedQuery = entityManager.createNamedQuery("GrupoEntidad.cuentaPorProfesor", UUID.class);
        typedQuery.setParameter("idGrupo", idGrupo);
        try {
            return typedQuery.getSingleResult().compareTo(idProfesor) == 0;
        } catch (NoResultException e) {
            return false;
        }
    }

    /**
     * Busca el total de grupos que contiene una escuela
     * @param idProfesor Identificador del profesor
     * @return Total de alumno
     */
    public Long totalPorProfesor(@NotNull UUID idProfesor) {
        return entityManager.createNamedQuery("GrupoEntidad.totalGrupo", Long.class)
                .setParameter("idProfesor", idProfesor).getSingleResult();
    }

    /**
     * Actualiza un grupo con los profesores
     * @param idGrupo Identificador del grupo
     * @param idProfesor Identificador del profesor
     * @return Número de elementos modificados
     */
    public Integer reasignar(@NotNull UUID idGrupo, @NotNull UUID  idProfesor, @NotNull CicloEscolarModelo cicloEscolarModelo) {
        Query query = entityManager.createNamedQuery("GrupoEntidad.cambiaProfesor");
        query.setParameter("idGrupo", idGrupo);
        query.setParameter("idProfesor", idProfesor);
        query.setParameter("escuela", cicloEscolarModelo.getIdEscuela());
        query.setParameter("inicio", cicloEscolarModelo.getInicio());
        query.setParameter("fin", cicloEscolarModelo.getFin());
        return query.executeUpdate();
    }
}
