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

    @Inject
    private Validator validator;

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
        return new GradoEscolarModelo(entityManager.find(GradoEscolarEntidad.class, id));
    }

    public List<GradoEscolarModelo> buscaPorGrado(Short grado, String nombre) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GradoEscolarEntidad> criteriaQuery = criteriaBuilder.createQuery(GradoEscolarEntidad.class);
        Root<GradoEscolarEntidad> root = criteriaQuery.from(GradoEscolarEntidad.class);
        Predicate predicateGrado = criteriaBuilder.equal(root.get("grado"), grado);
        Predicate predicateNombre = criteriaBuilder.equal(root.get("nombre"), nombre);

        criteriaQuery.where(criteriaBuilder.and(predicateGrado, predicateNombre));

        List<GradoEscolarEntidad> gradoEscolarEntidadLista = entityManager.createQuery(criteriaQuery).getResultList();
        gradoEscolarEntidadLista.stream().map(GradoEscolarModelo::new).collect(Collectors.toList());
        return null;
    }


    public int elimina(@NotNull @Min(1) Short id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<GradoEscolarEntidad> criteriaDelete = criteriaBuilder.createCriteriaDelete(GradoEscolarEntidad.class);
        Root<GradoEscolarEntidad> root = criteriaDelete.from(GradoEscolarEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"),id));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    public void inserta(@NotNull @Valid GradoEscolarModelo gradoEscolarModelo) {

        GradoEscolarEntidad gradoEscolarEntidad = new GradoEscolarEntidad();
        gradoEscolarEntidad.setGrado(gradoEscolarModelo.getGrado());
        gradoEscolarEntidad.setNivel(gradoEscolarModelo.getNivel());
        entityManager.persist(gradoEscolarEntidad);
    }

    public void inserta( @NotNull String nivel, @NotNull Short grado) {

    }
}
