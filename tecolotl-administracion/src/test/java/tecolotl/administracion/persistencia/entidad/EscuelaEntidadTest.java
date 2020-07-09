package tecolotl.administracion.persistencia.entidad;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

@RunWith(Arquillian.class)
public class EscuelaEntidadTest {

 	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(EscuelaEntidad.class.getPackage())
				.addClass(CatalagoEntidad.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager entityManager;

 	@Inject private UserTransaction userTransaction;

 	@Test
	public void inserta() throws SystemException {
 		EscuelaEntidad escuelaEntidad = new EscuelaEntidad();
 		escuelaEntidad.setClaveCentroTrabajo("0000000000");
 		escuelaEntidad.setNombre("ACADEMIA ESTATAL DE LAS FUERZAS DE SEGURIDAD PUBLICA DEL ESTADO DE P");
		EntityTransaction entityTransaction = null;
 		try {
 			userTransaction.begin();
			entityManager.persist(escuelaEntidad);
			userTransaction.commit();
		} catch (Exception ex) {
			if (entityTransaction != null) {entityTransaction.rollback(); }
		}
	}

	@Test
	public void busca() {
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.busca", EscuelaEntidad.class);
		List<EscuelaEntidad> escuelaEntidadLista = typedQuery.getResultList();
		Assert.assertNotNull(escuelaEntidadLista);
		for (EscuelaEntidad escuelaEntidad : escuelaEntidadLista) {
			Assert.assertNotNull(escuelaEntidad.getClaveCentroTrabajo());
			Assert.assertNotNull(escuelaEntidad.getNombre());
			Assert.assertNotNull(escuelaEntidad.getMotivoBloqueoEntidad().getClave());
		}
	}

	@Test
	public void buscaDetalle() {
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.detalle", EscuelaEntidad.class);
		typedQuery.setParameter("claveCentroTrabajo", "0000000000");
		EscuelaEntidad escuelaEntidad = typedQuery.getSingleResult();
		Assert.assertNotNull(escuelaEntidad);
		Assert.assertNotNull(escuelaEntidad.getClaveCentroTrabajo());
		Assert.assertNotNull(escuelaEntidad.getNombre());
		Assert.assertNotNull(escuelaEntidad.getMotivoBloqueoEntidad().getValor());

	}

	@Test
	public void actualiza() throws SystemException {
		CriteriaUpdate<EscuelaEntidad> criteriaUpdate = entityManager.getCriteriaBuilder().createCriteriaUpdate(EscuelaEntidad.class);
		Root<EscuelaEntidad> root = criteriaUpdate.from(EscuelaEntidad.class);
		criteriaUpdate.set(root.get("nombre"), "ACADEMIA ESTATAL de las FUERZAS de SEGURIDAD PUBLICA del ESTADO DE P");
		criteriaUpdate.set(root.get("domicilio"), "Calle siempre muerta 13485");
		criteriaUpdate.set(root.get("numeroExterior"), "1111");
		criteriaUpdate.set(root.get("numeroInterior"), "1111");
		try {
			userTransaction.begin();
			int actualizacion = entityManager.createQuery(criteriaUpdate).executeUpdate();
			userTransaction.rollback();
			Assert.assertFalse(actualizacion == 0);
		} catch (Exception ex) {
			userTransaction.rollback();
		}
	}

	@Test
	public void borra() throws SystemException {
 		EscuelaEntidad escuelaEntidad = new EscuelaEntidad("0000000000");
 		try {
 			userTransaction.begin();
			entityManager.remove(escuelaEntidad);
			userTransaction.commit();
		} catch (Exception ex) {
 			userTransaction.rollback();
		}
	}

}