package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.entidad.TemaEntidad;
import tecolotl.alumno.entidad.TipoEstudianteEntidad;
import tecolotl.alumno.modelo.ActividadModelo;
import tecolotl.alumno.modelo.NivelLenguajeModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.nucleo.validacion.CatalogoLlavePrimariaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Permite la manipulación de las avtividades
 */
@Stateless
public class ActividadSesionBean {

    @PersistenceContext(unitName = "alumno")

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
     * Recupera la imagen de una actividad.
     * @param idActvidad Identificador de la actividad.
     * @return Imagen en bytes.
     */
    public byte[] imagen(@NotNull @Size(min = 11, max = 11) String idActvidad) {
        logger.fine(idActvidad);
        TypedQuery<byte[]> typedQuery = entityManager.createNamedQuery("ActividadEntidad.buscaImagen", byte[].class);
        typedQuery.setParameter("idAvtividad", idActvidad);
        return typedQuery.getSingleResult();
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

}
