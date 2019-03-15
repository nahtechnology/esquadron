package tecolotl.administracion.persistencia.entidad;

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
public class EstadoEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(EstadoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<EstadoEntidad> typedQuery = entityManager.createNamedQuery("EstadoEntidad.busca", EstadoEntidad.class);
        typedQuery.setMaxResults(10);
        List<EstadoEntidad> estadoLista = typedQuery.getResultList();
        Assert.assertNotNull(estadoLista);
        Assert.assertFalse(estadoLista.isEmpty());
        for (EstadoEntidad estadoEntidad : estadoLista) {
            Assert.assertNotNull(estadoEntidad.getId());
            Assert.assertNotNull(estadoEntidad.getNombre());
        }
    }

}
