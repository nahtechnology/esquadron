package tecolotl.alumno.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.Modelo.AlumnoModelo;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.GradoEscolarEntidad;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.PersonaSesionBean;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class AlumnoSesionBeanTest {
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(AlumnoModelo.class.getPackage())
            .addPackage(PersonaEntidad.class.getPackage())
            .addPackage(PersonaModelo.class.getPackage())
            .addPackage(AlumnoEntidad.class.getPackage())
            .addClasses(AlumnoEntidad.class, PersonaEntidad.class, CatalagoEntidad.class, GradoEscolarEntidad.class,
            AlumnoSesionBean.class, PersonaSesionBean.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    @Test
    public void busca(){
        List<AlumnoModelo> alumnoModeloLista = alumnoSesionBean.busca();
        Assert.assertNotNull(alumnoModeloLista);
        Assert.assertFalse(alumnoModeloLista.isEmpty());
        for (AlumnoModelo alumnoModelo : alumnoModeloLista){
            Assert.assertNotNull(alumnoModelo);
            Assert.assertNotNull(alumnoModelo.getId());
            Assert.assertNotNull(alumnoModelo.getNivelLenguaje());
        }
    }

    @Test
    public void buscaID(){
        AlumnoModelo alumnoModelo = alumnoSesionBean.busca(5);
        Assert.assertNotNull(alumnoModelo);
    }
}
