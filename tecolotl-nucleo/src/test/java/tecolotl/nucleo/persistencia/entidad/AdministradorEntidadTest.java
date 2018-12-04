package tecolotl.nucleo.persistencia.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class AdministradorEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(AdministradorEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void buscaNombre() {
        TypedQuery<AdministradorEntidad> typedQuery = entityManager.createNamedQuery("AdministradorEntidad.buscaApodo", AdministradorEntidad.class);
        typedQuery.setParameter("apodo", "admin");
        AdministradorEntidad administradorEntidad = typedQuery.getSingleResult();
        Assert.assertNotNull(administradorEntidad);
        Assert.assertNotNull(administradorEntidad.getNombre());
        Assert.assertNotNull(administradorEntidad.getApellidoMaterno());
        Assert.assertNotNull(administradorEntidad.getApellidoPaterno());
    }
}
