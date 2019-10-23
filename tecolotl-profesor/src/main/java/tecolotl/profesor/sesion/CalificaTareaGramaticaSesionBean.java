package tecolotl.profesor.sesion;


import tecolotl.profesor.entidad.CalificaTareaGramaticaEntidad;
import tecolotl.profesor.modelo.CalificaTareaGramaticaModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CalificaTareaGramaticaSesionBean {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Metodo que busca todas las tareas para calificar
     * @return una coleccion de {@link CalificaTareaGramaticaModelo}
     */
    public List<CalificaTareaGramaticaModelo> busca(){
        TypedQuery<CalificaTareaGramaticaEntidad> typedQuery = entityManager.createNamedQuery("CalificaTareaGramaticaEntidad.busca", CalificaTareaGramaticaEntidad.class);
        List<CalificaTareaGramaticaEntidad> calificaTareaGramaticaEntidadLista = typedQuery.getResultList();
        logger.info("Total de tareas para calificar: ".concat(String.valueOf(calificaTareaGramaticaEntidadLista.size())));
        return calificaTareaGramaticaEntidadLista.stream().map(CalificaTareaGramaticaModelo::new).collect(Collectors.toList());
    }
}
