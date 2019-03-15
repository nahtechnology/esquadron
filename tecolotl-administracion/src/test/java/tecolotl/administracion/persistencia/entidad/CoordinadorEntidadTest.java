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

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import java.util.List;

@RunWith(Arquillian.class)
public class CoordinadorEntidadTest {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(CoordinadorEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Test
    public void busca() {
        TypedQuery<CoordinadorEntidad> typedQuery = entityManager.createNamedQuery("CoordinadorEntidad.busca", CoordinadorEntidad.class);
        List<CoordinadorEntidad> coordinadorEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(coordinadorEntidadLista);
        Assert.assertFalse(coordinadorEntidadLista.isEmpty());
        for (CoordinadorEntidad coordinadorEntidad : coordinadorEntidadLista) {
            Assert.assertNotNull(coordinadorEntidad);
            Assert.assertNotNull(coordinadorEntidad.getNombre());
            Assert.assertNotNull(coordinadorEntidad.getApellidoMaterno());
            Assert.assertNotNull(coordinadorEntidad.getApellidoPaterno());
            Assert.assertNotNull(coordinadorEntidad.getContrasenia());
            Assert.assertNotNull(coordinadorEntidad.getCorreoElectronico());
            CoordinadorEntidadPK coordinadorEntidadPK = coordinadorEntidad.getCoordinadorEntidadPK();
            Assert.assertNotNull(coordinadorEntidadPK);
            Assert.assertNotNull(coordinadorEntidadPK.getNickname());
            Assert.assertNotNull(coordinadorEntidadPK.getEscuelaEntidad());
        }
    }

    @Test
    public void buscaPorEscuela() {
        TypedQuery<CoordinadorEntidad> typedQuery = entityManager.createNamedQuery("CoordinadorEntidad.buscaEscuela", CoordinadorEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", "21DBA0044D");
        List<CoordinadorEntidad> coordinadorEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(coordinadorEntidadLista);
        Assert.assertFalse(coordinadorEntidadLista.isEmpty());
        for (CoordinadorEntidad coordinadorEntidad : coordinadorEntidadLista) {
            Assert.assertNotNull(coordinadorEntidad);
            Assert.assertNotNull(coordinadorEntidad.getNombre());
            Assert.assertNotNull(coordinadorEntidad.getApellidoMaterno());
            Assert.assertNotNull(coordinadorEntidad.getApellidoPaterno());
            Assert.assertNotNull(coordinadorEntidad.getContrasenia());
            Assert.assertNotNull(coordinadorEntidad.getCorreoElectronico());
            CoordinadorEntidadPK coordinadorEntidadPK = coordinadorEntidad.getCoordinadorEntidadPK();
            Assert.assertNotNull(coordinadorEntidadPK);
            Assert.assertNotNull(coordinadorEntidadPK.getNickname());
            Assert.assertNotNull(coordinadorEntidadPK.getEscuelaEntidad());
        }
    }

    @Test
    public void guardar() {
        CoordinadorEntidad coordinadorEntidad = new CoordinadorEntidad();
        CoordinadorEntidadPK coordinadorEntidadPK = new CoordinadorEntidadPK();
        EscuelaEntidad escuelaEntidad = new EscuelaEntidad("01DCT0284C");
        coordinadorEntidadPK.setEscuelaEntidad(escuelaEntidad);
        coordinadorEntidadPK.setNickname("emma");
        coordinadorEntidad.setCoordinadorEntidadPK(coordinadorEntidadPK);
        coordinadorEntidad.setNombre("Emmanuel");
        coordinadorEntidad.setApellidoPaterno("Martinez");
        coordinadorEntidad.setApellidoMaterno("Lopez");
        coordinadorEntidad.setContrasenia("1234");
        coordinadorEntidad.setCorreoElectronico("coordinado@servidor.com");
        try {
            userTransaction.begin();
            entityManager.persist(coordinadorEntidad);
            userTransaction.rollback();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }
}
