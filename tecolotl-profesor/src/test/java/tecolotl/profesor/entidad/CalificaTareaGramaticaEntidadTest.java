package tecolotl.profesor.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.ContactoModelo;
import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.completar.CompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidadPK;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidadPK;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidadPK;
import tecolotl.alumno.entidad.hablar.HablarEntidad;
import tecolotl.alumno.entidad.hablar.TareaHablarEntidad;
import tecolotl.alumno.entidad.hablar.TareaHablarEntidadPK;
import tecolotl.alumno.entidad.mapamental.*;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidadPK;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidadPK;
import tecolotl.alumno.entidad.relacionar.*;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.CorreoEnum;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.sesion.GrupoAlumnoSesionBean;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(Arquillian.class)
public class CalificaTareaGramaticaEntidadTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(MapaMentalActividadEntidad.class, MapaMentalActividadEntidadPK.class,
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
                        MapaMentalActividadEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        TareaRelacionarActividadEntidad.class, TareaRelacionarActividadEntidadPK.class, RelacionarActividadEntidad.class,
                        RelacionarActividadEntidadPK.class, RelacionarEntidad.class, CalificaTareaGramaticaEntidad.class, TareaGramaticaEntidad.class,
                        TareaGramaticaEntidadPK.class, GramaticaEntidad.class, GramaticaEntidadPK.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<CalificaTareaGramaticaEntidad> typedQuery = entityManager.createNamedQuery("CalificaTareaGramaticaEntidad.busca", CalificaTareaGramaticaEntidad.class);
        List<CalificaTareaGramaticaEntidad> calificaTareaGramaticaEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(calificaTareaGramaticaEntidadLista);
        Assert.assertFalse(calificaTareaGramaticaEntidadLista.isEmpty());
        for (CalificaTareaGramaticaEntidad calificaTareaGramaticaEntidad : calificaTareaGramaticaEntidadLista){
            Assert.assertNotNull(calificaTareaGramaticaEntidad);
            Assert.assertNotNull(calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getActividadEntidad().getId());
            Assert.assertNotNull(calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getGramaticaEntidad().getGramaticaEntidadPK().getCodigo());
            Assert.assertNotNull(calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getTareaEntidad().getId());
            Assert.assertNotNull(calificaTareaGramaticaEntidad.getTareaGramaticaEntidad().getTareaGramaticaEntidadPK().getVuelta());
        }
    }
}
