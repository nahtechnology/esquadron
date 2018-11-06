package tecolotl.nucleo.persistencia.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.nucleo.persistence.enumeracion.NivelLenguajeEnumeracion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareaEntidadTest {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(TareaEntidad.class)
                .addClass(NivelLenguajeEnumeracion.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("Tarea.busca", TareaEntidad.class);
        List<TareaEntidad> listaTarea = typedQuery.getResultList();
        Assert.assertNotNull(listaTarea);
        logger.info(String.valueOf(listaTarea.size()));
        Assert.assertFalse("Lista vacia", listaTarea.isEmpty());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void crear() {

    }

}
