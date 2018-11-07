package tecolotl.nucleo.persistencia.entidad;

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

import tecolotl.nucleo.persistence.enumeracion.NivelLenguajeEnumeracion;

@RunWith(Arquillian.class)
public class TareaEntidadTest {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(TareaEntidad.class)
                .addClass(NivelLenguajeEnumeracion.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("tarea.busca", TareaEntidad.class);
        List<TareaEntidad> listaTarea = typedQuery.getResultList();
        Assert.assertNotNull(listaTarea);
        Assert.assertFalse("Lista vacia", listaTarea.isEmpty());
    }
    
    @Test
    public void buscaId() {
    	TareaEntidad tareaEntidad = entityManager.find(TareaEntidad.class, 1);
    	Assert.assertNotNull("Sin tarea", tareaEntidad);
    	Assert.assertNotNull("Pregunta detonadora", tareaEntidad.getPreguntaDetonadora());
    	Assert.assertTrue("tiempo menor a cero", tareaEntidad.getTiempo() > 0);
    	Assert.assertTrue("puntaje a cero", tareaEntidad.getPuntaje() > 0);
    	Assert.assertNotNull("Sin nivel de lenguaje", tareaEntidad.getNivelLenguaje());
    	logger.info(tareaEntidad.getNivelLenguaje().name());
    	Assert.assertNotNull("Sin video", tareaEntidad.getVideo());
    	Assert.assertNotNull("Sin transcripcion", tareaEntidad.getTranscripcion());
    }
	
}
