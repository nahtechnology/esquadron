package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.profesor.entidad.CalificaTareaMapamentalEntidad;
import tecolotl.profesor.entidad.CalificaTareaMapamentalEntidadPK;
import tecolotl.profesor.modelo.CalificaTareaMapaMentalModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
        TypedQuery<CalificaTareaMapamentalEntidad> typedQuery = entityManager.createNamedQuery("CalificaTareaMapamental.busca", CalificaTareaMapamentalEntidad.class);
        List<CalificaTareaMapamentalEntidad> calificaTareaMapamentalEntidadLista = typedQuery.getResultList();
        logger.info("Total de tareas para calificar: ".concat(String.valueOf(calificaTareaMapamentalEntidadLista.size())));
        return calificaTareaMapamentalEntidadLista.stream().map(CalificaTareaMapaMentalModelo::new).collect(Collectors.toList());
    }

    /**
     * Agrega una respuesta relacionado a un mapa mental
     * @param calificaTareaMapaMentalModelo
     */
    public void agrega(@NotNull @Valid CalificaTareaMapaMentalModelo calificaTareaMapaMentalModelo) {
        CalificaTareaMapamentalEntidadPK calificaTareaMapamentalEntidadPK = new CalificaTareaMapamentalEntidadPK();
        calificaTareaMapamentalEntidadPK.setTareaEntidad(new TareaEntidad(calificaTareaMapaMentalModelo.getIdTarea()));
        calificaTareaMapamentalEntidadPK.setCardinalidad(calificaTareaMapaMentalModelo.getCardinalidad());
        calificaTareaMapamentalEntidadPK.setVuelta(calificaTareaMapaMentalModelo.getVuelta());
        CalificaTareaMapamentalEntidad calificaTareaMapamentalEntidad =
                new CalificaTareaMapamentalEntidad(calificaTareaMapamentalEntidadPK);
        calificaTareaMapamentalEntidad.setComentario(calificaTareaMapaMentalModelo.getComentario());
        calificaTareaMapamentalEntidad.setPuntaje(calificaTareaMapaMentalModelo.getPuntaje());
        entityManager.persist(calificaTareaMapamentalEntidad);
        calificaTareaMapaMentalModelo.setMomento(calificaTareaMapamentalEntidad.getMomento());
    }

    /**
     * Actualiza las respuesta de un mapa mental
     * @param calificaTareaMapaMentalModelo
     * @return
     */
    public int respuesta(@NotNull @Valid CalificaTareaMapaMentalModelo calificaTareaMapaMentalModelo) {
        Query query = entityManager.createNamedQuery("CalificaTareaMapamentalEntidad.califica");
        query.setParameter("idTarea", calificaTareaMapaMentalModelo.getIdTarea())
                .setParameter("cardinalidad", calificaTareaMapaMentalModelo.getCardinalidad())
                .setParameter("comentario", calificaTareaMapaMentalModelo.getComentario())
                .setParameter("puntaje", calificaTareaMapaMentalModelo.getPuntaje())
                .setParameter("vuelta", calificaTareaMapaMentalModelo.getVuelta());
        return query.executeUpdate();
    }
}
