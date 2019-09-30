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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Path;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Tarea relacinar.
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
        TypedQuery<TareaRelacionarActividadEntidad> typedQuery =
                entityManager.createNamedQuery("TareaRelacionarActividadEntidad.buscaTarea", TareaRelacionarActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaRelacionarActividadEntidad> tareaRelacionarActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Elementos entontrados".concat(String.valueOf(tareaRelacionarActividadEntidadLista.size())));
        return tareaRelacionarActividadEntidadLista.stream().map(RelacionarModelo::new).collect(Collectors.toList());
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
        TypedQuery<RelacionarEntidad> typedQuery = entityManager.createNamedQuery("RelacionarEntidad.buscaNoImagen", RelacionarEntidad.class);
        typedQuery.setFirstResult(inicio).setMaxResults(maximo);
        return typedQuery.getResultList().stream().map(RelacionarOriginalModelo::new).collect(Collectors.toList());
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
        tareaRelacionarActividadEntidadPK.setRelacionarActividadEntidad(
                new RelacionarActividadEntidad(new RelacionarActividadEntidadPK(
                        new RelacionarEntidad(relacionarModelo.getCodigo()), new ActividadEntidad(relacionarModelo.getIdActividad())
                ))
        );
        return tareaRelacionarActividadEntidadPK;
    }

}
