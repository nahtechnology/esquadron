package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.glosario.GlosarioActividadEntidad;
import tecolotl.alumno.modelo.glosario.GlosarioModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class GlosarioSesionBean {

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @PersistenceContext
    private EntityManager entityManager;

    public List<GlosarioModelo> busca(@NotNull @Size(min = 11, max = 11) String idActividad) {
        TypedQuery<GlosarioActividadEntidad> typedQuery = entityManager.createNamedQuery("GlosarioActividadEntidad.buscaActividad", GlosarioActividadEntidad.class);
        return null;
    }
}
