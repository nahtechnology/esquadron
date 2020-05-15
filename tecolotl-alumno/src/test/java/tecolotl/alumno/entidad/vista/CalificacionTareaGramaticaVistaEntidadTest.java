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
public class CalificacionTareaGramaticaVistaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(CalificacionTareaGramaticaVistaEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<CalificacionTareaGramaticaVistaEntidad> typedQuery =
                entityManager.createNamedQuery("CalificacionTareaGramaticaVistaEntidad.busca", CalificacionTareaGramaticaVistaEntidad.class);
        typedQuery.setParameter("idTarea", UUID.fromString("9a86337b-162a-4dec-b3d2-38145b4e9ecc"));
        List<CalificacionTareaGramaticaVistaEntidad> lista = typedQuery.getResultList();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        lista.forEach(calificacionTareaGramaticaVistaEntidad -> {
            assertNotNull(calificacionTareaGramaticaVistaEntidad);
            assertNotNull(calificacionTareaGramaticaVistaEntidad.getId());
            assertNotNull(calificacionTareaGramaticaVistaEntidad.getCodigo());
            assertNotNull(calificacionTareaGramaticaVistaEntidad.getIdActividad());
            assertNotNull(calificacionTareaGramaticaVistaEntidad.getPalabra());
            assertNotNull(calificacionTareaGramaticaVistaEntidad.getPuntaje());
            assertNotNull(calificacionTareaGramaticaVistaEntidad.getRespuesta());
            assertNotNull(calificacionTareaGramaticaVistaEntidad.getVuelta());
        });
    }

}
