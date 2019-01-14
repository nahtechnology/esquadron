package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.TipoContactoEntidad;

import java.util.Objects;

public class TipoContactoDto implements Comparable<TipoContactoDto>{

    private int id;
    private String descripcion;

    public TipoContactoDto() {
    }

    public TipoContactoDto(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public TipoContactoDto(TipoContactoEntidad tipoContactoEntidad) {
        id = tipoContactoEntidad.getId();
        descripcion = tipoContactoEntidad.getDescripcion();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int compareTo(TipoContactoDto tipoContactoDto) {
        return Integer.compare(id, tipoContactoDto.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoContactoDto that = (TipoContactoDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
