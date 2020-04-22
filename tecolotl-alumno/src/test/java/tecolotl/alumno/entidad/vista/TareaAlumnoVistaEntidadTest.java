package tecolotl.alumno.entidad.vista;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareaAlumnoVistaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(TareaAlumnoVistaEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TareaAlumnoVistaEntidad> typedQuery =
                entityManager.createNamedQuery("TareaAlumnoVistaEntidad.buscaAlumno", TareaAlumnoVistaEntidad.class);
        typedQuery.setParameter("idAlumno", UUID.fromString("0cbaa96c-ba77-408d-b046-56e0fd1ffe56"));
        List<TareaAlumnoVistaEntidad> tareaAlumnoVistaEntidadLista = typedQuery.getResultList();
        assertNotNull(tareaAlumnoVistaEntidadLista);
        assertFalse(tareaAlumnoVistaEntidadLista.isEmpty());
        tareaAlumnoVistaEntidadLista.forEach(tareaAlumnoVistaEntidad -> {
            assertNotNull(tareaAlumnoVistaEntidad);
            assertNotNull(tareaAlumnoVistaEntidad.getId());
            assertNotNull(tareaAlumnoVistaEntidad.getAsignacion());
            assertNotNull(tareaAlumnoVistaEntidad.getIdActividad());
            assertNotNull(tareaAlumnoVistaEntidad.getPreguntaDetonadora());
        });
    }

}
