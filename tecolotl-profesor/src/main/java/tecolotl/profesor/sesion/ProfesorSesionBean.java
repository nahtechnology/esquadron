package tecolotl.profesor.sesion;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaPoblacionModelo;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.EscuelaLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.PersonaActivaModelo;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.CalificacionPendientesModelo;
import tecolotl.profesor.modelo.ProfesorGrupoAlumnoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.ProfesorNuevoValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class ProfesorSesionBean implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    /**
     * Inserta un nuevo profesor.
     * @param profesorModelo dato para insertar un profesor.
     */
    public void inserta(@NotNull ProfesorModelo profesorModelo){
        validadorSessionBean.validacion(profesorModelo, ProfesorNuevoValidacion.class, PersonaNuevaValidacion.class, EscuelaLlavePrimariaValidacion.class);
        ProfesorEntidad profesorEntidad = new ProfesorEntidad();
        profesorEntidad.setNombre(profesorModelo.getNombre());
        profesorEntidad.setApellidoPaterno(profesorModelo.getApellidoPaterno());
        profesorEntidad.setApellidoMaterno(profesorModelo.getApellidoMaterno());
        profesorEntidad.setApodo(profesorModelo.getApodo());
        profesorEntidad.setContrasenia(profesorModelo.getContrasenia());
        profesorEntidad.setCorreoEletronico(profesorModelo.getCorreoEletronico());
        profesorEntidad.setSexo(profesorModelo.getSexo());
        profesorEntidad.setEscuelaEntidad(new EscuelaEntidad(profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo()));
        entityManager.persist(profesorEntidad);
        profesorModelo.setId(profesorEntidad.getId());
    }

    /**
     * Busca el total de profesores y de alumnos de una escuela.
     * @param claveCentroTrabajo Clave centro de la escuela
     * @return EscuelaPoblacionModelo con los datos
     */
    public EscuelaPoblacionModelo total(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        logger.fine(claveCentroTrabajo);
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.buscaTotalEscuela", Long.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        Long totalProfesores = typedQuery.getSingleResult();
        EscuelaPoblacionModelo escuelaPoblacionModelo = new EscuelaPoblacionModelo();
        escuelaPoblacionModelo.setTotalProfesores(totalProfesores.intValue());
        Query query = entityManager.createNativeQuery(
                "SELECT sum(grupo.total_alumno) FROM (SELECT count(ga.id_grupo) AS total_alumno FROM profesor.profesor AS p LEFT JOIN profesor.grupo AS g ON p.id = g.id_profesor " +
                "LEFT JOIN profesor.ciclo_escolar ce ON g.inicio = ce.inicio and g.fin = ce.fin and g.id_escuela = ce.id_escuela JOIN profesor.grupo_alumno ga ON g.id = ga.id_grupo JOIN alumno.alumno a ON ga.id_alumno = a.id " +
                "WHERE p.id_escuela = ? AND ce.activo = TRUE AND a.estatus = TRUE GROUP BY p.id) AS grupo");
        query.setParameter(1, claveCentroTrabajo);
        Object resp = query.getSingleResult();
        escuelaPoblacionModelo.setTotalAlumnos( resp == null ? 0 : ((Number)resp).intValue());
        return escuelaPoblacionModelo;
    }

    /**
     * Busca un profesor.
     * @return una lista de todos los profesores.
     */
    public List<ProfesorModelo> busca(){
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.busca", ProfesorEntidad.class);
        List<ProfesorEntidad> profesorEntidadLista = typedQuery.getResultList();
        logger.finer("Numero de profesores encontrados: ".concat(String.valueOf(profesorEntidadLista.size())));
        return profesorEntidadLista.stream().map(ProfesorModelo::new).collect(Collectors.toList());
    }

    /**
     * Método para buscar un profesor por ID.
     * @param idProfesor Identificado del profesor.
     * @return un profesor.
     */
    public ProfesorModelo busca(@NotNull UUID idProfesor){
        return new ProfesorModelo(entityManager.find(ProfesorEntidad.class, idProfesor));
    }

    /**
     * Busca los detalles  de un profesor por apodo
     * @param apodo Apodo con el que buscar
     * @return {@link ProfesorModelo} con los datos encontrados
     */

    public ProfesorModelo busca(@NotNull String apodo, boolean galaxia) {
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.buscaApodoGalaxia", ProfesorEntidad.class);
        String datos[] = apodo.split(",");
        typedQuery.setParameter("apodo", datos[0]);
        typedQuery.setParameter("galaxia", Integer.parseInt(datos[1]));
        ProfesorEntidad profesorEntidad = typedQuery.getSingleResult();
        ProfesorModelo profesorModelo = new ProfesorModelo(profesorEntidad);
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo(profesorEntidad.getEscuelaEntidad().getClaveCentroTrabajo()));
        return profesorModelo;
    }

    /**
     * Actualiza los datos de un profesor
     * @param profesorModelo datos para poder modificar un profesor
     */
    public void actualiza(@NotNull ProfesorModelo profesorModelo){
        logger.fine(profesorModelo.toString());
        ProfesorEntidad profesorEntidad = entityManager.find(ProfesorEntidad.class,profesorModelo.getId());
        profesorEntidad.setContrasenia(profesorModelo.getContrasenia());
        profesorEntidad.setNombre(profesorModelo.getNombre());
        profesorEntidad.setApellidoPaterno(profesorModelo.getApellidoPaterno());
        profesorEntidad.setApellidoMaterno(profesorModelo.getApellidoMaterno());
        profesorEntidad.setSexo(profesorModelo.getSexo());
        profesorEntidad.setCorreoEletronico(profesorModelo.getCorreoEletronico());
    }

    /**
     * Elimina un profesor.
     * @param idProfesor dato para eliminar el profesor.
     * @return numero de elementos modificados, 0 en caso de no existir.
     */
    public Integer elimina(@NotNull UUID idProfesor){
        logger.fine(idProfesor.toString());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.eliminaProfesor", Long.class);
        typedQuery.setParameter("idProfesor", idProfesor);
        if (typedQuery.getSingleResult() == 0){
            CriteriaDelete<ProfesorEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(ProfesorEntidad.class);
            Root<ProfesorEntidad> root = criteriaDelete.from(ProfesorEntidad.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), idProfesor));
            return entityManager.createQuery(criteriaDelete).executeUpdate();
        }else {
            return 1;
        }
    }

    /**
     * Valida si el profesor tiene asginado el grupo asignado
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela.
     * @return Coleción de {@link ProfesorModelo}
     */
    public List<ProfesorModelo> buscaPorEscuela(@NotNull String claveCentroTrabajo) {
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.buscaIdEscuela", ProfesorEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        return typedQuery.getResultList().stream().map(ProfesorModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca los totaoles de grupos y total de alumno que tiene cada grupo de una escuela
     * @param claveCentroTrabajo Identificador de la escuela
     * @return Coleccion de datos con los totales
     */
    public List<ProfesorGrupoAlumnoModelo> buscaTotalGrupoAlumno(@NotNull String claveCentroTrabajo) {
        Query query = entityManager.createNativeQuery(
                "SELECT CAST(p.id AS VARCHAR),p.nombre,p.apellido_paterno,p.apellido_materno,count(grupo.id) AS total_grupo," +
                        "CASE WHEN sum(grupo.total_alumno) IS NULL THEN 0 ELSE sum(grupo.total_alumno) END AS total_alumno FROM profesor.profesor p LEFT JOIN " +
                        "(SELECT g.id,g.id_profesor, count(ga.id_alumno) AS total_alumno FROM profesor.grupo g JOIN " +
                        "profesor.ciclo_escolar ce ON g.inicio = ce.inicio and g.fin = ce.fin and g.id_escuela = ce.id_escuela JOIN " +
                        "profesor.grupo_alumno ga ON g.id = ga.id_grupo JOIN alumno.alumno a ON ga.id_alumno = a.id WHERE ce.activo = TRUE AND a.estatus = TRUE GROUP BY g.id " +
                        ") AS grupo ON p.id = grupo.id_profesor WHERE p.id_escuela = ? GROUP BY p.id");
        query.setParameter(1, claveCentroTrabajo);
        List<Object[]> lista = (List<Object[]>) query.getResultList();
        return lista.stream().map(objects -> {
            ProfesorGrupoAlumnoModelo profesorGrupoAlumnoModelo = new ProfesorGrupoAlumnoModelo();
            profesorGrupoAlumnoModelo.setId(UUID.fromString((String)objects[0]));
            profesorGrupoAlumnoModelo.setNombre((String)objects[1]);
            profesorGrupoAlumnoModelo.setApellidoPaterno((String)objects[2]);
            profesorGrupoAlumnoModelo.setApellidoMaterno((String)objects[3]);
            profesorGrupoAlumnoModelo.setTotalGrupo(((BigInteger)objects[4]).intValue());
            profesorGrupoAlumnoModelo.setTotalAlumno(((BigDecimal)objects[5]).intValue());
            return profesorGrupoAlumnoModelo;
        }).collect(Collectors.toList());
    }

    public List<CalificacionPendientesModelo> buscaTotalCalificado(@NotNull UUID idProfesor, @NotNull Date fechaInicio, @NotNull Date fechaFinal) {
        Query query = entityManager.createNativeQuery("SELECT CAST(g.id AS VARCHAR) AS id_grupo,CAST(g.id_profesor AS VARCHAR),g.grado,g.grupo,CAST(count(t.id) AS INTEGER) AS tarea_asignadas ,sum(ctm.calificada) AS pendientes_mapamental,\n" +
                "sum(ctg.calificada) AS pendientes_gramatica,promedio.prome FROM profesor.ciclo_escolar ce LEFT JOIN profesor.grupo g ON ce.inicio = g.inicio and ce.fin = g.fin and ce.id_escuela = g.id_escuela LEFT JOIN\n" +
                "profesor.grupo_alumno ga ON g.id = ga.id_grupo LEFT JOIN alumno.alumno a ON ga.id_alumno = a.id LEFT JOIN alumno.tarea t ON ga.id_grupo = t.id_grupo AND ga.id_alumno = t.id_alumno LEFT JOIN(SELECT id_tarea,\n" +
                "CASE WHEN count(id_tarea) - count(puntaje) > 0 THEN 1 ELSE 0 END AS calificada FROM profesor.califica_tarea_mapamental GROUP BY id_tarea) AS ctm On t.id = ctm.id_tarea LEFT JOIN(SELECT id_tarea,\n" +
                "CASE WHEN count(id_tarea) - count(puntaje) > 0 THEN 1 ELSE 0 END AS calificada FROM profesor.califica_tarea_gramatica GROUP BY id_tarea) AS ctg ON ctg.id_tarea = t.id LEFT JOIN(SELECT id_grupo,\n" +
                "round(avg(promedio_general), 2) AS prome FROM (SELECT cc.id_grupo,id_alumno,unnest(array [round(avg(cc.transcripcion)) ,round(avg(cc.mapa_mental_promedio)) ,round(avg(cc.relacionar_imagen_promedio)) ,\n" +
                "round(avg(cc.gramatica_promedio)) ,round(avg(cc.completar_promedio)), round(avg(cc.relacionar_oracion_promedio)),round(avg(cc.oraciones_promedio))]) AS promedio_general FROM alumno.alumno a JOIN \n" +
                "profesor.calificacion_coordinador cc ON cc.id_alumno = a.id WHERE a.estatus = TRUE GROUP BY id_grupo, id_alumno) AS promedio GROUP BY id_grupo) AS promedio ON promedio.id_grupo = g.id WHERE\n" +
                "id_profesor = ? AND ce.inicio = ? AND ce.fin = ? AND a.estatus = TRUE GROUP BY g.id,g.id_profesor,g.grado,g.grupo,promedio.prome ORDER BY g.grado");
        query.setParameter(1, idProfesor);
        query.setParameter(2, fechaInicio);
        query.setParameter(3, fechaFinal);
        List<Object[]> lista = (List<Object[]>) query.getResultList();
        return lista.stream().map(objects -> {
            CalificacionPendientesModelo calificacionPendientesModelo = new CalificacionPendientesModelo();
            calificacionPendientesModelo.setIdGrupo(UUID.fromString((String)objects[0]));
            calificacionPendientesModelo.setIdProfesor(UUID.fromString((String)objects[1]));
            calificacionPendientesModelo.setGrado((Short) objects[2]);
            calificacionPendientesModelo.setGrupo((String)objects[3]);
            calificacionPendientesModelo.setTareasAsignadas(((Integer)objects[4]));
            calificacionPendientesModelo.setPendientesMapaMental(objects[5] == null ? null : ((BigInteger)objects[5]).intValue());
            calificacionPendientesModelo.setPendientesGramatica(objects[6] == null ? null : ((BigInteger)objects[6]).intValue());
            calificacionPendientesModelo.setPromedio(objects[7] == null ? null : ((BigDecimal)objects[7]).floatValue());
            return calificacionPendientesModelo;
        }).collect(Collectors.toList());
    }

    public PersonaActivaModelo activo(@NotNull UUID idProfesor) {
        PersonaActivaModelo personaActivaModelo = new PersonaActivaModelo();
        Query query = entityManager.createNativeQuery("SELECT e.bloqueo_profesor AS bloqueado," +
                "date_part('day', max(l.inicio) + interval '1 year' - current_date) AS dias FROM administracion.escuela e JOIN " +
                "administracion.licencia l ON e.clave_centro_trabajo = l.id_escuela JOIN profesor.profesor p ON e.clave_centro_trabajo = p.id_escuela " +
                "WHERE p.id = ? GROUP BY e.clave_centro_trabajo");
        query.setParameter(1, idProfesor);
        try {
            Object[] objects = (Object[]) query.getSingleResult();
            personaActivaModelo.setBloqueado((Boolean) objects[0]);
            personaActivaModelo.setDias(((Double) objects[1]).intValue());
        } catch (NoResultException e) {
            personaActivaModelo.setBloqueado(true);
            personaActivaModelo.setDias(0);
        }
        return personaActivaModelo;

    }
}
