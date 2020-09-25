package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.vista.TareaAlumnoVistaEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.DetalleAlumnoModelo;
import tecolotl.nucleo.modelo.PersonaActivaModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Session Bean implementation class AlumnoSesionBean
 */
@Stateless
public class AlumnoSesionBean implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    /**
     * Busca todos los alumnos.
     * @return Colección de {@link AlumnoModelo}
     */
    public List<AlumnoModelo> busca(){
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.busca", AlumnoEntidad.class);
        List<AlumnoEntidad> alumnoEntidadLista = typedQuery.getResultList();
        return alumnoEntidadLista.stream().map(AlumnoModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca los detalles de un alumno por apodo
     * @param apodo Apodo a ser buscado
     * @return
     */
    public AlumnoModelo busca(@NotNull @Size String apodo, boolean galaxia) {
        Query query = entityManager.createNativeQuery("SELECT a.*FROM administracion.escuela e JOIN profesor.grupo g ON g.id_escuela = e.clave_centro_trabajo JOIN " +
                "profesor.grupo_alumno ga ON g.id = ga.id_grupo JOIN alumno.alumno a ON ga.id_alumno = a.id WHERE a.apodo = ? AND e.galaxia = ?;", AlumnoEntidad.class);
        String[] datos = apodo.split(",");
        query.setParameter(1, datos[0]);
        query.setParameter(2, Short.parseShort(datos[1]));
        return new AlumnoModelo((AlumnoEntidad)query.getSingleResult());
    }

    /**
     * Busca un alumno.
     * @param idAlumno Identificador del alumno.
     * @return Colección de {@link AlumnoModelo}
     */
    public AlumnoModelo busca(@NotNull UUID idAlumno){
        logger.fine(idAlumno.toString());
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.buscaId", AlumnoEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        AlumnoEntidad alumnoEntidad = typedQuery.getSingleResult();
        logger.finer(alumnoEntidad.toString());
        return new AlumnoModelo(alumnoEntidad);
    }

    /**
     * Incremente o disminuye de nivel de lenguaje de un alumno.
     * @param idAlumno Identificador del alumno a ser actualizado.
     * @param nivel Nivel de lenguaje a ser cambiado.
     * @return Número de elementos modificados.
     */
    public int cambioNivel(@NotNull UUID idAlumno, short nivel) {
        Query query = entityManager.createNamedQuery("AlumnoEntidad.subeNivel");
        query.setParameter("idAlumno", idAlumno);
        query.setParameter("nivel", nivel);
        return query.executeUpdate();
    }

    public PersonaActivaModelo activo(@NotNull UUID idAlumno) {
        PersonaActivaModelo personaActivaModelo = new PersonaActivaModelo();
        Query query = entityManager.createNativeQuery("SELECT e.bloqueo_alumno AS bloqueado," +
                "cast(date_part('day', max(l.inicio) + interval '1 year' - current_date) AS INTEGER) AS dias FROM administracion.escuela e JOIN " +
                "administracion.licencia l ON e.clave_centro_trabajo = l.id_escuela JOIN profesor.grupo g ON g.id_escuela = e.clave_centro_trabajo JOIN " +
                "profesor.grupo_alumno ga ON g.id = ga.id_grupo WHERE ga.id_alumno = ? GROUP BY e.clave_centro_trabajo");
        query.setParameter(1, idAlumno);
        Object[] objects = (Object[]) query.getSingleResult();
        personaActivaModelo.setBloqueado((Boolean)objects[0]);
        personaActivaModelo.setDias((Integer)objects[1]);
        return personaActivaModelo;
    }

    /**
     * Actualiza el estatus de un alumno, si se ecuantra activo o bloqueado
     * @param idAlumno Identidicador del alumno a ser actualizado
     * @param estatus Nuevo estatus que deseamos establecer
     */
    public void actualiza(@NotNull UUID idAlumno, boolean estatus) {
        Query query = entityManager.createNamedQuery("AlumnoEntidad.cambiaEstatus");
        query.setParameter("idAlumno", idAlumno);
        query.setParameter("estatus", estatus);
        query.executeUpdate();
    }

}
