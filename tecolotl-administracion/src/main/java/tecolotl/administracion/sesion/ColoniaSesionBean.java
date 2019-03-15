package tecolotl.administracion.sesion;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;
import tecolotl.administracion.persistencia.entidad.EstadoEntidad;
import tecolotl.administracion.persistencia.entidad.MunicipioEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ColoniaSesionBean {

    private Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext
    private EntityManager entityManager;


}