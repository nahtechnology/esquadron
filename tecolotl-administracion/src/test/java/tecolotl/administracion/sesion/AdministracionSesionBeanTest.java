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

import tecolotl.administracion.dto.ColoniaDto;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

@RunWith(Arquillian.class)
public class AdministracionSesionBeanTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(AdministracionSesionBean.class.getPackage())
                .addPackage(ColoniaEntidad.class.getPackage())
                .addClass(ColoniaDto.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	} 
	
	@Inject
	private AdministracionSesionBean administracionSesionBean;

    @Test
    public void buscaCodigoPostal() {
    	ColoniaDto coloniaDto;
    	coloniaDto = administracionSesionBean.buscar("72810");
    	Assert.assertNotNull(coloniaDto);
    	Assert.assertNotNull(coloniaDto.getEstado());
    	Assert.assertNotNull(coloniaDto.getMunicipio());
    	Assert.assertFalse(coloniaDto.getListaCodigoPostal().isEmpty());
    }

    @Test
    public void buscaCodigoPostalNull() {
    	ColoniaDto coloniaDto = administracionSesionBean.buscar(null);
    	Assert.assertNull(coloniaDto);
    }
}



