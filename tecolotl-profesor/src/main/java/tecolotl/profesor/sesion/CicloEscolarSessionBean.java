package tecolotl.profesor.sesion;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.entidad.CicloEscolarEntidad;
import tecolotl.profesor.entidad.CicloEscolarEntidadPK;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.validacion.CicloEscolarLlavePrimariaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Ciclo escolar de una escuela
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
@Stateless
public class CicloEscolarSessionBean {

    @Inject
    private Logger logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca todos los ciclo escolares de una escuela
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela a buscar.
     * @return Colección de {@link CicloEscolarModelo}.
     */
    public List<CicloEscolarModelo> busca(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        logger.fine(claveCentroTrabajo);
        TypedQuery<CicloEscolarEntidad> typedQuery = entityManager.createNamedQuery("CicloEscolarEntidad.buscaEscuela", CicloEscolarEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        List<CicloEscolarEntidad> cicloEscolarEntidadLista = typedQuery.getResultList();
        logger.finer("Elementos encontrados:".concat(String.valueOf(cicloEscolarEntidadLista.size())));
        List<CicloEscolarModelo> cicloEscolarModeloLista = new ArrayList<>();
        for (CicloEscolarEntidad cicloEscolarEntidad : cicloEscolarEntidadLista) {
            logger.finest(cicloEscolarEntidad.toString());
            cicloEscolarModeloLista.add(new CicloEscolarModelo(cicloEscolarEntidad));
        }
        return cicloEscolarModeloLista;
    }

    /**
     * Busca la descripcion de un ciclo escolar
     * @param cicloEscolarModelo Datos del ciclo escolar
     * @return Ciclo Escolar encontrado
     */
    public void busca(@NotNull CicloEscolarModelo cicloEscolarModelo) {
        logger.fine(cicloEscolarModelo.toString());
        validadorSessionBean.validacion(cicloEscolarModelo, CicloEscolarLlavePrimariaValidacion.class);
        CicloEscolarEntidad cicloEscolarEntidad = entityManager.find(CicloEscolarEntidad.class, llavePrimaria(cicloEscolarModelo));
        if (cicloEscolarEntidad != null) {
            cicloEscolarModelo.setDescripcion(cicloEscolarEntidad.getDescripcion());
        }
    }

    /**
     * Crea un nuevo ciclo escolar para una escuela
     * @param cicloEscolarModelo Datos del ciclo escolar
     */
    public void inserta(@NotNull @Valid CicloEscolarModelo cicloEscolarModelo) {
        logger.fine(cicloEscolarModelo.toString());
        CicloEscolarEntidad cicloEscolarEntidad = new CicloEscolarEntidad(llavePrimaria(cicloEscolarModelo));
        cicloEscolarEntidad.setDescripcion(cicloEscolarModelo.getDescripcion());
        entityManager.persist(cicloEscolarEntidad);
    }

    /**
     * Elimina una ciclo escolar de una escuela
     * @param cicloEscolarModelo Datos del ciclo escolar
     */
    public void elimina(@NotNull CicloEscolarModelo cicloEscolarModelo) {
        CicloEscolarEntidad cicloEscolarEntidad = entityManager.find(CicloEscolarEntidad.class, llavePrimaria(cicloEscolarModelo));
        entityManager.remove(cicloEscolarEntidad);
    }

    /**
     * Cambia la descripcion de un ciclo escolar
     * @param cicloEscolarModelo Datos del ciclo escolar
     */
    public void actualiza(@NotNull @Valid CicloEscolarModelo cicloEscolarModelo) {
        CicloEscolarEntidad cicloEscolarEntidad = entityManager.find(CicloEscolarEntidad.class, llavePrimaria(cicloEscolarModelo));
        cicloEscolarEntidad.setDescripcion(cicloEscolarModelo.getDescripcion());
    }

    /**
     * Crea una llave primaria de un ciclo escolar
     * @param cicloEscolarModelo Datos del ciclo escolar
     * @return Llave primaria de un ciclo escolar
     */
    private CicloEscolarEntidadPK llavePrimaria(CicloEscolarModelo cicloEscolarModelo) {
        validadorSessionBean.validacion(cicloEscolarModelo, CicloEscolarLlavePrimariaValidacion.class);
        CicloEscolarEntidadPK cicloEscolarEntidadPK = new CicloEscolarEntidadPK();
        cicloEscolarEntidadPK.setFin(cicloEscolarModelo.getFin());
        cicloEscolarEntidadPK.setInicio(cicloEscolarModelo.getInicio());
        cicloEscolarEntidadPK.setEscuelaEntidad(new EscuelaEntidad(cicloEscolarModelo.getIdEscuela()));
        return cicloEscolarEntidadPK;
    }

}
