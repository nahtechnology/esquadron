package tecolotl.alumno.entidad;


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
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class GradoEscolarEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(CatalagoEntidad.class, AlumnoEntidad.class, PersonaEntidad.class,
                        NivelLenguajeEntidad.class, GradoEscolarEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<GradoEscolarEntidad> typedQuery = entityManager.createNamedQuery("GradoEscolarEntidad.busca", GradoEscolarEntidad.class);
        List<GradoEscolarEntidad> gradoEscolarEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(gradoEscolarEntidadLista);
        Assert.assertFalse(gradoEscolarEntidadLista.isEmpty());
        for (GradoEscolarEntidad gradoEscolarEntidad : gradoEscolarEntidadLista){
            Assert.assertNotNull(gradoEscolarEntidad);
            Assert.assertNotNull(gradoEscolarEntidad.getId());
            Assert.assertNotNull(gradoEscolarEntidad.getGrado());
            Assert.assertNotNull(gradoEscolarEntidad.getNivel());
        }
    }
}