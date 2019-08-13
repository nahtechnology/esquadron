package tecolotl.alumno.sesion;

import tecolotl.alumno.entidad.ActividadEntidad;
import tecolotl.alumno.modelo.GlosarioModelo;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@Stateless
public class ActividadSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @Inject
    private Logger logger;

    public void buscaGlosarioID(){

    //TODO no tocar, porfavor ;)
    }
}
