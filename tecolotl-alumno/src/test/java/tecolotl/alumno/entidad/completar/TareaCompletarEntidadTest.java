package tecolotl.alumno.entidad.completar;


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
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidadPK;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidadPK;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class TareaCompletarEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(PersonaEntidad.class, AlumnoEntidad.class,
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
        TypedQuery<TareaCompletarEntidad> typedQuery = entityManager.createNamedQuery("TareaCompletarEntidad.busca", TareaCompletarEntidad.class);
        List<TareaCompletarEntidad> tareaCompletarEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaCompletarEntidadLista);
        Assert.assertFalse(tareaCompletarEntidadLista.isEmpty());
        for (TareaCompletarEntidad tareaCompletarEntidad: tareaCompletarEntidadLista){
            Assert.assertNotNull(tareaCompletarEntidad);
            Assert.assertNotNull(tareaCompletarEntidad.getTareaCompletarEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaCompletarEntidad.getTareaCompletarEntidadPK().getCompletarEntidad().getId());
            Assert.assertNotNull(tareaCompletarEntidad.getRespuesta());
            Assert.assertNotNull(tareaCompletarEntidad.getHora_respuesta());
        }
    }

    @Test
    public void buscaTarea_id(){
        TypedQuery<TareaCompletarEntidad> typedQuery = entityManager.createNamedQuery("TareaCompletarEntidad.buscaid_tarea", TareaCompletarEntidad.class);
        typedQuery.setParameter("id_tarea", 4);
        List<TareaCompletarEntidad> tareaCompletarEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaCompletarEntidadLista);
        Assert.assertFalse(tareaCompletarEntidadLista.isEmpty());
        for (TareaCompletarEntidad tareaCompletarEntidad: tareaCompletarEntidadLista){
            Assert.assertNotNull(tareaCompletarEntidad);
            Assert.assertNotNull(tareaCompletarEntidad.getTareaCompletarEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaCompletarEntidad.getTareaCompletarEntidadPK().getCompletarEntidad().getId());
            Assert.assertNotNull(tareaCompletarEntidad.getRespuesta());
            Assert.assertNotNull(tareaCompletarEntidad.getHora_respuesta());
        }
    }
}
