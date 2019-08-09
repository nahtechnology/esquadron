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
public class TareaGlosarioActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses( GlosarioEntidad.class, ClaseGlosarioEntidad.class, TareaEntidad.class,
                        ActividadEntidad.class, GlosarioActividadEntidad.class, GlosarioActividadEntidadPK.class,
                        TareaGlosarioActividadEntidadPK.class, NivelLenguajeEntidad.class, ActividadEntidad.class,
                        CatalagoEntidad.class, TipoEstudianteEntidad.class, TareaVideoEntidad.class,
                        TareaVideoEntidadPK.class, TareaGlosarioActividadEntidad.class, TareaEscribirActividadEntidad.class,
                        TareaEscribirActividadEntidadPK.class, EscribirActividadEntidad.class, EscribirEntidad.class, EscribirActividadEntidadPK.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca() {
        TypedQuery<TareaGlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaGlosarioActividadEntidad.busca", TareaGlosarioActividadEntidad.class);
        List<TareaGlosarioActividadEntidad> tareaGlosarioEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaGlosarioEntidadLista);
        Assert.assertFalse(tareaGlosarioEntidadLista.isEmpty());
        for (TareaGlosarioActividadEntidad tareaGlosarioEntidad : tareaGlosarioEntidadLista){
            Assert.assertNotNull(tareaGlosarioEntidad);
            TareaGlosarioActividadEntidadPK tareaGlosarioEntidadPK = tareaGlosarioEntidad.getTareaGlosarioActividadEntidadPK();
            Assert.assertNotNull(tareaGlosarioEntidadPK);
            Assert.assertNotNull(tareaGlosarioEntidadPK.getGlosarioActividadEntidad());
            Assert.assertNotNull(tareaGlosarioEntidadPK.getGlosarioActividadEntidad().getGlosarioActividadEntidadPK().getActividadEntidad());
            Assert.assertNotNull(tareaGlosarioEntidadPK.getGlosarioActividadEntidad().getGlosarioActividadEntidadPK().getGlosarioEntidad());
            Assert.assertNotNull(tareaGlosarioEntidadPK.getTareaEntidad());

        }
    }
}
