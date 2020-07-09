package tecolotl.profesor.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
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
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.sesion.OracionesSesionBean;
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
import tecolotl.profesor.entidad.CicloEscolarEntidad;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RunWith(Arquillian.class)
public class ProfesorSesionBeanTest {

    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
            //nucleo
            .addPackage(CorreoEnum.class.getPackage()).addPackage(CatalogoModelo.class.getPackage()).addPackage(CatalagoEntidad.class.getPackage())
            .addPackage(CatalogoSesionBean.class.getPackage()).addPackage(CatalogoNuevoValidacion.class.getPackage())
            //administracion
            .addPackage(CoordinadorModelo.class.getPackage()).addPackage(ContactoModelo.class.getPackage())
            .addPackage(ContactoSesionBean.class.getPackage()).addPackage(ColoniaNuevaValidacion.class.getPackage())
            .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
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
            .addPackage(CicloEscolarEntidad.class.getPackage()).addPackage(GrupoAlumnoModelo.class.getPackage())
            .addPackage(GrupoAlumnoSesionBean.class.getPackage()).addPackage(GrupoProfesorValidacion.class.getPackage())
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Test
    public void busca() {
        List<ProfesorModelo> profesorModeloLista = profesorSesionBean.busca();
        Assert.assertNotNull(profesorModeloLista);
        Assert.assertFalse(profesorModeloLista.isEmpty());
        for (ProfesorModelo profesorModelo : profesorModeloLista){
            Assert.assertNotNull(profesorModelo);
            Assert.assertNotNull(profesorModelo.getId());
            Assert.assertNotNull(profesorModelo.getEscuelaBaseModelo());
        }
    }

    @Test
    public void buscaPorEscuela() {
        List<ProfesorModelo> profesorModeloLista = profesorSesionBean.buscaPorEscuela("21DBA0014J");
        Assert.assertNotNull(profesorModeloLista);
        Assert.assertFalse(profesorModeloLista.isEmpty());
        profesorModeloLista.forEach(profesor -> {
            Assert.assertNotNull(profesor);
            Assert.assertNotNull(profesor.getId());
            Assert.assertNotNull(profesor.getNombre());
            Assert.assertNotNull(profesor.getApellidoPaterno());
            Assert.assertNotNull(profesor.getApellidoMaterno());
            Assert.assertNotNull(profesor.getSexo());
            Assert.assertNotNull(profesor.getCorreoEletronico());
            Assert.assertNull(profesor.getContrasenia());
            Assert.assertNull(profesor.getEscuelaBaseModelo());
            Assert.assertNull(profesor.getGrupoModeloLista());
        });
    }

    @Test
    public void buscaId() {
        ProfesorModelo profesorModelo = profesorSesionBean.busca(UUID.fromString("f64d1fd6-67e0-423a-a2a8-4eb866b18b6d"));
        Assert.assertNotNull(profesorModelo);
        Assert.assertNotNull(profesorModelo.getId());
        Assert.assertNotNull(profesorModelo.getCorreoEletronico());
        Assert.assertNotNull(profesorModelo.getApellidoMaterno());
        Assert.assertNotNull(profesorModelo.getApellidoMaterno());
        Assert.assertNotNull(profesorModelo.getApodo());
        Assert.assertNotNull(profesorModelo.getNombre());
    }

    /*@Test
    public void inserta2(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Eliminando");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("Otro mas");
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBS0029K"));
        profesorSesionBean.inserta(profesorModelo);
        Assert.assertNotNull(profesorModelo);
    }*/

    @Test
    public void inserta(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setId(UUID.randomUUID());
        profesorModelo.setNombre("Jesus");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("El Juanito");
        profesorModelo.setCorreoEletronico("correo_profesor@servidor.com");
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBA0014J"));
        profesorModelo.setContrasenia("1:1,2:2".getBytes());
        profesorModelo.setSexo('M');
        profesorSesionBean.inserta(profesorModelo);
        Assert.assertNotNull(profesorModelo);
    }

    @Test
    public void inserta2(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Darnes");
        profesorModelo.setApellidoPaterno("Vilariño");
        profesorModelo.setApellidoMaterno("Ayala");
        profesorModelo.setApodo("Mamá Darnes");
        profesorModelo.setCorreoEletronico("ellasitenia@servidor.com");
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("00000000XX"));
        profesorModelo.setContrasenia(new byte[]{});
        profesorModelo.setSexo('F');
        profesorSesionBean.inserta(profesorModelo);
        Assert.assertNotNull(profesorModelo);
    }

    @Test
    public void actualiza(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setId(UUID.fromString("43ed2caa-617c-4501-954d-f91eb4bf1b17"));
        profesorModelo.setNombre("Eliminando de nuevo");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("Don Levantones");
        profesorModelo.setCorreoEletronico("nuevocorreo@nossirve.com");
        profesorModelo.setSexo('F');
        profesorModelo.setContrasenia("1234".getBytes());
        EscuelaBaseModelo escuelaBaseModelo = new EscuelaBaseModelo("21DBA0014J");
        profesorModelo.setEscuelaBaseModelo(escuelaBaseModelo);
        profesorSesionBean.actualiza(profesorModelo);
        //int elementosModificados = profesorSesionBean.actualiza(profesorModelo);
        //Assert.assertFalse(elementosModificados == 0);
    }

    @Test
    public void elimina() {
        Integer profesorEliminado = 0;
        profesorEliminado = profesorSesionBean.elimina(UUID.fromString("f64d1fd6-67e0-423a-a2a8-4eb866b18b6d"));
        Assert.assertNotNull(profesorEliminado);
        Assert.assertFalse(profesorEliminado == 0);
    }

    @Test
    public void buscaApodo() {
        ProfesorModelo profesorModelo = profesorSesionBean.busca("El Juanito", true);
        Assert.assertNotNull(profesorModelo);
        Assert.assertNotNull(profesorModelo.getId());
        Assert.assertNotNull(profesorModelo.getNombre());
        Assert.assertNotNull(profesorModelo.getApellidoPaterno());
        Assert.assertNotNull(profesorModelo.getApellidoMaterno());
        Assert.assertNotNull(profesorModelo.getApodo());
        Assert.assertNotNull(profesorModelo.getCorreoEletronico());
        Assert.assertNotNull(profesorModelo.getSexo());
        Assert.assertNotNull(profesorModelo.getEscuelaBaseModelo().getClaveCentroTrabajo());
    }
}
