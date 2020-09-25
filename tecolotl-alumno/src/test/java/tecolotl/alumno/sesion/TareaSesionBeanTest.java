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
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.hablar.HablarEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidadPK;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.TareaActividadModelo;
import tecolotl.alumno.modelo.TareaAlumnoModelo;
import tecolotl.alumno.modelo.TareaModelo;
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.hablar.HablarModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.oraciones.OracionesModelo;
import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.scope.RelacionOracionRespuestaScope;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirRespuestaValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.TareaRelacionarOracionRespuestaValidacion;
import tecolotl.nucleo.herramienta.CorreoEnum;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RunWith(Arquillian.class)
public class TareaSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                //nucleo
                .addPackage(CorreoEnum.class.getPackage()).addPackage(CatalogoModelo.class.getPackage()).addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage()).addPackage(CatalogoNuevoValidacion.class.getPackage())
                //administracion

                //alumno
                .addPackage(MapaMentalActividadEntidad.class.getPackage()).addPackage(ClaseGlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage()).addPackage(MapaMentalModelo.class.getPackage()).addPackage(GlosarioModelo.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage()).addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage()).addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(RelacionarModelo.class.getPackage())
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(TareaGramaticaEntidad.class.getPackage())
                .addPackage(GramaticaModelo.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(GlosarioEntidad.class.getPackage())
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
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(GlosarioLlavePrimariaValidacion.class.getPackage())
                .addPackage(EscribirNuevoValidacion.class.getPackage())
                .addPackage(EscribirRespuestaValidacion.class.getPackage())
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(TareaRelacionarOracionRespuestaValidacion.class.getPackage())
                .addPackage(TareaOracionesModelo.class.getPackage())
                .addPackage(TareaOracionesEntidad.class.getPackage())
                .addPackage(OracionesModelo.class.getPackage())
                .addPackage(OracionesEntidad.class.getPackage())
                .addPackage(OracionesSesionBean.class.getPackage())
                .addPackage(LoggerProducer.class.getPackage())
                .addPackage(HablarEntidad.class.getPackage())
                .addPackage(HablarModelo.class.getPackage())
                .addPackage(TareaCompletarModelo.class.getPackage())
                .addPackage(TareaCompletarEntidad.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidadPK.class.getPackage())
                //profesor
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private TareaSesionBean tareaSesionBean;

    @Test
    public void busca() {
        List<TareaModelo> tareaModeloLista = tareaSesionBean.busca();
        Assert.assertNotNull(tareaModeloLista);
        Assert.assertFalse(tareaModeloLista.isEmpty());
        tareaModeloLista.forEach(tareaModelo -> {
            Assert.assertNotNull(tareaModelo);
            Assert.assertNotNull(tareaModelo.getId());
            Assert.assertNotNull(tareaModelo.getAsignacion());
            Assert.assertNotNull(tareaModelo.isResolviendoTranscript());
        });
    }

    @Test
    public void buscaID(){
        List<TareaModelo> tareaModeloLista = tareaSesionBean.busca(UUID.fromString("f7e4c1fe-d444-4186-a331-78cfeb04b209"));
        Assert.assertNotNull(tareaModeloLista);
        Assert.assertFalse(tareaModeloLista.isEmpty());
        for (TareaModelo tareaModelo : tareaModeloLista){
            Assert.assertNotNull(tareaModelo);
            Assert.assertNotNull(tareaModelo.getId());
            Assert.assertNotNull(tareaModelo.getAsignacion());
            Assert.assertNotNull(tareaModelo.getReproducciones());
        }
    }

    @Test
    public void buscaTareas() {
        List<TareaResuetasModelo> tareaResuetasModeloLista = tareaSesionBean.tareasResuelta(UUID.fromString("72406be2-3710-4370-a892-98dfec006c9d"));
        Assert.assertNotNull(tareaResuetasModeloLista);
        Assert.assertFalse(tareaResuetasModeloLista.isEmpty());
        tareaResuetasModeloLista.forEach(tareaResuetasModelo -> {
            Assert.assertNotNull(tareaResuetasModelo);
            Assert.assertNotNull(tareaResuetasModelo.getRespuesta());
            Assert.assertNotNull(tareaResuetasModelo.getTarea());
            Assert.assertNotNull(tareaResuetasModelo.getTotal());
        });
    }

    @Test
    public void buscaTareasDetalle() {
        List<TareaActividadModelo> tareaActividadModeloLista = tareaSesionBean.buscaActividad(UUID.fromString("f7e4c1fe-d444-4186-a331-78cfeb04b209"), "");
        Assert.assertNotNull(tareaActividadModeloLista);
        Assert.assertFalse(tareaActividadModeloLista.isEmpty());
        tareaActividadModeloLista.forEach(tareaResuetasModelo -> {
            Assert.assertNotNull(tareaResuetasModelo);
            Assert.assertNotNull(tareaResuetasModelo.getIdActividad());
            Assert.assertNotNull(tareaResuetasModelo.getId());
            Assert.assertNotNull(tareaResuetasModelo.getAsignacion());
            Assert.assertNotNull(tareaResuetasModelo.getReproducciones());
        });
    }

    @Test
    public void incrementaReproducciones() {
        int modificaciones = tareaSesionBean.reproducciones((short) 1, UUID.fromString("f7e4c1fe-d444-4186-a331-78cfeb04b209"));
        Assert.assertNotEquals(modificaciones, 0);
    }

    @Test
    public void decrementaReproducciones() {
        int modificaciones = tareaSesionBean.reproducciones((short) -1, UUID.fromString("f7e4c1fe-d444-4186-a331-78cfeb04b209"));
        Assert.assertNotEquals(modificaciones, 0);
    }

    @Test
    public void buscaCalificaciones(){
        List<TareaAlumnoModelo> tareaAlumnoModeloLista = tareaSesionBean.buscaCalificaciones(UUID.fromString("0cbaa96c-ba77-408d-b046-56e0fd1ffe56"));
        Assert.assertNotNull(tareaAlumnoModeloLista);
        Assert.assertFalse(tareaAlumnoModeloLista.isEmpty());
    }

}
