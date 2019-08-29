package tecolotl.alumno.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareaMapaMentalActividadEntidadTest {

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
        TypedQuery<TareaMapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaEscribirActividadEntidad.buscaEscribir", TareaMapaMentalActividadEntidad.class);
    //    typedQuery.setParameter("idActividad", "JcMtWwiyzpU");
    //    typedQuery.setParameter("idTarea", 1);
        List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista = typedQuery.getResultList();
        assertNotNull(tareaMapaMentalActividadEntidadLista);
        assertFalse(tareaMapaMentalActividadEntidadLista.isEmpty());
        tareaMapaMentalActividadEntidadLista.forEach(t -> {
            assertNotNull(t);
        });
    }

}
