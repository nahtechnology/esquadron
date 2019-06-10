package tecolotl.profesor.sesion;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.*;
import tecolotl.alumno.entidad.*;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.modelo.ProfesorModelo;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.escuela.EscuelaBaseModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDashboardModelo;
import tecolotl.administracion.modelo.escuela.EscuelaDetalleModelo;
import tecolotl.administracion.modelo.escuela.MotivoBloqueoModelo;
import tecolotl.administracion.persistencia.entidad.*;
import tecolotl.administracion.sesion.EscuelaSesionBean;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.GrupoEntidadPK;
import tecolotl.profesor.entidad.ProfesorEntidad;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class ProfesorSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "prueba.war")
            .addPackage(PersonaModelo.class.getPackage())
            .addPackage(ProfesorModelo.class.getPackage())
            .addPackage(ProfesorEntidad.class.getPackage())
            .addPackage(PersonaEntidad.class.getPackage())
            .addClasses(PersonaModelo.class, PersonaEntidad.class, ProfesorEntidad.class,
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
                        ValidadorSessionBean.class, ProfesorSesionBean.class, ColoniaNuevaValidacion.class)
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Test
    public void busca(){
        List<ProfesorModelo> profesorModeloLista = profesorSesionBean.busca();
        Assert.assertNotNull(profesorModeloLista);
        Assert.assertFalse(profesorModeloLista.isEmpty());
        for (ProfesorModelo profesorModelo : profesorModeloLista){
            Assert.assertNotNull(profesorModelo);
            Assert.assertNotNull(profesorModelo.getId());
            Assert.assertNotNull(profesorModelo.getEscuelaBaseModelo());
        }
    }

    @Test
    public void inserta(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Cochi");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("Cochiloco");
        profesorModelo.setContrasenia("123456".getBytes());
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBS0029K"));
    }

    /*public void inserta(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Jesus");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("El Juanito");
        profesorModelo.setContrasenia("123456".getBytes());
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBS0029K"));
    }*/

    @Test
    public void actualiza(){
        ProfesorModelo profesorModelo = new ProfesorModelo(2);
        profesorModelo.setApodo("Don Levantonces");
        int elementosModificados = profesorSesionBean.actualiza(profesorModelo);
        Assert.assertFalse(elementosModificados == 0);
    }

    @Test
    public void elimina(){
        ProfesorModelo profesorModelo = new ProfesorModelo(new ProfesorEntidad());
        profesorSesionBean.elimina(2);
        Assert.assertNotNull(profesorModelo);
    }
}
