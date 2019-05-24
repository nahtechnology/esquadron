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
import tecolotl.alumno.Modelo.GradoEscolarModelo;
import tecolotl.alumno.entidad.*;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.PersonaSesionBean;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class GradoEscolarSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(GradoEscolarModelo.class.getPackage())
            .addPackage(AlumnoModelo.class.getPackage())
            .addPackage(PersonaModelo.class.getPackage())
            .addPackage(PersonaEntidad.class.getPackage())
            .addClasses(AlumnoEntidad.class, GradoEscolarEntidad.class, PersonaEntidad.class,
                    PersonaSesionBean.class, AlumnoSesionBean.class, GradoEscolarSesionBean.class,
                    CatalagoEntidad.class, NivelLenguajeEntidad.class, ActividadEntidad.class,
                    TipoEstudianteEntidad.class)
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GradoEscolarSesionBean gradoEscolarSesionBean;

    @Test
    public void busca(){
        List<GradoEscolarModelo> gradoEscolarModeloLista = gradoEscolarSesionBean.busca();
        Assert.assertNotNull(gradoEscolarModeloLista);
        Assert.assertFalse(gradoEscolarModeloLista.isEmpty());
        for (GradoEscolarModelo gradoEscolarModelo : gradoEscolarModeloLista){
            Assert.assertNotNull(gradoEscolarModelo);
            Assert.assertNotNull(gradoEscolarModelo.getId());
            Assert.assertNotNull(gradoEscolarModelo.getNivel());
            Assert.assertNotNull(gradoEscolarModelo.getGrado());
        }
    }
}
