package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.logging.Logger;



/**
 * Servicios para el manejo de las escuelas
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class EscuelaSesionBean {

	@Inject
	private Logger logger;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Recupera una colección de Modelos que representa una escuela, cuenta el total de licencias que cuenta cada escuela
	 * y los dias restatantes para el máximo de licencias que tiene cada escuela.
	 * @return Colección de {@link EscuelaDashboardModelo}, en caso de no existir elemento recupera una colección vacia.
	 */
	public Collection<EscuelaDashboardModelo> busca() {
		Calendar calendarFechaHoy = Calendar.getInstance();
		int diasTotales = 0;
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.busca", EscuelaEntidad.class);
		List<EscuelaEntidad> escuelaEntidadLista = typedQuery.getResultList();
		if (escuelaEntidadLista.isEmpty()) {
			return Collections.emptySet();
		} else {
			NavigableSet<EscuelaDashboardModelo> escuelaDashboardModeloConjunto = new TreeSet<>();
			for (EscuelaEntidad escuelaEntidad : escuelaEntidadLista) {
				escuelaDashboardModeloConjunto.add(new EscuelaDashboardModelo(escuelaEntidad));
			}
			Query query = entityManager.createQuery("SELECT l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo, " +
					"COUNT (l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo), MAX(l.inicio) FROM LicenciaEntidad l " +
					"GROUP BY l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo");
			for (Object[] objecto : (List<Object[]>) query.getResultList()) {
				EscuelaDashboardModelo escuelaDashboardModelo = escuelaDashboardModeloConjunto.ceiling(
						new EscuelaDashboardModelo((String) objecto[0]));
				escuelaDashboardModelo.setLicencias((Integer) objecto[1]);
				Calendar calendarServidor = Calendar.getInstance();
				calendarServidor.setTime((Date) objecto[2]);
				calendarServidor.add(Calendar.YEAR, 1);
				if (calendarFechaHoy.compareTo(calendarServidor) > 0) {
					escuelaDashboardModelo.setDiasRestantes(0);
				} else {
					diasTotales += calendarFechaHoy.getMaximum(Calendar.DAY_OF_YEAR) - calendarFechaHoy.get(Calendar.DAY_OF_YEAR);
					for (int anio = calendarFechaHoy.get(Calendar.YEAR); anio < calendarServidor.get(Calendar.YEAR); anio++) {
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.YEAR, anio);
						diasTotales += calendar.getMaximum(Calendar.DAY_OF_YEAR);
					}
					diasTotales += calendarServidor.get(Calendar.DAY_OF_YEAR);
					escuelaDashboardModelo.setDiasRestantes(diasTotales);
					diasTotales = 0;
				}
			}
			return escuelaDashboardModeloConjunto;
		}
	}


	public void inserta(EscuelaDetalleModelo escuelaDetalleModelo) {
		logger.fine("Escuela a persistir:".concat(escuelaDetalleModelo.toString()));
		EscuelaEntidad escuelaEntidad = new EscuelaEntidad(escuelaDetalleModelo.getClaveCentroTrabajo());
		escuelaEntidad.setNombre(escuelaDetalleModelo.getNombre());
		escuelaEntidad.setDomicilio(escuelaDetalleModelo.getDomicilio());
		escuelaEntidad.setNumeroExterior(escuelaDetalleModelo.getNumeroExterior());
		escuelaEntidad.setNumeroInterior(escuelaDetalleModelo.getNumeroInterior());
		ColoniaEntidad coloniaEntidad = new ColoniaEntidad(escuelaDetalleModelo.getColoniaModelo().getId());
		escuelaEntidad.setColoniaEntidad(coloniaEntidad);
		logger.fine(escuelaEntidad::toString);
	}

	/**
	 * Realiza cambios a una escuela existente, los cambios que se pueden realizar son en su clave de trabajo, colonia,nombre de escuela
	 * @param claveCentroTrabajo Identificador unico  de la escuela
	 * @param colonia identificador a la cual perterece la escuela
	 * @param nombre Nombre de la escuela
	 * @param domicilio Calle y número donde se encuentra la escuela
	 */
	public EscuelaEntidad actualizar(String claveCentroTrabajo, int colonia, String nombre,String domicilio) {
	    if (claveCentroTrabajo == null) {
	        return null;
        }
    	EscuelaEntidad escuelaEntidad= entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
    	ColoniaEntidad coloniaEntidad= new ColoniaEntidad();
		coloniaEntidad.setId(colonia);
		escuelaEntidad.setNombre(nombre);
		escuelaEntidad.setDomicilio(domicilio);
		escuelaEntidad.setColoniaEntidad(coloniaEntidad);
		return escuelaEntidad;
    }

/*	public void bloqueo (String claveCentroTrabajo, Short motivoBloqueo) {
		EscuelaEntidad escuelaEntidad = entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
		MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad();
		motivoBloqueoEntidad.setId(motivoBloqueo);
		escuelaEntidad.setMotivoBloqueoEntidad(motivoBloqueoEntidad);
	}*/

/*	public EscuelaDetalleDto busca(String claveCentroTrabajo) {
		if (claveCentroTrabajo == null) {
			return null;
		}
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.buscaDesatalle", EscuelaEntidad.class);
		typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
		EscuelaEntidad escuelaEntidad = typedQuery.getSingleResult();
		TypedQuery<ContactoEntidad> contactoEntidadTypedQuery =entityManager.createNamedQuery("ContactoEntidad.buscaCCT", ContactoEntidad.class);
		contactoEntidadTypedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
		List<ContactoEntidad> contactoEntidadList = contactoEntidadTypedQuery.getResultList();
		escuelaEntidad.setContacto(contactoEntidadList);
		return new EscuelaDetalleDto(escuelaEntidad);
	}
*/

/*	public EscuelaDto elimina(String claveCentroTrabajo) {
		EscuelaEntidad escuelaEntidad = entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
		entityManager.remove(escuelaEntidad);
		return new EscuelaDto(escuelaEntidad);
	}
*/
}
