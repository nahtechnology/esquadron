package tecolotl.alumno.entidad;

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
public class GlosarioEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GlosarioEntidad.class, ActividadEntidad.class)
                .addAsResource("META-INF/persisrtence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<GlosarioEntidad> typedQuery = entityManager.createNamedQuery("GlosarioEntidad.busca", GlosarioEntidad.class);
        List<GlosarioEntidad> glosarioEntidadsLista = typedQuery.getResultList();
        for(GlosarioEntidad glosarioEntidad : glosarioEntidadsLista){
            Assert.assertNotNull(glosarioEntidad);
            Assert.assertNotNull(glosarioEntidad.getPalabra());
            Assert.assertNotNull(glosarioEntidad.getImagen());
            Assert.assertNotNull(glosarioEntidad.getSignificado());
            Assert.assertNotNull(glosarioEntidad.getActividadEntidad());
        }
    }
}
