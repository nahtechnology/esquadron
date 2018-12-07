package tecolotl.administracion.persistencia.entidad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
			Assert.assertNotNull("No encontro fecha de inicio",licenciaEntidad.getInicio());
			Assert.assertNotNull("No encontro id",licenciaEntidad.getId());
			Assert.assertNotNull(licenciaEntidad.getEscuela());
			Assert.assertNotNull("No encontro clave de trabajo", licenciaEntidad.getEscuela().getClaveCentroTrabajo());
			Assert.assertNotNull(licenciaEntidad.getIdEscuela());
		}
	}

	@Test
	public void buscaCuentaEscuela() {
		Query query = entityManager.createQuery("SELECT l.idEscuela, COUNT (l.idEscuela), MAX(l.inicio) FROM LicenciaEntidad l GROUP BY l.idEscuela");
		List<Object[]> respuesta = query.getResultList();
		Assert.assertNotNull(respuesta);
		for (Object[] objectos : respuesta) {
			for (Object objecto : objectos) {
				Assert.assertNotNull(objecto);
			}
		}
	}
}