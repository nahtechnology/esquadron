package tecolotl.alumno.Modelo;

import tecolotl.alumno.entidad.AlumnoEntidad;

public class AlumnoModelo {

    private Integer id;
    private String nivelLenguaje;

    public AlumnoModelo() {
    }

    public AlumnoModelo(AlumnoEntidad alumnoEntidad) {
        this.id = alumnoEntidad.getId();
        this.nivelLenguaje = alumnoEntidad.getNivelLenguajeEntidad().getValor();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }
}
