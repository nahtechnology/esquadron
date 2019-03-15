package tecolotl.administracion.sesion;

import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TipoContactoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;


}
