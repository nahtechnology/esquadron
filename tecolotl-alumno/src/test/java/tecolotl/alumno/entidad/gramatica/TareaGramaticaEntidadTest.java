package tecolotl.alumno.entidad.gramatica;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidadPK;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaGramaticaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonaEntidad.class, AlumnoEntidad.class,
                        TareaGramaticaEntidad.class, TareaGramaticaEntidadPK.class, TareaEntidad.class, ActividadEntidad.class,
                        TipoEstudianteEntidad.class, NivelLenguajeEntidad.class, CatalagoEntidad.class, TemaEntidad.class,
                        TareaMapaMentalActividadEntidad.class, TareaMapaMentalActividadEntidadPK.class, GlosarioActividadEntidad.class,
                        GlosarioEntidad.class, ClaseGlosarioEntidad.class, GlosarioEntidadPK.class, TareaGlosarioActividadEntidad.class,
                        TareaGlosarioActividadEntidadPK.class, MapaMentalEntidad.class, MapaMentalActividadEntidad.class,
                        GramaticaEntidad.class, GramaticaEntidadPK.class, GlosarioActividadEntidadPK.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<TareaGramaticaEntidad> typedQuery = entityManager.createNamedQuery("TareaGramaticaEntidad.busca", TareaGramaticaEntidad.class);
        List<TareaGramaticaEntidad> tareaGramaticaEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaGramaticaEntidadLista);
        Assert.assertFalse(tareaGramaticaEntidadLista.isEmpty());
        for (TareaGramaticaEntidad tareaGramaticaEntidad : tareaGramaticaEntidadLista){
            Assert.assertNotNull(tareaGramaticaEntidad);
            Assert.assertNotNull(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getActividadEntidad().getId());
            Assert.assertNotNull(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getCodigo());
            Assert.assertNotNull(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaGramaticaEntidad.getRespuesta());
            Assert.assertNotNull(tareaGramaticaEntidad.getHoraRespuesta());
        }
    }

    @Test
    public void buscaTarea_id(){
        TypedQuery<TareaGramaticaEntidad> typedQuery = entityManager.createNamedQuery("TareaGramaticaEntidad.buscaTarea", TareaGramaticaEntidad.class);
        typedQuery.setParameter("idTarea", 3);
        List<TareaGramaticaEntidad> tareaGramaticaEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaGramaticaEntidadLista);
        Assert.assertFalse(tareaGramaticaEntidadLista.isEmpty());
        for (TareaGramaticaEntidad tareaGramaticaEntidad : tareaGramaticaEntidadLista){
            Assert.assertNotNull(tareaGramaticaEntidad);
            Assert.assertNotNull(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getActividadEntidad().getId());
            Assert.assertNotNull(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getCodigo());
            Assert.assertNotNull(tareaGramaticaEntidad.getTareaGramaticaEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaGramaticaEntidad.getRespuesta());
            Assert.assertNotNull(tareaGramaticaEntidad.getHoraRespuesta());
        }
    }
}
