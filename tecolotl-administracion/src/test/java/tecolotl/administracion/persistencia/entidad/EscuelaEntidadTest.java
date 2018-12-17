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
		for (EscuelaEntidad escuelaEntidad : escuelaEntidadLista) {
			Assert.assertNotNull(escuelaEntidad.getClaveCentroTrabajo());
			Assert.assertNotNull(escuelaEntidad.getDomicilio());
			Assert.assertNotNull(escuelaEntidad.getNombre());
			Assert.assertNotNull(escuelaEntidad.getMotivoBloqueoEntidad());
			Assert.assertNotNull(escuelaEntidad.getColoniaEntidad());
			Assert.assertNotNull(escuelaEntidad.getNumeroExterior());
		}
	}

	@Test
	public void buscaDetalle() {
		TypedQuery<EscuelaEntidad> typedQuery = entityManager.createNamedQuery("EscuelaEntidad.buscaDesatalle", EscuelaEntidad.class);
		typedQuery.setParameter("claveCentroTrabajo", "21DBA0034X");
		EscuelaEntidad escuelaEntidad = typedQuery.getSingleResult();
		Assert.assertNotNull(escuelaEntidad);
		Assert.assertNotNull(escuelaEntidad.getClaveCentroTrabajo());
		Assert.assertNotNull(escuelaEntidad.getNumeroExterior());
		Assert.assertNotNull(escuelaEntidad.getMotivoBloqueoEntidad());
		Assert.assertNotNull(escuelaEntidad.getMotivoBloqueoEntidad().getDescripcion());
		Assert.assertNotNull(escuelaEntidad.getColoniaEntidad());
		Assert.assertNotNull(escuelaEntidad.getColoniaEntidad().getNombre());
		Assert.assertNotNull(escuelaEntidad.getColoniaEntidad().getMunicipio());
		Assert.assertNotNull(escuelaEntidad.getColoniaEntidad().getMunicipio().getEstado());
		Assert.assertNotNull(escuelaEntidad.getColoniaEntidad().getMunicipio().getEstado().getNombre());
		Assert.assertNotNull(escuelaEntidad.getDomicilio());
		List<LicenciaEntidad> licenciaEntidadLista = escuelaEntidad.getLicencia();
		Assert.assertNotNull(licenciaEntidadLista);
		Assert.assertFalse(licenciaEntidadLista.isEmpty());
		for (LicenciaEntidad licenciaEntidad : licenciaEntidadLista) {
			Assert.assertNotNull(licenciaEntidad);
			Assert.assertNotNull(licenciaEntidad.getInicio());
		}
	}
}