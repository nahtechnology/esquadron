package tecolotl.profesor.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
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
import tecolotl.administracion.validacion.escuela.*;
import tecolotl.alumno.entidad.*;
import tecolotl.nucleo.herramienta.EncriptadorContrasenia;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.modelo.PersonaModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.entidad.GrupoEntidadPK;
import tecolotl.profesor.entidad.ProfesorEntidad;
import tecolotl.profesor.modelo.ProfesorModelo;

import javax.inject.Inject;
import java.util.List;

@RunWith(Arquillian.class)
public class ProfesorSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
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
                        ValidadorSessionBean.class, ProfesorSesionBean.class, ColoniaNuevaValidacion.class,
                        EncriptadorContrasenia.class, PersonaNuevaValidacion.class, ContactoLlavePrimariaValidacion.class,
                        ContactoNuevoValidacion.class, CoordinadorLlavePrimaria.class, CoordinadorNuevoValidacion.class,
                        LicenciaNuevaValidacion.class, LicenciaActualizaValidacion.class)
            .addAsResource("META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private ProfesorSesionBean profesorSesionBean;

    @Inject
    private EncriptadorContrasenia encriptadorContrasenia;

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
    public void buscaClaveCen(){
        List<ProfesorModelo> profesorModeloLista = profesorSesionBean.buscaClave("");
        Assert.assertNotNull(profesorModeloLista);
        Assert.assertFalse(profesorModeloLista.isEmpty());
        for(ProfesorModelo profesorModelo : profesorModeloLista){
            Assert.assertNotNull(profesorModelo);
            Assert.assertNotNull(profesorModelo.getId());
            Assert.assertNotNull(profesorModelo.getEscuelaBaseModelo());
        }
    }

    @Test
    public void inserta2(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Eliminando");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("Otro mas");
        profesorModelo.setContrasenia(encriptadorContrasenia.encrypt("123456"));
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBS0029K"));
        profesorSesionBean.inserta(profesorModelo);
        Assert.assertNotNull(profesorModelo);
    }

    @Test
    public void inserta(){
        ProfesorModelo profesorModelo = new ProfesorModelo();
        profesorModelo.setNombre("Jesus");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("El Juanito");
        try{
            profesorModelo.setContrasenia(encriptadorContrasenia.encrypt("123456"));
        }catch(Exception e){}
        profesorModelo.setEscuelaBaseModelo(new EscuelaBaseModelo("21DBS0029K"));
        profesorSesionBean.inserta(profesorModelo);
        Assert.assertNotNull(profesorModelo);
    }

    @Test
    public void actualiza(){
        ProfesorModelo profesorModelo = new ProfesorModelo(-42);
        profesorModelo.setNombre("Cochi");
        profesorModelo.setApellidoPaterno("Reyes");
        profesorModelo.setApellidoMaterno("Sanchez");
        profesorModelo.setApodo("Don Levantones");
        profesorModelo.setContrasenia("123456".getBytes());
        EscuelaBaseModelo escuelaBaseModelo = new EscuelaBaseModelo("21DBS0029K");
        escuelaBaseModelo.setNombre("AGUSTIN MELGAR");
        escuelaBaseModelo.setDomicilio("RAFAEL CAÑEDO BENITEX");
        escuelaBaseModelo.setNumeroExterior("12234");
        profesorModelo.setEscuelaBaseModelo(escuelaBaseModelo);

        int elementosModificados = profesorSesionBean.actualiza(profesorModelo);
        Assert.assertFalse(elementosModificados == 0);
    }

    @Test
    public void elimina(){
        Integer profesorEliminado = 0;
        profesorEliminado = profesorSesionBean.elimina(-41);
        Assert.assertNotNull(profesorEliminado);
        Assert.assertFalse(profesorEliminado == 0);
    }
}
