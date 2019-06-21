package tecolotl.profesor.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProfesorEntidadTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "prueba.war")
                .addClasses(ProfesorEntidad.class, PersonaEntidad.class, LoggerProducer.class, CatalagoEntidad.class,
                            GrupoEntidad.class, PersonaNuevaValidacion.class)
                .addPackage(EscuelaEntidad.class.getPackage())
                .addPackage(AlumnoEntidad.class.getPackage())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<ProfesorEntidad> typedQuery = entityManager.createNamedQuery("ProfesorEntidad.busca", ProfesorEntidad.class);
        List<ProfesorEntidad> profesorEntidadLista = typedQuery.getResultList();
        assertNotNull(profesorEntidadLista);
        assertFalse(profesorEntidadLista.isEmpty());
        for (ProfesorEntidad profesorEntidad : profesorEntidadLista) {
            assertNotNull(profesorEntidad);
            assertNotNull(profesorEntidad.getNombre());
            assertNotNull(profesorEntidad.getApellidoPaterno());
            assertNotNull(profesorEntidad.getApellidoMaterno());
            assertNotNull(profesorEntidad.getId());
            assertNotNull(profesorEntidad.getId());
            assertNotNull(profesorEntidad.getApodo());
            assertNotNull(profesorEntidad.getContrasenia());
        }
    }
}
