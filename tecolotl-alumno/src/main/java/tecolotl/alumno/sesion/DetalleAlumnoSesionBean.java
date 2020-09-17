package tecolotl.alumno.sesion;

import tecolotl.alumno.modelo.DetalleAlumnoModelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Stateless
public class DetalleAlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DetalleAlumnoModelo> busca(@NotNull UUID idAlumno) {
        DetalleAlumnoModelo detalleAlumnoModelo = null; List<DetalleAlumnoModelo> detalleAlumnoModeloLista = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT ce.id_escuela, ce.inicio, ce.fin, ce.descripcion, CAST(g.id AS VARCHAR), g.grado, g.grupo " +
                "FROM profesor.ciclo_escolar ce JOIN profesor.grupo g ON ce.inicio = g.inicio and ce.fin = g.fin and ce.id_escuela = g.id_escuela JOIN " +
                "profesor.grupo_alumno ga ON g.id = ga.id_grupo WHERE ga.id_alumno = ? AND ce.activo = true;");
        query.setParameter(1, idAlumno);
        List<Object[]> list = query.getResultList();
        for (Object[] objectos : list) {
            detalleAlumnoModelo = new DetalleAlumnoModelo();
            detalleAlumnoModelo.set
        }
    }
}
