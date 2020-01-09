package tecolotl.alumno.scope;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.alumno.entidad.NivelLenguajeEntidad;
import tecolotl.alumno.modelo.AlumnoModelo;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class AlumnoRequestScope {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Logger logger;

    @Resource
    private UserTransaction userTransaction;

    public void agregarAlumnos(@NotNull @Size(min = 1)List<AlumnoModelo> alumnoModeloLista){
        logger.fine(alumnoModeloLista.toString());
        try{
            userTransaction.begin();
            alumnoModeloLista.forEach(alumnoModelo->{
                AlumnoEntidad alumnoEntidad = new AlumnoEntidad();
                alumnoEntidad.setId(alumnoModelo.getId());
                alumnoEntidad.setNombre(alumnoModelo.getNombre());
                alumnoEntidad.setApellidoPaterno(alumnoModelo.getApellidoPaterno());
                alumnoEntidad.setApellidoMaterno(alumnoModelo.getApellidoMaterno());
                alumnoEntidad.setNacimiento(alumnoModelo.getNacimiento());
                alumnoEntidad.setApodo(alumnoModelo.getApodo());
                alumnoEntidad.setSexo(alumnoModelo.getSexo());
                alumnoEntidad.setNivelLenguajeEntidad(new NivelLenguajeEntidad(alumnoModelo.getNivelLenguajeModelo().getClave()));
                alumnoEntidad.setCorreoPadreFamilia(alumnoModelo.getCorreoPadreFamilia());
                alumnoEntidad.setContraseniaPadreFamilia(alumnoModelo.getContrasenia());
                alumnoEntidad.setContrasenia(alumnoModelo.getContrasenia());
                entityManager.persist(alumnoEntidad);
            });
            userTransaction.commit();
        }catch (Exception e){
            logger.log(Level.SEVERE,"No se pueden agregar los datos: ".concat(e.getMessage()),e);
            try{
                userTransaction.rollback();
            }catch (Exception ex){
                logger.log(Level.SEVERE,"No se pudo hacer rollback por: ".concat(ex.getMessage()),ex);
            }
        }
    }
}
