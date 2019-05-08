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
public class TareaVideoEntidadTest {
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(TareaVideoEntidad.class, TareaVideoEntidadPK.class, TareaEntidad.class,
                        AlumnoEntidad.class, GradoEscolarEntidad.class, PersonaEntidad.class,
                        ActividadEntidad.class, NivelLenguajeEntidad.class, TipoEstudianteEntidad.class,
                        CatalagoEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<TareaVideoEntidad> typedQuery = entityManager.createNamedQuery("TareaVideoEntidad.busca", TareaVideoEntidad.class);
        List<TareaVideoEntidad> tareaVideoEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaVideoEntidadLista);
        Assert.assertFalse(tareaVideoEntidadLista.isEmpty());
        for (TareaVideoEntidad tareaVideoEntidad : tareaVideoEntidadLista){
            Assert.assertNotNull(tareaVideoEntidad);
            Assert.assertNotNull(tareaVideoEntidad.getReproducciones());
            TareaVideoEntidadPK tareaVideoEntidadPK = tareaVideoEntidad.getTareaVideoEntidadPK();
            Assert.assertNotNull(tareaVideoEntidadPK.getActividadEntidad());
            Assert.assertNotNull(tareaVideoEntidadPK.getTareaEntidad());
        }
    }
}
