package tecolotl.alumno.sesion;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import tecolotl.alumno.entidad.*;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.GramaticaEntidadPK;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidad;
import tecolotl.alumno.entidad.gramatica.TareaGramaticaEntidadPK;
import tecolotl.alumno.entidad.mapamental.*;
import tecolotl.alumno.entidad.relacionar.TareaRelacionarActividadEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.modelo.TemaModelo;
import tecolotl.alumno.modelo.TipoEstudianteModelo;
import tecolotl.alumno.modelo.glosario.ClaseGlosarioModelo;
import tecolotl.alumno.modelo.gramatica.GramaticaModelo;
import tecolotl.alumno.validacion.ActividadNuevaValidacion;
import tecolotl.alumno.validacion.glosario.GlosarioNuevoValidacion;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.CatalogoModelo;
import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RunWith(Arquillian.class)
public class GramaticaSesionBeanTest {

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GramaticaEntidad.class, GramaticaModelo.class, GramaticaEntidadPK.class, ActividadModelo.class, ActividadEntidad.class,
                        TipoEstudianteEntidad.class, TipoEstudianteModelo.class, NivelLenguajeEntidad.class, NivelLenguajeModelo.class,
                        ClaseGlosarioEntidad.class, ClaseGlosarioModelo.class, CatalagoEntidad.class, CatalogoModelo.class, ValidadorSessionBean.class,
                        TemaEntidad.class, TemaModelo.class, LoggerProducer.class, GramaticaSesionBean.class, TareaGramaticaEntidad.class, TareaGramaticaEntidadPK.class,
                        TareaEntidad.class, TareaGlosarioActividadEntidad.class, TareaGlosarioActividadEntidadPK.class, TareaMapaMentalActividadEntidad.class,
                        TareaMapaMentalActividadEntidadPK.class, MapaMentalActividadEntidad.class, GlosarioActividadEntidad.class, GlosarioActividadEntidadPK.class,
                        MapaMentalEntidad.class, GlosarioEntidad.class, GlosarioEntidadPK.class)
                .addPackage(GlosarioNuevoValidacion.class.getPackage())
                .addPackage(ActividadNuevaValidacion.class.getPackage())
                .addPackage(CatalogoNuevoValidacion.class.getPackage()).addPackage(MapaMentalEntidadPK.class.getPackage())
                .addPackage(AlumnoEntidad.class.getPackage()).addPackage(PersonaEntidad.class.getPackage()).addPackage(TareaRelacionarActividadEntidad.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private GramaticaSesionBean gramaticaSesionBean;

    @Test
    public void busca(){
        List<GramaticaModelo> gramaticaModeloLista = gramaticaSesionBean.busca();
        Assert.assertNotNull(gramaticaModeloLista);
        Assert.assertFalse(gramaticaModeloLista.isEmpty());
        for (GramaticaModelo gramaticaModelo : gramaticaModeloLista){
            Assert.assertNotNull(gramaticaModelo);
            Assert.assertNotNull(gramaticaModelo.getActividadModelo());
            Assert.assertNotNull(gramaticaModelo.getCodigo());
            Assert.assertNotNull(gramaticaModelo.getPalabra());
        }
    }

    @Test
    public void buscaPorActividad(){
        List<GramaticaModelo> gramaticaModeloLista = gramaticaSesionBean.busca("9B6DMShBV6k");
        Assert.assertNotNull(gramaticaModeloLista);
        Assert.assertFalse(gramaticaModeloLista.isEmpty());
        for (GramaticaModelo gramaticaModelo : gramaticaModeloLista){
            Assert.assertNotNull(gramaticaModelo);
            Assert.assertNotNull(gramaticaModelo.getActividadModelo());
            Assert.assertNotNull(gramaticaModelo.getCodigo());
            Assert.assertNotNull(gramaticaModelo.getPalabra());
        }
    }

    @Test
    public void buscarTareaGramatica(){
        List<GramaticaModelo> gramaticaModeloLista = gramaticaSesionBean.busca(UUID.fromString("72406be2-3710-4370-a892-98dfec006c9d"));
        Assert.assertNotNull(gramaticaModeloLista);
        Assert.assertFalse(gramaticaModeloLista.isEmpty());
        for (GramaticaModelo gramaticaModelo : gramaticaModeloLista){
            Assert.assertNotNull(gramaticaModelo);
            Assert.assertNotNull(gramaticaModelo.getActividadModelo());
            Assert.assertNotNull(gramaticaModelo.getCodigo());
            Assert.assertNotNull(gramaticaModelo.getPalabra());
        }
    }
}
