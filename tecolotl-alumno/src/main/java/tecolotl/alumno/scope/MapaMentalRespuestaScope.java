package tecolotl.alumno.scope;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.mapamental.*;
import tecolotl.alumno.modelo.mapamental.TareaMapaMentalModelo;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class MapaMentalRespuestaScope {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Resource
    private UserTransaction userTransaction;

    public void respuesta(@NotNull @Size(min = 1) List<TareaMapaMentalModelo> tareaMapaMentalModeloLista,
                          @NotNull UUID idTarea,
                          @NotNull String idActividad) {
        logger.fine(tareaMapaMentalModeloLista.toString());
        logger.fine(idTarea.toString());
        logger.fine(idActividad);
        try {
            userTransaction.begin();
            tareaMapaMentalModeloLista.forEach(tareaMapaMentalModelo -> {
                TareaMapaMentalActividadEntidad tareaMapaMentalActividadEntidad =
                        entityManager.find(TareaMapaMentalActividadEntidad.class, llavePrimaria(tareaMapaMentalModelo, idTarea, idActividad));
                tareaMapaMentalActividadEntidad.setTextoRespuesta(tareaMapaMentalModelo.getRespuesta());
            });
            userTransaction.commit();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se puede insertar datos por:" + e.getMessage(), e);
            try {
                userTransaction.rollback();
            } catch (SystemException ex) {
                logger.log(Level.SEVERE, "No se pudo hcer rollback por:" + ex.getMessage(), ex);
            }
        }
    }

    private TareaMapaMentalActividadEntidadPK llavePrimaria(TareaMapaMentalModelo tareaMapaMentalModelom, UUID idTarea, String idActividad) {
        MapaMentalEntidadPK mapaMentalEntidadPK = new MapaMentalEntidadPK(tareaMapaMentalModelom.getCodigo(), tareaMapaMentalModelom.getCardinalidad());
        return new TareaMapaMentalActividadEntidadPK(
                new MapaMentalActividadEntidad(
                        new MapaMentalActividadEntidadPK(new MapaMentalEntidad(mapaMentalEntidadPK), new ActividadEntidad(idActividad))
                ), new TareaEntidad(idTarea), tareaMapaMentalModelom.getVuelta()
        );
    }
}
