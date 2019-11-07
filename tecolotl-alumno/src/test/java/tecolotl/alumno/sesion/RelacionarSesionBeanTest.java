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
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.hablar.HablarEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidadPK;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.hablar.HablarModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class RelacionarSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(RelacionarModelo.class.getPackage())
                .addPackage(GramaticaModelo.class.getPackage())
                .addPackage(GramaticaEntidad.class.getPackage())
                .addPackage(TareaRelacionarOracionesEntidad.class.getPackage())
                .addPackage(TareaRelacionarOracionModelo.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(MapaMentalEntidad.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(TareaResuetasModelo.class.getPackage())
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage())
                .addPackage(TareaOracionesModelo.class.getPackage())
                .addPackage(TareaOracionesEntidad.class.getPackage())
                .addPackage(HablarModelo.class.getPackage())
                .addPackage(HablarEntidad.class.getPackage())
                .addPackage(TareaCompletarModelo.class.getPackage())
                .addPackage(TareaCompletarEntidad.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidadPK.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private RelacionarSesionBean relacionarSesionBean;

    @Test
    public void buscaActividad() {
        List<RelacionarModelo> relacionarSesionBeanLista = relacionarSesionBean.busca("0_1NU60qHWs");
        assertNotNull(relacionarSesionBeanLista);
        assertFalse(relacionarSesionBeanLista.isEmpty());
        relacionarSesionBeanLista.forEach(relacionarModelo -> {
            assertNotNull(relacionarModelo);
            assertNotNull(relacionarModelo.getIdClaseGlosario());
            assertNotNull(relacionarModelo.getPalabra());
            assertNotNull(relacionarModelo.getIdActividad());
        });
    }

    @Test
    public void bibliotecaLibre() {
        List<RelacionarModelo> relacionarSesionBeanLista = relacionarSesionBean.bibliotecaLibre("0_1NU60qHWs");
        assertNotNull(relacionarSesionBeanLista);
        assertFalse(relacionarSesionBeanLista.isEmpty());
        relacionarSesionBeanLista.forEach(relacionarModelo -> {
            assertNotNull(relacionarModelo);
            assertNotNull(relacionarModelo.getIdClaseGlosario());
            assertNotNull(relacionarModelo.getPalabra());
            assertNotNull(relacionarModelo.getIdActividad());
        });
    }

    @Test
    public void buscaTarea() {
        List<RelacionarModelo> relacionarSesionBeanLista = relacionarSesionBean.busca(121);
        assertNotNull(relacionarSesionBeanLista);
        assertFalse(relacionarSesionBeanLista.isEmpty());
        relacionarSesionBeanLista.forEach(relacionarModelo -> {
            assertNotNull(relacionarModelo);
            assertNotNull(relacionarModelo.getIdActividad());
            assertNotNull(relacionarModelo);
            assertNotNull(relacionarModelo);
            assertNotNull(relacionarModelo);
        });
    }

    @Test
    public void respuesta() {
        RelacionarModelo relacionarModelo = new RelacionarModelo();
        relacionarModelo.setIdActividad("0_1NU60qHWs");
        relacionarModelo.setIdTarea(2);
        relacionarSesionBean.respuesta(relacionarModelo, "8c373fd1a64c1a3083fff348caf4b4ab");
    }
}
