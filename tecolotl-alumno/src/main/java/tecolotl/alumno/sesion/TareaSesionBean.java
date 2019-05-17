package tecolotl.alumno.sesion;

import tecolotl.alumno.Modelo.TareaModelo;
import tecolotl.alumno.entidad.TareaEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TareaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TareaModelo> busca(){
        List<TareaModelo> tareaModeloLista = new ArrayList<>();
        TypedQuery<TareaEntidad> typedQuery = entityManager.createNamedQuery("TareaEntidad.busca", TareaEntidad.class);
        List<TareaEntidad> tareaEntidadLista = typedQuery.getResultList();
        return tareaEntidadLista.stream().map(TareaModelo::new).collect(Collectors.toList());
    }
}
