package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.RelacionarOracionesEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidadPK;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.alumno.validacion.relacionar_oraciones.RelacionarOracionLlavePrimariaValidacion;
import tecolotl.alumno.validacion.relacionar_oraciones.TareaRelacionarOracionRespuestaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class RelacionarOracionSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @Inject
    private UserTransaction userTransaction;

    @Inject
    private Logger logger;

    public List<TareaRelacionarOracionModelo> busca(@NotNull Integer idTarea) {
        logger.fine(idTarea.toString());
        TypedQuery<TareaRelacionarOracionesEntidad> typedQuery =
                entityManager.createNamedQuery("TareaRelacionarOracionesEntidad.buscaidTarea", TareaRelacionarOracionesEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaRelacionarOracionesEntidad> tareaRelacionarOracionesEntidadLista = typedQuery.getResultList();
        logger.finer("Elemento encontrados:".concat(String.valueOf(tareaRelacionarOracionesEntidadLista.size())));
        if (logger.isLoggable(Level.FINEST)) {
            tareaRelacionarOracionesEntidadLista.forEach(tareaRelacionarOracionesEntidad -> logger.finest(tareaRelacionarOracionesEntidad.toString()));
        }
        return tareaRelacionarOracionesEntidadLista.stream().map(TareaRelacionarOracionModelo::new).collect(Collectors.toList());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void respuesta(@NotNull @Size(min = 1) List<TareaRelacionarOracionModelo> tareaRelacionarOracionModeloLista) {
        logger.fine(String.valueOf(tareaRelacionarOracionModeloLista.size()));
    /*    tareaRelacionarOracionModeloLista.forEach(tareaRelacionarOracionModelo -> {
            validadorSessionBean.validacion(tareaRelacionarOracionModelo, RelacionarOracionLlavePrimariaValidacion.class, TareaRelacionarOracionRespuestaValidacion.class);
        });
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            userTransaction.begin();
            for (TareaRelacionarOracionModelo tareaRelacionarOracionModelo : tareaRelacionarOracionModeloLista) {
                TareaRelacionarOracionesEntidad tareaRelacionarOracionesEntidad = new TareaRelacionarOracionesEntidad();
                tareaRelacionarOracionesEntidad.setTareaRelacionarOracionesEntidadPK(llavePrimaria(tareaRelacionarOracionModelo));
                tareaRelacionarOracionesEntidad.setRespuesta(tareaRelacionarOracionModelo.getRespuesta());
                entityManager.persist(tareaRelacionarOracionesEntidad);
            }
            logger.finer("Se persistieron:".concat(String.valueOf(tareaRelacionarOracionModeloLista.size())));
            userTransaction.commit();
        } catch (Exception excepcion) {
            logger.log(Level.SEVERE, "No se pudo persisitir por el siguiente problema:"+excepcion.getMessage(), excepcion);
            try {
                userTransaction.rollback();
            } catch (SystemException systemException) {
                logger.log(Level.SEVERE, "No se pudo persisitir por el siguiente problema:" + systemException.getMessage(), systemException);
            }
        }*/
    }

    private TareaRelacionarOracionesEntidadPK llavePrimaria(TareaRelacionarOracionModelo tareaRelacionarOracionModelo) {
        TareaRelacionarOracionesEntidadPK tareaRelacionarOracionesEntidadPK = new TareaRelacionarOracionesEntidadPK();
        tareaRelacionarOracionesEntidadPK.setTareaEntidad(new TareaEntidad(tareaRelacionarOracionModelo.getIdTarea()));
        tareaRelacionarOracionesEntidadPK.setRelacionarOracionesEntidad(
                new RelacionarOracionesEntidad(tareaRelacionarOracionModelo.getRelacionarOracionModelo().getId()));
        return tareaRelacionarOracionesEntidadPK;
    }
}
