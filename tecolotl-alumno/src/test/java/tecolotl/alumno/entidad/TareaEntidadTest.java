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
public class TareaEntidadTest {
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(AlumnoEntidad.class, PersonaEntidad.class, TareaEntidad.class, TareaGlosarioActividadEntidad.class,
                        TareaGlosarioActividadEntidadPK.class, GlosarioEntidad.class, ActividadEntidad.class,
                        TipoEstudianteEntidad.class,
                        NivelLenguajeEntidad.class, CatalagoEntidad.class, GradoEscolarEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.busca", TareaEntidad.class);
        List<TareaEntidad> tareaEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaEntidadLista);
        Assert.assertFalse(tareaEntidadLista.isEmpty());
        for (TareaEntidad tareaEntidad : tareaEntidadLista){
            Assert.assertNotNull(tareaEntidad);
            Assert.assertNotNull(tareaEntidad.getId());
            Assert.assertNotNull(tareaEntidad.getAlumnoEntidad());
            Assert.assertNotNull(tareaEntidad.getAsignacion());
        }
    }

    @Test
    public void buscaPorAlumno() {
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.buscaPorAlumno", TareaEntidad.class);
        typedQuery.setParameter("idAlumno", 5);
        TareaEntidad tareaEntidad = typedQuery.getSingleResult();
        Assert.assertNotNull(tareaEntidad);
    }
}
