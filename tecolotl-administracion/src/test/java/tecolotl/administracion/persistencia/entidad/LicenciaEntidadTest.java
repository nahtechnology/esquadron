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

public class LicenciaEntidadTest {

	 @Deployment
	   public static Archive<?> createDeployment() {
	       return ShrinkWrap.create(WebArchive.class, "test.war")
	               .addClass(LicenciaEntidad.class)
	               .addPackage(EscuelaEntidad.class.getPackage())
	               .addAsResource("META-INF/persistence.xml")
	               .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	   }
	 
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void busca() {
		TypedQuery<LicenciaEntidad> typedQuery = entityManager.createNamedQuery("LicenciaEntidad.busca", LicenciaEntidad.class);
		List<LicenciaEntidad> licenciaLista = typedQuery.getResultList();
		Assert.assertNotNull(licenciaLista);
		Assert.assertFalse(licenciaLista.isEmpty());
		for (LicenciaEntidad licenciaEntidad: licenciaLista) {
			Assert.assertNotNull("No encontro fecha de inicio",licenciaEntidad.getFechaInicio());
			Assert.assertNotNull("No encontro id",licenciaEntidad.getId());
			Assert.assertNotNull("No encontro clave de trabajo", licenciaEntidad.getIdEscuela().getClaveCentroTrabajo());
		}
	}
}

