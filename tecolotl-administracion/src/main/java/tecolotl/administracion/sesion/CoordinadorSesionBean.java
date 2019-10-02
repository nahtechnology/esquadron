package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidad;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidadPK;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.CoordinadorLlavePrimaria;
import tecolotl.administracion.validacion.escuela.CoordinadorNuevoValidacion;
import tecolotl.nucleo.herramienta.CorreoSessionBean;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.persistencia.entidad.PersonaMotivoBloqueoEntidad;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Coordinador de una escuela, es el encargado de administrar los alumnos y profesores de una escuela.
 * @since 0.1
 * @author Antonio Francisco Alonso Valerdi
 */
@Stateless
public class CoordinadorSesionBean {

    @PersistenceContext(unitName = "administracion")
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;
    //classloader
    @Inject
    private CorreoSessionBean correoSessionBean;


    /**
     * Busca todos los coordinadores de una escuela, sin el motivo de bloqueo de dicho coordinador.
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela.
     * @return Colección de {@link CoordinadorModelo}, lista vacia en caso de no tener alumnos.
     */
    public List<CoordinadorModelo> busca(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        logger.fine("Bucando coordinadores con CCT:".concat(claveCentroTrabajo));
        return entityManager.createNamedQuery("CoordinadorEntidad.buscaEscuela", CoordinadorEntidad.class)
                .setParameter("claveCentroTrabajo",claveCentroTrabajo).getResultList().stream()
                .map(CoordinadorModelo::new).collect(Collectors.toList());
    }

    public CoordinadorModelo busca(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo,
                                   @NotNull Short contador) {
        logger.fine("Buscando por id:".concat(contador.toString()).concat(" clave centro trabajo:").concat(claveCentroTrabajo));
        CoordinadorEntidad coordinadorEntidad = entityManager.createNamedQuery(
                "CoordinadorEntidad.buscaDetalle", CoordinadorEntidad.class)
                .setParameter("coordinadorEntidadPK", new CoordinadorEntidadPK(claveCentroTrabajo, contador))
                .getSingleResult();
        logger.finer("coordinador encontrado:".concat(coordinadorEntidad.toString()));
        CoordinadorModelo coordinadorModelo = new CoordinadorModelo(coordinadorEntidad);
        coordinadorModelo.getPersonaMotivoBloqueoModelo().setValor(
                coordinadorEntidad.getPersonaMotivoBloqueoEntidad().getValor());
        return coordinadorModelo;
    }

    /**
     * Cambia es estatus de una coordinador.
     * @param coordinadorModelo Datos del coordinador a cambiar el estatus.
     * @param motivoBloqueo Estatus del coordinador.
     */
    public void estatus(@NotNull CoordinadorModelo coordinadorModelo,
                        @NotNull Short motivoBloqueo) {
        logger.fine(coordinadorModelo.toString());
        logger.fine(motivoBloqueo.toString());
        validadorSessionBean.validacion(coordinadorModelo, CoordinadorLlavePrimaria.class);
        CoordinadorEntidad coordinadorEntidad = entityManager.find(CoordinadorEntidad.class, generaLlavePrimaria(coordinadorModelo));
        coordinadorEntidad.setPersonaMotivoBloqueoEntidad(new PersonaMotivoBloqueoEntidad(motivoBloqueo));
    }

    /**
     * Agrega un coordinador a una escuela, por defecto todos los nuevos coordinadores se encuentran actuvos.
     * @param coordinadorModelo Datos del coordinador a ser insertados en la base de datos.
     */
    public void agreaga(@NotNull CoordinadorModelo coordinadorModelo) {
        logger.finer("coordinador a ser insertado".concat(coordinadorModelo.toString()));
        validadorSessionBean.validacion(coordinadorModelo, CoordinadorNuevoValidacion.class, PersonaNuevaValidacion.class);
        CoordinadorEntidad coordinadorEntidad = new CoordinadorEntidad(generaLlavePrimaria(coordinadorModelo));
        coordinadorEntidad.setCorreoEletronico(coordinadorModelo.getCorreoEletronico());
        coordinadorEntidad.setApodo(coordinadorModelo.getApodo());
        coordinadorEntidad.setNombre(coordinadorModelo.getNombre());
        coordinadorEntidad.setApellidoPaterno(coordinadorModelo.getApellidoPaterno());
        coordinadorEntidad.setApellidoMaterno(coordinadorModelo.getApellidoMaterno());
        coordinadorEntidad.setContrasenia(coordinadorModelo.getContrasenia());
        //TODO insertar función de envio de correo envío de correo.
        entityManager.persist(coordinadorEntidad);
    }

    /**
     * Actualiza los datos de un coordinador
     * @param coordinadorModelo
     */
    public void actualiza(@NotNull @Valid CoordinadorModelo coordinadorModelo) {
        logger.fine("Coordinador a ser actualizado".concat(coordinadorModelo.toString()));
        CoordinadorEntidad coordinadorEntidad = entityManager.find(CoordinadorEntidad.class, generaLlavePrimaria(coordinadorModelo));
        coordinadorEntidad.setCorreoEletronico(coordinadorModelo.getCorreoEletronico());
        coordinadorEntidad.setApodo(coordinadorModelo.getApodo());
        coordinadorEntidad.setNombre(coordinadorModelo.getNombre());
        coordinadorEntidad.setApellidoPaterno(coordinadorModelo.getApellidoPaterno());
        coordinadorEntidad.setApellidoMaterno(coordinadorModelo.getApellidoMaterno());
        coordinadorEntidad.setContrasenia(coordinadorModelo.getContrasenia());
    }

    /**
     * Elimina un coordinador
     * @param coordinadorModelo
     */
    public void elimina(@NotNull CoordinadorModelo coordinadorModelo) {
        logger.fine(coordinadorModelo.toString());
        entityManager.remove(entityManager.find(CoordinadorEntidad.class, generaLlavePrimaria(coordinadorModelo)));
    }

    /**
     * Cuenta el total de coordinadores por escuela.
     * @param claveCentroTrabajo Identidicador de la escuela.
     * @return total de coordiandores encontrados.
     */
    public Long cuenta(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        return entityManager.createNamedQuery("CoordinadorEntidad.cuentaPorEscuela", Long.class)
                .setParameter("claveCentroTrabajo", claveCentroTrabajo).getSingleResult();
    }

    /**
     * Genera la llave primaria de un coordinador.
     * @param coordinadorModelo Datos donde vienen integrado la llave primaria.
     * @return CoordinadorEntidadPK con los datos de llave primaria.
     */
    private CoordinadorEntidadPK generaLlavePrimaria(CoordinadorModelo coordinadorModelo) {
        CoordinadorEntidadPK coordinadorEntidadPK = new CoordinadorEntidadPK();
        coordinadorEntidadPK.setEscuelaEntidad(new EscuelaEntidad(coordinadorModelo.getClaveCentroTrabajo()));
        coordinadorEntidadPK.setContador(coordinadorModelo.getContador());
        logger.fine("Llave primaria del coordinador:".concat(coordinadorEntidadPK.toString()));
        return coordinadorEntidadPK;
    }

    /**
     * Metodo que envia el correo y lee un archivo con BufferedReader y FileReader
     * lo cual no lo hace apto más que para ver eñ contenido del archivo y así procesarlo.
     */
    public void enviaCorreo() {
    }
}
