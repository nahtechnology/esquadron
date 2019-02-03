package tecolotl.administracion.dto;

import java.util.Objects;

public class CatalogoDto {

    private Short id;
    private String descripcion;

    public CatalogoDto() {
    }

    public CatalogoDto(Short id) {
        this.id = id;
    }

    public CatalogoDto(Short id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogoDto that = (CatalogoDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CatalogoDto{");
        sb.append("id=").append(id);
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
