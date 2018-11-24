package tecolotl.nucleo.persistencia.entidad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LenguajeEntidadTest {

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
        List<LenguajeEntidad> listaLenguaje = typedQuery.getResultList();
        Assert.assertNotNull(listaLenguaje);
        Assert.assertFalse(listaLenguaje.isEmpty());
        for (LenguajeEntidad lenguajeEntidad : listaLenguaje) {
            Assert.assertNotNull(lenguajeEntidad.getDescripcion());
            Assert.assertNotNull(lenguajeEntidad.getNombre());
            Assert.assertTrue(lenguajeEntidad.getId() > 0);
        }
    }
}