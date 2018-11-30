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

public class EscuelaEntidadTest {

	 @Deployment
	    public static Archive<?> createDeployment() {
	        return ShrinkWrap.create(WebArchive.class, "test.war")
	                .addPackage(EscuelaEntidad.class.getPackage())
	                .addAsResource("META-INF/persistence.xml")
	                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	    }
		 
		@PersistenceContext
		private EntityManager entityManager;
		
		@Test
		public void busca() {
			TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.busca", EscuelaEntidad.class);
			List<EscuelaEntidad> escuelaEntidadLista = typedQuery.getResultList();
			Assert.assertNotNull(escuelaEntidadLista);
			Assert.assertFalse(escuelaEntidadLista.isEmpty());
			for (EscuelaEntidad motivoBloqueoEntidad : escuelaEntidadLista) {
				Assert.assertNotNull(motivoBloqueoEntidad.getClaveCentroTrabajo());
				Assert.assertNotNull(motivoBloqueoEntidad.getDomicilio());
				Assert.assertNotNull(motivoBloqueoEntidad.getNombre());
				Assert.assertNotNull(motivoBloqueoEntidad.getMotivoBloqueoEntidad());
				Assert.assertNotNull(motivoBloqueoEntidad.getColoniaEntidad());
				
			}
		}
	}