package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.mapamental.TareaMapaMentalActividadEntidadPK;
import tecolotl.alumno.modelo.mapamental.MapaMentalModelo;
import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.logging.Logger;

@Stateless
public class CalificaTareaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    //TODO Toño revisa esto porfavor, no la había visto pero ya me hice los sesion bean de cada uno.
    public void mapaMetal(@NotNull TareaMapaMentalModelo mapaMentalModelo,
                          @NotNull @Size(min = 0) String comentario,
                          @NotNull @Min(0) Short puntaje) {
        logger.fine(mapaMentalModelo.toString());
        TareaMapaMentalActividadEntidadPK tareaMapaMentalActividadEntidadPK = new TareaMapaMentalActividadEntidadPK();
        //tareaMapaMentalActividadEntidadPK.setVuelta(mapaMentalModelo.getVuelta());

    }
}
