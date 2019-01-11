package tecolotl.administracion.dto;

import tecolotl.administracion.persistencia.entidad.MotivoBloqueoEntidad;

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
}
