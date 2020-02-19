package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.escuela.LicenciaModelo;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.LicenciaEntidad;
import tecolotl.administracion.persistencia.entidad.LicenciaEntidadPk;
import tecolotl.administracion.validacion.escuela.LicenciaActualizaValidacion;
import tecolotl.administracion.validacion.escuela.LicenciaNuevaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servicios para las licencias de una escuela
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class LicenciaSesionBean implements Serializable {

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private Logger logger;

	@Inject
	private ValidadorSessionBean validadorSessionBean;

	/**
	 * Busca las licencias que pertenecen a una escuela.
	 * @param claveCentrotrabajo Clave centro de trabajo de la escuela.
	 * @return Colección de licencias que pertenecen a la escuela
	 */
	public List<LicenciaModelo> busca(@NotNull String claveCentrotrabajo) {
		logger.fine(claveCentrotrabajo);
		TypedQuery<LicenciaEntidad> typedQuery = entityManager.createNamedQuery("LicenciaEntidad.buscaEscuela", LicenciaEntidad.class);
		typedQuery.setParameter("claveCentroTrabajo", claveCentrotrabajo);
		List<LicenciaModelo> licenciaModeloLista = new ArrayList<>();
		for (LicenciaEntidad licenciaEntidad : typedQuery.getResultList()) {
			licenciaModeloLista.add(new LicenciaModelo(licenciaEntidad));
		}
		return licenciaModeloLista;
	}

    /**
     * Busca el detalle de una licencia, en caso de no haber se recupera nulo
     * @param contador contador de la licencia de la escuela
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela
     * @return Objecto del tipo {@link LicenciaModelo} con los datos encontrados
     */
	public LicenciaModelo busca(@NotNull Short contador, @NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
	    logger.fine(contador.toString());
	    logger.fine(claveCentroTrabajo);
	    try {
            return new LicenciaModelo(entityManager.find(LicenciaEntidad.class, new LicenciaEntidadPk(contador, claveCentroTrabajo)));
        } catch (NoResultException e) {
	        return null;
        }
    }

    /**
     * Busca la licencia de una escuela por la fecha
     * @param fecha Fecha a comparar
     * @return Datos de la licencia
     */
    public LicenciaModelo busca(@NotNull Date fecha,@NotNull @Size(min = 10, max=12) String claveCentroTrabajo) {
        Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fecha);
	    calendar.add(Calendar.YEAR, -1);
	    TypedQuery<LicenciaEntidad> typedQuery = entityManager.createNamedQuery("LicenciaEntidad.buscaActivo", LicenciaEntidad.class);
	    typedQuery.setParameter("fechaFin", fecha);
	    typedQuery.setParameter("fechaInicio", calendar.getTime());
	    typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
	    try {
			return new LicenciaModelo(typedQuery.getSingleResult());
		} catch (NoResultException e) {
	    	return null;
		}
    }

	/**
	 * Inserta una licencia a una escuela.
	 * @param claveCentroTrabajo Datos de la escuela.
	 */
	public void inserta(@NotNull String claveCentroTrabajo, @NotNull LicenciaModelo licenciaModelo) {
		logger.fine(claveCentroTrabajo);
		LicenciaEntidadPk licenciaEntidadPk = new LicenciaEntidadPk();
		licenciaEntidadPk.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
		LicenciaEntidad licenciaEntidad = new LicenciaEntidad(licenciaEntidadPk);
		licenciaEntidad.setAlumnos(licenciaModelo.getAlumnos());
		licenciaEntidad.setInicio(licenciaModelo.getInicio());
		entityManager.persist(licenciaEntidad);
	}

	/**
	 * Actualiza la fecha de inicio de la licencia. Es importante marcar que solo se utilza la fecha de inicion,
	 * @param licenciaModelo Datos para ser actualizado.
	 */
	public int actualiza(@NotNull LicenciaModelo licenciaModelo) {
		logger.fine(licenciaModelo.toString());
		validadorSessionBean.validacion(licenciaModelo, LicenciaActualizaValidacion.class);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<LicenciaEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(LicenciaEntidad.class);
		Root<LicenciaEntidad> root = criteriaUpdate.from(LicenciaEntidad.class);
		criteriaUpdate.set(root.get("inicio"), licenciaModelo.getInicio());
		criteriaUpdate.set(root.get("alumnos"), licenciaModelo.getAlumnos());
		criteriaUpdate.where(criteriaBuilder.equal(root.get("licenciaEntidadPk"), creaLlave(licenciaModelo)));
		return entityManager.createQuery(criteriaUpdate).executeUpdate();
	}

	/**
	 * Remueve la licencia de una escuela
	 * @param licenciaModelo
	 */
	public void elimina(@NotNull LicenciaModelo licenciaModelo) {
		logger.fine(licenciaModelo.toString());
		validadorSessionBean.validacion(licenciaModelo, LicenciaNuevaValidacion.class);
		entityManager.remove(entityManager.find(LicenciaEntidad.class, creaLlave(licenciaModelo)));
	}

	/**
	 * Creación de la llave primaria
	 * @param licenciaModelo
	 * @return
	 */
	public LicenciaEntidadPk creaLlave(LicenciaModelo licenciaModelo) {
		LicenciaEntidadPk licenciaEntidadPk = new LicenciaEntidadPk();
		licenciaEntidadPk.setContador(licenciaModelo.getContador());
		licenciaEntidadPk.setEscuelaEntidad(new EscuelaEntidad(licenciaModelo.getClaveCentroTrabajo()));
		return licenciaEntidadPk;
	}

    /**
     * Busca el total de alumnos que puede tener una escuela regitradps, buscando por el total de licencias
     * y que esten en el rango de escuelas de su fecha de inicio
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela
     * @return total de alumnos
     */
	public int totalAlumno(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        Query query = entityManager.createNativeQuery("SELECT sum(l.alumnos)FROM administracion.licencia AS l WHERE " +
                "current_date BETWEEN l.inicio AND l.inicio + INTERVAL '1 year' AND l.id_escuela = ?");
        query.setParameter(1, claveCentroTrabajo);
        Object resp = query.getSingleResult();
        return (resp == null ? '0' : ((Number)resp).intValue());
    }

	/**
	 * Cuenta el total de licencias
	 * @return Número de licencias encontradas
	 */
	public Long cuenta() {
		return entityManager.createNamedQuery("LicenciaEntidad.cuenta", Long.class).getSingleResult();
	}

}