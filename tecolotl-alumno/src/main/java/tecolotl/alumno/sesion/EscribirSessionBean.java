package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.escribir.EscribirActividadEntidad;
import tecolotl.alumno.entidad.escribir.TareaEscribirActividadEntidad;
import tecolotl.alumno.modelo.escribir.EscribirBaseModelo;
import tecolotl.alumno.modelo.escribir.EscribirModelo;
import tecolotl.alumno.validacion.escribir.EscribirLlavePrimariaValidacion;
import tecolotl.alumno.validacion.escribir.EscribirRespuestaValidacion;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class EscribirSessionBean {

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @PersistenceContext
    private EntityManager entityManager;

    public List<EscribirBaseModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine("Buscando por:".concat(idActividad));
        TypedQuery<EscribirActividadEntidad> typedQuery = entityManager.createNamedQuery("EscribirActividadEntidad.buscaActivdad", EscribirActividadEntidad.class);
        typedQuery.setParameter("idActividad", idActividad);
        List<EscribirActividadEntidad> escribirActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Escrbir total localizados:".concat(String.valueOf(escribirActividadEntidadLista.size())));
        return escribirActividadEntidadLista.stream().map(ea -> new EscribirBaseModelo(ea.getEscribirEntidad())).collect(Collectors.toList());
    }

    public List<EscribirModelo> busca(@NotNull Integer idTarea) {
        logger.finer(idTarea.toString());
        TypedQuery<TareaEscribirActividadEntidad> typedQuery = entityManager.createNamedQuery("TareaEscribirActividadEntidad.buscaTarea", TareaEscribirActividadEntidad.class);
        typedQuery.setParameter("idTarea", idTarea);
        List<TareaEscribirActividadEntidad> tareaEscribirActividadEntidadLista = typedQuery.getResultList();
        logger.finer("Tarea escribir encontrados:"+tareaEscribirActividadEntidadLista.size());
        List<EscribirModelo> escribirModeloLista = new ArrayList<>();
        for (TareaEscribirActividadEntidad tareaEscribirActividadEntidad : tareaEscribirActividadEntidadLista) {
            logger.finer(tareaEscribirActividadEntidad.toString());
            escribirModeloLista.add(new EscribirModelo(tareaEscribirActividadEntidad));
        }
        return escribirModeloLista;
    }

    public void respuesta(@NotNull EscribirModelo escribirModelo,
                         @NotNull Integer idTarea,
                         @NotNull @Size(min = 11, max = 11) String idActividad) {
        logger.fine(escribirModelo.toString()); logger.fine(idTarea.toString()); logger.fine(idActividad);
        validadorSessionBean.validacion(escribirModelo, EscribirLlavePrimariaValidacion.class, EscribirRespuestaValidacion.class);
        TareaEscribirActividadEntidad tareaEscribirActividadEntidad = entityManager.createNamedQuery("TareaEscribirActividadEntidad.busca", TareaEscribirActividadEntidad.class)
                .setParameter("idTarea", idTarea).setParameter("idActividad", idActividad).setParameter("idEscribir", escribirModelo.getId())
                .getSingleResult();
        tareaEscribirActividadEntidad.setTextRespuesta(escribirModelo.getTextoRespuesta());
    }

}
