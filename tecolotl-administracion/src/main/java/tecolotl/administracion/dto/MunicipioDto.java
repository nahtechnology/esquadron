package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.MunicipioEntidad;

public class MunicipioDto {

    private Integer id;
    private String nombre;

    public MunicipioDto() {
    }

    public MunicipioDto(Integer id) {
        this.id = id;
    }

    public MunicipioDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MunicipioDto(MunicipioEntidad municipioEntidad) {
        this.id = municipioEntidad.getId();
        this.nombre = municipioEntidad.getNombre();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
