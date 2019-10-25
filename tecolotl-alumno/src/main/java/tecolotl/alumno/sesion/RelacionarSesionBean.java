package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.relacionar.*;
import tecolotl.alumno.modelo.relacionar.RelacionarModelo;
import tecolotl.alumno.modelo.relacionar.RelacionarOriginalModelo;
import tecolotl.alumno.validacion.relacionar.RelacionarLlavePrimariaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.validation.Path;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Tarea relacinar. Relacionar una imagen con una palabra.
 */
@Stateless
public class RelacionarSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    /**
     * Bsuca todas las palabra de busqueda para relacionar por una actividad.
     * @param idActivdad Identificador de la actvidad.
     * @return Coleción de {@link RelacionarModelo}
     */
    public List<RelacionarModelo> busca(@NotNull @Size(min = 11, max = 11) String idActivdad) {
        logger.fine(idActivdad);
        TypedQuery<RelacionarActividadEntidad> typedQuery =
                entityManager.createNamedQuery("RelacionarActividadEntidad.buscaActividad", RelacionarActividadEntidad.class);
        typedQuery.setParameter("idActividad", idActivdad);
        List<RelacionarActividadEntidad> relacionarActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Elementos encontraodos".concat(String.valueOf(relacionarActividadEntidadLista.size())));
        List<RelacionarModelo> relacionarModeloLista = new ArrayList<>();
        return relacionarActividadEntidadLista.stream().map(RelacionarModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las tareas de realacionar.
     * @param idTarea Identificador de la tarea.
     * @return Coleción de {@link RelacionarModelo}
     */
    public List<RelacionarModelo> busca(@NotNull Integer idTarea) {
        logger.fine(idTarea.toString());
        Query query = entityManager.createNativeQuery(
                "SELECT r.codigo,r.palabra,tar.respuesta, tar.hora_respuesta FROM alumno.tarea_actividad_relacionar tar " +
                        "JOIN alumno.actividad_relacionar ar ON tar.id_relacionar = ar.id_relacionar and tar.id_actividad = ar.id_actividad " +
                        "JOIN alumno.relacionar r ON ar.id_relacionar = r.codigo WHERE tar.id_tarea = ?");
        query.setParameter(1, idTarea);
        List<Object[]> respuesta = query.getResultList();
        List<RelacionarModelo> relacionarModeloLista = new ArrayList<>();
        for (Object[] objects : respuesta) {
            RelacionarModelo relacionarModelo = new RelacionarModelo();
            relacionarModelo.setCodigo((String)objects[0]);
            relacionarModelo.setPalabra((String)objects[1]);
            relacionarModelo.setRespuesta((String)objects[2]);
            relacionarModelo.setHoraRespuesta((Date)objects[3]);
            relacionarModeloLista.add(relacionarModelo);
        }
        return relacionarModeloLista;
    }


    /**
     * Busca todas las relaciones sin filtro.
     * @param inicio posicion de la primera fila a buscar.
     * @param maximo máximo de elementos a buscar.
     * @return una coleccipn de {@link RelacionarOriginalModelo}
     */
    public List<RelacionarOriginalModelo> busca(int inicio, int maximo){
        logger.fine("Inicio: ".concat(String.valueOf(inicio)));
        logger.fine("Maximo: ".concat(String.valueOf(maximo)));
        //TODO

        return null;
    }

    /**
     * Agrega una respuesta a la palabra relacionar.
     * @param relacionarModelo Datos de la palabra relacionar.
     * @param respuesta Respuesta del alumno.
     * @return Número de elementos modifcados.
     */
    public void respuesta(@NotNull RelacionarModelo relacionarModelo,
                          @NotNull @Size(min = 32, max = 32) String respuesta) {
        logger.fine(relacionarModelo.toString());
        logger.fine(respuesta);
        validadorSessionBean.validacion(relacionarModelo, RelacionarLlavePrimariaValidacion.class);
        TareaRelacionarActividadEntidad tareaRelacionarActividadEntidad =
                entityManager.find(TareaRelacionarActividadEntidad.class, llavePrimaria(relacionarModelo));
        tareaRelacionarActividadEntidad.setRespuesta(respuesta);
    }

    /**
     * Genera una llave primaria de tipo relacion tarea.
     * @param relacionarModelo Datos para generar la llave.
     * @return Llave primaria.
     */
    protected TareaRelacionarActividadEntidadPK llavePrimaria(RelacionarModelo relacionarModelo) {
        TareaRelacionarActividadEntidadPK tareaRelacionarActividadEntidadPK = new TareaRelacionarActividadEntidadPK();
        tareaRelacionarActividadEntidadPK.setTareaEntidad(new TareaEntidad(relacionarModelo.getIdTarea()));
        return tareaRelacionarActividadEntidadPK;
    }

}
