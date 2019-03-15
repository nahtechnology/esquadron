package tecolotl.administracion.sesion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.LicenciaEntidad;

@Stateless
public class LicenciaSesionBean {

	@PersistenceContext
	private EntityManager entityManager;

	/**Se crea una nueva licencia asociada a una escuela, igresando la escuela y fecha donde empieza a correr la licencia
	 *
 	 * @param idEscuela Referencia de Escuela
	 * @param fechaInicio Punto de referencia donde inicia la vigencia de Licencia.
	 */
	public void insertar (String idEscuela,Date fechaInicio) {
		LicenciaEntidad licenciaEntidad = new LicenciaEntidad();
		EscuelaEntidad escuelaEntidad = new EscuelaEntidad();
		licenciaEntidad.setInicio(fechaInicio);
		escuelaEntidad.setClaveCentroTrabajo(idEscuela);
		entityManager.persist(licenciaEntidad);
	}
	

}