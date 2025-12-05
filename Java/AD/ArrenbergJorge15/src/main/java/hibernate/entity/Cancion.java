package hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "canciones")
public class Cancion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "anyo", nullable = false)
	private int anyo;

	@ManyToOne
	@JoinColumn(name = "cantante_id", nullable = false)
	private Cantante cantante;

	// Default constructor (required by Hibernate)
	public Cancion() {
	}

	// Constructor with parameters
	public Cancion(String titulo, int anyo, Cantante cantante) {
		this.titulo = titulo;
		this.anyo = anyo;
		this.cantante = cantante;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public Cantante getCantante() {
		return cantante;
	}

	public void setCantante(Cantante cantante) {
		this.cantante = cantante;
	}

	@Override
	public String toString() {
		return "Cancion [id=" + id + ", titulo=" + titulo + ", anyo=" + anyo + ", cantante="
				+ (cantante != null ? cantante.getNombre() : "Anónimo") + "]";
	}
}