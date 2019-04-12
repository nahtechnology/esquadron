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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class NivelLenguajeEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(NivelLenguajeEntidad.class, CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<NivelLenguajeEntidad> typedQuery = entityManager.createNamedQuery("NivelLenguajeEntidad.busca", NivelLenguajeEntidad.class);
        List<NivelLenguajeEntidad> nivelLenguajeEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(nivelLenguajeEntidadLista);
        Assert.assertFalse(nivelLenguajeEntidadLista.isEmpty());
        for (NivelLenguajeEntidad nivelLenguajeEntidad : nivelLenguajeEntidadLista) {
            Assert.assertNotNull(nivelLenguajeEntidad);
            Assert.assertNotNull(nivelLenguajeEntidad.getClave());
            Assert.assertNotNull(nivelLenguajeEntidad.getValor());
        }
    }
}
