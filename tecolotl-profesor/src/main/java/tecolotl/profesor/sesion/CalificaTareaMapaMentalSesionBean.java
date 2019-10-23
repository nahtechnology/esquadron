package tecolotl.profesor.sesion;

import org.hibernate.engine.spi.EntityUniqueKey;
import tecolotl.profesor.entidad.CalificaTareaMapamental;
import tecolotl.profesor.modelo.CalificaTareaMapaMentalModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CalificaTareaMapaMentalSesionBean {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Metodo que busca todas las tareas para calificar
     * @return una coleccion de {@link CalificaTareaMapaMentalModelo}
     */
    public List<CalificaTareaMapaMentalModelo> busca(){
        TypedQuery<CalificaTareaMapamental> typedQuery = entityManager.createNamedQuery("CalificaTareaMapamental.busca", CalificaTareaMapamental.class);
        List<CalificaTareaMapamental> calificaTareaMapamentalLista = typedQuery.getResultList();
        logger.info("Total de tareas para calificar: ".concat(String.valueOf(calificaTareaMapamentalLista.size())));
        return calificaTareaMapamentalLista.stream().map(CalificaTareaMapaMentalModelo::new).collect(Collectors.toList());
    }
}
