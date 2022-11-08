package clasesVisuales;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import clasesContenedoras.HistoriaGrandSlams;

public class VentanaGrandSlam{
	JTabbedPane tpCentral = new JTabbedPane();
	public static void main(String[] args) {
		HistoriaGrandSlams hgs = new HistoriaGrandSlams();
		
//		Creacion objetos
		JFrame ventanaPrincipal = new JFrame();
		
		
		
		JPanel pestanaResultados = new JPanel();
		JPanel pestanaTenistas = new JPanel();
		JPanel pestanaTorneos = new JPanel();
		
		JPanel pSur = new JPanel();	
		
		JButton bEvolucionRanking = new JButton("Evoulucion Ranking");
		JButton bAnyadir = new JButton("Anyadir");	
		JButton bGuardar = new JButton("Guardar");
		
		JTable tResultados = new JTable();
		JTable tTorneos = new JTable();
		
//		Configuracion ventana
		
		ventanaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventanaPrincipal.setSize(600,400);
		ventanaPrincipal.setTitle("Datos de Gran Slam");
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
//		Establecer componentes
		String[] columnNames = {"Nombre", "Nacionalidad", "Num. Victorias GS",};
	    Object[][] datos = {
	        {"Juan", 25, false},
	        {"Sonia", 33, true},
	        {"Pedro", 42, false}};

	    DefaultTableModel dtm = new DefaultTableModel(datos, columnNames);
	    final JTable tTenistas = new JTable(dtm);
		
		
//		Añadir componentes
		ventanaPrincipal.add(tpCentral,BorderLayout.CENTER);
		ventanaPrincipal.add(pSur,BorderLayout.SOUTH);
		
		tpCentral.addTab("Resultados", pestanaResultados);
		tpCentral.addTab("Tenistas", pestanaTenistas);
		tpCentral.addTab("Torneos", pestanaTorneos);
		
		pestanaResultados.setLayout(new FlowLayout());
		pestanaResultados.add(bGuardar);
		pestanaResultados.add(bAnyadir);
		
		pSur.add(bEvolucionRanking);
		
		pestanaResultados.add(tResultados);
		pestanaTenistas.add(tTenistas);
		pestanaTorneos.add(tTorneos);
		
//		Acciones
		
		tpCentral.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				System.out.println(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e);
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
					System.out.println("cntrl + N");
					seleccionarAnyo();
                } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("cntrl + S");
					guardar();
                }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(e);
			}
		});
		
		bEvolucionRanking.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Boton Evolucion Ranking");
				
				int anyoInicial = seleccionarAnyoInicial();
				if(anyoInicial != 0) {
					int anyoFinal = seleccionarAnyoFinal();
					if(anyoFinal != 0) {
						VentanaClasificacion vc = new VentanaClasificacion(anyoInicial, anyoFinal);
						vc.setVisible(true);
					}
				}
			}
		});
		
		bAnyadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Boton anyadir pulsado");
				
				seleccionarAnyo();
			}
		});
		
		bGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Boton guardar pulsado");
				
				guardar();
			}
		});
		
	}
	
	private static void guardar() {
		
		
	}

	private static void seleccionarAnyo() {
		System.out.println("Seleccionando Anyo");
		
		Object seleccionAnyo = JOptionPane.showInputDialog(
				null, 
				"Anyo del torneo",
				"Nuevo resultado",
				JOptionPane.QUESTION_MESSAGE);
		
		System.out.println("El usuario ha elegido "+seleccionAnyo);
		
		if(seleccionAnyo == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarTorneo();
		}
	}
	
	private static void seleccionarTorneo() {
		System.out.println("Seleccionando Torneo");
		
		Object seleccionTorneo = JOptionPane.showInputDialog(
		   null,
		   "Torneo",
		   "Nuevo resultado",
		   JOptionPane.QUESTION_MESSAGE,
		   null,
		   new Object[] { "Open Australia", "Roland Garros", "Wimbledon", "Open USA" }, 
		   "Open Australia");
		
		System.out.println("El usuario ha elegido "+seleccionTorneo);
		
		if(seleccionTorneo == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarTenista();
		}
	}
	
	private static void seleccionarTenista() {
		System.out.println("Seleccionando Tenista");
		
		Object seleccionTenista = JOptionPane.showInputDialog(
			null, 
			"Tenista ganador/a",
			"Nuevo resultado",
			JOptionPane.QUESTION_MESSAGE);

		System.out.println("El usuario ha elegido "+seleccionTenista);
		
		if(seleccionTenista == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarPais();
		}
	}
	
	private static void seleccionarPais() {
		System.out.println("Seleccionando Pais");
		
		Object seleccionPais = JOptionPane.showInputDialog(
			null, 
			"Pais de tenista ganador/a",
			"Nuevo resultado",
			JOptionPane.QUESTION_MESSAGE);

		System.out.println("El usuario ha elegido "+seleccionPais);
		
		if(seleccionPais == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarRanking();
		}
	}
	
	private static void seleccionarRanking() {
		System.out.println("Seleccionando Ranking");
		
		Object seleccionRanking = JOptionPane.showInputDialog(
			null, 
			"Ranking de ganador/a",
			"Nuevo resultado",
			JOptionPane.QUESTION_MESSAGE);
		
		System.out.println("El usuario ha elegido "+seleccionRanking);
		
		if(seleccionRanking == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarFinalista();
		}
	}
	
	private static void seleccionarFinalista() {
		System.out.println("Seleccionando Finalista");
		
		Object seleccionFinalista = JOptionPane.showInputDialog(
			null, 
			"Tenista finalista",
			"Nuevo resultado",
			JOptionPane.QUESTION_MESSAGE);

		System.out.println("El usuario ha elegido "+seleccionFinalista);
		
		if(seleccionFinalista == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarPaisF();
		}
	}

	private static void seleccionarPaisF() {
		System.out.println("Seleccionando Pais Finalista");
		
		Object seleccionPaisF = JOptionPane.showInputDialog(
			null, 
			"Pais de tenista finalista",
			"Nuevo resultado",
			JOptionPane.QUESTION_MESSAGE);

		System.out.println("El usuario ha elegido "+seleccionPaisF);
		
		if(seleccionPaisF == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarRankingF();
		}
	}
	
	private static void seleccionarRankingF() {
		System.out.println("Seleccionando Ranking Finalista");
		
		Object seleccionRankingF = JOptionPane.showInputDialog(
			null, 
			"Ranking de finalista",
			"Nuevo resultado",
			JOptionPane.QUESTION_MESSAGE);

		System.out.println("El usuario ha elegido "+seleccionRankingF);	
		
		if(seleccionRankingF == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			seleccionarResultado();
		}
	}
	
	private static void seleccionarResultado() {
		System.out.println("Seleccionando Resultado");
		
		Object seleccionResultado = JOptionPane.showInputDialog(
				null, 
				"Resultado del partido final",
				"Nuevo resultado",
				JOptionPane.QUESTION_MESSAGE);

			System.out.println("El usuario ha elegido "+seleccionResultado);
			
			if(seleccionResultado == null) { 
				System.out.println("Cancelar");
			} else {
				System.out.println("Aceptar");
				System.out.println("Guardando nuevo resultado");
			}
	}

	private static int seleccionarAnyoInicial() {
		System.out.println("Seleccionando Anyo Inicial");
		
		Object seleccionAnyoInicial = JOptionPane.showInputDialog(
				null, 
				"Anyo inicial para ver evolucion del ranking",
				"Entrada",
				JOptionPane.QUESTION_MESSAGE,null,null, "1968");
		
		System.out.println("El usuario ha elegido "+seleccionAnyoInicial);
		int resultado = Integer.parseInt(seleccionAnyoInicial + "");
		
		if(seleccionAnyoInicial == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			if(resultado < 1968 || resultado > 2021) {
				return seleccionarAnyoInicial();
			}
			return resultado;
		}
		return 0;
	}
	
	private static int seleccionarAnyoFinal() {
		System.out.println("Seleccionando Anyo Final");
		
		Object seleccionAnyoFinal = JOptionPane.showInputDialog(
				null, 
				"Anyo final para ver evolucion del ranking",
				"Entrada",
				JOptionPane.QUESTION_MESSAGE,null,null, "2021");
		
		System.out.println("El usuario ha elegido "+seleccionAnyoFinal);
		int resultado = Integer.parseInt(seleccionAnyoFinal + "");
		
		if(seleccionAnyoFinal == null) { 
			System.out.println("Cancelar");
		} else {
			System.out.println("Aceptar");
			if(resultado < 1968 || resultado > 2021) {
				return seleccionarAnyoFinal();
			}
			
			return resultado;
		}
		return 0;
	}
}
