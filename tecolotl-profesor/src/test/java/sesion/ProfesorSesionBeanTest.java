package sesion;

import modelo.ProfesorModelo;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.persistencia.entidad.*;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.GradoEscolarEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.GrupoEntidadPK;
import tecolotl.profesor.entidad.ProfesorEntidad;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class ProfesorSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(PersonaModelo.class.getPackage())
            .addPackage(ProfesorModelo.class.getPackage())
            .addPackage(ProfesorEntidad.class.getPackage())
            .addClasses(PersonaModelo.class, PersonaEntidad.class, ProfesorEntidad.class,
                        ProfesorModelo.class, ProfesorSesionBean.class, EscuelaEntidad.class,
                        EscuelaBaseModelo.class, EscuelaSesionBean.class, EscuelaDetalleModelo.class,
                        MotivoBloqueoModelo.class, ColoniaEntidad.class, MunicipioEntidad.class,
                        EstadoEntidad.class, MotivoBloqueoEntidad.class, CatalagoEntidad.class,
                        CatalogoModelo.class, ContactoEntidad.class, ContactoEntidadPK.class,
                        TipoContactoEntidad.class, LicenciaEntidad.class, LicenciaEntidadPk.class,
                        AlumnoEntidad.class, GrupoEntidad.class, GrupoEntidadPK.class,
                        NivelLenguajeEntidad.class, GradoEscolarEntidad.class, EscuelaDashboardModelo.class,
                LoggerProducer.class)
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Test
    public void busca(){
        List<ProfesorModelo> profesorModeloLista = profesorSesionBean.busca();
        Assert.assertNotNull(profesorModeloLista);
        Assert.assertFalse(profesorModeloLista.isEmpty());
        for (ProfesorModelo profesorModelo : profesorModeloLista){
            Assert.assertNotNull(profesorModelo);
            Assert.assertNotNull(profesorModelo.getId());
            Assert.assertNotNull(profesorModelo.getEscuelaBaseModelo());
        }
    }

}
