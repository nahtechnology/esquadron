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
import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class ClaseGlosarioEntidadTest {
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(CatalagoEntidad.class, ClaseGlosarioEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<ClaseGlosarioEntidad> typedQuery = entityManager.createNamedQuery("ClaseGlosarioEntidad.busca",ClaseGlosarioEntidad.class);
        List<ClaseGlosarioEntidad> claseGlosarioEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(claseGlosarioEntidadLista);
        Assert.assertFalse(claseGlosarioEntidadLista.isEmpty());
        for (ClaseGlosarioEntidad claseGlosarioEntidad : claseGlosarioEntidadLista){
            Assert.assertNotNull(claseGlosarioEntidad);
            Assert.assertNotNull(claseGlosarioEntidad.getClave());
            Assert.assertNotNull(claseGlosarioEntidad.getValor());
        }
    }
}
