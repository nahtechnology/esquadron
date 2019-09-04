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
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.relacionar.RelacionarEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalBaseModelo;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
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
                .addPackage(TareaGramaticaEntidad.class.getPackage())
                .addPackage(GramaticaModelo.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(RelacionarEntidad.class.getPackage())
                .addPackage(RelacionarModelo.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Test
    public void busca(){
        List<TareaModelo> tareaModeloLista = tareaSesionBean.busca(1);
        Assert.assertNotNull(tareaModeloLista);
        Assert.assertFalse(tareaModeloLista.isEmpty());
        for (TareaModelo tareaModelo : tareaModeloLista){
            Assert.assertNotNull(tareaModelo);
            Assert.assertNotNull(tareaModelo.getId());
            Assert.assertNotNull(tareaModelo.getAsignacion());
            Assert.assertNotNull(tareaModelo.getReproducciones());
        }
    }


}
