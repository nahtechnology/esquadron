package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.oraciones.TareaOracionesEntidad;
import tecolotl.alumno.modelo.oraciones.TareaOracionesModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class OracionesSesionBean {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<TareaOracionesModelo> busca(@NotNull UUID idTarea){
        logger.fine(idTarea.toString());
        TypedQuery<TareaOracionesEntidad> typedQuery = entityManager.createNamedQuery("TareaOracionesEntidad.buscaidTarea", TareaOracionesEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaOracionesEntidad> tareaOracionesEntidadLista = typedQuery.getResultList();
        logger.finer("ElementosEncontrados: ".concat(String.valueOf(tareaOracionesEntidadLista.size())));
        return tareaOracionesEntidadLista.stream().map(TareaOracionesModelo::new).collect(Collectors.toList());
    }
}
