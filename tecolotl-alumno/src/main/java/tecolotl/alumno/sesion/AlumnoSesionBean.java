package tecolotl.alumno.sesion;

import tecolotl.alumno.Modelo.AlumnoModelo;
import tecolotl.alumno.entidad.AlumnoEntidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Null;

/**
 * Session Bean implementation class AlumnoSesionBean
 */
@Stateless
public class AlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AlumnoModelo> busca(){
        List<AlumnoModelo> alumnoModeloLista = new ArrayList<>();
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.busca", AlumnoEntidad.class);
        List<AlumnoEntidad> alumnoEntidadLista = typedQuery.getResultList();
        for (AlumnoEntidad alumnoEntidad :
                alumnoEntidadLista) {
            alumnoModeloLista.add(new AlumnoModelo(alumnoEntidad));
        }
        return alumnoModeloLista;
    }
}
