package tecolotl.alumno.persistencia.entidad;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
public class GradoEscolarEntidadTest {
	
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Deployment
	public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(GradoEscolarEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        
}
	
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<GradoEscolarEntidad> typedQuery = entityManager.createNamedQuery("GradoEscolarEntidad.findAll", GradoEscolarEntidad.class);
        List<GradoEscolarEntidad> listaTarea = typedQuery.getResultList();
        Assert.assertNotNull(listaTarea);
        logger.info(String.valueOf(listaTarea.size()));
        Assert.assertFalse("Lista vacia", listaTarea.isEmpty());
    }
    
    public void crear() {

    }
	
}