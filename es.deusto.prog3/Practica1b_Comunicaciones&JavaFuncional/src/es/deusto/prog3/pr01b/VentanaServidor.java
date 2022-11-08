package es.deusto.prog3.pr01b;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class VentanaServidor extends JFrame {
	private static String HOST = "localhost";  // IP de conexión para la comunicación
	private static int PUERTO = 4000;          // Puerto de conexión
	
	JLabel lEstado = new JLabel( " " );
	JTextArea taMensajes = new JTextArea( 10, 1 );
    boolean finComunicacion = false;
	// *VARIOS CLIENTES*
	// Como el servidor va a gestionar varios clientes hacemos una lista de sockets en lugar de solo uno, y una lista de salidas para mensajes de difusión
    ArrayList<Socket> lSockets = new ArrayList<>(); 
    ArrayList<PrintWriter> lSalidas = new ArrayList<>();
    int numCliente = 0;  // Añadimos un número de cliente para saber cuántos se conectan
	public VentanaServidor() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 400, 300 );
		setLocation( 400, 0 );
		setTitle( "Ventana servidor" );
		taMensajes.setEditable( false );
		getContentPane().add( new JScrollPane(taMensajes), BorderLayout.CENTER );
		getContentPane().add( lEstado, BorderLayout.SOUTH );
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// *VARIOS CLIENTES*
					// Se cierran todos los sockets abiertos 
					for (Socket socket : lSockets) socket.close();
				} catch (IOException e1) {
		    		lEstado.setText("Error en servidor: " + e1.getMessage());
				}
				finComunicacion = true;
			}
		});
	}
	
	private static VentanaServidor vs;
	public static void main(String[] args) {
		vs = new VentanaServidor();
		vs.setVisible( true );
		(new Thread() {
			@Override
			public void run() {
				vs.lanzaServidor();
			}
		}).start();
	}
	
	public void lanzaServidor() {
		// *VARIOS CLIENTES*
		// Como el servidor va a gestionar varios clientes, en lugar de abrir solo una conexión, abre repetidamente conexiones hasta final
		try(ServerSocket serverSocket = new ServerSocket( PUERTO )) {
			serverSocket.setSoTimeout( 5000 );  // Para que haya un timeout en el accept - por si cerramos la aplicación para que no se quede esperando de forma infinita
			while (!finComunicacion) {
				try {
					Socket socket = serverSocket.accept(); // Se queda esperando a una conexión con timeout
					// *VARIOS CLIENTES*
					// Cada vez que un cliente se conecta, se genera un HILO que hace la comunicación (la lectura) con ese cliente. Y el servidor sigue ejecutando para esperar a otro cliente
					lSockets.add( socket );
					numCliente++;
					Thread t = new Thread ( new Runnable() { @Override public void run() {
						int numC = numCliente;
						try {
							lEstado.setText( "Cliente " + numC + " conectado" );
							
//							ObjectInputStream inputObjetoDesdeCliente = new ObjectInputStream(socket.getInputStream());  // Canal de entrada de socket (leer del cliente)
//				    		ObjectOutputStream outputObjetoACliente = new ObjectOutputStream(socket.getOutputStream());  // Canal de salida de socket (escribir al cliente)
							
				    		BufferedReader inputDesdeCliente = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							PrintWriter outputACliente = new PrintWriter(socket.getOutputStream(), true);
							
							lSalidas.add( outputACliente );  // Para mensajes de difusión
							while(!finComunicacion) {  // ciclo de lectura desde el cliente hasta que acabe la comunicación
//								try {
//									System.out.println("objeto recibido");
//									Object objRecibido = inputObjetoDesdeCliente.readObject();
//								} catch (ClassNotFoundException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//								try {
//									Personaje personaRecibida = (Personaje) inputObjetoDesdeCliente.readObject();
//									System.out.println(personaRecibida.toString());
//								} catch (ClassNotFoundException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//								}
								
								String textoRecibido = inputDesdeCliente.readLine();  // Ojo: bloqueante (este hilo se queda esperando)
								if(textoRecibido.equals("fin")) {
									break;
								}
								lEstado.setText( "Recibido de cliente " + numC + ": [" + textoRecibido + "]" );
								taMensajes.append( "[" + numC + "] " + textoRecibido + "\n" );
								taMensajes.setSelectionStart( taMensajes.getText().length() );
								if (textoRecibido.equals("hola")) {
									for (PrintWriter outputCl : lSalidas) {
										if(outputACliente != outputCl) {
											outputCl.println( "El cliente " + numC + " saluda a todos." );
										}
									}
								} else if(textoRecibido.equals("hora")){
									outputACliente.println("Hora: " + (new Date()) );
								} else {
									outputACliente.println("Recibido: [" + textoRecibido + "]" );
								}
							}
							lEstado.setText( "El cliente " + numC + " se ha desconectado." );
							socket.close();
							lSockets.remove( socket );
							lSalidas.remove( outputACliente );
						} catch(IOException e) {
							if (finComunicacion) {
								System.out.println( "Cerrada comunicación con cliente " + numC + " por cierre de servidor." );
							} else {
								e.printStackTrace();
							}
						}
					} } );
					t.start();
				} catch (SocketTimeoutException e) {
					// Timeout en socket servidor - se reintenta (en el mismo while)
				}
			}
		} catch(IOException e) {
			lEstado.setText("Error en servidor: " + e.getMessage());
		}

	}
}
