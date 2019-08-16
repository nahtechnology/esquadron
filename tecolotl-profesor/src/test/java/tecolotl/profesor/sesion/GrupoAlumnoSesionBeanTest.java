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
import tecolotl.profesor.entidad.*;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class GrupoAlumnoSesionBeanTest {

    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(GrupoAlumnoEntidad.class.getPackage())
                .addPackage(GrupoAlumnoEntidad.class.getPackage())
                .addPackage(GrupoAlumnoEntidadPK.class.getPackage())
                .addPackage(GrupoEntidad.class.getPackage())
                .addPackage(GrupoModelo.class.getPackage())
                .addClasses(GrupoModelo.class, GrupoSesionBean.class, GrupoEntidad.class,
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
                        GrupoAlumnoModelo.class, GrupoAlumnoSesionBean.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GrupoAlumnoSesionBean grupoAlumnoSesionBean;

    @Test
    public void busca(){
        List<GrupoAlumnoModelo> grupoAlumnoModeloLista = grupoAlumnoSesionBean.busca();
        Assert.assertNotNull(grupoAlumnoModeloLista);
        Assert.assertFalse(grupoAlumnoModeloLista.isEmpty());
        for (GrupoAlumnoModelo grupoAlumnoModelo : grupoAlumnoModeloLista){
            Assert.assertNotNull(grupoAlumnoModelo);
            Assert.assertNotNull(grupoAlumnoModelo.getIdAlumno());
            Assert.assertNotNull(grupoAlumnoModelo.getIdGrupo());
        }
    }

    @Test
    public void inserta(){
        GrupoAlumnoModelo grupoAlumnoModelo = new GrupoAlumnoModelo();
        grupoAlumnoModelo.setIdAlumno(5);
        grupoAlumnoModelo.setIdGrupo(3);
        grupoAlumnoSesionBean.inserta(grupoAlumnoModelo);
        Assert.assertNotNull(grupoAlumnoModelo);
    }

    @Test
    public void elimina(){
        GrupoAlumnoModelo grupoAlumnoModelo = new GrupoAlumnoModelo();
        grupoAlumnoModelo.setIdGrupo(2);
        grupoAlumnoModelo.setIdAlumno(5);
        int grupoAEliminado = grupoAlumnoSesionBean.elimina(grupoAlumnoModelo);
        Assert.assertFalse(grupoAEliminado == 0);
    }
}
