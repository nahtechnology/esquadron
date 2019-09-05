package tecolotl.alumno.entidad.hablar;


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
import tecolotl.alumno.entidad.completar.CompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidadPK;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidad;
import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidadPK;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidadPK;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidadPK;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaHablarEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(TareaHablarEntidad.class, TareaHablarEntidadPK.class, AlumnoEntidad.class,
                        PersonaEntidad.class, HablarEntidad.class,
                        TareaCompletarEntidad.class, TareaCompletarEntidadPK.class, CompletarEntidad.class,
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
        TypedQuery<TareaHablarEntidad> typedQuery = entityManager.createNamedQuery("TareaHablarEntidad.busca", TareaHablarEntidad.class);
        List<TareaHablarEntidad> tareaHablarEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaHablarEntidadLista);
        Assert.assertFalse(tareaHablarEntidadLista.isEmpty());
        for (TareaHablarEntidad tareaHablarEntidad: tareaHablarEntidadLista){
            Assert.assertNotNull(tareaHablarEntidad);
            Assert.assertNotNull(tareaHablarEntidad.getTareaHablarEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaHablarEntidad.getTareaHablarEntidadPK().getHablarEntidad().getId());
        }
    }

    @Test
    public void buscaTarea_id(){
        TypedQuery<TareaHablarEntidad> typedQuery = entityManager.createNamedQuery("TareaHablarEntidad.buscaid_tarea", TareaHablarEntidad.class);
        typedQuery.setParameter("id_tarea", 2);
        List<TareaHablarEntidad> tareaHablarEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaHablarEntidadLista);
        Assert.assertFalse(tareaHablarEntidadLista.isEmpty());
        for (TareaHablarEntidad tareaHablarEntidad: tareaHablarEntidadLista){
            Assert.assertNotNull(tareaHablarEntidad);
            Assert.assertNotNull(tareaHablarEntidad.getTareaHablarEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaHablarEntidad.getTareaHablarEntidadPK().getHablarEntidad().getId());
        }
    }
}
