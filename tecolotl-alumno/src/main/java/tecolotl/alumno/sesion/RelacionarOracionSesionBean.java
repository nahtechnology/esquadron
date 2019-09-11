package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.RelacionarOracionesEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidad;
import tecolotl.alumno.entidad.relacionar_oraciones.TareaRelacionarOracionesEntidadPK;
import tecolotl.alumno.modelo.relacionar_oraciones.TareaRelacionarOracionModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
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

    private TareaRelacionarOracionesEntidadPK llavePrimaria(TareaRelacionarOracionModelo tareaRelacionarOracionModelo) {
        TareaRelacionarOracionesEntidadPK tareaRelacionarOracionesEntidadPK = new TareaRelacionarOracionesEntidadPK();
        tareaRelacionarOracionesEntidadPK.setTareaEntidad(new TareaEntidad(tareaRelacionarOracionModelo.getIdTarea()));
        tareaRelacionarOracionesEntidadPK.setRelacionarOracionesEntidad(
                new RelacionarOracionesEntidad(tareaRelacionarOracionModelo.getRelacionarOracionModelo().getId()));
        return tareaRelacionarOracionesEntidadPK;
    }
}
