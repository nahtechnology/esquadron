package tecolotl.nucleo.persistencia.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@RunWith(Arquillian.class)
public class NivelLenguajeEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(NivelLenguajeEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<NivelLenguajeEntidad> typedQuery = entityManager.createNamedQuery("NivelLenguaje.busca", NivelLenguajeEntidad.class);
        List<NivelLenguajeEntidad> listaNivelLenguaje = typedQuery.getResultList();
        Assert.assertNotNull(listaNivelLenguaje);
        Assert.assertFalse(listaNivelLenguaje.isEmpty());
        for (NivelLenguajeEntidad nivelLenguajeEntidad : listaNivelLenguaje) {
            Assert.assertNotNull(nivelLenguajeEntidad.getDescripcion());
            Assert.assertNotNull(nivelLenguajeEntidad.getNombre());
            Assert.assertTrue(nivelLenguajeEntidad.getId() > 0);
        }
    }

}
