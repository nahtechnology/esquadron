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
import tecolotl.alumno.Modelo.TareaModelo;
import tecolotl.alumno.entidad.*;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.PersonaSesionBean;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(PersonaModelo.class.getPackage())
                .addPackage(AlumnoModelo.class.getPackage())
                .addPackage(TareaModelo.class.getPackage())
                .addPackage(PersonaEntidad.class.getPackage())
                .addClasses(PersonaEntidad.class, CatalagoEntidad.class, GradoEscolarEntidad.class,
                        AlumnoEntidad.class, TareaEntidad.class, TipoEstudianteEntidad.class,
                        NivelLenguajeEntidad.class, PersonaSesionBean.class, AlumnoSesionBean.class,
                        TareaSesionBean.class, GradoEscolarSesionBean.class, LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Inject
    private TareaSesionBean tareaSesionBean;

    /*@Test
    public void busca(){
        List<TareaModelo> tareaModeloLista = tareaSesionBean.busca();
        Assert.assertNotNull(tareaModeloLista);
        Assert.assertFalse(tareaModeloLista.isEmpty());
        for (TareaModelo tareaModelo : tareaModeloLista){
            Assert.assertNotNull(tareaModelo);
            Assert.assertNotNull(tareaModelo.getId());
            Assert.assertNotNull(tareaModelo.getAlumnoEntidad());
            Assert.assertNotNull(tareaModelo.getAsignacion());
        }
    }*/

    @Test
    public void busca2(){
        TareaModelo tareaModelo = tareaSesionBean.busca(2);
        Assert.assertNotNull(tareaModelo);
        Assert.assertNotNull(tareaModelo.getId());
        Assert.assertNotNull(tareaModelo.getAlumnoEntidad());
        Assert.assertNotNull(tareaModelo.getAsignacion());
    }

    @Test
    public void inserta(){
        /*TareaModelo tareaModelo = new TareaModelo(3, , new Date(2018, 10, 13));
        tareaSesionBean.inserta(tareaModelo);
        Assert.assertNotNull(tareaModelo);*/
    }

    @Test
    public void elimina(){
        TareaModelo tareaModelo = new TareaModelo(new TareaEntidad());
        tareaSesionBean.elimina(5);
        Assert.assertNotNull(tareaModelo);
    }

    @Test
    public void actualiza(){
        TareaModelo tareaModelo = new TareaModelo(new TareaEntidad());
        tareaModelo.setAsignacion(new Date());
        int elemModificados = tareaSesionBean.actualiza(tareaModelo);
        Assert.assertFalse(elemModificados == 0);
    }
}
