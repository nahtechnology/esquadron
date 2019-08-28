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
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.entidad.TemaEntidad;
import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.modelo.TemaModelo;
import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.validacion.CatalogoLlavePrimariaValidacion;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ActividadSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(ActividadSesionBean.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClasses(ActividadEntidad.class, TipoEstudianteEntidad.class, TemaEntidad.class, NivelLenguajeEntidad.class,
                        CatalagoEntidad.class)
                .addClasses(ActividadModelo.class, TipoEstudianteModelo.class, TemaModelo.class, NivelLenguajeModelo.class,
                        CatalogoModelo.class)
                .addClasses(ActividadNuevaValidacion.class)
                .addClasses(ActividadSesionBean.class, ValidadorSessionBean.class, LoggerProducer.class, CatalogoNuevoValidacion.class,
                        CatalogoLlavePrimariaValidacion.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ActividadSesionBean actividadSesionBean;

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
    public void transcripcion() {
        String transcripcion = actividadSesionBean.transcripcion("DNHmujbuC74");
        assertNotNull(transcripcion);
        assertTrue(transcripcion.length() < 2);
    }

    @Test
    public void inserta() {
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
        actividadSesionBean.inserta(actividadModelo);
        assertNotNull(actividadModelo.getIdVideo());
    }

    @Test
    public void elimina() {
        actividadSesionBean.elimina("JcMtWwidzpU");
    }

}
