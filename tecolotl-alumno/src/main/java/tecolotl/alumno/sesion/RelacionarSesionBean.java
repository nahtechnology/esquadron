package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.relacionar.*;
import tecolotl.alumno.modelo.glosario.ClaseGlosarioModelo;
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
import java.util.UUID;
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
        List<RelacionarActividadEntidad> relacionarActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Elementos encontraodos".concat(String.valueOf(relacionarActividadEntidadLista.size())));
        return relacionarActividadEntidadLista.stream().map(RelacionarModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca las tareas para la biblioteca libre
     * @param idActivdad
     * @return
     */
    public List<RelacionarModelo> bibliotecaLibre(@NotNull @Size(min = 11, max = 11) String idActivdad) {
        logger.fine(idActivdad);
        TypedQuery<RelacionarActividadEntidad> typedQuery = entityManager.createNamedQuery("RelacionarActividadEntidad.buscaBibliotecaLibre", RelacionarActividadEntidad.class);
        typedQuery.setParameter("idActividad", idActivdad);
        return typedQuery.getResultList().stream().map(RelacionarModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todas las tareas de realacionar.
     * @param idTarea Identificador de la tarea.
     * @return Coleción de {@link RelacionarModelo}
     */
    public List<RelacionarModelo> busca(@NotNull UUID idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<TareaRelacionarActividadEntidad> typedQuery =
                entityManager.createNamedQuery("TareaRelacionarActividadEntidad.buscaTarea", TareaRelacionarActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<RelacionarModelo> relacionarModeloLista = new ArrayList<>();
        for (TareaRelacionarActividadEntidad tareaRelacionarActividadEntidad : typedQuery.getResultList()) {
            RelacionarModelo relacionarModelo = new RelacionarModelo();
            relacionarModelo.setIdActividad(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK()
                    .getRelacionarActividadEntidad().getRelacionarActividadEntidadPK().getActividadEntidad().getId());
            relacionarModelo.setClaseGlosarioModelo(new ClaseGlosarioModelo(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK()
                    .getRelacionarActividadEntidad().getRelacionarActividadEntidadPK().getGlosarioEntidad().getGlosarioEntidadPK().getClaseGlosarioEntidad()));
            relacionarModelo.setPalabra(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getRelacionarActividadEntidad()
                    .getRelacionarActividadEntidadPK().getGlosarioEntidad().getGlosarioEntidadPK().getPalabra());
            relacionarModelo.setIdTarea(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getTareaEntidad().getId());
            relacionarModelo.setVuelta(tareaRelacionarActividadEntidad.getTareaRelacionarActividadEntidadPK().getVuelta());
            relacionarModelo.setRespuesta(tareaRelacionarActividadEntidad.getRespuesta());
            relacionarModelo.setHoraRespuesta(tareaRelacionarActividadEntidad.getHoraRespuesta());
            relacionarModeloLista.add(relacionarModelo);
        }
        return relacionarModeloLista;
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
