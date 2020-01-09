package tecolotl.alumno.sesion;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.scope.AlumnoRequestScope;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.EscribirRespuestaValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.TareaRelacionarOracionRespuestaValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(Arquillian.class)
public class AlumnoRequestScopeTest {

    @Deployment
    public static Archive createDeployment(){
        return ShrinkWrap.create(WebArchive.class,"test.war")
                .addPackage(PersonaEntidad.class.getPackage())
                .addPackage(PersonaModelo.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(AlumnoRequestScope.class.getPackage())
                .addPackage(NivelLenguajeEntidad.class.getPackage())
                .addPackage(NivelLenguajeModelo.class.getPackage())
                .addPackage(AlumnoModelo.class.getPackage())
                .addPackage(AlumnoEntidad.class.getPackage())
                .addPackage(TareaGlosarioActividadEntidad.class.getPackage())
                .addPackage(TareaMapaMentalActividadEntidad.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidad.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(GlosarioLlavePrimariaValidacion.class.getPackage())
                .addPackage(EscribirNuevoValidacion.class.getPackage())
                .addPackage(EscribirRespuestaValidacion.class.getPackage())
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(TareaRelacionarOracionRespuestaValidacion.class.getPackage())
                .addPackage(LoggerProducer.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AlumnoRequestScope alumnoRequestScope;

    @Test
    public void insertaAlumno(){
        List<AlumnoModelo> alumnoModeloLista = new ArrayList<>();
        AlumnoModelo alumnoModelo = new AlumnoModelo();
        alumnoModelo.setNombre("Juan Carlos");
        alumnoModelo.setApellidoPaterno("Bodoque");
        alumnoModelo.setApellidoMaterno("Huachic");
        alumnoModelo.setNacimiento(new Date());
        alumnoModelo.setApodo("elPatron");
        alumnoModelo.setSexo('m');
        alumnoModelo.setNivelLenguajeModelo(new NivelLenguajeModelo((short)2));
        alumnoModelo.setCorreoPadreFamilia("juan.carlos.bodoque@ejemplo.com");
        alumnoModelo.setContrasenia("0:0,1:1".getBytes());
        alumnoModeloLista.add(alumnoModelo);
        alumnoRequestScope.agregarAlumnos(alumnoModeloLista);
    }
}
