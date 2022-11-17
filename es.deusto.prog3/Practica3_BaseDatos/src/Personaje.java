import java.time.Instant;

public class Personaje {

	private String nombre;
	public enum sexo {HOMBRE, MUJER, OTRO};
	Instant fechaactual;
	private int nivel;
	long identificador= 0;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Instant getFechaactual() {
		return fechaactual;
	}
	public void setFechaactual(Instant fechaactual) {
		this.fechaactual = fechaactual;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public long getIdentificador() {
		return identificador;
	}
	public void setIdentificador(long identificador) {
		this.identificador = identificador;
		this.identificador++;
	}
	public Personaje(String nombre, Instant fechaactual, int nivel, long identificador) {
		super();
		this.nombre = nombre;
		this.fechaactual = Instant.now();
		this.nivel = nivel;
		this.identificador = identificador++;
	}
	public Personaje() {
		super();
		this.fechaactual = Instant.now();
	}

}
