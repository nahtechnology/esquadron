package tecolotl.administracion.sesion;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;



/**
 * Servicios para el manejo de las escuelas
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class EscuelaSesionBean {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Busca las escuelas, recupera de forma completa, en caso de no existir elemento la colección estará vacia.
	 * Calcula el total de las licencias por cada escuela. Cuenta los días restantes partiendo de la de hoy más un año
	 * para calcular los dias, en caso de que ya halla pasado la fecha siempre regresará 0
	 * @return Colección de {@link EscuelaDto}
	 * @see EscuelaDto
	 *
	 */
	public Collection<EscuelaDto> busca() {
		Calendar calendarFechaHoy = Calendar.getInstance(); int diasTotales = 0;
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.busca", EscuelaEntidad.class);
		Map<String, EscuelaDto> escuelaDtoMap = new HashMap<>();
		for (EscuelaEntidad escuelaEntidad : typedQuery.getResultList()) {
			escuelaDtoMap.put(escuelaEntidad.getClaveCentroTrabajo(), new EscuelaDto(escuelaEntidad));
		}
		List<Object[]> resutado = entityManager.createQuery("SELECT l.idEscuela, COUNT (l.idEscuela), MAX(l.inicio) FROM LicenciaEntidad l GROUP BY l.idEscuela").getResultList();
		for (Object[] objects : resutado) {
			EscuelaDto escuelaDto = escuelaDtoMap.get(objects[0]);
			escuelaDto.setLicencias(((Long)objects[1]).intValue());
			Calendar calendarServidor = Calendar.getInstance();
			calendarServidor.setTime((Date) objects[2]);
			calendarServidor.add(Calendar.YEAR, 1);
			if (calendarFechaHoy.compareTo(calendarServidor) > 0) {
				escuelaDto.setDiasRestantes(0);
			} else {
				diasTotales += calendarFechaHoy.getMaximum(Calendar.DAY_OF_YEAR) - calendarFechaHoy.get(Calendar.DAY_OF_YEAR);
				for (int anio = calendarFechaHoy.get(Calendar.YEAR) + 1; anio < calendarServidor.get(Calendar.YEAR); anio++) {
					logger.finer("año a calcular:".concat(String.valueOf(anio)));
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, anio);
					diasTotales += calendar.getMaximum(Calendar.DAY_OF_YEAR);
				}
				diasTotales += calendarServidor.get(Calendar.DAY_OF_YEAR);
				escuelaDto.setDiasRestantes(diasTotales);
				diasTotales = 0;
			}
		}
		return escuelaDtoMap.values();
	}

	/**
	 * Busca todas las colonias correspondientes a un código postal, en caso de que el parametro sea nulo regresará nulo.
	 * En caso de de noexistir el codigo postal regresa un objecto vacio
	 * @param codigoPostal Código Postal a ser buscado
	 * @return Un objeto con los datos al que pertenece el código postal
	 */
	public ColoniaDto busca(String codigoPostal) {
		logger.info("Codigo Postal:".concat(codigoPostal));
		if (codigoPostal == null) {
			return null;
		}
		TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaCodigoPostal", ColoniaEntidad.class);
		typedQuery.setParameter("codigoPostal", codigoPostal);
		if (logger.isLoggable(Level.FINE)) {
			ColoniaDto coloniaDto = new ColoniaDto(typedQuery.getResultList());
			logger.fine(coloniaDto.toString());
			return coloniaDto;
		} else {
			return new ColoniaDto(typedQuery.getResultList());
		}
	}

	/**
	 * Crea una nueva escuela
	 * @param claveCentroTrabajo Clave centro de trabajo de la escuela
	 * @param colonia identificador a la cual perterece la escuela
	 * @param nombre Combre de la escuela
	 * @param domicilio Calle y número donde se encuentra la escuela
	 */
	public void insertar (String claveCentroTrabajo, int colonia, String nombre,String domicilio) {
		if (logger.isLoggable(Level.FINE)) {
			logger.fine("claveCentroTrabajo:".concat(claveCentroTrabajo));
			logger.fine("colonia".concat(String.valueOf(colonia)));
			logger.fine("domicilio".concat(domicilio));
		}
		EscuelaEntidad  escuelaEntidad= new EscuelaEntidad();
		ColoniaEntidad  coloniaEntidad= new ColoniaEntidad();
		MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad();
		escuelaEntidad.setClaveCentroTrabajo(claveCentroTrabajo);
		coloniaEntidad.setId(colonia);
		escuelaEntidad.setNombre(nombre);
		escuelaEntidad.setDomicilio(domicilio);
		escuelaEntidad.setColoniaEntidad(coloniaEntidad);
		motivoBloqueoEntidad.setId((short)0);
		escuelaEntidad.setMotivoBloqueoEntidad(motivoBloqueoEntidad);
		entityManager.persist(escuelaEntidad);
	}

    public void actualizar(String claveCentroTrabajo, int colonia, String nombre,String domicilio) {
    	EscuelaEntidad escuelaEntidad= entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
    	ColoniaEntidad  coloniaEntidad= new ColoniaEntidad();
		coloniaEntidad.setId(colonia);
		escuelaEntidad.setNombre(nombre);
		escuelaEntidad.setDomicilio(domicilio);
		escuelaEntidad.setColoniaEntidad(coloniaEntidad);
    }
	public void bloqueo (String claveCentroTrabajo, int motivoBloqueo) {
		EscuelaEntidad escuelaEntidad= entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
		MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad();
		motivoBloqueoEntidad.setId((short) motivoBloqueo);
		escuelaEntidad.setMotivoBloqueoEntidad(motivoBloqueoEntidad);
	}

}
