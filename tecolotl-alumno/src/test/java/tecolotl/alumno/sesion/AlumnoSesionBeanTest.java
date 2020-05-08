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
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.DetalleAlumnoModelo;
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
import tecolotl.alumno.validacion.mapamental.EscribirNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirRespuestaValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.TareaRelacionarOracionRespuestaValidacion;
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

@RunWith(Arquillian.class)
public class AlumnoSesionBeanTest {
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
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
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

    @Inject
    private Logger logger;

    @Test
    public void busca(){
        List<AlumnoModelo> alumnoModeloLista = alumnoSesionBean.busca();
        Assert.assertNotNull(alumnoModeloLista);
        Assert.assertFalse(alumnoModeloLista.isEmpty());
        for (AlumnoModelo alumnoModelo : alumnoModeloLista){
            Assert.assertNotNull(alumnoModelo);
            Assert.assertNotNull(alumnoModelo.getId());
            Assert.assertNotNull(alumnoModelo.getNombre());
            Assert.assertNotNull(alumnoModelo.getApellidoPaterno());
            Assert.assertNotNull(alumnoModelo.getApellidoMaterno());
            Assert.assertNotNull(alumnoModelo.getApodo());
            logger.info(String.valueOf(alumnoModelo.getContrasenia()));
            Assert.assertNotNull(alumnoModelo.getContrasenia());
            Assert.assertNotNull(alumnoModelo.getNacimiento());
            Assert.assertNotNull(alumnoModelo.getCorreoPadreFamilia());
            Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo());
            Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo().getValor());
            Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo());
            Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo().getClave());
            Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo().getValor());
        }
    }

    @Test
    public void buscaID(){
        AlumnoModelo alumnoModelo = alumnoSesionBean.busca(UUID.fromString("f7e4c1fe-d444-4186-a331-78cfeb04b209"));
        Assert.assertNotNull(alumnoModelo);
        Assert.assertNotNull(alumnoModelo.getId());
        Assert.assertNotNull(alumnoModelo.getNombre());
        Assert.assertNotNull(alumnoModelo.getApellidoPaterno());
        Assert.assertNotNull(alumnoModelo.getApellidoMaterno());
        Assert.assertNotNull(alumnoModelo.getApodo());
        Assert.assertNotNull(alumnoModelo.getContrasenia());
        Assert.assertNotNull(alumnoModelo.getNacimiento());
        Assert.assertNotNull(alumnoModelo.getCorreoPadreFamilia());
        Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo());
        Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo().getValor());
        Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo());
        Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo().getClave());
        Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo().getValor());
    }

    @Test
    public void buscaApodo() {
        AlumnoModelo alumnoModelo = alumnoSesionBean.busca(UUID.fromString("antonio"));
        Assert.assertNotNull(alumnoModelo);
        Assert.assertNotNull(alumnoModelo.getId());
        Assert.assertNotNull(alumnoModelo.getNacimiento());
        Assert.assertNotNull(alumnoModelo.getCorreoPadreFamilia());
        Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo());
        Assert.assertNotNull(alumnoModelo.getNivelLenguajeModelo().getValor());
        Assert.assertNotNull(alumnoModelo.getApellidoMaterno());
        Assert.assertNotNull(alumnoModelo.getApellidoPaterno());
        Assert.assertNotNull(alumnoModelo.getApodo());
    }

    @Test
    public void incrementaNivel() {
        int modificados = alumnoSesionBean.cambioNivel(UUID.fromString("0cbaa96c-ba77-408d-b046-56e0fd1ffe56"), (short) 1);
        Assert.assertNotEquals(modificados, 0);
    }

    @Test
    public void bajaNivel() {
        int modificados = alumnoSesionBean.cambioNivel(UUID.fromString("0cbaa96c-ba77-408d-b046-56e0fd1ffe56"), (short) -1);
        Assert.assertNotEquals(modificados, 0);
    }
}
