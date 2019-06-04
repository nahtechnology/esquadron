package tecolotl.alumno.sesion;

import tecolotl.alumno.Modelo.GradoEscolarModelo;
import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.GradoEscolarEntidad;

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
public class GradoEscolarSesionBean {
    @PersistenceContext
    private EntityManager entityManager;

    //@Inject
    //private Validator validator;

    /**
     * Inserta un nuevo grado escolar
     * @param gradoEscolarModelo dato para poder insertar un grado escolar
     */

    public void inserta(@NotNull @Valid GradoEscolarModelo gradoEscolarModelo) {
        GradoEscolarEntidad gradoEscolarEntidad = new GradoEscolarEntidad();
        gradoEscolarEntidad.setId(gradoEscolarModelo.getId());
        gradoEscolarEntidad.setGrado(gradoEscolarModelo.getGrado());
        gradoEscolarEntidad.setNivel(gradoEscolarModelo.getNivel());
        entityManager.persist(gradoEscolarEntidad);
    }

    /**
     * Inserta un nuevo grado escolar
     * @param id llave primaria
     * @param nivel nivel del grado
     * @param grado dato del grado
     */
    public void inserta( @NotNull @Min(1) @Valid Short id, @NotNull String nivel, @NotNull Short grado) {
        GradoEscolarEntidad gradoEscolarEntidad = new GradoEscolarEntidad();
        gradoEscolarEntidad.setId(id);
        gradoEscolarEntidad.setNivel(nivel);
        gradoEscolarEntidad.setGrado(grado);
        entityManager.persist(gradoEscolarEntidad);
    }
    /**
     * Busca un grado escolar busqueda default
     * @return todos los grados escolares
     */
    public List<GradoEscolarModelo> busca(){
        List<GradoEscolarModelo> gradoEscolarModeloLista = new ArrayList<>();
        TypedQuery<GradoEscolarEntidad> typedQuery = entityManager.createNamedQuery("GradoEscolarEntidad.busca", GradoEscolarEntidad.class);
        List<GradoEscolarEntidad> gradoEscolarEntidadLista = typedQuery.getResultList();
        return gradoEscolarEntidadLista.stream().map(GradoEscolarModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca un grado escolar por llave primaria
     * @param id Llave primaria
     * @return Grado escolar encontrado
     */
    public GradoEscolarModelo busca(@NotNull @Min(1) Short id) {
        System.out.println(id);
        return new GradoEscolarModelo(entityManager.find(GradoEscolarEntidad.class, id));
    }

    /**
     * busca un grado escolar por nombre y grado
     * @param grado grado escolar
     * @param nivel nivel del grado
     * @return Lista de grados escolares
     */
    public List<GradoEscolarModelo> busca(@NotNull @Valid @Min(1) Short grado, @NotNull @Valid String nivel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GradoEscolarEntidad> criteriaQuery = criteriaBuilder.createQuery(GradoEscolarEntidad.class);
        Root<GradoEscolarEntidad> root = criteriaQuery.from(GradoEscolarEntidad.class);
        Predicate predicateGrado = criteriaBuilder.equal(root.get("grado"), grado);
        Predicate predicateNombre = criteriaBuilder.equal(root.get("nivel"), nivel);
        criteriaQuery.where(criteriaBuilder.and(predicateGrado, predicateNombre));
        List<GradoEscolarEntidad> gradoEscolarEntidadLista = entityManager.createQuery(criteriaQuery).getResultList();
        return gradoEscolarEntidadLista.stream().map(GradoEscolarModelo::new).collect(Collectors.toList());
    }

    /**
     * Elimina un grado escolar por llave primaria
     * @param id llave primaria
     * @return  numero de elemtos eliminados, 0 en caso de no existir
     */
    public Integer elimina(@NotNull @Min(1) Short id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<GradoEscolarEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(GradoEscolarEntidad.class);
        Root<GradoEscolarEntidad> root = criteriaDelete.from(GradoEscolarEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"),id));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    /**
     * Actualiza un dato por llave primaria
     * @param id llave primaria
     * @return numero de elementos modificados, 0 si no existe
     */
    public Integer actualiza(@NotNull @Min(1) @Valid Short id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<GradoEscolarEntidad> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(GradoEscolarEntidad.class);
        Root<GradoEscolarEntidad> root = criteriaUpdate.from(GradoEscolarEntidad.class);
        Predicate predicate = criteriaBuilder.equal(root.get("id"),id);
        criteriaUpdate.set(root.get("nivel"), "NewLevel");
        criteriaUpdate.where(predicate);
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

}
