package tecolotl.alumno.sesion;

import tecolotl.alumno.modelo.DetalleAlumnoModelo;
import tecolotl.alumno.modelo.vista.DetalleAlumno2Modelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Stateless
public class DetalleAlumnoSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DetalleAlumno2Modelo> busca(@NotNull UUID idAlumno) {
        DetalleAlumno2Modelo detalleAlumno2Modelo = null;
        List<DetalleAlumno2Modelo> detalleAlumnoModeloLista = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT ce.id_escuela, ce.inicio, ce.fin, ce.descripcion, CAST(g.id AS VARCHAR), g.grado, g.grupo " +
                "FROM profesor.ciclo_escolar ce JOIN profesor.grupo g ON ce.inicio = g.inicio and ce.fin = g.fin and ce.id_escuela = g.id_escuela JOIN " +
                "profesor.grupo_alumno ga ON g.id = ga.id_grupo WHERE ga.id_alumno = ? AND ce.activo = true;");
        query.setParameter(1, idAlumno);
        List<Object[]> list = query.getResultList();
        for (Object[] objectos : list) {
            detalleAlumno2Modelo = new DetalleAlumno2Modelo();
            detalleAlumno2Modelo.setIdEscuela((String)objectos[0]);
            detalleAlumno2Modelo.setInicio((Date)objectos[1]);
            detalleAlumno2Modelo.setFin((Date)objectos[2]);
            detalleAlumno2Modelo.setDescripcion((String)objectos[3]);
            detalleAlumno2Modelo.setIdGrupo((String)objectos[4]);
            detalleAlumno2Modelo.setGrado((Short)objectos[5]);
            detalleAlumno2Modelo.setGrupo((String)objectos[6]);
            detalleAlumnoModeloLista.add(detalleAlumno2Modelo);
        }
        return detalleAlumnoModeloLista;
    }
}
