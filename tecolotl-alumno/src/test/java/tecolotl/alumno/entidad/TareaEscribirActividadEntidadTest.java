package tecolotl.alumno.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareaEscribirActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(ActividadEntidad.class.getPackage()).addClasses(PersonaEntidad.class, CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TareaEscribirActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaEscribirActividadEntidad.buscaEscribir", TareaEscribirActividadEntidad.class);
    //    typedQuery.setParameter("idActividad", "JcMtWwiyzpU");
    //    typedQuery.setParameter("idTarea", 1);
        List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista = typedQuery.getResultList();
        assertNotNull(tareaEscribirActividadEntidadLista);
        assertFalse(tareaEscribirActividadEntidadLista.isEmpty());
        tareaEscribirActividadEntidadLista.forEach(t -> {
            assertNotNull(t);
            assertNotNull(t.getTextoRespuesta());
            assertNotNull(t.getHoraRespuesta());
            assertNotNull(t.getTareaEscribirActividadEntidadPK().getEscribirActividadEntidad().getEscribirActividadEntidadPK().getEscribirEntidad().getPregunta());
        });
    }

}
