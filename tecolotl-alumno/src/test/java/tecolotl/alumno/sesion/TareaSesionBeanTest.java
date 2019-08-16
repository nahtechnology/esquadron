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
import tecolotl.alumno.entidad.escribir.EscribirActividadEntidad;
import tecolotl.alumno.entidad.escribir.EscribirEntidad;
import tecolotl.alumno.entidad.escribir.TareaEscribirActividadEntidad;
import tecolotl.alumno.entidad.escribir.TareaEscribirActividadEntidadPK;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.escribir.EscribirBaseModelo;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.modelo.escribir.EscribirModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirNuevoValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.sesion.PersonaSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(EscribirEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(EscribirModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Test
    public void busca(){
        List<TareaModelo> tareaModeloLista = tareaSesionBean.busca();
        Assert.assertNotNull(tareaModeloLista);
        Assert.assertFalse(tareaModeloLista.isEmpty());
        for (TareaModelo tareaModelo : tareaModeloLista){
            Assert.assertNotNull(tareaModelo);
        }
    }

    @Test
    public void inserta(){
        TareaModelo tareaModelo = new TareaModelo();
        tareaSesionBean.inserta(tareaModelo.getId());
        Assert.assertNotNull(tareaModelo);
        Assert.assertNotNull(tareaModelo.getId());
    }

    @Test
    public void insertaCascada() {
        EscribirBaseModelo escribirBaseModelo1 = new EscribirBaseModelo(1);
        EscribirBaseModelo escribirBaseModelo2 = new EscribirBaseModelo(2);
        GlosarioModelo glosarioModelo1 = new GlosarioModelo("bandit");
        GlosarioModelo glosarioModelo2 = new GlosarioModelo("tipper");
        TareaModelo tareaModelo = new TareaModelo();
        tareaModelo.setEscribirBaseModeloLista(Arrays.asList(escribirBaseModelo1, escribirBaseModelo2));
        tareaModelo.setGlosarioModeloLista(Arrays.asList(glosarioModelo1, glosarioModelo2));
        tareaSesionBean.inserta(tareaModelo, "JcMtWwiyzpU");
        Assert.assertNotNull(tareaModelo.getId());
        Assert.assertNotEquals(tareaModelo.getId(), Long.valueOf(0));
    }
}
