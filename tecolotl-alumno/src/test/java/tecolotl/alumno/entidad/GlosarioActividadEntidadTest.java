package tecolotl.alumno.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
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
public class GlosarioActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(CatalagoEntidad.class, ClaseGlosarioEntidad.class, GlosarioEntidad.class,
                            ActividadEntidad.class, NivelLenguajeEntidad.class, TipoEstudianteEntidad.class,
                            GlosarioActividadEntidad.class, GlosarioActividadEntidadPK.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<GlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("GlosarioActividadEntidad.busca",GlosarioActividadEntidad.class);
        List<GlosarioActividadEntidad> glosarioActividadEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(glosarioActividadEntidadLista);
        Assert.assertFalse(glosarioActividadEntidadLista.isEmpty());
        for (GlosarioActividadEntidad glosarioActividadEntidad : glosarioActividadEntidadLista){
            Assert.assertNotNull(glosarioActividadEntidad);
            Assert.assertNotNull(glosarioActividadEntidad.getGlosarioActividadEntidadPK());
            Assert.assertNotNull(glosarioActividadEntidad.getGlosarioActividadEntidadPK().getActividadEntidad());
            Assert.assertNotNull(glosarioActividadEntidad.getGlosarioActividadEntidadPK().getGlosarioEntidad());
        }
    }
}
