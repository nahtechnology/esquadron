package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.EscribirActividadEntidad;
import tecolotl.alumno.modelo.EscribirBaseModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class EscribirSessionBean {

    @Inject
    private Logger logger;

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

    public int registraTarea(@NotNull @Min(1) Integer idTarea, @NotNull @Size(min = 11, max = 11) String idActividad) {
        return 0;
    }
}
