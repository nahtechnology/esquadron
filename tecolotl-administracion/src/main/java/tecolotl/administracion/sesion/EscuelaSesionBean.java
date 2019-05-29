package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
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
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;



/**
 * Servicios para el manejo de las escuelas
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class EscuelaSesionBean implements Serializable {

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
				escuelaDashboardModelo.setLicencias(((Long)objecto[1]).intValue());
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

	/**
	 * Inserta una escuela
	 * @param escuelaDetalleModelo Datos de la escuela a ser insertados
	 */
	public void inserta(@NotNull EscuelaDetalleModelo escuelaDetalleModelo) {
		logger.fine("Escuela a persistir:".concat(escuelaDetalleModelo.toString()));
		EscuelaEntidad escuelaEntidad = new EscuelaEntidad(escuelaDetalleModelo.getClaveCentroTrabajo());
		escuelaEntidad.setNombre(escuelaDetalleModelo.getNombre());
		escuelaEntidad.setDomicilio(escuelaDetalleModelo.getDomicilio());
		escuelaEntidad.setNumeroExterior(escuelaDetalleModelo.getNumeroExterior());
		escuelaEntidad.setNumeroInterior(escuelaDetalleModelo.getNumeroInterior());
		ColoniaEntidad coloniaEntidad = new ColoniaEntidad(escuelaDetalleModelo.getColoniaModelo().getId());
		escuelaEntidad.setColoniaEntidad(coloniaEntidad);
		logger.finer(escuelaEntidad.toString());
		entityManager.persist(escuelaEntidad);
	}

	/**
	 * Actualiza los datos de una escuela
	 * @param escuelaDetalleModelo Datos que serán actualizadps
	 * @return Número de elementos modificados, cero en caso de no haber cambios
	 */
	public int actualizar(@NotNull @Valid EscuelaDetalleModelo escuelaDetalleModelo) {
		logger.fine("Escuela a actualizar:".concat(escuelaDetalleModelo.toString()));
	    int cambios = 0;
		if (escuelaDetalleModelo != null) {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaUpdate<EscuelaEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(EscuelaEntidad.class);
			Root<EscuelaEntidad> root = criteriaUpdate.from(EscuelaEntidad.class);
			Predicate predicate = criteriaBuilder.equal(root.get("claveCentroTrabajo"), escuelaDetalleModelo.getClaveCentroTrabajo());
			criteriaUpdate.set(root.get("nombre"), escuelaDetalleModelo.getNombre());
			criteriaUpdate.set(root.get("domicilio"), escuelaDetalleModelo.getDomicilio());
			criteriaUpdate.set(root.get("numeroInterior"), escuelaDetalleModelo.getNumeroInterior());
			criteriaUpdate.set(root.get("numeroExterior"), escuelaDetalleModelo.getNumeroExterior());
			ColoniaEntidad coloniaEntidad = new ColoniaEntidad(escuelaDetalleModelo.getColoniaModelo().getId());
			criteriaUpdate.set(root.get("coloniaEntidad"), coloniaEntidad);
			criteriaUpdate.where(predicate);
			cambios = entityManager.createQuery(criteriaUpdate).executeUpdate();
		}
		return cambios;
    }

	/**
	 * Cambia el estatus de motivo de bloque de una escuela, los parámetros son validados para que sean diferentes de nulo
	 * ya que si alguno de ellos son nulo no se puede seralzar los cambios. En caso de que la escuela con la CCT no exista
	 * no se actualiza ningún dato.
	 * @param escuelaBaseModelo Escuela a ser actualizada
	 * @param motivoBloqueoModelo Motivo de Bloque con el que se le bloquea
	 */
	public void bloqueo (@NotNull EscuelaBaseModelo escuelaBaseModelo, @NotNull MotivoBloqueoModelo motivoBloqueoModelo) {
		logger.finer("Escuela a actualizar:".concat(escuelaBaseModelo.toString()).concat(" con motivo de bloqueo:").concat(motivoBloqueoModelo.toString()));
		EscuelaEntidad escuelaEntidad = entityManager.find(EscuelaEntidad.class, escuelaBaseModelo.getClaveCentroTrabajo());
		MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad(motivoBloqueoModelo.getClave());
		escuelaEntidad.setMotivoBloqueoEntidad(motivoBloqueoEntidad);
	}

	/**
	 * Pone estatus activo de una escuela
	 * @param claveCentroTrabajo Escuela a ser activada
	 */
	public void activa(@NotNull String claveCentroTrabajo) {
		logger.fine(claveCentroTrabajo);
		EscuelaEntidad escuelaEntidad = entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
		MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad((short)0);
		escuelaEntidad.setMotivoBloqueoEntidad(motivoBloqueoEntidad);
	}

	/**
	 * Busca los detalle de una escuela, en caso de no existir se regresa nulo.
	 * @param claveCentroTrabajo Clave Centro de Trabajo .
	 * @return {@link EscuelaDetalleModelo} Con los datos.
	 */
	public EscuelaDetalleModelo busca(@NotNull String claveCentroTrabajo) {
		logger.fine(claveCentroTrabajo);
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.detalle", EscuelaEntidad.class);
		typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
		EscuelaEntidad escuelaEntidad = typedQuery.getSingleResult();
		return escuelaEntidad == null ? null : new EscuelaDetalleModelo(escuelaEntidad);
	}

	/**
	 * Busca una escuela con los datos base de una escula o CCT, en caso de no existir regresa nulo.
	 * @param escuelaBaseModelo Escuela base con la CCT para buscar
	 * @return {@link EscuelaDetalleModelo} Con los datos.
	 */
	public EscuelaDetalleModelo busca(@Valid @NotNull EscuelaBaseModelo escuelaBaseModelo) {
		return busca(escuelaBaseModelo.getClaveCentroTrabajo());
	}

	/**
	 * Remueve una escuela partiendo
	 * @param claveCentroTrabajo
	 * @return Número de elementos modificados
	 */
	public int elimina(@NotNull String claveCentroTrabajo) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaDelete<EscuelaEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(EscuelaEntidad.class);
		Root<EscuelaEntidad> root = criteriaDelete.from(EscuelaEntidad.class);
		Predicate predicate = criteriaBuilder.equal(root.get("claveCentroTrabajo"), claveCentroTrabajo);
		criteriaDelete.where(predicate);
		return entityManager.createQuery(criteriaDelete).executeUpdate();
	}

}
