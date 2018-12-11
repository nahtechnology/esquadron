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
import tecolotl.administracion.dto.EscuelaDto;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import java.util.Collection;

@RunWith(Arquillian.class)
public class EscuelaSesionBeanTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(EscuelaSesionBean.class.getPackage())
                .addPackage(ColoniaEntidad.class.getPackage())
                .addPackage(ColoniaDto.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	} 
	
	@Inject
	private EscuelaSesionBean escuelaSesionBean;

    @Test
    public void buscaEscuelas() {
		Collection<EscuelaDto> escuelaDtoCollection = escuelaSesionBean.busca();
		Assert.assertNotNull(escuelaDtoCollection);
		Assert.assertFalse(escuelaDtoCollection.isEmpty());
		for (EscuelaDto escuelaDto : escuelaDtoCollection) {
			Assert.assertNotNull(escuelaDto);
			Assert.assertNotNull(escuelaDto.getNombre());
			Assert.assertNotNull(escuelaDto.getClaveCentroTrabajo());
		}
    }

    @Test
    public void buscaColonia() {
    	ColoniaDto coloniaDto = escuelaSesionBean.busca("72000");
    	Assert.assertNotNull(coloniaDto);
    	Assert.assertNotNull(coloniaDto.getEstado());
    	Assert.assertNotNull(coloniaDto.getMunicipio());
    	Assert.assertFalse(coloniaDto.getListaCodigoPostal().isEmpty());
    	for (ColoniaDto.CodigoPostal codigoPostal : coloniaDto.getListaCodigoPostal()) {
			Assert.assertNotNull(codigoPostal.getCodigoPostal());
			Assert.assertNotNull(codigoPostal.getNombre());
		}
	}

    @Test
	public void insertar() {
		escuelaSesionBean.insertar("21DBA0004Z", 227805, "Escuela Secundaria Tenica no.32","5 poniente 512");
	}
    @Test
	public void actualizar() {
		escuelaSesionBean.actualizar("21DBA0004Z", 227805, "CAmbio Escuela Secundaria Tenica no.32","Cambio 5 poniente 512");
	}
    @Test
	public void bloqueo() {
		escuelaSesionBean.bloqueo("21DBA0004Z",2);
	}
    
    @Test
   	public void buscarDuplicado() {
   		escuelaSesionBean.buscaDuplicado("21DBA0004Z");
   	}
}



