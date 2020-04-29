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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareasGrupoVistaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(TareasGrupoVistaEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        Query query = entityManager.createNamedQuery("TareasGrupoVistaEntidad.busca");
        query.setParameter("idGrupo", UUID.fromString("290471d9-f654-4442-b9ed-a4ea1b5ed401"));
        List<Object[]> lista = query.getResultList();
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        lista.forEach(objects -> {
            assertNotNull(objects);
            assertNotNull(objects[0]);
            assertNotNull(objects[1]);
            assertNotNull(objects[2]);
        });
    }
}
