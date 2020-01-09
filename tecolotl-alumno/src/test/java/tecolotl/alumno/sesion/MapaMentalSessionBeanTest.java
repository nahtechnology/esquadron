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
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
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
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.hablar.HablarModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalResueltoModelo;
import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;
import tecolotl.alumno.modelo.oraciones.OracionesModelo;
import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.scope.RelacionOracionRespuestaScope;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioLlavePrimariaValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirRespuestaValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
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
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class MapaMentalSessionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
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
                .addPackage(TareaOracionesModelo.class.getPackage())
                .addPackage(HablarModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private MapaMentalSessionBean mapaMentalSessionBean;

    @Inject
    private Logger logger;

    @Test
    public void buscaActividad() {
        List<MapaMentalModelo> mapaMentalBaseModeloLista = mapaMentalSessionBean.busca("0_1NU60qHWs");
        assertNotNull(mapaMentalBaseModeloLista);
        assertFalse(mapaMentalBaseModeloLista.isEmpty());
        mapaMentalBaseModeloLista.forEach(escribirBaseModelo -> {
            assertNotNull(escribirBaseModelo);
            assertNotNull(escribirBaseModelo.getCardinalidad());
            assertNotNull(escribirBaseModelo.getCodigo());
            assertNotNull(escribirBaseModelo.getPregunta());
        });
    }

    @Test
    public void buscaTarea() {
        List<TareaMapaMentalModelo> mapaMentalModeloLista = mapaMentalSessionBean.busca(UUID.fromString("72406be2-3710-4370-a892-98dfec006c9d"));
        assertNotNull(mapaMentalModeloLista);
        assertFalse(mapaMentalModeloLista.isEmpty());
        mapaMentalModeloLista.forEach(mapaMentalModelo -> {
            assertNotNull(mapaMentalModelo);
            assertNotNull(mapaMentalModelo.getPregunta());
            assertNotNull(mapaMentalModelo.getCardinalidad());
            assertNotNull(mapaMentalModelo.getCodigo());
            assertNotNull(mapaMentalModelo.getRespuesta());
            assertNotNull(mapaMentalModelo.getVuelta());
            assertNotNull(mapaMentalModelo.getHoraRespuesta());
        });
    }

    @Test
    public void respuesta() {
        TareaMapaMentalModelo tareaMapaMentalModelo = new TareaMapaMentalModelo();
        tareaMapaMentalModelo.setCodigo("e3f5ee64f2daf5919d752d107749155a");
        tareaMapaMentalModelo.setRespuesta("Respuesta nueva");
        tareaMapaMentalModelo.setCardinalidad((short)6);
        tareaMapaMentalModelo.setVuelta((short)0);
        mapaMentalSessionBean.respuesta(tareaMapaMentalModelo, UUID.fromString("72406be2-3710-4370-a892-98dfec006c9d"), "0_1NU60qHWs");
    }

    @Test
    public void buscaResuelto() {
        List<MapaMentalResueltoModelo> mapaMentalResueltoModeloLista = mapaMentalSessionBean.buscaResuelto(UUID.fromString("72406be2-3710-4370-a892-98dfec006c9d"));
        assertNotNull(mapaMentalResueltoModeloLista);
        assertFalse(mapaMentalResueltoModeloLista.isEmpty());
        mapaMentalResueltoModeloLista.forEach(mapaMentalResueltoModelo -> {
            assertNotNull(mapaMentalResueltoModelo);
            assertTrue(mapaMentalResueltoModelo.getCardinalidad() > 0);
            assertTrue(mapaMentalResueltoModelo.getTotalResueltos() > -1);
        });
    }

    @Test
    public void buscaIdTareaCardinalidad() {
        List<TareaMapaMentalModelo> tareaMapaMentalModeloLista = mapaMentalSessionBean.busca(UUID.fromString("72406be2-3710-4370-a892-98dfec006c9d"), (short)6);
        assertNotNull(tareaMapaMentalModeloLista);
        assertFalse(tareaMapaMentalModeloLista.isEmpty());
        tareaMapaMentalModeloLista.forEach(tareaMapaMentalModelo -> {
            assertNotNull(tareaMapaMentalModelo);
            assertNotNull(tareaMapaMentalModelo.getCodigo());
            assertNotNull(tareaMapaMentalModelo.getCardinalidad());
            assertNotNull(tareaMapaMentalModelo.getPregunta());
        });
    }

}
