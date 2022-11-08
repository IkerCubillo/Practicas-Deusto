package es.deusto.prog3.pr01b;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Pruebas {
	
	public static void main(String[] args) {
		VentanaCliente vc = new VentanaCliente( "A" );
		vc.setVisible( true );
		(new Thread() {
			@Override
			public void run() {
				vc.lanzaCliente();
			}
		}).start();
		// *VARIOS CLIENTES* Lanzamos más clientes tras pausita
//		try {Thread.sleep(2000); } catch (InterruptedException e) {} 
//		VentanaCliente vc2 = new VentanaCliente( "B" );
//		vc2.setLocation( vc2.getLocation().x, vc2.getLocation().y + 200 );  // Un poco más abajo
//		vc2.setVisible( true );
//		(new Thread() {
//			@Override
//			public void run() {
//				vc2.lanzaCliente();
//			}
//		}).start();
//		try {Thread.sleep(2000); } catch (InterruptedException e) {} 
//		VentanaCliente vc3 = new VentanaCliente( "C" );
//		vc3.setLocation( vc3.getLocation().x, vc3.getLocation().y + 400 );  // Más abajo aún
//		vc3.setVisible( true );
//		(new Thread() {
//			@Override
//			public void run() {
//				vc3.lanzaCliente();
//			}
//		}).start();
	}

	@SuppressWarnings("serial")
	public static class VentanaCliente extends JFrame {
		private static String HOST = "localhost";  // IP de conexión para la comunicación
		private static int PUERTO = 4000;          // Puerto de conexión
		private JTextArea taEstado = new JTextArea();
//		private JPanel pCentral = new JPanel();
		private JTextField tfMensaje = new JTextField( "Introduce tu mensaje y pulsa <Enter>" );
		private PrintWriter outputAServer;
        private boolean finComunicacion = false;
        private String nombre;
        private JLabel lPersonaje = new JLabel();
        
		public VentanaCliente( String nombre ) {
			this.nombre = nombre;
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 0, 0 );
			setTitle( "Ventana cliente " + nombre + " - 'fin' acaba y 'hola' saluda" );
			taEstado.setEditable( false );
			getContentPane().add( tfMensaje, BorderLayout.NORTH );
//			getContentPane().add( pCentral, BorderLayout.CENTER );
			getContentPane().add( taEstado, BorderLayout.CENTER );
//			pCentral.setFocusable(true);
			
			
			lPersonaje.setHorizontalTextPosition(JLabel.CENTER);
			lPersonaje.setVerticalTextPosition(JLabel.CENTER);	    	
	    	
	    	// Creaci�n Personaje 
	    	ArrayList<Integer> hi = new ArrayList<>();
	    	hi.add(3);
	    	hi.add(2);
			
			ImageIcon imageIcon = new ImageIcon("src/es/deusto/prog3/pr01b/img/" + "pok01" + ".png"); // load the image to a imageIcon
	    	Image image = imageIcon.getImage(); // transform it 
	    	Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	    	imageIcon = new ImageIcon(newimg); 
	    	
	    	lPersonaje.setIcon(imageIcon);
	    	
	    	lPersonaje.setBounds(200, 10, 60, 60);
	    	taEstado.add(lPersonaje);
	    	
	    	System.out.println(lPersonaje.getX());
	    	System.out.println(lPersonaje.getY());
	    	
			
//	    	pCentral.updateUI();
			taEstado.addKeyListener(new KeyListener() {
				
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
					
					System.out.println(lPersonaje.getLocation());
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						lPersonaje.setLocation(lPersonaje.getX(),lPersonaje.getY()-4);
	                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						lPersonaje.setLocation(lPersonaje.getX(),lPersonaje.getY()+4);
	                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						lPersonaje.setLocation(lPersonaje.getX()-4,lPersonaje.getY());
	                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						lPersonaje.setLocation(lPersonaje.getX()+4,lPersonaje.getY());
	                }
					getContentPane().revalidate();
				}

				@Override
				public void keyReleased(KeyEvent e) {
				}
			});
			
			tfMensaje.addFocusListener( new FocusAdapter() { // Selecciona todo el texto del cuadro en cuanto se le da el foco del teclado
				@Override
				public void focusGained(FocusEvent e) {
					tfMensaje.selectAll();
				}
			});
			tfMensaje.addActionListener( new ActionListener() { // Evento de <enter> de textfield
				@Override
				public void actionPerformed(ActionEvent e) {

					lPersonaje.setText(tfMensaje.getText());
					outputAServer.println( tfMensaje.getText() );
					if (tfMensaje.getText().equals("fin")) {
						finComunicacion = true;
					}
					tfMensaje.setText( "" );
				}
			});
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					outputAServer.println( "fin" );
					finComunicacion = true;
				}
			});
	}
		
		
		
		public void lanzaCliente() {
			try (Socket socket = new Socket( HOST, PUERTO )) {
	            BufferedReader inputDesdeServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            outputAServer = new PrintWriter(socket.getOutputStream(), true);
	            do { // Ciclo de lectura desde el servidor hasta que acabe la comunicación
	            	String feedback = inputDesdeServer.readLine();  // Ojo-bloqueante. Devuelve mensaje de servidor o null cuando se cierra la comunicación
	            	if (feedback!=null) {
	            		taEstado.append( feedback + "\n" );
	            	} else {  // Comunicación cortada por el servidor o por error en comunicación
	            		finComunicacion = true;
	            	}
	            } while (!finComunicacion);
	        } catch (IOException e) {
            	taEstado.append( "Error en cliente: " + e.getMessage() + "\n" );
	        }
	        taEstado.append( "Fin de proceso de cliente.\n" );
	        System.out.println( "Cerrando ventana cliente " + nombre + " en 2 segundos..." );
	        if (finComunicacion) {
	        	try { Thread.sleep( 2000 ); } catch (InterruptedException e) {}
	        	dispose();
	        }
	    }
	}

}
