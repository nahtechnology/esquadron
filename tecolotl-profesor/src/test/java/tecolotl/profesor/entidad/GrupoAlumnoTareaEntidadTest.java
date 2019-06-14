package tecolotl.profesor.entidad;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.persistencia.entidad.*;
import tecolotl.alumno.entidad.*;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class GrupoAlumnoTareaEntidadTest {
    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(GrupoAlumnoTareaEntidad.class.getPackage())
                .addPackage(GrupoAlumnoTareaEntidadPK.class.getPackage())
                .addPackage(GrupoEntidad.class.getPackage())
                .addClasses(ProfesorEntidad.class, GrupoEntidad.class, GrupoAlumnoEntidad.class,
                        GrupoAlumnoEntidadPK.class, Logger.class, PersonaEntidad.class,
                        CatalagoEntidad.class, EscuelaEntidad.class, AlumnoEntidad.class,
                        ColoniaEntidad.class, MunicipioEntidad.class, EstadoEntidad.class,
                        MotivoBloqueoEntidad.class, ContactoEntidad.class, ContactoEntidadPK.class,
                        TipoContactoEntidad.class, LicenciaEntidad.class, LicenciaEntidadPk.class,
                        GradoEscolarEntidad.class, NivelLenguajeEntidad.class, ActividadEntidad.class,
                        TareaEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        TipoEstudianteEntidad.class, GlosarioEntidad.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<GrupoAlumnoTareaEntidad> typedQuery = entityManager.createNamedQuery("GrupoAlumnoTareaEntidad.busca", GrupoAlumnoTareaEntidad.class);
        List<GrupoAlumnoTareaEntidad> grupoAlumnoTareaEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(grupoAlumnoTareaEntidadLista);
        Assert.assertFalse(grupoAlumnoTareaEntidadLista.isEmpty());
        for (GrupoAlumnoTareaEntidad grupoAlumnoTareaEntidad : grupoAlumnoTareaEntidadLista){
            Assert.assertNotNull(grupoAlumnoTareaEntidad);
            Assert.assertNotNull(grupoAlumnoTareaEntidad.getAsignacion());

        }
    }
}

