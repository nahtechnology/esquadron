package tecolotl.administracion.persistencia.entidad;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.*;

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
public class MotivoBloqueoEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(MotivoBloqueoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<MotivoBloqueoEntidad> typedQuery = entityManager.createNamedQuery("MotivoBloqueoEntidad.busca", MotivoBloqueoEntidad.class);
        List<MotivoBloqueoEntidad> motivoLista = typedQuery.getResultList();
        Assert.assertNotNull(motivoLista);
        Assert.assertFalse(motivoLista.isEmpty());
        for (MotivoBloqueoEntidad motivoBloqueoEntidad : motivoLista) {
            Assert.assertNotNull(motivoBloqueoEntidad.getDescripcion());
        }
    }

    @Test
    public void inserta() {
        MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad();
        motivoBloqueoEntidad.setDescripcion("Prueba");
        entityManager.persist(motivoBloqueoEntidad);
        Assert.assertFalse(motivoBloqueoEntidad.getId() == 0);
    }
}
