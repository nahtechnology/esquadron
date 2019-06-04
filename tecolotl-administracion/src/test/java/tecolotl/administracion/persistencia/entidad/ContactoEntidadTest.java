package tecolotl.administracion.persistencia.entidad;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
public class ContactoEntidadTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(ContactoEntidad.class.getPackage())
				.addClass(CatalagoEntidad.class)
				.addAsResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private UserTransaction userTransaction;

	@Test
	public void busca() {
		TypedQuery<ContactoEntidad> typedQuery = entityManager.createNamedQuery("ContactoEntidad.busca", ContactoEntidad.class);
		List<ContactoEntidad> contactoEntidadLista = typedQuery.getResultList();
		Assert.assertNotNull(contactoEntidadLista);
		Assert.assertFalse(contactoEntidadLista.isEmpty());
		for (ContactoEntidad contactoEntidad : contactoEntidadLista) {
			Assert.assertNotNull(contactoEntidad.getTelefono());
			Assert.assertNotNull(contactoEntidad.getContactoEntidadPK());
			Assert.assertNotNull(contactoEntidad.getNombre());
		}
	}

	@Test
	public void buscaPorCCT() {
		TypedQuery<ContactoEntidad> typedQuery = entityManager.createNamedQuery("ContactoEntidad.buscaCCT", ContactoEntidad.class);
		typedQuery.setParameter("claveCentroTrabajo", "0000000000");
		List<ContactoEntidad> contactoEntidadLista = typedQuery.getResultList();
		Assert.assertNotNull(contactoEntidadLista);
		Assert.assertNotNull(contactoEntidadLista.isEmpty());
		for (ContactoEntidad contactoEntidad: contactoEntidadLista) {
			Assert.assertNotNull(contactoEntidad.getTelefono());
			Assert.assertNotNull(contactoEntidad.getNombre());
			Assert.assertNotNull(contactoEntidad.getCorreoElectronico());
			Assert.assertNotNull(contactoEntidad.getTipoContactoEntidad());
			Assert.assertNotNull(contactoEntidad.getTipoContactoEntidad().getValor());
			ContactoEntidadPK contactoEntidadPK = contactoEntidad.getContactoEntidadPK();
			Assert.assertNotNull(contactoEntidadPK.getEscuelaEntidad().getClaveCentroTrabajo());
			Assert.assertNotNull(contactoEntidadPK.getContador());
		}
	}

	@Test
	public void guarda() throws SystemException {
		ContactoEntidadPK contactoEntidadPK = new ContactoEntidadPK();
		contactoEntidadPK.setEscuelaEntidad(new EscuelaEntidad("0000000000"));
		//contactoEntidadPK.setTipoContactoEntidad(new TipoContactoEntidad((short)1));
		ContactoEntidad contactoEntidad = new ContactoEntidad();
		contactoEntidad.setContactoEntidadPK(contactoEntidadPK);
		contactoEntidad.setCorreoElectronico("correo@servidor.com");
		contactoEntidad.setNombre("Estrada Analco José Isamel");
		contactoEntidad.setTelefono("2323126945");
		contactoEntidad.setTipoContactoEntidad(new TipoContactoEntidad((short)1));
		try {
			userTransaction.begin();
			entityManager.persist(contactoEntidad);
			userTransaction.commit();
		} catch (Exception ex) {
			userTransaction.rollback();
		}
	}
}
