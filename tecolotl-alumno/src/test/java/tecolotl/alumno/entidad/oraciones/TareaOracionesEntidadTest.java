package tecolotl.alumno.entidad.oraciones;


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
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaOracionesEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonaEntidad.class, AlumnoEntidad.class,
                        TareaOracionesEntidad.class, TareaOracionesEntidadPK.class, TareaEntidad.class, OracionesEntidad.class,
                            OracionesEntidadPK.class, ActividadEntidad.class, NivelLenguajeEntidad.class, TipoEstudianteEntidad.class,
                            ClaseGlosarioEntidad.class, GlosarioEntidad.class, GlosarioEntidadPK.class, GlosarioActividadEntidad.class,
                            GlosarioActividadEntidadPK.class, TemaEntidad.class, CatalagoEntidad.class, LoggerProducer.class,
                        TareaMapaMentalActividadEntidad.class, TareaMapaMentalActividadEntidadPK.class, MapaMentalEntidad.class,
                        MapaMentalActividadEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<TareaOracionesEntidad> typedQuery = entityManager.createNamedQuery("TareaOracionesEntidad.busca", TareaOracionesEntidad.class);
        List<TareaOracionesEntidad> tareaOracionesEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaOracionesEntidadLista);
        Assert.assertFalse(tareaOracionesEntidadLista.isEmpty());
        for (TareaOracionesEntidad tareaOracionesEntidad: tareaOracionesEntidadLista){
            Assert.assertNotNull(tareaOracionesEntidad);
            Assert.assertNotNull(tareaOracionesEntidad.getTareaOracionesEntidadPK().getOracionesEntidad().getOracionesEntidadPK().getActividadEntidad().getId());
            Assert.assertNotNull(tareaOracionesEntidad.getTareaOracionesEntidadPK().getOracionesEntidad().getOracionesEntidadPK().getCodigo());
            Assert.assertNotNull(tareaOracionesEntidad.getTareaOracionesEntidadPK().getTareaEntidad());
            Assert.assertNotNull(tareaOracionesEntidad.getRespuesta());
        }
    }

    @Test
    public void buscaTarea_id(){
        TypedQuery<TareaOracionesEntidad> typedQuery = entityManager.createNamedQuery("TareaOracionesEntidad.buscaid_tarea", TareaOracionesEntidad.class);
        typedQuery.setParameter("id_tarea", 5);
        List<TareaOracionesEntidad> tareaOracionesEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaOracionesEntidadLista);
        Assert.assertFalse(tareaOracionesEntidadLista.isEmpty());
        for (TareaOracionesEntidad tareaOracionesEntidad: tareaOracionesEntidadLista){
            Assert.assertNotNull(tareaOracionesEntidad);
            Assert.assertNotNull(tareaOracionesEntidad.getTareaOracionesEntidadPK().getOracionesEntidad().getOracionesEntidadPK().getActividadEntidad().getId());
            Assert.assertNotNull(tareaOracionesEntidad.getTareaOracionesEntidadPK().getOracionesEntidad().getOracionesEntidadPK().getCodigo());
            Assert.assertNotNull(tareaOracionesEntidad.getTareaOracionesEntidadPK().getTareaEntidad());
            Assert.assertNotNull(tareaOracionesEntidad.getRespuesta());
        }
    }
}
