package clasesVisuales;


import java.awt.*;

import javax.swing.*;

public class VentanaClasificacion extends JFrame {
	JPanel panel;
	public VentanaClasificacion(int anyoInicial, int anyoFinal) {
		inicializarVentana(anyoInicial, anyoFinal);
	}
	
	private void inicializarVentana(int anyoInicial, int anyoFinal) {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Visualizacion datos GS entre " + anyoInicial + " y " + anyoFinal);
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize( (int) dimension.getWidth()-200, (int) dimension.getHeight()-400);
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);	
//	    init();
	}
//	private void init() {
//        panel = new JPanel();
//        getContentPane().add(panel);
//        // Fuente de Datos
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.setValue(8, "Mujeres", "Lunes");
//        dataset.setValue(7, "Hombres", "Lunes");
//        dataset.setValue(9, "Mujeres", "Martes");
//        dataset.setValue(4, "Hombres", "Martes");
//        dataset.setValue(4, "Mujeres", "Miercoles");
//        dataset.setValue(5, "Hombres", "Miercoles");
//        dataset.setValue(8, "Mujeres", "Jueves");
//        dataset.setValue(9, "Hombres", "Jueves");
//        dataset.setValue(7, "Mujeres", "Viernes");
//        dataset.setValue(8, "Hombres", "Viernes");
//        // Creando el Grafico
//        JFreeChart chart = ChartFactory.createBarChart3D
//        ("Participacion por Genero","Genero", "Dias", 
//        dataset, PlotOrientation.VERTICAL, true,true, false);
//        chart.setBackgroundPaint(Color.cyan);
//        chart.getTitle().setPaint(Color.black); 
//        CategoryPlot p = chart.getCategoryPlot(); 
//        p.setRangeGridlinePaint(Color.red); 
//        // Mostrar Grafico
//        ChartPanel chartPanel = new ChartPanel(chart);
//        panel.add(chartPanel);
//    }
}
