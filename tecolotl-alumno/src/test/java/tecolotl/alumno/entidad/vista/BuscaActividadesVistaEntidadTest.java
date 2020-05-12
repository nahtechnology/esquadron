package tecolotl.alumno.entidad.vista;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.nucleo.herramienta.LoggerProducer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BuscaActividadesVistaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(BuscaActividadesVistaEntidad.class)
                .addClass(LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Test
    public void busca() {
        TypedQuery<BuscaActividadesVistaEntidad> typedQuery =
                entityManager.createNamedQuery("BuscaActividadesVistaEntidad.buscaAlumno", BuscaActividadesVistaEntidad.class);
        typedQuery.setParameter("idAlumno", UUID.fromString("d205fd17-ae9d-459a-9e15-6d7e653e4c2c"));
        List<BuscaActividadesVistaEntidad> lista = typedQuery.getResultList();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        lista.forEach(buscaActividadesVistaEntidad -> {
            assertNotNull(buscaActividadesVistaEntidad);
            assertNotNull(buscaActividadesVistaEntidad.getIdActvidad());
            logger.info(buscaActividadesVistaEntidad.getIdActvidad());
            assertNotNull(buscaActividadesVistaEntidad.getMapaMental());
            logger.info(buscaActividadesVistaEntidad.getMapaMental());
            assertNotNull(buscaActividadesVistaEntidad.getPreguntaDetonadora());
        });
    }

}
