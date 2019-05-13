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
public class TareaGlosarioEntidadTest {
    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses( GlosarioEntidad.class, TareaEntidad.class, ActividadEntidad.class,
                        TareaGlosarioEntidad.class, TareaGlosarioEntidadPK.class, AlumnoEntidad.class,
                        PersonaEntidad.class, NivelLenguajeEntidad.class, ActividadEntidad.class,
                        CatalagoEntidad.class, GradoEscolarEntidad.class, TipoEstudianteEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TareaGlosarioEntidad> typedQuery = entityManager.createNamedQuery("TareaGlosarioEntidad.busca", TareaGlosarioEntidad.class);
        List<TareaGlosarioEntidad> tareaGlosarioEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaGlosarioEntidadLista);
        Assert.assertFalse(tareaGlosarioEntidadLista.isEmpty());
        for (TareaGlosarioEntidad tareaGlosarioEntidad : tareaGlosarioEntidadLista){
            Assert.assertNotNull(tareaGlosarioEntidad);
            Assert.assertNotNull(tareaGlosarioEntidad.getRespuesta());
            TareaGlosarioEntidadPK tareaGlosarioEntidadPK = tareaGlosarioEntidad.getTareaGlosarioEntidadPK();
            Assert.assertNotNull(tareaGlosarioEntidadPK);
            Assert.assertNotNull(tareaGlosarioEntidadPK.getGlosarioEntidad());
            Assert.assertNotNull(tareaGlosarioEntidadPK.getTareaEntidad());

        }
    }
}
