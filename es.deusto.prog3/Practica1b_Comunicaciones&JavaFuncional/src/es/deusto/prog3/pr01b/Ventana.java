package es.deusto.prog3.pr01b;

import java.awt.BorderLayout;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
	public class Ventana extends JFrame {
		private static ObjectOutputStream outputObjetoAServer;
		private static String HOST = "localhost";;  // IP de conexión para la comunicación
		private static int PUERTO = 4000;          // Puerto de conexión
		private JTextArea taEstado = new JTextArea();
		private JTextField tfMensaje = new JTextField( "Introduce tu mensaje y pulsa <Enter>" );
//		private PrintWriter outputAServer;
        private boolean finComunicacion = false;
        private String nombre;
        private JLabel lPersonaje = new JLabel();
        private Personaje personaje = new Personaje();
        
        public static void main(String[] args) {
        	// Ejecucion principal
        	Ventana vc = new Ventana();
    		vc.setVisible( true );
    		(new Thread() {
    			@Override
    			public void run() {
    				vc.lanzaCliente();
    			}
    		}).start();
    		// *VARIOS CLIENTES* Lanzamos más clientes tras pausita
//    		try {Thread.sleep(2000); } catch (InterruptedException e) {} 
//    		VentanaCliente vc2 = new VentanaCliente( "B" );
//    		vc2.setLocation( vc2.getLocation().x, vc2.getLocation().y + 200 );  // Un poco más abajo
//    		vc2.setVisible( true );
//    		(new Thread() {
//    			@Override
//    			public void run() {
//    				vc2.lanzaCliente();
//    			}
//    		}).start();
//    		try {Thread.sleep(2000); } catch (InterruptedException e) {} 
//    		VentanaCliente vc3 = new VentanaCliente( "C" );
//    		vc3.setLocation( vc3.getLocation().x, vc3.getLocation().y + 400 );  // Más abajo aún
//    		vc3.setVisible( true );
//    		(new Thread() {
//    			@Override
//    			public void run() {
//    				vc3.lanzaCliente();
//    			}
//    		}).start();
    	}
        
        public void inicioConfiguracion() {
        	
        	// Obteniendo Datos
			Object servidor = JOptionPane.showInputDialog(
					null, 
					"Introduce servidor al que conectarse:",
					"Elije servidor",
					JOptionPane.QUESTION_MESSAGE,null,null, "localhost");
	    	if(servidor == null) {
				System.exit(0);
			}
	    	
	    	Object puerto = JOptionPane.showInputDialog(
					null, 
					"Introduce puerto de comunicaci�n:",
					"Elije puerto",
					JOptionPane.QUESTION_MESSAGE,null,null, "4000");
	    	
	    	Object nombrePersonaje = JOptionPane.showInputDialog(
					null, 
					"Nombre de tu personaje",
					"Elije nombre",
					JOptionPane.QUESTION_MESSAGE,null,null, "<Elije nombre>");
			    	
	    	
	    	Object[] possibleValues= { "pok01", "pok02", "pok03", "pok04", "pok05", "pok06", "pok07", "pok08" };
	    	Object imagenPersonaje = JOptionPane.showInputDialog(null,
	    	"Icono de tu personaje", "Elije icono",
	    	JOptionPane.QUESTION_MESSAGE, null,
	    	possibleValues, possibleValues[0]);
	    	
	    	// Ejecutando cambios

	    	HOST = servidor + "";
	    	PUERTO = Integer.valueOf((puerto + ""));
	    	
	    	personaje.setImagen(new ImageIcon("src/es/deusto/prog3/pr01b/img/" + imagenPersonaje + ".png"));
	    	personaje.setNombre((String) nombrePersonaje);	
		}
        
		public Ventana() {
			inicioConfiguracion();
			
			// Configuracion ventana
			this.nombre = personaje.getNombre();
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 0, 0 );
			setTitle( "Ventana cliente " + nombre + " - 'fin' acaba y 'hola' saluda" );
			taEstado.setEditable( false );
			
			// Anyadiendo elementos
			getContentPane().add( tfMensaje, BorderLayout.NORTH );
			getContentPane().add( taEstado, BorderLayout.CENTER );
			taEstado.add(lPersonaje);
			
			// Configuracion label
			lPersonaje.setHorizontalTextPosition(JLabel.CENTER);
			lPersonaje.setVerticalTextPosition(JLabel.CENTER);
	    	lPersonaje.setIcon(personaje.getImagen());
	    	lPersonaje.setBounds(personaje.getPosicionX(), personaje.getPosicionY(), personaje.getTamanyoX(), 			personaje.getPosicionY());
	    	
	    	// Listeners
			taEstado.addKeyListener(new KeyListener() {
				
				public void keyTyped(KeyEvent e) {
				}
				@Override
				public void keyPressed(KeyEvent e) {
					
					System.out.println(lPersonaje.getLocation());
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						personaje.setPosicionY(personaje.getPosicionY()-4);
	                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	                	personaje.setPosicionY(personaje.getPosicionY()+4);
	                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	                	personaje.setPosicionX(personaje.getPosicionX()-4);
	                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						personaje.setPosicionX(personaje.getPosicionX()+4);
	                }
					lPersonaje.setLocation(personaje.getPosicionX(), personaje.getPosicionY());
					System.out.println(personaje.getPosicionX() + " " + personaje.getPosicionY());
					System.out.println(lPersonaje.getLocation());
					
//					try {
//						outputObjetoAServer.writeObject(personaje.toString());
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					
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
					
					if (tfMensaje.getText().equals("fin")) {
						finComunicacion = true;
					}
					personaje.setMensaje(tfMensaje.getText());
					lPersonaje.setText(personaje.getMensaje());
					personaje.setMensaje(tfMensaje.getText());
					lPersonaje.setText(personaje.getMensaje());
					try {
						outputObjetoAServer.writeObject(tfMensaje.getText() );
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					tfMensaje.setText( "" );
				}
			});
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					finComunicacion = true;
				}
			});
		}
		
		public void lanzaCliente() {
	        try (Socket socket = new Socket( HOST, PUERTO )) {
	           
				ObjectInputStream inputObjetoDesdeServer = new ObjectInputStream(socket.getInputStream());  // Canal de entrada de socket (leer del cliente)
				outputObjetoAServer = new ObjectOutputStream(socket.getOutputStream());  // Canal de salida de socket (escribir al cliente)

	        	
//	            BufferedReader inputDesdeServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//	            outputAServer = new PrintWriter(socket.getOutputStream(), true);
//            	flujoOut.writeObject( personaje);
//            	System.out.println("personaje enviado" + personaje.toString());
//            	System.out.println("personaje enviado");
	            do { // Ciclo de lectura desde el servidor hasta que acabe la comunicación

	            	
					try {
						Object feedback = inputObjetoDesdeServer.readObject();
						if (feedback!=null) {
		            		taEstado.append( feedback.toString() + "\n" );
		            	} else {  // Comunicación cortada por el servidor o por error en comunicación
		            		finComunicacion = true;
		            	}
					} catch (ClassNotFoundException e) {} 
	     
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