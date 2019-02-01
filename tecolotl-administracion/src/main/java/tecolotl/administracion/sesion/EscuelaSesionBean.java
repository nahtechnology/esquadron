package tecolotl.administracion.sesion;

import tecolotl.administracion.dto.EscuelaDetalleDto;
import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.persistencia.entidad.ContactoEntidad;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



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
	 * Valida que no exista una escuela. Busca una escuela por medio de clave detrabajo, esto se realiza para evitar que se duplique escuela.
	 * @param claveCentroTrabajo Las escuelas cuentan con una clave de trabajo unica.
	 * @return
	 */
	public boolean buscaDuplicado(String claveCentroTrabajo) {
		logger.fine("Clave Centro de Trabajo:".concat(claveCentroTrabajo));
		if (claveCentroTrabajo == null) {
			return false;
		}
		Query query = entityManager.createNamedQuery("EscuelaEntidad.buscaExistencia");
		query.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        Long existe = (Long)query.getSingleResult();
		return  existe != 0;
	}
	

	/**
	 * Crea una nueva escuela
	 * @param claveCentroTrabajo Identificador unico  de la escuela
	 * @param colonia identificador a la cual perterece la escuela
	 * @param nombre Nombre de la escuela
	 * @param domicilio Calle y número donde se encuentra la escuela
	 * @return {@link EscuelaDto} con los datos de la nueva escuela
	 */
	public EscuelaDto insertar (String claveCentroTrabajo, int colonia, String nombre, String domicilio, String numeroInterior, String numeroExterior) {
		if (logger.isLoggable(Level.FINE)) {
			logger.fine("claveCentroTrabajo:".concat(claveCentroTrabajo));
			logger.fine("colonia".concat(String.valueOf(colonia)));
			logger.fine("domicilio".concat(domicilio));
		}
		EscuelaEntidad  escuelaEntidad= new EscuelaEntidad();
		ColoniaEntidad  coloniaEntidad= new ColoniaEntidad();
		TypedQuery<MotivoBloqueoEntidad> typedQuery = entityManager.createNamedQuery("MotivoBloqueoEntidad.buscaDescripcion", MotivoBloqueoEntidad.class);
		typedQuery.setParameter("descripcion", "Sin bloqueo");
		escuelaEntidad.setClaveCentroTrabajo(claveCentroTrabajo);
		coloniaEntidad.setId(colonia);
		escuelaEntidad.setNombre(nombre);
		escuelaEntidad.setDomicilio(domicilio);
		escuelaEntidad.setColoniaEntidad(coloniaEntidad);
		escuelaEntidad.setNumeroInterior(numeroInterior);
		escuelaEntidad.setNumeroExterior(numeroExterior);
		escuelaEntidad.setMotivoBloqueoEntidad(typedQuery.getSingleResult());
		entityManager.persist(escuelaEntidad);
		return new EscuelaDto(escuelaEntidad);
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
    /**
     * Modifica el estatus de una escuela, se realiza una busqueda por medio de la clave de trabajo
     * @param claveCentroTrabajo Identificador unico  de la escuela
     * @param motivoBloqueo numero que hace referencia el  bloquear/desbloquear a una escuela (por ejemplo por falta de pago, sanción). 
     */
	public void bloqueo (String claveCentroTrabajo, Short motivoBloqueo) {
		EscuelaEntidad escuelaEntidad = entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
		MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad();
		motivoBloqueoEntidad.setId(motivoBloqueo);
		escuelaEntidad.setMotivoBloqueoEntidad(motivoBloqueoEntidad);
	}

	/**
	 * Busca una escuela por id clave centro de trabajo y muestra todos los detalles
	 * @param claveCentroTrabajo Llave primaria de la escuela
	 * @return Objecto con los detalles de la escuela
	 */
	public EscuelaDetalleDto busca(String claveCentroTrabajo) {
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

	/**
	 * Elimina una escuela
	 * @param claveCentroTrabajo Identificador para la escuela
	 * @return {@link EscuelaDto} eliminado
	 */
	public EscuelaDto elimina(String claveCentroTrabajo) {
		EscuelaEntidad escuelaEntidad = entityManager.find(EscuelaEntidad.class, claveCentroTrabajo);
		entityManager.remove(escuelaEntidad);
		return new EscuelaDto(escuelaEntidad);
	}

}
