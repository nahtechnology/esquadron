package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.EstadoEntidad;

import java.util.Objects;

public class EstadoDto {

    private String id;
    private String nombre;

    public EstadoDto(EstadoEntidad estadoEntidad) {
        id = estadoEntidad.getId();
        nombre = estadoEntidad.getNombre();
    }

    public EstadoDto(String id) {
        this.id = id;
    }

    public EstadoDto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoDto estadoDto = (EstadoDto) o;
        return id.equals(estadoDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EstadoDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
