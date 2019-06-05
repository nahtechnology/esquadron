package tecolotl.alumno.sesion;

import tecolotl.alumno.modelo.AlumnoModelo;
import tecolotl.alumno.entidad.AlumnoEntidad;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Session Bean implementation class AlumnoSesionBean
 */
@Stateless
public class AlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<AlumnoModelo> busca(){
        TypedQuery<AlumnoEntidad> typedQuery = entityManager.createNamedQuery("AlumnoEntidad.busca", AlumnoEntidad.class);
        List<AlumnoEntidad> alumnoEntidadLista = typedQuery.getResultList();
        return alumnoEntidadLista.stream().map(AlumnoModelo::new).collect(Collectors.toList());
    }

    public AlumnoModelo busca(@NotNull @Min(1) Integer id){
        //return new AlumnoModelo(entityManager.createNamedQuery("AlumnoEntidad.buscaTareas", AlumnoEntidad.class).setParameter("id", id).getSingleResult());
        System.out.println(id);
        return new AlumnoModelo();
    }
}
