package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.coordinador.CoordinadorModelo;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidad;
import tecolotl.administracion.persistencia.entidad.CoordinadorEntidadPK;
import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.administracion.validacion.escuela.CoordinadorLlavePrimaria;
import tecolotl.administracion.validacion.escuela.CoordinadorNuevoValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.modelo.PersonaActivaModelo;
import tecolotl.nucleo.modelo.PersonaMotivoBloqueoModelo;
import tecolotl.nucleo.persistencia.entidad.PersonaMotivoBloqueoEntidad;
import tecolotl.nucleo.validacion.PersonaNuevaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Coordinador de una escuela, es el encargado de administrar los alumnos y profesores de una escuela.
 * @since 0.1
 * @author Antonio Francisco Alonso Valerdi
 */
@Stateless
public class CoordinadorSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

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

    /**
     * Busca los detalles de un coordinador
     * @param claveCentroTrabajo clave centro de trabajo al que pretenece el coordinador
     * @param contador Contador del coordinador
     * @return Datos del coordinador
     */
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
     * Busca si existe algun coordinado con un apodo
     * @param apodo Apodo del coordinador
     * @return True en caso de existir, false en cualquier otro caso
     */
    public boolean exite(@NotNull String apodo, @NotNull String claveCentroTrabajo) {
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("CoordinadorEntidad.buscaApodo", Long.class);
        typedQuery.setParameter("apodo", apodo);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        return typedQuery.getSingleResult() != 0;
    }

    /**
     * Busca un coordinador por su apodo
     * @param galaxia galaxia a la escuela que pertenece
     * @param apodo Apodo del coordinador
     * @return Objeto con los datos del coordinador
     */
    public CoordinadorModelo busca(@NotNull Integer galaxia,
                                   @NotNull String apodo) {
        TypedQuery<CoordinadorEntidad> typedQuery = entityManager.createNamedQuery("CoordinadorEntidad.detalleApodo", CoordinadorEntidad.class);
        typedQuery.setParameter("galaxia", galaxia).setParameter("apodo", apodo);
        return new CoordinadorModelo(typedQuery.getSingleResult());
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
        coordinadorEntidad.setSexo(coordinadorModelo.getSexo());
        entityManager.persist(coordinadorEntidad);
        coordinadorModelo.setPersonaMotivoBloqueoModelo(new PersonaMotivoBloqueoModelo(coordinadorEntidad.getPersonaMotivoBloqueoEntidad()));
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
        coordinadorEntidad.setSexo(coordinadorModelo.getSexo());
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

    public PersonaActivaModelo activo(Short contador, String claveCentroTrabajo) {
        PersonaActivaModelo personaActivaModelo = new PersonaActivaModelo();
        Query query = entityManager.createNativeQuery("SELECT e.bloqueo_profesor AS bloqueado, date_part('day', max(l.inicio) + interval '1 year' - current_date) AS dias " +
                "FROM administracion.escuela e JOIN administracion.licencia l ON e.clave_centro_trabajo = l.id_escuela JOIN administracion.coordinador c ON e.clave_centro_trabajo = c.id_escuela " +
                "WHERE c.contador = ? AND c.id_escuela = ? GROUP BY e.clave_centro_trabajo");
        query.setParameter(1, contador);
        query.setParameter(2, claveCentroTrabajo);
        Object[] objects = (Object[])query.getSingleResult();
        personaActivaModelo.setBloqueado((Boolean)objects[0]);
        personaActivaModelo.setDias(((Double)objects[1]).intValue());
        return personaActivaModelo;
    }

}
