package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

import java.util.Objects;

public class MotivoBloqueoDto implements Comparable<MotivoBloqueoDto> {
	
	private short id;
	private String descripcion;
	
	public MotivoBloqueoDto() {
		
	}

	public MotivoBloqueoDto(MotivoBloqueoEntidad motivoBloqueoEntidad) {
		id = motivoBloqueoEntidad.getId();
		descripcion = motivoBloqueoEntidad.getDescripcion();
	}
	
	public MotivoBloqueoDto(short id) {
		this.id = id;
	}
	
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int compareTo(MotivoBloqueoDto motivoBloqueoDto) {
		return (int)Short.compare(id, motivoBloqueoDto.id);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MotivoBloqueoDto that = (MotivoBloqueoDto) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MotivoBloqueoDto{");
		sb.append("id=").append(id);
		sb.append(", descripcion='").append(descripcion).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
