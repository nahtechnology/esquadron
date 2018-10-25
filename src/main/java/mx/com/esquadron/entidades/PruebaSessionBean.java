package mx.com.esquadron.entidades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PruebaSessionBean {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void crear(String nombre, int edad) {
		EntidadPersona entidadPersona = new EntidadPersona();
		entidadPersona.setNombre(nombre);
		entidadPersona.setEdad(edad);
		entityManager.persist(entidadPersona);
	}
}
