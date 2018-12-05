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
public class LicenciaEscuelaEntidadTest {
	 @Deployment
	    public static Archive<?> createDeployment() {
	        return ShrinkWrap.create(WebArchive.class, "test.war")
	                .addClass(LicenciaEscuelaEntidad.class)
	                .addPackage(EscuelaEntidad.class.getPackage())
	                .addAsResource("META-INF/persistence.xml")
	                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	    }
		 
		@PersistenceContext
		private EntityManager entityManager;
		
		@Test
		public void busca() {
			TypedQuery<LicenciaEscuelaEntidad> typedQuery = entityManager.createNamedQuery("LicenciaEscuelaEntidad.buscaestatus", LicenciaEscuelaEntidad.class);
			List<LicenciaEscuelaEntidad> licenciaEscuelaLista = typedQuery.getResultList();
			Assert.assertNotNull(licenciaEscuelaLista);
			Assert.assertFalse(licenciaEscuelaLista.isEmpty());
	
		}
	}
