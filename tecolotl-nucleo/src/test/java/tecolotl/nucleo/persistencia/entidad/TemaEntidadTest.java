package tecolotl.nucleo.persistencia.entidad;

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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TemaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(LenguajeEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<LenguajeEntidad> typedQuery = entityManager.createNamedQuery("LenguajeEntidad.busca", LenguajeEntidad.class);
        List<LenguajeEntidad> lenguajeEntidadList = typedQuery.getResultList();
        Assert.assertNotNull(lenguajeEntidadList);
        Assert.assertFalse(lenguajeEntidadList.isEmpty());
        for (LenguajeEntidad lenguajeEntidad : lenguajeEntidadList) {
            Assert.assertNotNull(lenguajeEntidad.getNombre());
            Assert.assertNotNull(lenguajeEntidad.getDescripcion());
            Assert.assertTrue(lenguajeEntidad.getId() > 0);
        }
    }
}
