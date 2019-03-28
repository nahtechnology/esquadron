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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
	public List<LicenciaModelo> busca (@NotNull String claveCentrotrabajo) {
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
	 * @param licenciaModelo Datos de la licencia.
	 */
	public void inserta(@NotNull @Valid LicenciaModelo licenciaModelo) {
		logger.fine(licenciaModelo.toString());
		validadorSessionBean.validacion(licenciaModelo, LicenciaNuevaValidacion.class);
		LicenciaEntidad licenciaEntidad = new LicenciaEntidad(creaLlave(licenciaModelo));
		licenciaEntidad.setAdquisicion(licenciaModelo.getAdquisicion());
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
		criteriaUpdate.where(criteriaBuilder.equal(root.get("licenciaEntidadPk"), creaLlave(licenciaModelo)));
		return entityManager.createQuery(criteriaUpdate).executeUpdate();
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

}