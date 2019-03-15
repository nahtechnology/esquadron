package tecolotl.administracion.persistencia.entidad;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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

@RunWith(Arquillian.class)
public class MotivoBloqueoEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MotivoBloqueoEntidad.class, CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @Test
    public void busca() {
        TypedQuery<MotivoBloqueoEntidad> typedQuery = entityManager.createNamedQuery("MotivoBloqueoEntidad.busca", MotivoBloqueoEntidad.class);
        typedQuery.setParameter("valor", "Sin bloqueo");
        List<MotivoBloqueoEntidad> motivoLista = typedQuery.getResultList();
        Assert.assertNotNull(motivoLista);
        Assert.assertFalse(motivoLista.isEmpty());
        for (MotivoBloqueoEntidad motivoBloqueoEntidad : motivoLista) {
            Assert.assertNotNull(motivoBloqueoEntidad.getValor());
            Assert.assertNotNull(motivoBloqueoEntidad.getClave());
        }
    }

    @Test
    public void inserta() throws SystemException {
        MotivoBloqueoEntidad motivoBloqueoEntidad = new MotivoBloqueoEntidad();
        motivoBloqueoEntidad.setValor("Motivo de bloqueo Nuevo");
        try {
            userTransaction.begin();
            entityManager.persist(motivoBloqueoEntidad);
            userTransaction.rollback();
        } catch (NotSupportedException | SystemException ex) {
            userTransaction.rollback();
        }
    }

    @Test
    public void actualiza() throws SystemException {
        MotivoBloqueoEntidad motivoBloqueoEntidad = entityManager.find(MotivoBloqueoEntidad.class, (short)1);
        try {
            userTransaction.begin();
            motivoBloqueoEntidad.setValor("Sin Bloqueo 2");
            userTransaction.rollback();
        } catch (Exception e) {
            userTransaction.rollback();
        }
    }
}
