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
import tecolotl.alumno.entidad.AlumnoControlSesionEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.modelo.AlumnoControlSesionModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.persistencia.entidad.TipoRegistroEntidad;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@RunWith(Arquillian.class)
public class ControlSesionSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsResource("META-INF/persistence.xml")
                .addClass(ControlSesionSesionBean.class).addClass(AlumnoControlSesionEntidad.class)
                .addClass(tecolotl.nucleo.persistencia.entidad.ControlSesionEntidad.class)
                .addClass(TipoRegistroEntidad.class).addClass(CatalagoEntidad.class)
                .addPackage(AlumnoEntidad.class.getPackage()).addPackage(PersonaEntidad.class.getPackage())
                .addPackage(TareaGlosarioActividadEntidad.class.getPackage())
                .addPackage(TareaMapaMentalActividadEntidad.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidad.class.getPackage())
                .addPackage(AlumnoControlSesionModelo.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ControlSesionSesionBean controlSesionSesionBean;

    @Test
    public void inicioSesion() {
        controlSesionSesionBean.inserta(UUID.fromString("0cbaa96c-ba77-408d-b046-56e0fd1ffe56"), (short)1, new Date());
    }

    @Test
    public void cierreSesion() {
        controlSesionSesionBean.inserta(UUID.fromString("0cbaa96c-ba77-408d-b046-56e0fd1ffe56"), (short)2, new Date());
    }

    @Test
    public void busca() {
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.set(2020, 5,1);
        Calendar calendarFin = Calendar.getInstance();
        calendarFin.set(2020, 5, 30);
        controlSesionSesionBean.busca(
                UUID.fromString("008f49eb-67f2-4aa1-adbc-ac48762989c0"),
                calendarInicio.getTime(),
                calendarFin.getTime(), TimeZone.getTimeZone("America/Mexico_City"));
    }

}
