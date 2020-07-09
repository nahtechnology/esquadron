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
import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
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
import tecolotl.profesor.modelo.AlumnoTareasNivelModelo;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.modelo.TareaAlumnoGrupoModelo;
import tecolotl.profesor.scope.AlumnoGrupoScope;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class GrupoAlumnoSesionBeanTest {

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
                .addPackage(AlumnoGrupoScope.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidadPK.class.getPackage())
                //profesor
                .addPackage(CicloEscolarEntidad.class.getPackage()).addPackage(GrupoAlumnoModelo.class.getPackage())
                .addPackage(GrupoAlumnoSesionBean.class.getPackage()).addPackage(GrupoProfesorValidacion.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Inject
    private AlumnoGrupoScope alumnoGrupoScope;

    @Inject
    private Logger logger;

    @Test
    public void busca(){
        List<GrupoAlumnoModelo> grupoAlumnoModeloLista = grupoAlumnoSesionBean.busca();
        Assert.assertNotNull(grupoAlumnoModeloLista);
        Assert.assertFalse(grupoAlumnoModeloLista.isEmpty());
        for (GrupoAlumnoModelo grupoAlumnoModelo : grupoAlumnoModeloLista){
            logger.info(grupoAlumnoModelo.toString());
            Assert.assertNotNull(grupoAlumnoModelo);
            Assert.assertNotNull(grupoAlumnoModelo.getIdAlumno());
            Assert.assertNotNull(grupoAlumnoModelo.getIdGrupo());
        }
    }

    @Test public void buscaGrupo() {
        List<TareaAlumnoGrupoModelo> tareaAlumnoGrupoModeloLista = grupoAlumnoSesionBean.busca(UUID.fromString("e5ee7964-f87c-4225-985b-89263b8b6346"));
        Assert.assertNotNull(tareaAlumnoGrupoModeloLista);
        Assert.assertFalse(tareaAlumnoGrupoModeloLista.isEmpty());
        tareaAlumnoGrupoModeloLista.forEach(tareaAlumnoGrupoModelo -> {
            Assert.assertNotNull(tareaAlumnoGrupoModelo);
            Assert.assertNotNull(tareaAlumnoGrupoModelo.getId());
            Assert.assertNotNull(tareaAlumnoGrupoModelo.getNombre());
            Assert.assertNotNull(tareaAlumnoGrupoModelo.getApellidoPaterno());
            Assert.assertNotNull(tareaAlumnoGrupoModelo.getApellidoMaterno());
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalAsignadaCompletar() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalRespuestaCompletar() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalAsignadaGramatica() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalRespuestaGramatica() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalAsignadaMapaMental() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalRespuestaMapaMental() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalAsignadaOraciones() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalRespuestaOraciones() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalAsignadaRelacionarOracion() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalRespuestaRelacionarOracion() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getCalificadoMapaMental() > -1);
            Assert.assertTrue(tareaAlumnoGrupoModelo.getTotalCalificadasGramatica() > -1);
        });
    }

    @Test public void buscaAlumnos() {
        List<AlumnoModelo> alumnoModeloLista = grupoAlumnoSesionBean.buscaAlumno(UUID.fromString("e5ee7964-f87c-4225-985b-89263b8b6346"));
        Assert.assertNotNull(alumnoModeloLista);
        Assert.assertFalse(alumnoModeloLista.isEmpty());
        alumnoModeloLista.forEach(alumnoModelo -> {
            Assert.assertNotNull(alumnoModelo);
            Assert.assertNotNull(alumnoModelo.getId());
            Assert.assertNotNull(alumnoModelo.getNombre());
            Assert.assertNotNull(alumnoModelo.getApellidoMaterno());
            Assert.assertNotNull(alumnoModelo.getApellidoPaterno());
            Assert.assertNull(alumnoModelo.getNacimiento());
        });
    }
    @Test public void buscaActividades(){
        List<ActividadModelo> actividadModelos = grupoAlumnoSesionBean.buscaActividades(UUID.fromString("e5ee7964-f87c-4225-985b-89263b8b6346"));
        Assert.assertNotNull(actividadModelos);
        Assert.assertFalse(actividadModelos.isEmpty());
        actividadModelos.forEach(actividadModelo -> {
            logger.info(actividadModelo.toString());
            Assert.assertNotNull(actividadModelo);
            Assert.assertNotNull(actividadModelo.getIdVideo());
            Assert.assertNotNull(actividadModelo.getLenguaje());
            Assert.assertNotNull(actividadModelo.getNivelLenguajeModeloLista());
            Assert.assertNotNull(actividadModelo.getPreguntaDetonadora());
            Assert.assertNotNull(actividadModelo.getPuntaje());
            Assert.assertNotNull(actividadModelo.getTranscripcion());
            Assert.assertNotNull(actividadModelo.getTipoEstudianteModelo());
            Assert.assertNotNull(actividadModelo.getTemaModelo());
            Assert.assertNotNull(actividadModelo.getTiempo());
        });


    }

    @Test
    public void buscaTotalAlumnos(){
        Long total = grupoAlumnoSesionBean.buscaTotalAlumnosGrupo(UUID.fromString("e94f59fd-97ef-489b-805d-7ac034f2c024"));
        Assert.assertNotNull(total);
        logger.info(total.toString());
    }

    @Test public void insertaAlumno() {
        AlumnoModelo alumnoModelo = new AlumnoModelo();
        alumnoModelo.setApodo("antonio");
        alumnoModelo.setNombre("Antonio");
        alumnoModelo.setApellidoPaterno("Alonso");
        alumnoModelo.setApellidoMaterno("Valerdi");
        alumnoModelo.setNivelLenguajeModelo(new NivelLenguajeModelo((short)1));
        alumnoModelo.setNacimiento(new Date());
        alumnoModelo.setSexo('m');
        alumnoModelo.setContrasenia("1:1,2:2".getBytes(StandardCharsets.UTF_8));
        alumnoGrupoScope.inserta(alumnoModelo, UUID.fromString("e5ee7964-f87c-4225-985b-89263b8b6346"));
        Assert.assertNotNull(alumnoModelo.getId());
    }

    @Test public void buscaAlumnoNivel() {
        List<AlumnoTareasNivelModelo> alumnoTareasNivelModeloLista = grupoAlumnoSesionBean.buscaAlumnoNivel(Arrays.asList(UUID.fromString("290471d9-f654-4442-b9ed-a4ea1b5ed401")));
        Assert.assertNotNull(alumnoTareasNivelModeloLista);
        Assert.assertFalse(alumnoTareasNivelModeloLista.isEmpty());
        for (AlumnoTareasNivelModelo alumnoTareasNivelModelo : alumnoTareasNivelModeloLista) {
            Assert.assertNotNull(alumnoTareasNivelModelo);
            Assert.assertNotNull(alumnoTareasNivelModelo.getIdGrupo());
            Assert.assertNotNull(alumnoTareasNivelModelo.getNombre());
        }
    }

    @Test
    public void inserta(){
        GrupoAlumnoModelo grupoAlumnoModelo = new GrupoAlumnoModelo();
        grupoAlumnoModelo.setIdAlumno(UUID.fromString("9e6ff5b8-20f7-4f7d-b801-fc91a751ae50"));
        grupoAlumnoModelo.setIdGrupo(UUID.fromString("fc469982-5c2c-4821-b22e-c3b0f52c19d6"));
        grupoAlumnoSesionBean.inserta(grupoAlumnoModelo);
        Assert.assertNotNull(grupoAlumnoModelo);
    }

    @Test
    public void elimina(){
        GrupoAlumnoModelo grupoAlumnoModelo = new GrupoAlumnoModelo();
        grupoAlumnoModelo.setIdGrupo(UUID.fromString("fc469982-5c2c-4821-b22e-c3b0f52c19d6"));
        grupoAlumnoModelo.setIdAlumno(UUID.fromString("9e6ff5b8-20f7-4f7d-b801-fc91a751ae50"));
        int grupoAEliminado = grupoAlumnoSesionBean.elimina(grupoAlumnoModelo);
        Assert.assertFalse(grupoAEliminado == 0);
    }

    @Test
    public void detalleAlumno() {
        List<AlumnoModelo> alumnoModeloLista = grupoAlumnoSesionBean.detalleAlumnos(UUID.fromString("e5ee7964-f87c-4225-985b-89263b8b6346"));
        Assert.assertNotNull(alumnoModeloLista);
        Assert.assertFalse(alumnoModeloLista.isEmpty());
        alumnoModeloLista.forEach(alumnoModelo -> {
            System.out.println(alumnoModelo);
            Assert.assertNotNull(alumnoModelo.getId());
            Assert.assertNotNull(alumnoModelo.getNombre());
            Assert.assertNotNull(alumnoModelo.getApellidoPaterno());
            Assert.assertNotNull(alumnoModelo.getApellidoMaterno());
            Assert.assertNotNull(alumnoModelo.getApodo());
            Assert.assertNotNull(alumnoModelo.getContrasenia());
        });
    }
}
