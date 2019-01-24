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
public class VideoEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(VideoEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<VideoEntidad> typedQuery = entityManager.createNamedQuery("VideoEntidad.busca", VideoEntidad.class);
        List<VideoEntidad> videoList = typedQuery.getResultList();
        Assert.assertNotNull(videoList);
        Assert.assertFalse(videoList.isEmpty());
        for (VideoEntidad videoEntidad : videoList) {
            Assert.assertNotNull(videoEntidad.getPreguntaDetonadora());
            Assert.assertNotNull(videoEntidad.getTemaList());

            for (TemaEntidad temaEntidad : videoEntidad.getTemaList()) {
                Assert.assertNotNull(temaEntidad.getNombre());
                Assert.assertNotNull(temaEntidad.getDescripcion());
            }
        }
    }

}
