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
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.relacionar.RelacionarEntidad;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.modelo.DetalleAlumnoModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.sesion.PersonaSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class AlumnoSesionBeanTest {
    @Deployment
    public static Archive<?> createDeployment(){
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
                .addPackage(TareasResueltasEntidad.class.getPackage())
                .addPackage(TareaResuetasModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AlumnoSesionBean alumnoSesionBean;

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
        AlumnoModelo alumnoModelo = alumnoSesionBean.busca(1);
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
    public void detalle() {
        DetalleAlumnoModelo detalleAlumnoModelo = alumnoSesionBean.detalle(1);
        Assert.assertNotNull(detalleAlumnoModelo);
        Assert.assertNotNull(detalleAlumnoModelo.getTotalTareas());
        Assert.assertNotNull(detalleAlumnoModelo.getGrado());
        Assert.assertNotNull(detalleAlumnoModelo.getGrado());
        Assert.assertNotNull(detalleAlumnoModelo.getInicio());
        Assert.assertNotNull(detalleAlumnoModelo.getFin());
        Assert.assertNotNull(detalleAlumnoModelo.getNombre());
        Assert.assertNotNull(detalleAlumnoModelo.getApellidoPaterno());
        Assert.assertNotNull(detalleAlumnoModelo.getApellidoMaterno());
    }
}
