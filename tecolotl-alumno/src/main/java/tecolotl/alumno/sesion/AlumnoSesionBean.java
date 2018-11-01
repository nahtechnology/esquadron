package tecolotl.alumno.sesion;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tecolotl.alumno.persistencia.entidad.AlumnoEntidad;

/**
 * Session Bean implementation class AlumnoSesionBean
 */
@Stateless
@LocalBean
public class AlumnoSesionBean {

	@PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<AlumnoEntidad> bucar() {
		Query query = entityManager.createQuery("SELECT a FROM AlumnoEntidad a");
		return (List<AlumnoEntidad>)query.getResultList();
	}
}
