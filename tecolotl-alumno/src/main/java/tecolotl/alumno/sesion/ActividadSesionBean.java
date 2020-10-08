package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.entidad.TemaEntidad;
import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.alumno.entidad.vista.ActividadBuquedaVista;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.alumno.modelo.TemaModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.validacion.CatalogoLlavePrimariaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Permite la manipulación de las avtividades
 */
@Stateless
public class ActividadSesionBean implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @Inject
    private Logger logger;

    /**
     * Busca todas las actividades.
     * @return Coleción de {@link ActividadModelo}
     */
    public List<ActividadModelo> busca() {
        TypedQuery<ActividadEntidad> typedQuery = entityManager.createNamedQuery("ActividadEntidad.busca", ActividadEntidad.class);
        return typedQuery.getResultList().stream().map(ActividadModelo::new).collect(Collectors.toList());
    }


    public ActividadModelo detalle(@NotNull @Size(max = 12) String idActvidad) {
        return new ActividadModelo(entityManager.find(ActividadEntidad.class, idActvidad));
    }

    /**
     * Busca todas las activdades donde no esten asigandas a los alumnos de un grupo
     * @param idGrupo Identificador del grupo
     * @return Colección de {@link ActividadModelo} con los datos
     */
    public List<ActividadModelo> buscaGrupo(@NotNull Integer idGrupo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM alumno.actividad WHERE actividad.id_video NOT IN ( SELECT tga.id_actividad FROM " +
                "profesor.grupo_alumno ga JOIN alumno.tarea t ON t.id_alumno = ga.id_alumno JOIN alumno.tarea_glosario_actividad tga ON t.id = tga.id_tarea " +
                "WHERE ga.id_grupo = 1 GROUP BY tga.id_actividad)");
        return null;
    }

    /**
     * Busca actividades con palabra reservada de lenguaje o pregunta detonadora
     * @param lenguaje Palabra con la que se va a buscar
     * @return Colección de {@link ActividadModelo}
     */
    public List<ActividadModelo> buscaLenguaje(@NotNull String lenguaje) {
        TypedQuery<ActividadBuquedaVista> typedQuery = entityManager.createNamedQuery("ActividadBuquedaVista.buscaPreguntaLenguaje", ActividadBuquedaVista.class);
        typedQuery.setParameter("palabra", "%".concat(lenguaje.toLowerCase()).concat("%"));
        return typedQuery.getResultList().stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca actividades que tengan una nivel de lenguaje relacionado
     * @param nivelLenguaje Nivel de lenguaje a buscaa, por ejemplo A1
     * @return Coleción de {@link ActividadModelo}
     */
    public List<ActividadModelo> busca(@NotNull @Size(min = 2, max = 2) String nivelLenguaje) {
        logger.fine(nivelLenguaje);
        TypedQuery<ActividadEntidad> typedQuery = entityManager.createNamedQuery("ActividadEntidad.buscaNivelLenguaje", ActividadEntidad.class);
        typedQuery.setParameter("nivelLenguaje", nivelLenguaje);
        List<ActividadEntidad> actividadEntidadLista = typedQuery.getResultList();
        logger.finer("Total de actividades:"+actividadEntidadLista.size());
        List<ActividadModelo> actividadModeloLista = new ArrayList<>();
        for (ActividadEntidad actividadEntidad : actividadEntidadLista) {
            logger.finest(actividadEntidad.toString());
            actividadModeloLista.add(new ActividadModelo(actividadEntidad));
        }
        return actividadModeloLista;
    }

    /**
     * Busca todas las actividades que no esten asignadas a un alumno, teniendo en cuenta que será de forma paginada.
     * @param idAlumno Identificador del alumno.
     * @return Colección
     */
    public List<ActividadModelo> busca(@NotNull UUID idAlumno) {
        logger.fine(idAlumno.toString());
        TypedQuery<ActividadEntidad> typedQuery = entityManager.createNamedQuery("ActividadEntidad.buscaBibliotecaLibre", ActividadEntidad.class);
        typedQuery.setParameter("idAlumno", idAlumno);
        List<ActividadEntidad> actividadEntidadLista = typedQuery.getResultList();
        List<ActividadModelo> actividadModeloLista = new ArrayList<>(actividadEntidadLista.size());
        for (ActividadEntidad actividadEntidad : actividadEntidadLista) {
            ActividadModelo actividadModelo = new ActividadModelo();
            actividadModelo.setIdVideo(actividadEntidad.getId());
            actividadModelo.setNivelLenguajeModeloLista(new ArrayList<>(actividadEntidad.getNivelLenguajeEntidad().size()));
            for (NivelLenguajeEntidad nivelLenguajeEntidad : actividadEntidad.getNivelLenguajeEntidad()) {
                actividadModelo.getNivelLenguajeModeloLista().add(new NivelLenguajeModelo(nivelLenguajeEntidad));
            }
            actividadModelo.setPreguntaDetonadora(actividadEntidad.getPreguntaDetonadora());
            actividadModelo.setTemaModelo(new TemaModelo(actividadEntidad.getTemaEntidad()));
            actividadModeloLista.add(actividadModelo);
        }
        return actividadModeloLista;

    }

    /**
     * Recupera la transcripcion de una actividad.
     * @param idActividad Identidicador de la actividad a recuperar.
     * @return La transcripción.
     */
    public String transcripcion(@NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(idActividad);
        return entityManager.createNamedQuery("ActividadEntidad.transcripcion", String.class)
                .setParameter("idActividad", idActividad).getSingleResult();
    }

    /**
     * Busca la pegunta detonadora de una actividad.
     * @param idActividad Identificador de la actividad.
     * @return Cadena de caracteres con la pregunta detonadora.
     */
    public String preguntaDetonadora(@NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(idActividad);
        return entityManager.createQuery("SELECT a.preguntaDetonadora FROM ActividadEntidad a WHERE a.id = :idActividad", String.class)
                .setParameter("idActividad", idActividad).getSingleResult();
    }

    /**
     * Agrega una nueva actividad
     * @param actividadModelo Datos de la nueva actividad
     */
    public void agrega(@NotNull ActividadModelo actividadModelo) {
        logger.fine(actividadModelo.toString());
        validadorSessionBean.validacion(actividadModelo, CatalogoLlavePrimariaValidacion.class);
        ActividadEntidad actividadEntidad = new ActividadEntidad();
        actividadEntidad.setId(actividadModelo.getIdVideo());
        actividadEntidad.setPuntaje(actividadModelo.getPuntaje());
        actividadEntidad.setTiempo(actividadModelo.getTiempo());
        actividadEntidad.setPreguntaDetonadora(actividadModelo.getPreguntaDetonadora());
        actividadEntidad.setLenguaje(actividadModelo.getLenguaje());
        actividadEntidad.setTrasncripcion(actividadModelo.getTranscripcion());
        actividadEntidad.setTipoEstudianteEntidad(new TipoEstudianteEntidad(actividadModelo.getTipoEstudianteModelo().getClave()));
        actividadEntidad.setNivelLenguajeEntidad(new ArrayList<>());
        for (NivelLenguajeModelo nivelLenguajeModelo : actividadModelo.getNivelLenguajeModeloLista()) {
            actividadEntidad.getNivelLenguajeEntidad().add(new NivelLenguajeEntidad(nivelLenguajeModelo.getClave()));
        }
        actividadEntidad.setTemaEntidad(new TemaEntidad(actividadModelo.getTemaModelo().getClave()));
        entityManager.persist(actividadEntidad);
        actividadModelo.setIdVideo(actividadEntidad.getId());
    }

    /**
     * Borra una actividad
     * @param idActividad Identificador de la actividad a ser borrado
     */
    public void elimina(@NotNull @Size(min = 11, max = 11) String idActividad) {
        ActividadEntidad actividadEntidad = entityManager.find(ActividadEntidad.class, idActividad);
        entityManager.remove(actividadEntidad);
    }

    /**
     * Busca todas las actividades con su estatus
     * @return Colección de {@link ActividadModelo}
     */
    public List<ActividadModelo> buscaEstatus() {
        TypedQuery<ActividadEntidad> typedQuery = entityManager.createNamedQuery("ActividadEntidad.buscaEstatus", ActividadEntidad.class);
        return typedQuery.getResultList().stream().map(ActividadModelo::new).collect(Collectors.toList());
    }

    /**
     * Cambia el estatus de activo/inactivo de un actividad
     * @param idVideo Identificador del video
     * @param estatus Estatus al que se desa cambiar
     */
    public int estatus(@NotNull String idVideo, @NotNull Boolean estatus) {
        Query query = entityManager.createNamedQuery("ActividadEntidad.actualizaEstatus");
        query.setParameter("estatus", estatus);
        query.setParameter("idVideo", idVideo);
        return query.executeUpdate();
    }

}
