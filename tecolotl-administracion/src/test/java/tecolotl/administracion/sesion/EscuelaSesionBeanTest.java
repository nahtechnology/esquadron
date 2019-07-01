package tecolotl.administracion.sesion;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.*;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import java.util.Collection;

@RunWith(Arquillian.class)
public class EscuelaSesionBeanTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ColoniaEntidad.class.getPackage())
				.addPackage(EscuelaBaseModelo.class.getPackage())
				.addPackage(ColoniaModelo.class.getPackage())
				.addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
				.addPackage(ColoniaNuevaValidacion.class.getPackage())
				.addClasses(CatalagoEntidad.class, CatalogoModelo.class, PersonaEntidad.class,
						EscuelaSesionBean.class, LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	} 
	
	@Inject
	private EscuelaSesionBean escuelaSesionBean;

	@Test
	public void buscaVacio() {
		Collection<EscuelaDashboardModelo> escuelaDashboardModeloColeccion = escuelaSesionBean.busca();
		Assert.assertNotNull(escuelaDashboardModeloColeccion);
		Assert.assertTrue(escuelaDashboardModeloColeccion.isEmpty());
	}

	@Test
	public void busca() {
		Collection<EscuelaDashboardModelo> escuelaDashboardModeloColeccion = escuelaSesionBean.busca();
		Assert.assertNotNull(escuelaDashboardModeloColeccion);
		Assert.assertFalse(escuelaDashboardModeloColeccion.isEmpty());
		for (EscuelaDashboardModelo escuelaDashboardModelo : escuelaDashboardModeloColeccion) {
			Assert.assertNotNull(escuelaDashboardModelo);
			Assert.assertNotNull(escuelaDashboardModelo.getClaveCentroTrabajo());
			Assert.assertNotNull(escuelaDashboardModelo.getNombre());
			Assert.assertNotNull(escuelaDashboardModelo.getDomicilio());
			Assert.assertNotNull(escuelaDashboardModelo.getNumeroExterior());
			Assert.assertTrue(escuelaDashboardModelo.getDiasRestantes() > -1);
			Assert.assertTrue(escuelaDashboardModelo.getLicencias() > -1);
		}
	}

	@Test
	public void actualiza() {
		EscuelaDetalleModelo escuelaDetalleModelo = new EscuelaDetalleModelo("0000000000");
		escuelaDetalleModelo.setNombre("ACADEMIA ESTATAL de las FUERZAS de SEGURIDAD PUBLICA del ESTADO de P");
		escuelaDetalleModelo.setDomicilio("calle siempre viva");
		escuelaDetalleModelo.setNumeroExterior("9999");
		escuelaDetalleModelo.setNumeroInterior("1111");
		escuelaDetalleModelo.setColoniaModelo(new ColoniaModelo(4));
		int modificados = escuelaSesionBean.actualizar(escuelaDetalleModelo);
		Assert.assertTrue(modificados > 0);
	}

	@Test
	public void inserta() {
		EscuelaDetalleModelo escuelaDetalleModelo = new EscuelaDetalleModelo("0000000000");
		escuelaDetalleModelo.setNombre("ACADEMIA ESTATAL DE LAS FUERZAS DE SEGURIDAD PUBLICA DEL ESTADO DE P");
		escuelaDetalleModelo.setDomicilio("Calle siempre viva 13485");
		escuelaDetalleModelo.setNumeroExterior("9999");
		escuelaDetalleModelo.setColoniaModelo(new ColoniaModelo(3));
		escuelaSesionBean.inserta(escuelaDetalleModelo);
	}

	@Test
	public void insertaNull() {
		escuelaSesionBean.inserta(null);
	}

	@Test
	public void elimina() {
		int elimina = escuelaSesionBean.elimina("0000000000");
		Assert.assertFalse(elimina == 0);
	}

	@Test
	public void eliminaNull() {
		escuelaSesionBean.elimina(null);
	}

	@Test
	public void bloqueo() {
		EscuelaBaseModelo escuelaBaseModelo = new EscuelaBaseModelo("0000000000");
		MotivoBloqueoModelo motivoBloqueoModelo = new MotivoBloqueoModelo((short)0);
		escuelaSesionBean.bloqueo(escuelaBaseModelo, motivoBloqueoModelo);

	}

}



