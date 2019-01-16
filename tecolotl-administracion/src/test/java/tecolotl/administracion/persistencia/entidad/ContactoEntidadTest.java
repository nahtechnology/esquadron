package tecolotl.administracion.persistencia.entidad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)

public class ContactoEntidadTest {
	 @Deployment
	    public static Archive<?> createDeployment() {
	        return ShrinkWrap.create(WebArchive.class, "test.war")
	                .addClass(ContactoEntidad.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	    }
		 
		@PersistenceContext
		private EntityManager entityManager;
		
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
	}
