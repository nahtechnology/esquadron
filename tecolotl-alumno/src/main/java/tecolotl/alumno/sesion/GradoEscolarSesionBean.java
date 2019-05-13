package tecolotl.alumno.sesion;

import tecolotl.alumno.Modelo.GradoEscolarModelo;
import tecolotl.alumno.entidad.GradoEscolarEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class GradoEscolarSesionBean {
    @PersistenceContext
    private EntityManager entityManager;

    public List<GradoEscolarModelo> busca(){
        TypedQuery<GradoEscolarEntidad> typedQuery = entityManager.createNamedQuery("GradoEscolarEntidad.busca", GradoEscolarEntidad.class);
        List<GradoEscolarEntidad> gradoEscolarEntidadLista = typedQuery.getResultList();
        return gradoEscolarEntidadLista.stream().map(GradoEscolarModelo::new).collect(Collectors.toList());
    }
}
