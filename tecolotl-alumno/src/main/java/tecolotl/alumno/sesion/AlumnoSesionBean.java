package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.vista.TareaAlumnoVistaEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.modelo.DetalleAlumnoModelo;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Session Bean implementation class AlumnoSesionBean
 */
@Stateless
public class AlumnoSesionBean implements Serializable {

    @PersistenceContext(unitName = "alumno")
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
     * Busca un alumno.
     * @param idAlumno Identificador del alumno.
     * @return Colección de {@link AlumnoModelo}
     */
    public AlumnoModelo busca(@NotNull Integer idAlumno){
        logger.fine(idAlumno.toString());
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.buscaId", AlumnoEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        AlumnoEntidad alumnoEntidad = typedQuery.getSingleResult();
        logger.finer(alumnoEntidad.toString());
        return new AlumnoModelo(alumnoEntidad);
    }

    /**
     * Busca los detalles de un alumno
     * @param idAlumno Identificador del alumno.
     * @return DetalleAlumnoModelo con los datos.
     */
    public DetalleAlumnoModelo detalle(@NotNull Integer idAlumno) {
        logger.fine(idAlumno.toString());
        TypedQuery<TareaAlumnoVistaEntidad> typedQuery =
                entityManager.createNamedQuery("TareaAlumnoVistaEntidad.buscaAlumno", TareaAlumnoVistaEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        TareaAlumnoVistaEntidad tareaAlumnoVistaEntidad = typedQuery.getSingleResult();
        logger.finer(tareaAlumnoVistaEntidad.toString());
        return new DetalleAlumnoModelo(tareaAlumnoVistaEntidad);
    }
}
