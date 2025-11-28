package hibernate.entity;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "agenda")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private int codigo;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "email")
	private String email;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "altura")
	private int altura;

	public Persona(String nombre, String direccion, String email, String telefono, int altura) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.telefono = telefono;
		this.altura = altura;
	}

	public Persona() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "Persona [codigo=" + codigo + ", nombre=" + nombre + ", direccion=" + direccion + ", email=" + email
				+ ", telefono=" + telefono + ", altura=" + altura + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(altura, codigo, direccion, email, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return altura == other.altura && codigo == other.codigo && Objects.equals(direccion, other.direccion)
				&& Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(telefono, other.telefono);
	}

}