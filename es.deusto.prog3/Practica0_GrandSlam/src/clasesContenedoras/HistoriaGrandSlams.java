package clasesContenedoras;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import clasesBasicas.Resultado;
import clasesBasicas.Tenista;
import clasesBasicas.Torneo;

public class HistoriaGrandSlams {

	public ArrayList<Resultado> resultados;
	private HashMap<Integer, Torneo> torneoPorCodigo;
	private HashMap<String, Torneo> torneoPorNombre;
	public HashMap<String, Tenista> tenistas;
	
	public HistoriaGrandSlams() {
		this.resultados = new ArrayList<Resultado>();
		this.torneoPorCodigo = new HashMap<Integer, Torneo>();
		this.torneoPorNombre = new HashMap<String, Torneo>();
		this.tenistas = new HashMap<String, Tenista>();
		
		try {
			cargarEstructuras();
			System.out.println(resultados);
			System.out.println(torneoPorCodigo);
			System.out.println(tenistas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//
	
	public void cargarEstructuras() throws IOException {
		
		BufferedReader gsc = null;
		BufferedReader tour = null;
		
		try {
			gsc = new BufferedReader(new FileReader("Grand Slam Championships, Champion vs Runner-up, Men's Singles, 1968-2021.csv"));
			String linea;
			
			tour = new BufferedReader(new FileReader("Tournaments.csv"));
			String linea2;
			
			Boolean esPrimeraLinea = true;
			
			while ((linea2 = tour.readLine()) != null) {
				if (esPrimeraLinea) {
					esPrimeraLinea = false;
					continue;
				}
				String[] datosTorneos = linea2.split(",");
				
				torneoPorCodigo.put(Integer.parseInt(datosTorneos[0]), new Torneo(Integer.parseInt(datosTorneos[0])));
				torneoPorNombre.put(datosTorneos[1], new Torneo(Integer.parseInt(datosTorneos[0])));
			}
			
			esPrimeraLinea = true;
			
			while ((linea = gsc.readLine()) != null) {
				if (esPrimeraLinea) {
					esPrimeraLinea = false;
					continue;
				}
				String[] campos = linea.split(",");
				
				
				Resultado r = new Resultado(new Torneo(Integer.parseInt(campos[1])), Integer.parseInt(campos[0]),
						campos[2], campos[5], Integer.parseInt(campos[3]), Integer.parseInt(campos[6]), campos[8]);
				resultados.add(r);
				
				Tenista t = new Tenista(campos[2], campos[4], calcularNumWins(campos[2], resultados));
				tenistas.put(t.getName(), t);
				//ordenarMapaPorValores(tenistas);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			if (tour != null) {
				try {
					tour.close();
				}
				catch (IOException e) {
				    e.printStackTrace();
				}
			}
			
			if (gsc != null) {
				try {
					gsc.close();
				}
				catch (IOException e) {
				    e.printStackTrace();
				}
			}
		}
	}
	
	public void guardarDatosEnFichero(int hola) {
		
		
		
	}
	
	/**
	 * Método para ordenar los tenistas por su número de victorias en un HashMap
	 * @param tenistas2
	 * @return el mismo mapa ordenado
	 */
	/*public HashMap<String,Tenista> ordenarMapaPorValores(HashMap<String, Tenista> tenistas) {
		
		 Map<String, Tenista> sortedMap = new LinkedHashMap<>();
		 
		 tenistas.entrySet()
         .stream()
         .sorted(Map.Entry.comparingByValue())
         .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
		
		return tenistas;
	}
	*/
	
	/**
	 * Método para calcular las victorias de un jugador
	 * @param name del jugador
	 * @param resultados ArrayList con todos los resultados para poder recorrer las victorias
	 * @return dato int con el número de victorias del jugador
	 */
	public int calcularNumWins(String name, ArrayList<Resultado> resultados) {
		
		int cont = 0;
		
		for (Resultado r : resultados) {
			if (r.getWinner().equals(name)) {
				cont++;
			}	
		}
		return cont;
	}
	
	/**
	 * Método para calcular la clasificación por victorias entre dos años
	 * @param anyoInicial
	 * @param anyoFinal
	 * @return HashMap con clave nombre de tenista y valor número de victorias entre los años pasados como parametros
	 */
	public HashMap<String, Integer> calculaClasificacion(int anyoInicial, int anyoFinal) {
		
		HashMap<String, Integer> numVictoriasPorTenista = new HashMap<>();
		
		for (Resultado r : resultados) {
			if (r.getYear() >= anyoInicial && r.getYear() <= anyoFinal) {
				
				if (!numVictoriasPorTenista.containsKey(r.getWinner())) {
					numVictoriasPorTenista.put(r.getWinner(), 1);
				} else {
					numVictoriasPorTenista.put(r.getWinner(), (numVictoriasPorTenista.get(r.getWinner())+1));

				}
			}
		}
		return numVictoriasPorTenista;
	}
	
}
