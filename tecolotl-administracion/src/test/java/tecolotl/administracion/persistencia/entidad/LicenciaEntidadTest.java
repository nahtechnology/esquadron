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
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

@RunWith(Arquillian.class)
public class LicenciaEntidadTest {

	 @Deployment
	   public static Archive<?> createDeployment() {
	       return ShrinkWrap.create(WebArchive.class, "test.war")
	               .addClass(CatalagoEntidad.class)
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
			Assert.assertNotNull(licenciaEntidad.getAdquisicion());
			Assert.assertNotNull(licenciaEntidad.getInicio());
			LicenciaEntidadPk licenciaEntidadPk = licenciaEntidad.getLicenciaEntidadPk();
			Assert.assertNotNull(licenciaEntidadPk.getContador());
			Assert.assertNotNull(licenciaEntidadPk.getEscuelaEntidad());
		}
	}

	@Test
	public void buscaCuentaEscuela() {
		Query query = entityManager.createQuery("SELECT l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo, " +
				"COUNT (l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo), MAX(l.inicio) FROM LicenciaEntidad l " +
				"GROUP BY l.licenciaEntidadPk.escuelaEntidad.claveCentroTrabajo");
		List<Object[]> respuesta = query.getResultList();
		Assert.assertNotNull(respuesta);
		for (Object[] objectos : respuesta) {
			for (Object objecto : objectos) {
				Assert.assertNotNull(objecto);
			}
		}
	}

}