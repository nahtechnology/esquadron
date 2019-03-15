package tecolotl.administracion.persistencia.entidad;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.NotSupportedException;
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
public class TipoContactoEntidadTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
			.addClasses(TipoContactoEntidad.class, CatalagoEntidad.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Resource
	private UserTransaction userTransaction;

	@Test
	public void busca() {
		TypedQuery<TipoContactoEntidad> typedQuery = entityManager.createNamedQuery("TipoContactoEntidad.busca", TipoContactoEntidad.class);
		List<TipoContactoEntidad> tipoContactoEntidadLista = typedQuery.getResultList();
		Assert.assertNotNull(tipoContactoEntidadLista);
		Assert.assertFalse(tipoContactoEntidadLista.isEmpty());
		for (TipoContactoEntidad tipoContactoEntidad: tipoContactoEntidadLista) {
			Assert.assertNotNull(tipoContactoEntidad.getValor());
			Assert.assertNotNull(tipoContactoEntidad.getClave());
		}
	}

	@Test
	public void inserta() throws SystemException {
		TipoContactoEntidad tipoContactoEntidad = entityManager.find(TipoContactoEntidad.class, (short)1);
		Assert.assertNotNull(tipoContactoEntidad);
		try {
			userTransaction.begin();
			tipoContactoEntidad.setValor("Valor nuevo");
			userTransaction.rollback();
		} catch (NotSupportedException | SystemException e) {
			userTransaction.rollback();
		}
	}
}