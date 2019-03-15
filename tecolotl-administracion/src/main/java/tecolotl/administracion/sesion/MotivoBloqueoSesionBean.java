package tecolotl.administracion.sesion;

import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MotivoBloqueoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

}
