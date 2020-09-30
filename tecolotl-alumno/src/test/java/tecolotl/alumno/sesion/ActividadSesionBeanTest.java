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
import tecolotl.alumno.entidad.hablar.HablarEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidadPK;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.modelo.TemaModelo;
import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.hablar.HablarModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.oraciones.OracionesModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.TareaRelacionarOracionRespuestaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ActividadSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(TareaGramaticaEntidad.class.getPackage())
                .addPackage(GramaticaModelo.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage())
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
                .addPackage(OracionesEntidad.class.getPackage())
                .addPackage(OracionesModelo.class.getPackage())
                .addPackage(TareaRelacionarOracionesEntidad.class.getPackage())
                .addPackage(TareaRelacionarOracionModelo.class.getPackage())
                .addPackage(TareaRelacionarOracionRespuestaValidacion.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(HablarEntidad.class.getPackage())
                .addPackage(HablarModelo.class.getPackage())
                .addPackage(TareaCompletarModelo.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidadPK.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ActividadSesionBean actividadSesionBean;

    @Inject
    private Logger logger;

    @Test
    public void buscaTodos() {
        List<ActividadModelo> actividadModeloLista = actividadSesionBean.busca();
        assertNotNull(actividadModeloLista);
        assertFalse(actividadModeloLista.isEmpty());
        actividadModeloLista.forEach(actividadModelo -> {
            assertNotNull(actividadModelo);
            assertNotNull(actividadModelo.getIdVideo());
            assertNotNull(actividadModelo.getLenguaje());
            assertNotNull(actividadModelo.getPreguntaDetonadora());
            assertNotNull(actividadModelo.getTiempo());
            assertNotNull(actividadModelo.getPuntaje());
            assertNotNull(actividadModelo.getTemaModelo());
            assertNotNull(actividadModelo.getTemaModelo().getValor());
            assertNotNull(actividadModelo.getTipoEstudianteModelo());
            assertNotNull(actividadModelo.getTipoEstudianteModelo().getValor());
            assertNotNull(actividadModelo.getNivelLenguajeModeloLista());
            actividadModelo.getNivelLenguajeModeloLista().forEach(nivelLenguajeModelo -> {
                assertNotNull(nivelLenguajeModelo);
                assertNotNull(nivelLenguajeModelo.getValor());
            });
        });
    }

    @Test
    public void busca() {
        List<ActividadModelo> actividadModeloLista = actividadSesionBean.busca("A1");
        assertNotNull(actividadModeloLista);
        assertFalse(actividadModeloLista.isEmpty());
        actividadModeloLista.forEach(actividadModelo -> {
            assertNotNull(actividadModelo);
            assertNotNull(actividadModelo.getIdVideo());
            assertNotNull(actividadModelo.getLenguaje());
            assertNotNull(actividadModelo.getPreguntaDetonadora());
            assertNotNull(actividadModelo.getTiempo());
            assertNotNull(actividadModelo.getPuntaje());
            assertNotNull(actividadModelo.getTemaModelo());
            assertNotNull(actividadModelo.getTemaModelo().getValor());
            assertNotNull(actividadModelo.getTipoEstudianteModelo());
            assertNotNull(actividadModelo.getTipoEstudianteModelo().getValor());
            assertNotNull(actividadModelo.getNivelLenguajeModeloLista());
            actividadModelo.getNivelLenguajeModeloLista().forEach(nivelLenguajeModelo -> {
                assertNotNull(nivelLenguajeModelo);
                assertNotNull(nivelLenguajeModelo.getValor());
            });
        });
    }

    @Test
    public void buscaPalabra() {
        List<ActividadModelo> actividadModeloLista = actividadSesionBean.buscaLenguaje("simple");
        assertNotNull(actividadModeloLista);
        assertFalse(actividadModeloLista.isEmpty());
        actividadModeloLista.forEach(actividadModelo -> {
            assertNotNull(actividadModelo);
            assertNotNull(actividadModelo.getIdVideo());
            assertNotNull(actividadModelo.getLenguaje());
            assertNotNull(actividadModelo.getPreguntaDetonadora());
            assertNotNull(actividadModelo.getTiempo());
            assertNotNull(actividadModelo.getPuntaje());
            assertNotNull(actividadModelo.getTemaModelo());
            assertNotNull(actividadModelo.getTemaModelo().getValor());
            assertNotNull(actividadModelo.getTipoEstudianteModelo());
            assertNotNull(actividadModelo.getTipoEstudianteModelo().getValor());
            assertNotNull(actividadModelo.getNivelLenguajeModeloLista());
            actividadModelo.getNivelLenguajeModeloLista().forEach(nivelLenguajeModelo -> {
                assertNotNull(nivelLenguajeModelo);
                assertNotNull(nivelLenguajeModelo.getValor());
            });
        });
    }

    @Test
    public void transcripcion() {
        String transcripcion = actividadSesionBean.transcripcion("DNHmujbuC74");
        assertNotNull(transcripcion);
        assertTrue(transcripcion.length() < 2);
    }

    @Test
    public void buscaIdActividad() {
        List<ActividadModelo> actividadModeloList = actividadSesionBean.busca(UUID.fromString("f7e4c1fe-d444-4186-a331-78cfeb04b209"));
        assertNotNull(actividadModeloList);
        logger.info(actividadModeloList.toString());
    }

    @Test
    public void agrega() {
        ActividadModelo actividadModelo = new ActividadModelo();
        actividadModelo.setIdVideo("JcMtWwidzpU");
        actividadModelo.setPuntaje((short)100);
        actividadModelo.setLenguaje("Adjectives");
        actividadModelo.setTiempo(100);
        actividadModelo.setTipoEstudianteModelo(new TipoEstudianteModelo((short)1));
        actividadModelo.setPreguntaDetonadora("Where is are my $50,000 DLS?");
        actividadModelo.setTranscripcion("<span class=\"personaje\">Poppy:</span> Your eyes… they’re… Ugh! Oh! Your ears…<br><span class=\"personaje\">Bridget:</span>");
        actividadModelo.setTemaModelo(new TemaModelo((short)1));
        NivelLenguajeModelo nivelLenguajeModelo = new NivelLenguajeModelo((short)1);
        actividadModelo.setNivelLenguajeModeloLista(Arrays.asList(nivelLenguajeModelo));
        actividadSesionBean.agrega(actividadModelo);
        assertNotNull(actividadModelo.getIdVideo());
    }

    @Test
    public void elimina() {
        actividadSesionBean.elimina("JcMtWwidzpU");
    }

}
