package mx.com.esquadron.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persona", schema = "prueba")
public class EntidadPersona {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "edad")
	private int edad;
	
	@Column(name = "direccion")
	private String direccion;
	
	public EntidadPersona() {
	}
	
	public EntidadPersona(int id) {
		this.id = id;
	}

	public EntidadPersona(int id, String nombre, int edad, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.direccion = direccion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EntidadPersona [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", edad=");
		builder.append(edad);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append("]");
		return builder.toString();
	}

}
