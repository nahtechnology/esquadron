package tecolotl.profesor.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.modelo.direccion.ColoniaModelo;
import tecolotl.administracion.modelo.escuela.*;
import tecolotl.administracion.persistencia.entidad.*;
import tecolotl.administracion.sesion.ContactoSesionBean;
import tecolotl.administracion.validacion.direccion.ColoniaNuevaValidacion;
import tecolotl.administracion.validacion.escuela.ContactoLlavePrimariaValidacion;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.mapamental.MapaMentalActividadEntidad;
import tecolotl.alumno.entidad.glosario.ClaseGlosarioEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.alumno.sesion.ActividadSesionBean;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.CorreoEnum;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.sesion.CatalogoSesionBean;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;
import tecolotl.profesor.entidad.*;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoAlumnoModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.validacion.GrupoProfesorValidacion;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(Arquillian.class)
public class GrupoSesionBeanTest {

    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                //nucleo
                .addPackage(CorreoEnum.class.getPackage()).addPackage(CatalogoModelo.class.getPackage()).addPackage(CatalagoEntidad.class.getPackage())
                .addPackage(CatalogoSesionBean.class.getPackage()).addPackage(CatalogoNuevoValidacion.class.getPackage())
                //administracion
                .addPackage(CoordinadorModelo.class.getPackage()).addPackage(ColoniaModelo.class.getPackage()).addPackage(ContactoModelo.class.getPackage())
                .addPackage(ColoniaEntidad.class.getPackage()).addPackage(ContactoSesionBean.class.getPackage()).addClass(ColoniaNuevaValidacion.class)
                .addPackage(ContactoLlavePrimariaValidacion.class.getPackage())
                //alumno
                .addPackage(MapaMentalActividadEntidad.class.getPackage()).addPackage(ClaseGlosarioEntidad.class.getPackage())
                .addPackage(ActividadEntidad.class.getPackage()).addPackage(MapaMentalModelo.class.getPackage()).addPackage(GlosarioModelo.class.getPackage())
                .addPackage(ActividadModelo.class.getPackage()).addPackage(ActividadSesionBean.class.getPackage())
                .addPackage(EscribirLlavePrimariaValidacion.class.getPackage()).addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                //profesor
                .addPackage(CicloEscolarEntidad.class.getPackage()).addPackage(GrupoAlumnoModelo.class.getPackage())
                .addPackage(GrupoAlumnoSesionBean.class.getPackage()).addPackage(GrupoProfesorValidacion.class.getPackage())
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
        grupoModeloLista.forEach(grupoModelo -> {
            Assert.assertNotNull(grupoModelo);
            Assert.assertNotNull(grupoModelo.getGrado());
            Assert.assertNotNull(grupoModelo.getGrupo());
        });
    }

    @Test
    public void buscaProfesor(){
        List<GrupoModelo> grupoModeloLista = grupoSesionBean.busca(1);
        Assert.assertNotNull(grupoModeloLista);
        Assert.assertFalse(grupoModeloLista.isEmpty());
        grupoModeloLista.forEach(grupoModelo -> {
            Assert.assertNotNull(grupoModelo);
            Assert.assertNotNull(grupoModelo.getGrado());
            Assert.assertNotNull(grupoModelo.getGrupo());
        });
    }

    @Test
    public void inserta() throws ParseException {
        //TODO revisar las pruebas unitrias para las fechas
        GrupoModelo grupoModelo = new GrupoModelo();
        grupoModelo.setGrado((short)1);
        grupoModelo.setGrupo('A');
        CicloEscolarModelo cicloEscolarModelo = new CicloEscolarModelo();
        cicloEscolarModelo.setInicio(new SimpleDateFormat("dd/MM/yyyy").parse("22/08/2019"));
        cicloEscolarModelo.setFin(new SimpleDateFormat("dd/MM/yyyy").parse("22/08/2020"));
        cicloEscolarModelo.setIdEscuela("21DBA0051N");
        grupoModelo.setCicloEscolarModelo(cicloEscolarModelo);
        grupoModelo.setIdProfesor(1);
        grupoSesionBean.inserta(grupoModelo);
        Assert.assertNotNull(grupoModelo.getId());
        Assert.assertNotEquals(grupoModelo.getId().intValue(), 0);
    }

    @Test
    public void actualiza(){

    }

    @Test
    public void elimina(){
        int grupoEl = grupoSesionBean.elimina(-44);
        Assert.assertFalse(grupoEl == 0);
    }
}
