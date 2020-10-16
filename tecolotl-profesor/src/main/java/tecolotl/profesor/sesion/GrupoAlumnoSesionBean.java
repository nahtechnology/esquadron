package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.profesor.entidad.GrupoAlumnoEntidad;
import tecolotl.profesor.entidad.GrupoAlumnoEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.TareaAlumnoGrupoEntidad;
import tecolotl.profesor.modelo.AlumnoTareasNivelModelo;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.modelo.PromedioAlumnoModelo;
import tecolotl.profesor.modelo.TareaAlumnoGrupoModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class GrupoAlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    /**
     * Inserta un nuevo GrupoAlumno.
     * @param grupoAlumnoModelo dato para poder insertar un nuecvo GrupoAlumno.
     */
    public void inserta(@NotNull GrupoAlumnoModelo grupoAlumnoModelo){
        logger.info(grupoAlumnoModelo.toString());
        GrupoAlumnoEntidadPK grupoAlumnoEntidadPK = new GrupoAlumnoEntidadPK();
        grupoAlumnoEntidadPK.setAlumnoEntidad(new AlumnoEntidad(grupoAlumnoModelo.getIdAlumno()));
        grupoAlumnoEntidadPK.setGrupoEntidad(new GrupoEntidad(grupoAlumnoModelo.getIdGrupo()));
        GrupoAlumnoEntidad grupoAlumnoEntidad = new GrupoAlumnoEntidad();
        grupoAlumnoEntidad.setGrupoAlumnoEntidadPK(grupoAlumnoEntidadPK);
        entityManager.persist(grupoAlumnoEntidad);
    }

    /**
     * Busca un GrupoAlumno.
     * @return una Lista de GrupoAlumno.
     */
    public List<GrupoAlumnoModelo> busca(){
        TypedQuery<GrupoAlumnoEntidad> typedQuery = entityManager.createNamedQuery("GrupoAlumnoEntidad.busca", GrupoAlumnoEntidad.class);
        List<GrupoAlumnoEntidad> grupoAlumnoEntidadLista = typedQuery.getResultList();
        logger.finer("Numero de profesores encontrados: ".concat(String.valueOf(grupoAlumnoEntidadLista.size())));
        return grupoAlumnoEntidadLista.stream().map(GrupoAlumnoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos las tareas asigandas y realizadas de los alumnos asignados de un grupo
     * @param idGrupo Identificador del grupo
     * @return Colección de {@link TareaAlumnoGrupoModelo}
     */
    public List<TareaAlumnoGrupoModelo> busca(@NotNull UUID idGrupo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM profesor.tareas_grupo(?)", TareaAlumnoGrupoEntidad.class);
        query.setParameter(1, idGrupo);
        return ((List<TareaAlumnoGrupoEntidad>)query.getResultList()).stream().map(TareaAlumnoGrupoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos los alumnos de un grupo y muestra el avance
     * @param idGrupoLista catalogo de grupos
     * @return Colección de alumno con sus niveles
     */
    //TODO Checar PuerQuery
    public List<AlumnoTareasNivelModelo> buscaAlumnoNivel(@NotNull @Size(min = 1) List<UUID> idGrupoLista) {
        Query query = entityManager.createNativeQuery("SELECT * FROM profesor.busca_tareas_resueltas(CAST (? AS uuid[]))");
        final StringJoiner stringJoiner = new StringJoiner(",", "{", "}");
        idGrupoLista.forEach(grupo -> stringJoiner.add(grupo.toString()));
        query.setParameter(1,stringJoiner.toString());
        List<AlumnoTareasNivelModelo> alumnoTareasNivelModeloLista = new ArrayList<>();
        List<Object[]> lista = query.getResultList();
        for (Object[] objects : lista) {
            AlumnoTareasNivelModelo alumnoTareasNivelModelo = new AlumnoTareasNivelModelo();
            alumnoTareasNivelModelo.setIdGrupo(UUID.fromString((String) objects[0]));
            alumnoTareasNivelModelo.setIdAlumno(UUID.fromString((String)objects[1]));
            alumnoTareasNivelModelo.setNivelLenguajeAlumno((Short)objects[2]);
            alumnoTareasNivelModelo.setNombre((String)objects[3]);
            alumnoTareasNivelModelo.setApellidoPaterno((String)objects[4]);
            alumnoTareasNivelModelo.setApellidoMaterno((String)objects[5]);
            alumnoTareasNivelModelo.setPromedio(objects[6] == null ? null : ((Double)objects[6]).floatValue());
            alumnoTareasNivelModelo.setTotalTareas((Integer)objects[7]);
            alumnoTareasNivelModelo.setNivelLenguaje((String)objects[8]);
            alumnoTareasNivelModeloLista.add(alumnoTareasNivelModelo);
        }
        return alumnoTareasNivelModeloLista;
    }
    /**
     * Busca todas las Actividades que no se asignaron en un Grupo de
     */
    public List<ActividadModelo> buscaActividades(@NotNull UUID idGrupo){
    Query query = entityManager.createNativeQuery(" SELECT * FROM  alumno.actividad AS a LEFT JOIN (SELECT tga.id_actividad as nulo\n" +
            "                FROM\n" +
            "                   profesor.grupo_alumno ga left join\n" +
            "                   alumno.alumno a ON ga.id_alumno = a.id LEFT JOIN\n" +
            "                   alumno.tarea t ON ga.id_grupo = t.id_grupo AND ga.id_alumno = t.id_alumno LEFT JOIN\n" +
            "                   alumno.tarea_glosario_actividad tga ON t.id = tga.id_tarea\n" +
            "                WHERE ga.id_grupo= ? \n" +
            "                GROUP BY\n" +
            "                    tga.id_tarea, tga.id_actividad\n" +
            "            ) AS tarea_actividad ON  a.id_video = tarea_actividad.nulo  LEFT JOIN\n" +
            "               alumno.nivel_lenguaje_actividad nla ON  a.id_video = nla.id_actividad\n" +
            "        where tarea_actividad.nulo IS NULL", ActividadEntidad.class);

        query.setParameter(1, idGrupo);
        return ((List<ActividadEntidad>) query.getResultList()).stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

    public List<AlumnoModelo> buscaAlumno(@NotNull UUID idGrupo) {
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("GrupoAlumnoEntidad.buscaAlumnosPorGrupo", AlumnoEntidad.class);
        typedQuery.setParameter("idGrupo", idGrupo);
        List<AlumnoEntidad> alumnoEntidadLista = typedQuery.getResultList();
        List<AlumnoModelo> alumnoModeloLista = new ArrayList<>(alumnoEntidadLista.size());
        for (AlumnoEntidad alumnoEntidad : alumnoEntidadLista) {
            AlumnoModelo alumnoModelo = new AlumnoModelo();
            alumnoModelo.setId(alumnoEntidad.getId());
            alumnoModelo.setNombre(alumnoEntidad.getNombre());
            alumnoModelo.setApellidoPaterno(alumnoEntidad.getApellidoPaterno());
            alumnoModelo.setApellidoMaterno(alumnoEntidad.getApellidoMaterno());
            alumnoModeloLista.add(alumnoModelo);
        }
        return alumnoModeloLista;
    }

    /**
     * Busca el total de alumnos por Grupo.
     * @param idGrupo id del grupo.
     * @return el total de alumnos por Grupo.
     */
    public Long buscaTotalAlumnosGrupo(@NotNull UUID idGrupo){
        TypedQuery typedQuery = entityManager.createNamedQuery("GrupoAlumnoEntidad.buscaTotalAlumnosPorGrupo", Long.class);
        typedQuery.setParameter("idGrupo", idGrupo);
        return (Long) typedQuery.getSingleResult();
    }

    /**
     * Crea una tarea a todos los alumno de un grupo
     * @param idGrupo
     * @param idActividad
     * @return
     */
    public Integer tarea(@NotNull UUID idGrupo, @NotNull @Size(min = 11, max = 11) String idActividad, Boolean verRespuesta) {
        Query query = entityManager.createNativeQuery("SELECT * FROM profesor.crea_tarea_grupo(?, ?, ?)");
        query.setParameter(1, idGrupo).setParameter(2, idActividad).setParameter(3, verRespuesta);
        return (Integer)query.getSingleResult();
    }

    /**
     * Asigna una actividad como tarea a todo un grupo
     * @param idGrupo Identificador del grupo a asignar
     * @param idActividad Identificador de la actividad
     * @param verRespuesta el alumno puede ver las respuesta
     * @return numero de elementos modificados
     */
    public Integer tareaTotal(@NotNull UUID idGrupo, @NotNull @Size(min = 11, max = 11) String idActividad, Boolean verRespuesta) {
        Query query = entityManager.createNativeQuery("SELECT * FROM profesor.crea_tarea_todo_grupo(?, ?, ?)");
        query.setParameter(1, idGrupo).setParameter(2, idActividad).setParameter(3, verRespuesta);
        return (Integer)query.getSingleResult();
    }

    public Integer tarea(@NotNull UUID idGrupo,
                         @NotNull @Size(min = 11, max = 11) String idActividad,
                         @NotNull UUID idAlumno,
                         int vuelta,
                         @NotNull Short idNivelLenguaje,
                         @NotNull Boolean verRespuesta) {
        Query query = entityManager.createNativeQuery("CALL profesor.creartarea(?,?,?,?,?,?)");
        query.setParameter(1, idGrupo);
        query.setParameter(2, idAlumno);
        query.setParameter(3, idActividad);
        query.setParameter(4, vuelta);
        query.setParameter(5, idNivelLenguaje);
        query.setParameter(6, verRespuesta);
        return query.executeUpdate();
    }

    /**
     * Elimina un GrupoAlumno.
     * @param grupoAlumnoModelo dato para eliminar el GrupoAlumno.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer elimina(@NotNull GrupoAlumnoModelo grupoAlumnoModelo){
        logger.fine(grupoAlumnoModelo.toString());
        GrupoAlumnoEntidadPK grupoAlumnoEntidadPK = new GrupoAlumnoEntidadPK();
        grupoAlumnoEntidadPK.setAlumnoEntidad(new AlumnoEntidad(grupoAlumnoModelo.getIdAlumno()));
        grupoAlumnoEntidadPK.setGrupoEntidad(new GrupoEntidad(grupoAlumnoModelo.getIdGrupo()));
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete criteriaDelete = criteriaBuilder.createCriteriaDelete(GrupoAlumnoEntidad.class);
        Root<GrupoAlumnoEntidad> root = criteriaDelete.from(GrupoAlumnoEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("grupoAlumnoEntidadPK"), grupoAlumnoEntidadPK));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    public List<AlumnoModelo> detalleAlumnos(@NotNull UUID idGrupo) {
        logger.fine(idGrupo.toString());
        Query query = entityManager.createNativeQuery("SELECT CAST (a.id AS VARCHAR),a.apodo,a.nombre,a.apellido_paterno,a.apellido_materno,pgp_sym_decrypt_bytea(" +
                "a.contrasenia, 'sad7f65as7df6sa87f8asd76f87ads6fa98', 'compress-algo=0, cipher-algo=aes256') AS contrasenia " +
                "FROM profesor.grupo_alumno ga JOIN alumno.alumno a ON ga.id_alumno = a.id WHERE ga.id_grupo = ? AND a.estatus = TRUE");
        query.setParameter(1, idGrupo);
        List<AlumnoModelo> alumnoModeloLista = new ArrayList<>();
        for (Object[] objetos : (List<Object[]>)query.getResultList()) {
            AlumnoModelo alumnoModelo = new AlumnoModelo();
            alumnoModelo.setId(UUID.fromString((String) objetos[0]));
            alumnoModelo.setApodo((String)objetos[1]);
            alumnoModelo.setNombre((String) objetos[2]);
            alumnoModelo.setApellidoPaterno((String) objetos[3]);
            alumnoModelo.setApellidoMaterno((String) objetos[4]);
            alumnoModelo.setContrasenia((byte[]) objetos[5]);
            alumnoModeloLista.add(alumnoModelo);
        }
        return alumnoModeloLista;
    }

    public List<PromedioAlumnoModelo> promedioAlumno(@NotNull UUID idGrupo) {
        Query query = entityManager.createNativeQuery("SELECT CAST(a.id AS VARCHAR),a.nombre,a.apellido_paterno,a.apellido_materno,count(cc) AS total_tareas,nl.valor AS nivel_actual," +
                "round(avg(cc.transcripcion)) AS promedio_transcripcion,round(avg(cc.mapa_mental_promedio)) AS promedio_mapamental,round(avg(cc.relacionar_imagen_promedio)) AS promedio_relacionar_imagen," +
                "round(avg(cc.gramatica_promedio)) AS promedio_gramatica,round(avg(cc.completar_promedio)) AS promedio_completa,round(avg(cc.relacionar_oracion_promedio)) AS promdio_relacionar_oraciones," +
                "round(avg(cc.oraciones_promedio)) AS promedio_oraciones FROM profesor.grupo_alumno ga JOIN alumno.alumno a ON ga.id_alumno = a.id JOIN alumno.nivel_lenguaje nl ON a.id_nivel_lenguaje = nl.clave LEFT JOIN " +
                "profesor.calificacion_coordinador cc ON a.id = cc.id_alumno AND cc.id_grupo = ga.id_grupo WHERE a.estatus = TRUE AND ga.id_grupo = ? GROUP BY a.id,a.nombre, a.apellido_paterno, a.apellido_materno,nl.valor");
        query.setParameter(1, idGrupo);
        List<PromedioAlumnoModelo> promedioAlumnoModeloLista = new ArrayList<>();
        for (Object[] objectos : (List<Object[]>)query.getResultList()) {
            PromedioAlumnoModelo promedioAlumnoModelo = new PromedioAlumnoModelo();
            promedioAlumnoModelo.setId(UUID.fromString((String) objectos[0]));
            promedioAlumnoModelo.setNombre((String)objectos[1]);
            promedioAlumnoModelo.setApellidoPaterno((String)objectos[2]);
            promedioAlumnoModelo.setApellidoMaterno((String)objectos[3]);
            promedioAlumnoModelo.setTotalTareas(((BigInteger)objectos[4]).intValue());
            promedioAlumnoModelo.setNivelActual((String)objectos[5]);
            promedioAlumnoModelo.setPromedioTranscipcion(objectos[6] == null ? null : ((BigDecimal)objectos[6]).intValue());
            promedioAlumnoModelo.setPromedioPromedioMapamental(objectos[7] == null ? null : ((BigDecimal)objectos[7]).intValue());
            promedioAlumnoModelo.setPromedioRelacionarImagen(objectos[8] == null ? null : ((BigDecimal)objectos[8]).intValue());
            promedioAlumnoModelo.setPromedioGramatica(objectos[9] == null ? null : ((BigDecimal)objectos[9]).intValue());
            promedioAlumnoModelo.setPromedioCompletar(objectos[10] == null ? null : ((BigDecimal)objectos[10]).intValue());
            promedioAlumnoModelo.setPromedioRelacionarOracion(objectos[11] == null ? null : ((BigDecimal)objectos[11]).intValue());
            promedioAlumnoModelo.setPromedioOraciones(objectos[12] == null ? null : ((BigDecimal)objectos[12]).intValue());
            promedioAlumnoModeloLista.add(promedioAlumnoModelo);
        }
        return promedioAlumnoModeloLista;
    }

    /**
     * Reasigna un alumno de grupo
     * @param grupoViejo Identificador del grupo  a remover
     * @param grupoNuevo Identificador del grupo  a insertar
     * @param alumno Alumno a ser movido
     * @return numero de modificaciones
     */
    public void reasignar(UUID grupoViejo, UUID grupoNuevo, UUID alumno) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("GrupoAlumno.heredaAlumno");
        storedProcedureQuery.setParameter("viejoGrupo", grupoViejo);
        storedProcedureQuery.setParameter("nuevoGrupo", grupoNuevo);
        storedProcedureQuery.setParameter("alumno", alumno);
        storedProcedureQuery.execute();
    }
}
