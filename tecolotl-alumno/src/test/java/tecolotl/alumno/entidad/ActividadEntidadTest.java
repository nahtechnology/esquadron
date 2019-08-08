package tecolotl.alumno.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
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
public class ActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(NivelLenguajeEntidad.class, CatalagoEntidad.class, ActividadEntidad.class,
                        TipoEstudianteEntidad.class, PersonaEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<ActividadEntidad> typedQuery = entityManager.createNamedQuery("ActividadEntidad.busca", ActividadEntidad.class);
        List<ActividadEntidad> actividadEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(actividadEntidadLista);
        Assert.assertFalse(actividadEntidadLista.isEmpty());
        for (ActividadEntidad actividadEntidad : actividadEntidadLista) {
            Assert.assertNotNull(actividadEntidad);
            Assert.assertNotNull(actividadEntidad.getId());
            Assert.assertNotNull(actividadEntidad.getLenguaje());
            Assert.assertNotNull(actividadEntidad.getPreguntaDetonadora());
            Assert.assertNotNull(actividadEntidad.getTiempo());
            Assert.assertNotNull(actividadEntidad.getTipoEstudianteEntidad().getClave());
        }
    }

}
