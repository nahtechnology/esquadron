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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servicios para las licencias de una escuela
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class LicenciaSesionBean {

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
	 * Inserta una licencia a una escuela.
	 * @param claveCentroTrabajo Datos de la escuela.
	 */
	public void inserta(@NotNull String claveCentroTrabajo) {
		logger.fine(claveCentroTrabajo);
		LicenciaEntidadPk licenciaEntidadPk = new LicenciaEntidadPk();
		licenciaEntidadPk.setEscuelaEntidad(new EscuelaEntidad(claveCentroTrabajo));
		LicenciaEntidad licenciaEntidad = new LicenciaEntidad(licenciaEntidadPk);
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

	public int cuenta(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
		logger.fine("Contanto las licencias para la escuela:".concat(claveCentroTrabajo));
		return entityManager.createNamedQuery("LicenciaEntidad.cuentaPorEscuela", Long.class)
				.setParameter("claveCentroTrabajo", claveCentroTrabajo).getSingleResult().intValue();
	}
}