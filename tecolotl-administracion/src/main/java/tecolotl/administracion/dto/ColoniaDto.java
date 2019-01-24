package tecolotl.administracion.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tecolotl.administracion.persistencia.entidad.ColoniaEntidad;

/**
 * Objecto que representa los datos de una colonia
 * @author Antonio Francisco Alonso Valerdi
 * @since 0.1
 */
public class ColoniaDto {

	private Integer id;
	private String codigoPostal;
	private String nombre;
	
	public ColoniaDto() {
	
	}

	public ColoniaDto(ColoniaEntidad coloniaEntidad) {
		this.id = coloniaEntidad.getId();
		this.codigoPostal = coloniaEntidad.getCodigoPostal();
		this.nombre = coloniaEntidad.getNombre();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ColoniaDto that = (ColoniaDto) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ColoniaDto{");
		sb.append("id=").append(id);
		sb.append(", codigoPostal='").append(codigoPostal).append('\'');
		sb.append(", nombre='").append(nombre).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
