package tecolotl.alumno.sesion;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.entidad.AlumnoEntidad;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    public List<AlumnoModelo> busca(){
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.busca", AlumnoEntidad.class);
        List<AlumnoEntidad> alumnoEntidadLista = typedQuery.getResultList();
        return alumnoEntidadLista.stream().map(AlumnoModelo::new).collect(Collectors.toList());
    }

    public AlumnoModelo busca(@NotNull @Min(1) Integer idAlumno){
        logger.fine(idAlumno.toString());
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.buscaId", AlumnoEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        AlumnoEntidad alumnoEntidad = typedQuery.getSingleResult();
        logger.finer(alumnoEntidad.toString());
        return new AlumnoModelo(alumnoEntidad);
    }
}
