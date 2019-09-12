package tecolotl.alumno.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.relacionar.RelacionarEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.scope.RelacionOracionRespuestaScope;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class RelacionarOracionSesionBeanTest {

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
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(TareaResuetasModelo.class.getPackage())
                .addPackage(TareasResueltasEntidad.class.getPackage())
                .addPackage(TareaRelacionarOracionesEntidad.class.getPackage())
                .addPackage(TareaRelacionarOracionModelo.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(RelacionOracionRespuestaScope.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private RelacionarOracionSesionBean relacionarOracionSesionBean;

    @Inject
    private RelacionOracionRespuestaScope relacionOracionRespuestaScope;

    @Test
    public void busca() {
        List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista = relacionarOracionSesionBean.busca(5);
        assertNotNull(tareaRelacionarOracionModeloLista);
        assertFalse(tareaRelacionarOracionModeloLista.isEmpty());
        tareaRelacionarOracionModeloLista.forEach(tareaRelacionarOracionModelo -> {
            assertNotNull(tareaRelacionarOracionModelo);
            assertNotNull(tareaRelacionarOracionModelo.getIdTarea());
            assertNotNull(tareaRelacionarOracionModelo.getRelacionarOracionModelo());
            assertNotNull(tareaRelacionarOracionModelo.getRelacionarOracionModelo().getPregunta());
            assertNotNull(tareaRelacionarOracionModelo.getRelacionarOracionModelo().getRespuesta());
            assertNotNull(tareaRelacionarOracionModelo.getRelacionarOracionModelo().getId());
        });
    }

    @Test
    public void respuesta() {
        List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista = new ArrayList<>();
        TareaRelacionarOracionModelo tareaRelacionarOracionModelo = new TareaRelacionarOracionModelo(75, 5);
        tareaRelacionarOracionModelo.setRespuesta(80);
        tareaRelacionarOracionModeloLista.add(tareaRelacionarOracionModelo);
        tareaRelacionarOracionModelo = new TareaRelacionarOracionModelo(76, 5);
        tareaRelacionarOracionModelo.setRespuesta(79);
        tareaRelacionarOracionModeloLista.add(tareaRelacionarOracionModelo);
        tareaRelacionarOracionModelo = new TareaRelacionarOracionModelo(77, 5);
        tareaRelacionarOracionModelo.setRespuesta(78);
        tareaRelacionarOracionModeloLista.add(tareaRelacionarOracionModelo);
        tareaRelacionarOracionModelo = new TareaRelacionarOracionModelo(78, 5);
        tareaRelacionarOracionModelo.setRespuesta(77);
        tareaRelacionarOracionModeloLista.add(tareaRelacionarOracionModelo);
        tareaRelacionarOracionModelo = new TareaRelacionarOracionModelo(79, 5);
        tareaRelacionarOracionModelo.setRespuesta(76);
        tareaRelacionarOracionModeloLista.add(tareaRelacionarOracionModelo);
        tareaRelacionarOracionModelo = new TareaRelacionarOracionModelo(80, 5);
        tareaRelacionarOracionModelo.setRespuesta(75);
        tareaRelacionarOracionModeloLista.add(tareaRelacionarOracionModelo);
        relacionOracionRespuestaScope.respuesta(tareaRelacionarOracionModeloLista);
    }
}
