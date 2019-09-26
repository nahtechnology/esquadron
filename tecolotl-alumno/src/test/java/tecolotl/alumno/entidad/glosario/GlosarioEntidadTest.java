package tecolotl.alumno.entidad.glosario;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.completar.TareaCompletarEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.hablar.HablarEntidad;
import tecolotl.alumno.entidad.mapamental.MapaMentalEntidad;
import tecolotl.alumno.entidad.oraciones.OracionesEntidad;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.vista.TareasResueltasEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.completar.TareaCompletarModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.modelo.hablar.HablarModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.oraciones.OracionesModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.modelo.vista.TareaResuetasModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.alumno.validacion.mapamental.MapaMentalLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GlosarioEntidadTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(MapaMentalEntidad.class.getPackage()).addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage())
                .addPackage(MapaMentalModelo.class.getPackage())
                .addPackage(GlosarioModelo.class.getPackage())
                .addPackage(GlosarioEntidad.class.getPackage())
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage())
                .addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(MapaMentalLlavePrimariaValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(ValidadorSessionBean.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage())
                .addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage())
                .addPackage(CatalogoModelo.class.getPackage())
                .addPackage(RelacionarModelo.class.getPackage())
                .addPackage(TareaRelacionarActividadEntidad.class.getPackage())
                .addPackage(TareaGramaticaEntidad.class.getPackage())
                .addPackage(RelacionarOracionLlavePrimariaValidacion.class.getPackage())
                .addPackage(GramaticaModelo.class.getPackage()).addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(RelacionarLlavePrimariaValidacion.class.getPackage()).addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(TareaResuetasModelo.class.getPackage()).addPackage(TareasResueltasEntidad.class.getPackage())
                .addPackage(TareaRelacionarOracionesEntidad.class.getPackage()).addPackage(TareaRelacionarOracionModelo.class.getPackage())
                .addPackage(OracionesModelo.class.getPackage()).addPackage(OracionesEntidad.class.getPackage())
                .addPackage(HablarModelo.class.getPackage()).addPackage(HablarEntidad.class.getPackage())
                .addPackage(TareaCompletarEntidad.class.getPackage()).addPackage(TareaCompletarModelo.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void buscaReducido() {
        TypedQuery<GlosarioEntidad> typedQuery = entityManager.createNamedQuery("GlosarioEntidad.buscaNoImganeIdTarea", GlosarioEntidad.class);
        typedQuery.setParameter("idTarea", 4);
        List<GlosarioEntidad> glosarioEntidadLista = typedQuery.getResultList();
        assertNotNull(glosarioEntidadLista);
        assertFalse(glosarioEntidadLista.isEmpty());
        glosarioEntidadLista.forEach(glosarioEntidad -> {
            assertNotNull(glosarioEntidad);
            assertNotNull(glosarioEntidad.getGlosarioEntidadPK().getPalabra());
            assertNotNull(glosarioEntidad.getGlosarioEntidadPK().getClaseGlosarioEntidad().getValor());
            assertNotNull(glosarioEntidad.getSignificado());
        });
    }

}
