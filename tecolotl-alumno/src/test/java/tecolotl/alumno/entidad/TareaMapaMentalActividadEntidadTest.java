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
import tecolotl.alumno.entidad.completar.CompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidadPK;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.hablar.HablarEntidad;
import tecolotl.alumno.entidad.hablar.TareaHablarEntidad;
import tecolotl.alumno.entidad.hablar.TareaHablarEntidadPK;
import tecolotl.alumno.entidad.mapamental.*;
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

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TareaMapaMentalActividadEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(TareaMapaMentalActividadEntidad.class, TareaMapaMentalActividadEntidadPK.class,
                        MapaMentalActividadEntidad.class, MapaMentalActividadEntidadPK.class,
                        MapaMentalEntidad.class, MapaMentalEntidadPK.class, MapaMentalActividadEntidad.class, MapaMentalActividadEntidadPK.class,
                        TareaMapaMentalActividadEntidad.class, TareaMapaMentalActividadEntidadPK.class,
                        TareaHablarEntidad.class, TareaHablarEntidadPK.class, AlumnoEntidad.class,
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
    public void busca() {
        TypedQuery<TareaMapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaMapaMentalActividadEntidad.busca", TareaMapaMentalActividadEntidad.class);
        List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaMapaMentalActividadEntidadLista);
        Assert.assertFalse(tareaMapaMentalActividadEntidadLista.isEmpty());
        for (TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad : tareaMapaMentalActividadEntidadLista){
            Assert.assertNotNull(tareaMapaMentalActividadEntidad);
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCodigo());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCardinalidad());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getActividadEntidad().getId());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTextoRespuesta());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getHoraRespuesta());
        }
    }

    @Test
    public void buscaTarea_id(){
        TypedQuery<TareaMapaMentalActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaMapaMentalActividadEntidad.buscaidTarea", TareaMapaMentalActividadEntidad.class);
        typedQuery.setParameter("idTarea",4);
        List<TareaMapaMentalActividadEntidad> tareaMapaMentalActividadEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(tareaMapaMentalActividadEntidadLista);
        Assert.assertFalse(tareaMapaMentalActividadEntidadLista.isEmpty());
        for (TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad : tareaMapaMentalActividadEntidadLista){
            Assert.assertNotNull(tareaMapaMentalActividadEntidad);
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCodigo());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getMapaMentalEntidad().getMapaMentalEntidadPK().getCardinalidad());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getMapaMentalActividadEntidad().getMapaMentalActividadPK().getActividadEntidad().getId());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTareaMapaMentalActividadEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getTextoRespuesta());
            Assert.assertNotNull(tareaMapaMentalActividadEntidad.getHoraRespuesta());
        }
    }

}
