package tecolotl.administracion.sesion;

import tecolotl.administracion.modelo.AlumnoBusquedaModelo;
import tecolotl.administracion.modelo.GrupoBusquedaModelo;
import tecolotl.administracion.modelo.escuela.EscuelaAlumnoModelo;
import tecolotl.administracion.persistencia.vista.AlumnoEscuelaVista;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class BusquedaEscuelaSesionBean {

    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager entityManager;

    public List<EscuelaAlumnoModelo> busca(@NotNull String claveCentroTrabajo, @NotNull String nombre) {
        List<EscuelaAlumnoModelo> escuelaAlumnoModeloLista = new ArrayList<>();
        Query query = entityManager.createNativeQuery("SELECT e.clave_centro_trabajo,a.nombre,a.apellido_paterno,a.apellido_materno," +
                "p.apodo,g.grado,g.grupo,ce.descripcion FROM administracion.escuela e JOIN profesor.ciclo_escolar ce ON e.clave_centro_trabajo = ce.id_escuela JOIN " +
                "profesor.grupo g on ce.inicio = g.inicio and ce.fin = g.fin and ce.id_escuela = g.id_escuela JOIN profesor.profesor p ON g.id_profesor = p.id JOIN " +
                "profesor.grupo_alumno ga on g.id = ga.id_grupo JOIN alumno.alumno a ON ga.id_alumno = a.id WHERE e.clave_centro_trabajo = ? AND LOWER (a.nombre) LIKE ?");
        query.setParameter(1, claveCentroTrabajo);
        query.setParameter(2, "%".concat(nombre.toLowerCase()).concat("%"));
        List<Object[]> lista = (List<Object[]>)query.getResultList();
        for (Object[] objecto : lista) {
            EscuelaAlumnoModelo escuelaAlumnoModelo = new EscuelaAlumnoModelo();
            escuelaAlumnoModelo.setNombre((String)objecto[1]);
            escuelaAlumnoModelo.setApellidoPaterno((String)objecto[2]);
            escuelaAlumnoModelo.setApellidoMaterno((String)objecto[3]);
            escuelaAlumnoModelo.setProfesorApodo((String)objecto[4]);
            escuelaAlumnoModelo.setGrado((Short)objecto[5]);
            escuelaAlumnoModelo.setGrupo((String)objecto[6]);
            escuelaAlumnoModelo.setCicloEscolar((String)objecto[7]);
            escuelaAlumnoModeloLista.add(escuelaAlumnoModelo);
        }
        return escuelaAlumnoModeloLista;
    }

    /**
     * Busca todos los alumnos de una escuela, agrupados por grupos.
     * @param claveCentroTrabajo Clave centro de trabajo de la escuela a recuperar los alunno
     * @return Mapa con los datos de los alumnos. como llave primaria {@link GrupoBusquedaModelo} y los datos del alumno en uan lista de objetos {@link AlumnoBusquedaModelo}
     */
    public Map<GrupoBusquedaModelo, List<AlumnoBusquedaModelo>> buscaAlumno(@NotNull String claveCentroTrabajo) {
        Map<GrupoBusquedaModelo, List<AlumnoBusquedaModelo>> mapa;
        TypedQuery<AlumnoEscuelaVista> typedQuery = entityManager.createNamedQuery("AlumnoEscuelaVista.buscaPorEscuela", AlumnoEscuelaVista.class);
        typedQuery.setParameter("idEscuela", claveCentroTrabajo);
        List<AlumnoEscuelaVista> alumnoEscuelaVistaLista = typedQuery.getResultList();
        mapa = alumnoEscuelaVistaLista.stream().collect(Collectors.groupingBy(GrupoBusquedaModelo::new, Collectors.mapping(aev ->  aev.getIdAlumno() == null ? null : new AlumnoBusquedaModelo(aev) , Collectors.toList())));
        for (Map.Entry<GrupoBusquedaModelo, List<AlumnoBusquedaModelo>> entry : mapa.entrySet()) {
            entry.getValue().removeIf(Objects::isNull);
        }
        return mapa;
    }

}
