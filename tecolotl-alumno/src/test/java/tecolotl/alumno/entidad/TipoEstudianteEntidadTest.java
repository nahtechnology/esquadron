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
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TipoEstudianteEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(TipoEstudianteEntidad.class, CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TipoEstudianteEntidad> typedQuery = entityManager.createNamedQuery("TipoEstudianteEntidad.busca", TipoEstudianteEntidad.class);
        List<TipoEstudianteEntidad> tipoEstudianteEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tipoEstudianteEntidadLista);
        Assert.assertFalse(tipoEstudianteEntidadLista.isEmpty());
        for (TipoEstudianteEntidad tipoEstudianteEntidad : tipoEstudianteEntidadLista) {
            Assert.assertNotNull(tipoEstudianteEntidad);
            Assert.assertNotNull(tipoEstudianteEntidad.getClave());
            Assert.assertNotNull(tipoEstudianteEntidad.getValor());
        }
    }
}
