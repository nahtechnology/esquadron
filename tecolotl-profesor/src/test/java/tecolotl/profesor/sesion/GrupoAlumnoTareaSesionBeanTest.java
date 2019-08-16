package tecolotl.profesor.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.persistencia.entidad.*;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ProfesorValidacion;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.glosario.GlosarioEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidad;
import tecolotl.alumno.entidad.glosario.TareaGlosarioActividadEntidadPK;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;
import tecolotl.profesor.entidad.*;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.modelo.GrupoAlumnoTareaModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class GrupoAlumnoTareaSesionBeanTest {
    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(GrupoAlumnoTareaEntidad.class.getPackage())
            .addPackage(GrupoAlumnoTareaEntidadPK.class.getPackage())
            .addPackage(GrupoEntidad.class.getPackage())
            .addPackage(AlumnoEntidad.class.getPackage())
            .addPackage(TareaEntidad.class.getPackage())
            .addClasses(
                GrupoModelo.class, GrupoSesionBean.class, GrupoEntidad.class,
                PersonaModelo.class, PersonaEntidad.class, ProfesorEntidad.class,
                ProfesorModelo.class, ProfesorSesionBean.class, EscuelaEntidad.class,
                EscuelaBaseModelo.class, EscuelaSesionBean.class, EscuelaDetalleModelo.class,
                MotivoBloqueoModelo.class, ColoniaEntidad.class, MunicipioEntidad.class,
                EstadoEntidad.class, MotivoBloqueoEntidad.class, CatalagoEntidad.class,
                CatalogoModelo.class, ContactoEntidad.class, ContactoEntidadPK.class,
                TipoContactoEntidad.class, LicenciaEntidad.class, LicenciaEntidadPk.class,
                AlumnoEntidad.class, GrupoEntidad.class, GrupoEntidadPK.class,
                NivelLenguajeEntidad.class, GradoEscolarEntidad.class, EscuelaDashboardModelo.class,
                LoggerProducer.class, TareaEntidad.class, TareaGlosarioActividadEntidad.class,
                TareaGlosarioActividadEntidadPK.class, GlosarioEntidad.class, ActividadEntidad.class,
                TipoEstudianteEntidad.class, ColoniaModelo.class, ProfesorValidacion.class,
                ValidadorSessionBean.class, ProfesorSesionBean.class, ColoniaNuevaValidacion.class,
                GrupoProfesorValidacion.class, GrupoAlumnoEntidad.class, GrupoAlumnoEntidadPK.class,
                GrupoAlumnoModelo.class, GrupoAlumnoSesionBean.class, GrupoAlumnoTareaEntidad.class,
                GrupoAlumnoTareaEntidadPK.class, GrupoAlumnoTareaModelo.class, GrupoAlumnoTareaSesionBean.class,
                    PersonaNuevaValidacion.class
            )
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GrupoAlumnoTareaSesionBean grupoAlumnoTareaSesionBean;

    @Test
    public void busca(){
        List<GrupoAlumnoTareaModelo> grupoAlumnoTareaModeloLista = grupoAlumnoTareaSesionBean.busca();
        Assert.assertNotNull(grupoAlumnoTareaModeloLista);
        Assert.assertFalse(grupoAlumnoTareaModeloLista.isEmpty());
        for (GrupoAlumnoTareaModelo grupoAlumnoTareaModelo : grupoAlumnoTareaModeloLista){
            Assert.assertNotNull(grupoAlumnoTareaModelo);
            Assert.assertNotNull(grupoAlumnoTareaModelo.getIdGrupo());
            Assert.assertNotNull(grupoAlumnoTareaModelo.getIdAlumno());
            Assert.assertNotNull(grupoAlumnoTareaModelo.getIdTarea());
            Assert.assertNotNull(grupoAlumnoTareaModelo.getAsignacion());
        }
    }

    @Test
    public void inserta(){
        GrupoAlumnoTareaModelo grupoAlumnoTareaModelo = new GrupoAlumnoTareaModelo();
        grupoAlumnoTareaModelo.setIdGrupo(3);
        grupoAlumnoTareaModelo.setIdAlumno(10);
        grupoAlumnoTareaModelo.setIdTarea(8);
        grupoAlumnoTareaSesionBean.inserta(grupoAlumnoTareaModelo);
        Assert.assertNotNull(grupoAlumnoTareaModelo);
    }

    @Test
    public void elimina(){
        GrupoAlumnoTareaModelo grupoAlumnoTareaModelo = new GrupoAlumnoTareaModelo();
        grupoAlumnoTareaModelo.setIdGrupo(3);
        grupoAlumnoTareaModelo.setIdAlumno(10);
        grupoAlumnoTareaModelo.setIdTarea(8);
        Integer grupoATEliminado = grupoAlumnoTareaSesionBean.elimina(grupoAlumnoTareaModelo);
        Assert.assertFalse(grupoATEliminado == 0);
    }
}