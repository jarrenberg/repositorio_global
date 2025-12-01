package hibernate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cantantes")
public class Cantante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "pais", nullable = false)
	private String pais;

	@OneToMany(mappedBy = "cantante", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Cancion> repertorio;

	// Default constructor (required by Hibernate)
	public Cantante() {
		this.repertorio = new ArrayList<>();
	}

	// Constructor with parameters
	public Cantante(String nombre, String pais) {
		this.nombre = nombre;
		this.pais = pais;
		this.repertorio = new ArrayList<>();
	}

	// Getters and Setters
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Cancion> getRepertorio() {
		return repertorio;
	}

	public void setRepertorio(List<Cancion> repertorio) {
		this.repertorio = repertorio;
		// Bidirectional relationship maintenance
		if (repertorio != null) {
			for (Cancion cancion : repertorio) {
				cancion.setCantante(this);
			}
		}
	}

	// Convenience method to add a song
	public void agregarCancion(Cancion cancion) {
		if (repertorio == null) {
			repertorio = new ArrayList<>();
		}
		cancion.setCantante(this);
		repertorio.add(cancion);
	}

	// Convenience method to remove a song
	public void eliminarCancion(Cancion cancion) {
		if (repertorio != null) {
			repertorio.remove(cancion);
			cancion.setCantante(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cantante other = (Cantante) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(pais, other.pais);
	}

	@Override
	public String toString() {
		String canciones = "";
		for (Cancion c : repertorio) {
			canciones = String.format("%s\n\t%s", canciones, c.toString());
		}
		String resultado = "";
		if (repertorio.size() == 0) {
			resultado = "Cantante [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", repertorio= 0 canciones]";
		} else {
			resultado = "Cantante [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", repertorio:"+canciones;
		}
		return resultado;
	}
}