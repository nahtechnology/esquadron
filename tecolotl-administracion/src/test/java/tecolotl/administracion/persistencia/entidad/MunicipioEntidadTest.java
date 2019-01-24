package tecolotl.administracion.persistencia.entidad;

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
import javax.swing.text.html.parser.Entity;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class MunicipioEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MunicipioEntidad.class, EstadoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<MunicipioEntidad> typedQuery = entityManager.createNamedQuery("MunicipioEntidad.busca", MunicipioEntidad.class);
        List<MunicipioEntidad> municipioLista = typedQuery.getResultList();
        Assert.assertNotNull(municipioLista);
        Assert.assertFalse(municipioLista.isEmpty());
        for (MunicipioEntidad municipioEntidad : municipioLista) {
            Assert.assertNotNull(municipioEntidad.getNombre());
            Assert.assertNotNull(municipioEntidad.getEstado());
            Assert.assertNotNull(municipioEntidad.getEstado().getNombre());
            Assert.assertNotNull(municipioEntidad.getEstado().getId());
        }
    }

}
