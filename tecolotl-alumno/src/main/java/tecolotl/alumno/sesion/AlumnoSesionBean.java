package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.vista.TareaAlumnoVistaEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.DetalleAlumnoModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
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
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.buscaApodo", AlumnoEntidad.class);
        typedQuery.setParameter("apodo", galaxia ? apodo.split(",")[0] : apodo);
        return new AlumnoModelo(typedQuery.getSingleResult());
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

}
