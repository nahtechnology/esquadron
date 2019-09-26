package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.glosario.*;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * El glosario es un conjunto de herramientas asignadas a una actividad.
 * El glosario está compuesto por una palabra, imagen y un significado.
 * @author Antonio Francisco Alonso Valerdi
 */
@Stateless
public class GlosarioSesionBean implements Serializable {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca un glosario por su llave primaria
     * @param palabra
     * @param clave
     * @return
     */
    public GlosarioModelo busca(@NotNull String palabra, @NotNull Short clave) {
        return new GlosarioModelo(entityManager.find(GlosarioEntidad.class, llavePrimaria(palabra, clave)));
    }

    /**
     * Busca todos los glosarios relacionados con una actividad.
     * @param idActividad Identidicador de la actividad.
     * @return Colección de {@link GlosarioModelo}
     */
    public List<GlosarioModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(idActividad);
        TypedQuery<GlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("GlosarioActividadEntidad.buscaActividad", GlosarioActividadEntidad.class);
        typedQuery.setParameter("idActivdad", idActividad);
        List<GlosarioActividadEntidad> glosarioActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Glosario encontrado:" + glosarioActividadEntidadLista.size());
        return glosarioActividadEntidadLista.stream().map(
                glosarioActividadEntidad -> new GlosarioModelo(glosarioActividadEntidad.getGlosarioActividadEntidadPK().getGlosarioEntidad())
        ).collect(Collectors.toList());
    }

    /**
     * Busca glosarios por una palabra clave.
     * @param palabra Palabra a busca dentro del glosario
     * @return Colección de {@link GlosarioModelo}
     */
    public List<GlosarioModelo> busca(@NotNull  @Size(min = 1, max = 20) String palabra, @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(palabra);
        TypedQuery<GlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("GlosarioActividadEntidad.buscaPalabraNoActividad", GlosarioActividadEntidad.class);
        typedQuery.setParameter("palabra", "%".concat(palabra).concat("%")).setParameter("idActivdad", idActividad);
        List<GlosarioActividadEntidad> glosarioEntidadLista = typedQuery.getResultList();
        logger.finer("Glosario encontrados".concat(String.valueOf(glosarioEntidadLista.size())));
        return glosarioEntidadLista.stream().map(
                glosarioActividadEntidad -> new GlosarioModelo(glosarioActividadEntidad.getGlosarioActividadEntidadPK().getGlosarioEntidad())).collect(Collectors.toList());
    }

    /**
     * Busca la imagen de una glosario.
     * @param palabra Palabra a la pertence la imagen
     * @param idClave Identificador de la clase
     * @return Arreglo de bytes
     */
    public byte[] imagen(@NotNull @Size(min = 2, max = 20) String palabra, @NotNull Short idClave) {
        logger.fine(palabra);
        logger.fine(idClave.toString());
        TypedQuery<byte[]> typedQuery = entityManager.createNamedQuery("GlosarioEntidad.buscaImagen", byte[].class);
        typedQuery.setParameter("palabra", palabra).setParameter("clave", idClave);
        return typedQuery.getSingleResult();
    }

    /**
     * Busca todos los glosarios asignados a una tarea la cual corresponde a un alumno.
     * @param idTarea Identificador de la tarea
     * @return Colección de {@link GlosarioModelo}
     */
    public List<GlosarioModelo> busca(@NotNull Integer idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<TareaGlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaGlosarioActividadEntidad.buscaTarea", TareaGlosarioActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaGlosarioActividadEntidad> tareaGlosarioActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Glosario encontrados:"+tareaGlosarioActividadEntidadLista.size());
        return tareaGlosarioActividadEntidadLista.stream().map(tareaGlosarioActividadEntidad ->
            new GlosarioModelo(tareaGlosarioActividadEntidad.getTareaGlosarioActividadEntidadPK().getGlosarioActividadEntidad().getGlosarioActividadEntidadPK().getGlosarioEntidad())
        ).collect(Collectors.toList());
    }

    /**
     * Busca los glosario sin filtro alguno.
     * @param inicio Posición de la primera fila a buscar.
     * @param maximo El  máximo de elementos a buscar.
     * @return Colección de {@link GlosarioModelo}
     */
    public List<GlosarioModelo> busca(int inicio, int maximo) {
        logger.fine("Inicio:".concat(String.valueOf(inicio))); logger.fine("Maximo:".concat(String.valueOf(maximo)));
        TypedQuery<GlosarioEntidad> typedQuery = entityManager.createNamedQuery("GlosarioEntidad.busca", GlosarioEntidad.class);
        typedQuery.setFirstResult(inicio).setMaxResults(maximo);
        return typedQuery.getResultList().stream().map(GlosarioModelo::new).collect(Collectors.toList());
    }

    /**
     * Agreaga un nuevo glosario con su relacion en cascada de la actividad que pertenece.
     * @param glosarioModelo Datos del glosario a insertar.
     * @param idActividad Activdad a la que pertenece.
     */
    public void agregar(@NotNull GlosarioModelo glosarioModelo, @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(glosarioModelo.toString());
        GlosarioEntidad glosarioEntidad = new GlosarioEntidad(llavePrimaria(glosarioModelo));
        glosarioEntidad.setImagen(glosarioModelo.getImagen());
        glosarioEntidad.setSignificado(glosarioModelo.getSignificado());
        GlosarioActividadEntidad glosarioActividadEntidad = new GlosarioActividadEntidad(llavePrimaria(glosarioEntidad, idActividad));
        glosarioEntidad.setGlosarioActividadEntidadLista(Arrays.asList(glosarioActividadEntidad));
        if (glosarioEntidad.getSignificado() == null || glosarioEntidad.getSignificado().isEmpty()) {
            entityManager.persist(glosarioEntidad);
        } else {
            entityManager.persist(glosarioActividadEntidad);
        }
    }

    /**
     * Busca el id de una actividad.
     * @param idTarea Identificador de la tarea.
     * @return {@link String} con el identificador de la actividad
     */
    public String buscaActividad(@NotNull Integer idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<String> typedQuery = entityManager.createNamedQuery("TareaGlosarioActividadEntidad.buscaActividadPorTarea", String.class);
        typedQuery.setParameter("idTarea", idTarea);
        return typedQuery.getSingleResult();
    }

    protected GlosarioEntidadPK llavePrimaria(GlosarioModelo glosarioModelo) {
        GlosarioEntidadPK glosarioEntidadPK = new GlosarioEntidadPK();
        glosarioEntidadPK.setPalabra(glosarioModelo.getPalabra());
        glosarioEntidadPK.setClaseGlosarioEntidad(new ClaseGlosarioEntidad(glosarioModelo.getClaseGlosarioModelo().getClave()));
        return glosarioEntidadPK;
    }

    protected GlosarioActividadEntidadPK llavePrimaria(GlosarioEntidad glosarioEntidad, String idActividad) {
        GlosarioActividadEntidadPK glosarioActividadEntidadPK = new GlosarioActividadEntidadPK();
        glosarioActividadEntidadPK.setActividadEntidad(new ActividadEntidad(idActividad));
        glosarioActividadEntidadPK.setGlosarioEntidad(glosarioEntidad);
        return glosarioActividadEntidadPK;
    }

    protected GlosarioEntidadPK llavePrimaria(String palabra, Short clave) {
        return new GlosarioEntidadPK(palabra, new ClaseGlosarioEntidad(clave));
    }
}
