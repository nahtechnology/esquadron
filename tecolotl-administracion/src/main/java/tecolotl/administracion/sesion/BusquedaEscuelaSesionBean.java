package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.escuela.EscuelaAlumnoModelo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BusquedaEscuelaSesionBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EscuelaAlumnoModelo> busca(@NotNull String claveCentroTrabajo, @NotNull String nombre) {
        List<EscuelaAlumnoModelo> escuelaAlumnoModeloLista = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT e.clave_centro_trabajo,a.nombre,a.apellido_paterno,a.apellido_materno," +
                "p.apodo,g.grado,g.grupo,ce.descripcion FROM administracion.escuela e JOIN profesor.ciclo_escolar ce ON e.clave_centro_trabajo = ce.id_escuela JOIN " +
                "profesor.grupo g on ce.inicio = g.inicio and ce.fin = g.fin and ce.id_escuela = g.id_escuela JOIN profesor.profesor p ON g.id_profesor = p.id JOIN " +
                "profesor.grupo_alumno ga on g.id = ga.id_grupo JOIN alumno.alumno a ON ga.id_alumno = a.id WHERE e.clave_centro_trabajo = ? AND a.nombre LIKE ?");
        query.setParameter(1, claveCentroTrabajo);
        query.setParameter(2, "%".concat(nombre).concat("%"));
        List<Object[]> lista = (List<Object[]>)query.getResultList();
        for (Object[] objecto : lista) {
            EscuelaAlumnoModelo escuelaAlumnoModelo = new EscuelaAlumnoModelo();
            escuelaAlumnoModelo.setNombre((String)objecto[0]);
            escuelaAlumnoModelo.setApellidoPaterno((String)objecto[1]);
            escuelaAlumnoModelo.setApellidoMaterno((String)objecto[2]);
            escuelaAlumnoModelo.setProfesorApodo((String)objecto[3]);
            escuelaAlumnoModelo.setGrado((String)objecto[4]);
            escuelaAlumnoModelo.setGrupo((String)objecto[5]);
            escuelaAlumnoModelo.setCicloEscolar((String)objecto[6]);
            escuelaAlumnoModeloLista.add(escuelaAlumnoModelo);
        }
        return escuelaAlumnoModeloLista;
    }

}
