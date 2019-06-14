package tecolotl.profesor.sesion;

import org.hibernate.validator.constraints.EAN;
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
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.GrupoEntidadPK;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.modelo.ProfesorModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class GrupoSesionBeanTest {

    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(GrupoEntidad.class.getPackage())
                .addPackage(GrupoModelo.class.getPackage())
                .addPackage(ProfesorEntidad.class.getPackage())
                .addPackage(ProfesorModelo.class.getPackage())
                .addPackage(PersonaEntidad.class.getPackage())
                .addPackage(PersonaModelo.class.getPackage())
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
                        GrupoProfesorValidacion.class
                            )
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GrupoSesionBean grupoSesionBean;

    @Test
    public void busca(){
        List<GrupoModelo> grupoModeloLista = grupoSesionBean.busca();
        Assert.assertNotNull(grupoModeloLista);
        Assert.assertFalse(grupoModeloLista.isEmpty());
        for (GrupoModelo grupoModelo : grupoModeloLista){
            Assert.assertNotNull(grupoModelo);
            Assert.assertNotNull(grupoModelo.getId());
            Assert.assertNotNull(grupoModelo.getGrupo());
            Assert.assertNotNull(grupoModelo.getGrado());
            Assert.assertNotNull(grupoModelo.getProfesorModelo());
        }
    }

    @Test
    public void inserta(){
        GrupoModelo grupoModelo = new GrupoModelo();
        grupoModelo.setGrado((short)6);
        grupoModelo.setGrupo('D');
        grupoModelo.setProfesorModelo(new ProfesorModelo(-42));
        grupoSesionBean.inserta(grupoModelo);
        Assert.assertNotNull(grupoModelo);
    }

    @Test
    public void actualiza(){
        GrupoModelo grupoModelo = new GrupoModelo(3);
        grupoModelo.setGrado((short)6);
        grupoModelo.setGrupo('A');
        grupoModelo.setProfesorModelo(new ProfesorModelo(1));
        int grupoAct = grupoSesionBean.actualiza(grupoModelo);
        Assert.assertFalse(grupoAct == 0);
    }

    @Test
    public void elimina(){
        int grupoEl = grupoSesionBean.elimina(-44);
        Assert.assertFalse(grupoEl == 0);
    }
}
