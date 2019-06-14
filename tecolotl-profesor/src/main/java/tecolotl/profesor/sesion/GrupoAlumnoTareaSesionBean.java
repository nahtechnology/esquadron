package tecolotl.profesor.sesion;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.TareaEntidad;
import tecolotl.nucleo.herramienta.LoggerProducer;
import tecolotl.nucleo.herramienta.ValidadorSessionBean;
import tecolotl.profesor.entidad.GrupoAlumnoTareaEntidad;
import tecolotl.profesor.entidad.GrupoAlumnoTareaEntidadPK;
import tecolotl.profesor.entidad.GrupoEntidad;
import tecolotl.profesor.modelo.GrupoAlumnoTareaModelo;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class GrupoAlumnoTareaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private LoggerProducer logger;

    @Inject
    private ValidadorSessionBean validadorSessionBean;

    public void inserta(@NotNull GrupoAlumnoTareaModelo grupoAlumnoTareaModelo){
        GrupoAlumnoTareaEntidadPK grupoAlumnoTareaEntidadPK = new GrupoAlumnoTareaEntidadPK();
        grupoAlumnoTareaEntidadPK.setGrupoEntidad(new GrupoEntidad(grupoAlumnoTareaModelo.getIdGrupo()));
        grupoAlumnoTareaEntidadPK.setAlumnoEntidad(new AlumnoEntidad(grupoAlumnoTareaModelo.getIdAlumno()));
        grupoAlumnoTareaEntidadPK.setTareaEntidad(new TareaEntidad(grupoAlumnoTareaModelo.getIdTarea()));
        GrupoAlumnoTareaEntidad grupoAlumnoTareaEntidad = new GrupoAlumnoTareaEntidad();
        grupoAlumnoTareaEntidad.setGrupoAlumnoTareaEntidadPK(grupoAlumnoTareaEntidadPK);
        entityManager.persist(grupoAlumnoTareaEntidad);
    }
    public List<GrupoAlumnoTareaModelo> busca(){
        TypedQuery<GrupoAlumnoTareaEntidad> typedQuery = entityManager.createNamedQuery("GrupoAlumnoTareaEntidad.busca", GrupoAlumnoTareaEntidad.class);
        List<GrupoAlumnoTareaEntidad> grupoAlumnoTareaEntidadLista = typedQuery.getResultList();
        return grupoAlumnoTareaEntidadLista.stream().map(GrupoAlumnoTareaModelo::new).collect(Collectors.toList());
    }
    public Integer elimina(@NotNull GrupoAlumnoTareaModelo grupoAlumnoTareaModelo){
        GrupoAlumnoTareaEntidadPK grupoAlumnoTareaEntidadPK = new GrupoAlumnoTareaEntidadPK();
        grupoAlumnoTareaEntidadPK.setGrupoEntidad(new GrupoEntidad(grupoAlumnoTareaModelo.getIdAlumno()));
        grupoAlumnoTareaEntidadPK.setAlumnoEntidad(new AlumnoEntidad(grupoAlumnoTareaModelo.getIdAlumno()));
        grupoAlumnoTareaEntidadPK.setTareaEntidad(new TareaEntidad(grupoAlumnoTareaModelo.getIdTarea()));
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete criteriaDelete = criteriaBuilder.createCriteriaDelete(GrupoAlumnoTareaEntidad.class);
        Root<GrupoAlumnoTareaEntidad> root = criteriaDelete.from(GrupoAlumnoTareaEntidad.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("grupoAlumnoTareaPK"), grupoAlumnoTareaEntidadPK));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
