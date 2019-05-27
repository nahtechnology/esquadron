package tecolotl.alumno.sesion;

import tecolotl.alumno.Modelo.TareaModelo;
import tecolotl.alumno.entidad.TareaEntidad;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TareaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    //@Inject
    //private Validator validator;


    /**
     * Inserta una tarea.
     * @param tareaModelo datos de la tarea.
     */
    public void inserta(@NotNull @Valid TareaModelo tareaModelo){
        TareaEntidad tareaEntidad = new TareaEntidad();
        tareaEntidad.setAlumnoEntidad(tareaModelo.getAlumnoEntidad());
        tareaEntidad.setAsignacion(tareaModelo.getAsignacion());
        entityManager.persist(tareaEntidad);
    }

    /**
     * Busca una tarea.
     * @param tareaModelo datos de la tarea.
     * @return todas las tareas encontradas.
     */
    public List<TareaModelo> busca(TareaModelo tareaModelo){
        List<TareaModelo> tareaModeloLista = new ArrayList<>();
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.busca", TareaEntidad.class);
        List<TareaEntidad> tareaEntidadLista = typedQuery.getResultList();
        return tareaEntidadLista.stream().map(TareaModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca una traea por llave primaria
     * @param id dato para buscar una tarea
     * @return retorna la tarea encontrada.
     */
    public TareaModelo busca(@NotNull @Min(1) Integer id){
        return new TareaModelo(entityManager.find(TareaEntidad.class, id));
    }

    /**
     * Modifica una tarea
     * @param tareaModelo datos para modificar la tarea.
     * @return numero de elementos modificados, cero en caso de no existir.
     */
    public Integer actualiza(@NotNull @Valid TareaModelo tareaModelo){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<TareaEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(TareaEntidad.class);
        Root<TareaEntidad> root = criteriaUpdate.from(TareaEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"), tareaModelo.getId());
        criteriaUpdate.set(root.get("alumnoEntidad"), tareaModelo.getAlumnoEntidad());
        criteriaUpdate.set(root.get("asignacion"), tareaModelo.getAsignacion());
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * Elimina una tarea por llave primaria
     * @param id dato para eliminar una tarea
     * @return numero de elementos eliminados, 0 en caso de no existir
     */
    public int elimina(@NotNull @Min(1) Integer id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<TareaEntidad> tareaEntidadCriteriaDelete = criteriaBuilder.createCriteriaDelete(TareaEntidad.class);
        Root<TareaEntidad> root = tareaEntidadCriteriaDelete.from(TareaEntidad.class);
        tareaEntidadCriteriaDelete.where(criteriaBuilder.equal(root.get("id"),id));
        return entityManager.createQuery(tareaEntidadCriteriaDelete).executeUpdate();
    }

}
