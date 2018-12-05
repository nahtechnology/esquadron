package tecolotl.administracion.sesion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.persistencia.entidad.LicenciaEntidad;


@Stateless
public class LicenciaSesionBean {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Busca las escuelas, recupera de forma completa, en caso de no existir elemento la colección estará vacia
	 * @return Colección de {@link EscuelaDto}
	 * @see EscuelaDto
	 *
	 */
	public Collection<EscuelaDto> busca() {
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.busca", EscuelaEntidad.class);
		List<EscuelaDto> escuelaDtoList = new ArrayList<>();
		for (EscuelaEntidad escuelaEntidad :
				typedQuery.getResultList()) {
			escuelaDtoList.add(new EscuelaDto(escuelaEntidad));
		}
		return escuelaDtoList;
	}

	/**
	 * Busca todas las colonias correspondientes a un código postal, en caso de que el parametro sea nulo regresará nulo.
	 * En caso de de noexistir el codigo postal regresa un objecto vacio
	 * @param codigoPostal Código Postal a ser buscado
	 * @return Un objeto con los datos al que pertenece el código postal
	 */
	/*public LicenciaEntidad busca(String claveCentroTrebajo) {
		if (claveCentroTrebajo == null) {
			return null;
		}
		TypedQuery<LicenciaEntidad> typedQuery = entityManager.createNamedQuery("LicenciaEntidad.buscaClaveCentroTrabajo", LicenciaEntidad.class);
		typedQuery.setParameter("claveCentroTrebajo", claveCentroTrebajo);
		return new ColoniaDto(typedQuery.getResultList());
	}*/
	
	public void insertar (String idEscuela,Date fechaInicio) {
		LicenciaEntidad licenciaEntidad = new LicenciaEntidad();
		EscuelaEntidad escuelaEntidad = new EscuelaEntidad();
		licenciaEntidad.setFechaInicio(fechaInicio);
		escuelaEntidad.setClaveCentroTrabajo(idEscuela);
		entityManager.persist(licenciaEntidad);
	}
	

}