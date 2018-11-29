package tecolotl.administracion.sesion;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

@Stateless
public class AdministracionSesionBean {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public ColoniaDto buscar (String codigoPostal) {
		if (codigoPostal == null){
			return null;
		}
		TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("Administracion.buscaCP", ColoniaEntidad.class);
		typedQuery.setParameter("codigoPostal", codigoPostal);
		List<ColoniaEntidad> listaColonias = typedQuery.getResultList();
		ColoniaDto coloniaDto = new ColoniaDto(listaColonias);
		return  coloniaDto;
	}
	
}
