package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.AlumnoEntidad;
import tecolotl.nucleo.modelo.PersonaModelo;

import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

public class AlumnoModelo extends PersonaModelo {

    private UUID id;
    private Date nacimiento;
    private String correoPadreFamilia;
    private NivelLenguajeModelo nivelLenguajeModelo;

    public AlumnoModelo() {
        nivelLenguajeModelo = new NivelLenguajeModelo();
    }

    public AlumnoModelo(UUID id) {
        this.id = id;
    }

    public AlumnoModelo(AlumnoEntidad alumnoEntidad) {
        super(alumnoEntidad);
        this.id = alumnoEntidad.getId();
        this.nacimiento = alumnoEntidad.getNacimiento();
        this.correoPadreFamilia = alumnoEntidad.getCorreoPadreFamilia();
        this.nivelLenguajeModelo = new NivelLenguajeModelo(alumnoEntidad.getNivelLenguajeEntidad());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getCorreoPadreFamilia() {
        return correoPadreFamilia;
    }

    public void setCorreoPadreFamilia(String correoPadreFamilia) {
        this.correoPadreFamilia = correoPadreFamilia;
    }

    public NivelLenguajeModelo getNivelLenguajeModelo() {
        return nivelLenguajeModelo;
    }

    public void setNivelLenguajeModelo(NivelLenguajeModelo nivelLenguajeModelo) {
        this.nivelLenguajeModelo = nivelLenguajeModelo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AlumnoModelo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nacimiento=" + nacimiento)
                .add("correoPadreFamilia='" + correoPadreFamilia + "'")
                .add("nivelLenguajeModelo=" + nivelLenguajeModelo)
                .add("super" + super.toString())
                .toString();
    }
}
