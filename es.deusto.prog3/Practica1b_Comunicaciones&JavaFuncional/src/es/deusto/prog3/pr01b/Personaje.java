package es.deusto.prog3.pr01b;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Personaje {
	private ImageIcon imagen;
	private String nombre = "sinNombre";
	private int posicionX = 200;
	private int posicionY = 150;
	private String mensaje = "sinMensaje";
	private int tamanyoX = 60;
	private int tamanyoY = 60;
	
	public Personaje() {
	}
	
	public Personaje(ImageIcon imagen, String nombre, int posicionX, int posicionY, String mensaje, int tamanyoX,
			int tamanyoY) {
		super();
		this.nombre = nombre;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.mensaje = mensaje;
		this.tamanyoX = tamanyoX;
		this.tamanyoY = tamanyoY;
		this.imagen = recomposiocionImagen(imagen);
	}

	private ImageIcon recomposiocionImagen(ImageIcon imagen) {
		Image imagenDePaso = imagen.getImage(); // transform it 
    	Image newimg = imagenDePaso.getScaledInstance(tamanyoX, tamanyoY,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    	imagen = new ImageIcon(newimg); 
		return imagen;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = recomposiocionImagen(imagen);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getTamanyoX() {
		return tamanyoX;
	}

	public void setTamanyoX(int tamanyoX) {
		this.tamanyoX = tamanyoX;
	}

	public int getTamanyoY() {
		return tamanyoY;
	}

	public void setTamanyoY(int tamanyoY) {
		this.tamanyoY = tamanyoY;
	}
	
	@Override
	public String toString() {
		return "Personaje [imagen=" + imagen + ", nombre=" + nombre + ", posicionX=" + posicionX + ", posicionY="
				+ posicionY + ", mensaje=" + mensaje + ", tamanyoX=" + tamanyoX + ", tamanyoY=" + tamanyoY + "]";
	}

}
