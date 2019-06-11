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

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@RunWith(Arquillian.class)
public class GrupoAlumnoEntidadTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(ProfesorEntidad.class, GrupoEntidad.class, GrupoAlumnoEntidad.class,
                        GrupoAlumnoEntidadPK.class, Logger.class, PersonaEntidad.class,
                        CatalagoEntidad.class, EscuelaEntidad.class, AlumnoEntidad.class,
                        ColoniaEntidad.class, MunicipioEntidad.class, EstadoEntidad.class,
                        MotivoBloqueoEntidad.class, ContactoEntidad.class, ContactoEntidadPK.class,
                        TipoContactoEntidad.class, LicenciaEntidad.class, LicenciaEntidadPk.class,
                        GradoEscolarEntidad.class, NivelLenguajeEntidad.class, ActividadEntidad.class,
                        TareaEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class,
                        TipoEstudianteEntidad.class, GlosarioEntidad.class)
            .addPackage(GrupoAlumnoEntidad.class.getPackage())
            .addPackage(GrupoEntidad.class.getPackage())
            .addPackage(GrupoAlumnoEntidadPK.class.getPackage())
            .addPackage(AlumnoEntidad.class.getPackage())
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private EntityManager entityManager;

    @Test
    public void busca(){
        TypedQuery<GrupoAlumnoEntidad> typedQuery = entityManager.createNamedQuery("GrupoAlumnoEntidad.busca", GrupoAlumnoEntidad.class);
        List<GrupoAlumnoEntidad> grupoAlumnoEntidadLista = typedQuery.getResultList();
        Assert.assertNotNull(grupoAlumnoEntidadLista);
        Assert.assertFalse(grupoAlumnoEntidadLista.isEmpty());
        for (GrupoAlumnoEntidad grupoAlumnoEntidad :grupoAlumnoEntidadLista){
            Assert.assertNotNull(grupoAlumnoEntidad);
            //Assert.assertNotNull(grupoAlumnoEntidad.getGrupoAlumnoEntidadPK());
            //
    }
}
