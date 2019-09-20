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
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.glosario.ClaseGlosarioModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GlosarioSesionBeanTest {

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
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(RelacionarModelo.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidad.class.getPackage())
                .addPackage(TareaGramaticaEntidad.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(GramaticaModelo.class.getPackage()).addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage()).addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(TareaResuetasModelo.class.getPackage()).addPackage(TareasResueltasEntidad.class.getPackage())
                .addPackage(TareaRelacionarOracionesEntidad.class.getPackage()).addPackage(TareaRelacionarOracionModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GlosarioSesionBean glosarioSesionBean;

    @Test
    public void buscaId() {
        GlosarioModelo glosarioModelo = glosarioSesionBean.busca("bandit", (short)0);
        assertNotNull(glosarioModelo);
        glosarioModelo = glosarioSesionBean.busca("bandit", (short)0);
        assertNotNull(glosarioModelo);
        glosarioModelo = glosarioSesionBean.busca("bandit", (short)0);
        assertNotNull(glosarioModelo);
    }

    @Test
    public void buscaActivdad() {
        List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.busca("DNHmujbuC74");
        assertNotNull(glosarioModeloLista);
        assertFalse(glosarioModeloLista.isEmpty());
        glosarioModeloLista.forEach(glosarioModelo -> {
            assertNotNull(glosarioModelo);
            assertNotNull(glosarioModelo.getPalabra());
            assertNotNull(glosarioModelo.getImagen());
            assertNotNull(glosarioModelo.getSignificado());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo().getValor());
        });
    }

    @Test
    public void buscaPalabra() {
        List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.busca("pala", "g9WDeud275U");
        assertNotNull(glosarioModeloLista);
        assertFalse(glosarioModeloLista.isEmpty());
        glosarioModeloLista.forEach(glosarioModelo -> {
            assertNotNull(glosarioModelo);
            assertNotNull(glosarioModelo.getPalabra());
            assertNotNull(glosarioModelo.getImagen());
            assertNotNull(glosarioModelo.getSignificado());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo().getValor());
        });
    }

    @Test
    public void buscaTarea() {
        List<GlosarioModelo> glosarioModeloLista = glosarioSesionBean.busca(4);
        assertNotNull(glosarioModeloLista);
        assertFalse(glosarioModeloLista.isEmpty());
        glosarioModeloLista.forEach(glosarioModelo -> {
            assertNotNull(glosarioModelo);
            assertNotNull(glosarioModelo.getPalabra());
            assertNotNull(glosarioModelo.getSignificado());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo());
            assertNotNull(glosarioModelo.getClaseGlosarioModelo().getValor());
        });
    }

    @Test
    public void inserta() {
        GlosarioModelo glosarioModelo = new GlosarioModelo();
        glosarioModelo.setSignificado("Significado");
        glosarioModelo.setPalabra("palabra");
        glosarioModelo.setClaseGlosarioModelo(new ClaseGlosarioModelo((short)2));
        glosarioModelo.setImagen("palabra".getBytes());
        glosarioSesionBean.agregar(glosarioModelo, "DNHmujbuC74");
        assertNotNull(glosarioModelo);
    }

    @Test
    public void buscaIdActividad() {
        String idActividad = glosarioSesionBean.buscaActividad(1);
    }
}
