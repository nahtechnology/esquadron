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

import static org.junit.Assert.*;

import java.util.List;

@RunWith(Arquillian.class)
public class ColoniaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(EstadoEntidad.class)
                .addPackage(ColoniaEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void buscaCodigoPostal() {
        TypedQuery<ColoniaEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaCodigoPostal", ColoniaEntidad.class);
        typedQuery.setParameter("codigoPostal", "72470");
        List<ColoniaEntidad> colonialista = typedQuery.getResultList();
        Assert.assertNotNull(colonialista);
        Assert.assertFalse(colonialista.isEmpty());
        
    }

    @Test
    public void buscaMunicipioCodigoPostal() {
        TypedQuery<MunicipioEntidad> typedQuery = entityManager.createNamedQuery("ColoniaEntidad.buscaMunicipoCodigoPostal", MunicipioEntidad.class);
        typedQuery.setParameter("codigoPostal", "72000");
        typedQuery.setMaxResults(1);
        MunicipioEntidad municipioEntidad = typedQuery.getSingleResult();
        Assert.assertNotNull(municipioEntidad);
        Assert.assertNotNull(municipioEntidad.getNombre());
        Assert.assertNotNull(municipioEntidad.getId());
    }
}
