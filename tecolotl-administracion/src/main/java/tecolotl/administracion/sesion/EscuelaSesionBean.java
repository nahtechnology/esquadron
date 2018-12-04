package tecolotl.administracion.sesion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
	public ColoniaDto busca(String codigoPostal) {
		if (codigoPostal == null) {
			return null;
		}
		TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaCodigoPostal", ColoniaEntidad.class);
		typedQuery.setParameter("codigoPostal", codigoPostal);
		return new ColoniaDto(typedQuery.getResultList());
	}
	
	public void insertar (String claveCentroTrabajo, int colonia, String nombre,String domicilio) {
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
}
