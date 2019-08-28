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
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalBaseModelo;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
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
        MapaMentalBaseModelo mapaMentalBaseModelo1 = new MapaMentalBaseModelo(1);
        MapaMentalBaseModelo mapaMentalBaseModelo2 = new MapaMentalBaseModelo(2);
        GlosarioModelo glosarioModelo1 = new GlosarioModelo("bandit");
        GlosarioModelo glosarioModelo2 = new GlosarioModelo("tipper");
        TareaModelo tareaModelo = new TareaModelo();
        tareaModelo.setMapaMentalBaseModeloLista(Arrays.asList(mapaMentalBaseModelo1, mapaMentalBaseModelo2));
        tareaModelo.setGlosarioModeloLista(Arrays.asList(glosarioModelo1, glosarioModelo2));
        tareaSesionBean.inserta(tareaModelo, "JcMtWwiyzpU");
        Assert.assertNotNull(tareaModelo.getId());
        Assert.assertNotEquals(tareaModelo.getId(), Long.valueOf(0));
    }
}
