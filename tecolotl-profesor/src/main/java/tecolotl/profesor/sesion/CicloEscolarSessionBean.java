package tecolotl.profesor.sesion;

import tecolotl.administracion.persistencia.entidad.EscuelaEntidad;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.entidad.CicloEscolarEntidad;
import tecolotl.profesor.entidad.CicloEscolarEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.modelo.CicloEscolarModelo;
import tecolotl.profesor.modelo.GrupoModelo;
import tecolotl.profesor.validacion.CicloEscolarLlavePrimariaValidacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    public List<CicloEscolarModelo> busca(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo,
                                          boolean activo,
                                          @NotNull UUID idProfesor) {
        logger.fine(claveCentroTrabajo);
        TypedQuery<CicloEscolarEntidad> typedQuery = entityManager.createNamedQuery("CicloEscolarEntidad.buscaIdEscuela", CicloEscolarEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        typedQuery.setParameter("activo", activo);
        typedQuery.setParameter("idProfesor", idProfesor);
        return typedQuery.getResultList().stream().map(CicloEscolarModelo::new).collect(Collectors.toList());
    }

    /**
     * Busca todos los ciclos escolares de una escuela
     * @param claveCentroTrabajo  Clave centro de trabajo de una escuela
     * @return Colección de Ciclo escolares.
     */
    public List<CicloEscolarModelo> busca(@NotNull @Size(min = 10, max = 14) String claveCentroTrabajo) {
        logger.fine(claveCentroTrabajo);
        TypedQuery<CicloEscolarEntidad> typedQuery = entityManager.createNamedQuery("CicloEscolarEntidad.cuentaEscuelaCuentaGrupo", CicloEscolarEntidad.class);
        typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo);
        return typedQuery.getResultList().stream().map(CicloEscolarModelo::new).collect(Collectors.toList());
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
     * Cambia la descripcion y estatus de un ciclo escolar
     * @param cicloEscolarModelo Datos del ciclo escolar
     */
    public void actualiza(@NotNull @Valid CicloEscolarModelo cicloEscolarModelo) {
        CicloEscolarEntidad cicloEscolarEntidad = entityManager.find(CicloEscolarEntidad.class, llavePrimaria(cicloEscolarModelo));
        cicloEscolarEntidad.setDescripcion(cicloEscolarModelo.getDescripcion());
        cicloEscolarEntidad.setActivo(cicloEscolarModelo.getActivo());
    }

    /**
     * Cuenta el total de grupos que tiene un ciclo escolar.
     * @param cicloEscolarModelo Objeto con los datos del ciclo escolar
     * @return Total de grupos
     */
    public Long totalGrupo(@NotNull CicloEscolarModelo cicloEscolarModelo) {
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("CicloEscolarEntidad.cuentaGrupo", Long.class);
        typedQuery.setParameter("claveCentroTrabajo", cicloEscolarModelo.getIdEscuela());
        typedQuery.setParameter("fin", cicloEscolarModelo.getFin());
        typedQuery.setParameter("inicio", cicloEscolarModelo.getInicio());
        return typedQuery.getSingleResult();
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

    /**
     * Busca el total de alumnos de una escuela siempre y cuando esten en una ciclo escolar activo
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela a buscar
     * @return
     */
    public Long totalAlumnos(@NotNull @Size(max = 14, min = 10) String claveCentroTrabajo) {
        TypedQuery<Long> typedQuery = entityManager.createNamedQuery("GrupoAlumnoEntidad.buscaTotalAlumno", Long.class);
        try {
            return typedQuery.setParameter("claveCentroTrabajo", claveCentroTrabajo).getSingleResult();
        } catch (NoResultException e) {
            return 0L;
        }
    }

    /**
     * Hereda todos los grupos de un clilo ecolar
     * @param cicloEscolarModeloViejo ciclo escolar a ser copiado
     * @param cicloEscolarModeloNuevo ciclo escolar a copiar
     * @param claveCentroTrabajo Escula donde se copiaran los datos
     * @return nuero de elementos creados
     */
    public Integer hereda(@NotNull CicloEscolarModelo cicloEscolarModeloViejo,
                          @NotNull CicloEscolarModelo cicloEscolarModeloNuevo,
                          @NotNull String claveCentroTrabajo) {
        Query query = entityManager.createNativeQuery("SELECT * FROM administracion.hereda_grupos(?,?,?,?,?)");
        query.setParameter(1, claveCentroTrabajo);
        query.setParameter(2, cicloEscolarModeloViejo.getInicio());
        query.setParameter(3, cicloEscolarModeloViejo.getFin());
        query.setParameter(4, cicloEscolarModeloNuevo.getInicio());
        query.setParameter(5, cicloEscolarModeloNuevo.getFin());
        return query.executeUpdate();
    }

}
