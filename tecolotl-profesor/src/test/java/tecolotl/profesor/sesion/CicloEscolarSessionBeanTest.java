package tecolotl.profesor.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.escribir.EscribirActividadEntidad;
import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.escribir.EscribirModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.CorreoEnum;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;
import tecolotl.profesor.entidad.CicloEscolarEntidad;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
                .addPackage(CoordinadorModelo.class.getPackage()).addPackage(ColoniaModelo.class.getPackage()).addPackage(ContactoModelo.class.getPackage())
                .addPackage(ColoniaEntidad.class.getPackage()).addPackage(ContactoSesionBean.class.getPackage()).addClass(ColoniaNuevaValidacion.class)
                .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
                //alumno
                .addPackage(EscribirActividadEntidad.class.getPackage()).addPackage(ClaseGlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage()).addPackage(EscribirModelo.class.getPackage()).addPackage(GlosarioModelo.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage()).addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage()).addClass(GlosarioNuevoValidacion.class)
                //profesor
                .addPackage(CicloEscolarEntidad.class.getPackage()).addPackage(GrupoAlumnoModelo.class.getPackage())
                .addPackage(GrupoAlumnoSesionBean.class.getPackage()).addPackage(GrupoProfesorValidacion.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    private CicloEscolarSessionBean cicloEscolarSessionBean;

    @Test
    public void busca() {
        List<CicloEscolarModelo> cicloEscolarModeloLista = cicloEscolarSessionBean.busca("21DBA0051N");
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
    @InSequence(1)
    public void inserta() throws ParseException {
        CicloEscolarModelo cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModelo.setDescripcion("Ciclo escolar 2020 a 2021");
        cicloEscolarModelo.setInicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
        cicloEscolarModelo.setFin(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"));
        cicloEscolarModelo.setIdEscuela("21DBA0051N");
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
    @InSequence(2)
    public void actualiza() throws ParseException {
        String cambios = "cambios";
        CicloEscolarModelo cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModelo.setIdEscuela("21DBA0051N");
        cicloEscolarModelo.setInicio(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
        cicloEscolarModelo.setFin(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"));
        cicloEscolarModelo.setDescripcion(cambios);
        cicloEscolarSessionBean.actualiza(cicloEscolarModelo);
        cicloEscolarModelo.setDescripcion(null);
        cicloEscolarSessionBean.busca(cicloEscolarModelo);
        assertEquals(cicloEscolarModelo.getDescripcion(), cambios);
    }

}
