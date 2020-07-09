package tecolotl.profesor.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CicloEscolarSessionBeanTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "prueba.war")
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
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Test
    public void busca() {
        List<CicloEscolarModelo> cicloEscolarModeloLista = cicloEscolarSessionBean.busca("21DBA0014J", true, UUID.fromString("f64d1fd6-67e0-423a-a2a8-4eb866b18b6d"));
        assertNotNull(cicloEscolarModeloLista);
        assertFalse(cicloEscolarModeloLista.isEmpty());
        cicloEscolarModeloLista.forEach(cicloEscolarModelo -> {
            assertNotNull(cicloEscolarModelo);
            assertNotNull(cicloEscolarModelo.getDescripcion());
            assertNotNull(cicloEscolarModelo.getFin());
            assertNotNull(cicloEscolarModelo.getInicio());
            assertNotNull(cicloEscolarModelo.getIdEscuela());
        });
    }

    @Test
    public void totalgrupo() {
        CicloEscolarModelo cicloEscolarModelo = new CicloEscolarModelo();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0, 1);
        cicloEscolarModelo.setInicio(calendar.getTime());
        calendar.set(2020, 5,30);
        cicloEscolarModelo.setFin(calendar.getTime());
        cicloEscolarModelo.setIdEscuela("NO21JKBB5E");
        Long totalGrupo = cicloEscolarSessionBean.totalGrupo(cicloEscolarModelo);
        assertNotNull(totalGrupo);
        assertTrue(totalGrupo > -1);
    }

    @Test
    public void buscaEscuela() {
        List<CicloEscolarModelo> cicloEscolarModeloLista = cicloEscolarSessionBean.busca("NO21JKBB5E");
        assertNotNull(cicloEscolarModeloLista);
        assertFalse(cicloEscolarModeloLista.isEmpty());
        cicloEscolarModeloLista.forEach(cicloEscolarModelo -> {
            assertNotNull(cicloEscolarModelo);
            assertNotNull(cicloEscolarModelo.getActivo());
            assertNotNull(cicloEscolarModelo.getDescripcion());
            assertNotNull(cicloEscolarModelo.getFin());
            assertNotNull(cicloEscolarModelo.getInicio());
            assertNotNull(cicloEscolarModelo.getIdEscuela());
        });
    }

    @Test
    //@InSequence(1)
    public void inserta() throws ParseException {
        CicloEscolarModelo cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModelo.setDescripcion("Ciclo escolar 2020 a 2021");
        cicloEscolarModelo.setActivo(false);
        cicloEscolarModelo.setInicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2020"));
        cicloEscolarModelo.setFin(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2021"));
        cicloEscolarModelo.setIdEscuela("21DBA0014J");
        cicloEscolarSessionBean.inserta(cicloEscolarModelo);
        cicloEscolarSessionBean.busca(cicloEscolarModelo);
        assertNotNull(cicloEscolarModelo.getDescripcion());
    }

    @Test
    public void elimina() throws ParseException {
        CicloEscolarModelo cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModelo.setInicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
        cicloEscolarModelo.setFin(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"));
        cicloEscolarModelo.setIdEscuela("21DBA0051N");
        cicloEscolarSessionBean.elimina(cicloEscolarModelo);
        cicloEscolarSessionBean.busca(cicloEscolarModelo);
        assertNull(cicloEscolarModelo.getDescripcion());
    }

    @Test
    //@InSequence(2)
    public void actualiza() throws ParseException {
        String cambios = "cambios";
        CicloEscolarModelo cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModelo.setIdEscuela("21DBA0051N");
        cicloEscolarModelo.setInicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
        cicloEscolarModelo.setFin(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"));
        cicloEscolarModelo.setActivo(Boolean.TRUE);
        cicloEscolarModelo.setDescripcion(cambios);
        cicloEscolarSessionBean.actualiza(cicloEscolarModelo);
        cicloEscolarModelo.setDescripcion(null);
        cicloEscolarSessionBean.busca(cicloEscolarModelo);
        assertEquals(cicloEscolarModelo.getDescripcion(), cambios);
    }

}
