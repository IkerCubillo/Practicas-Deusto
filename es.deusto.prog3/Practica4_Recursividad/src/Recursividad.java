import java.util.ArrayList;

public class Recursividad {
	static ArrayList<Carta> baraja = new ArrayList<>();
	public static void main(String[] args) {
		añadirCartasBaraja();
		
//		System.out.println(invertirFraseSabin("Esta funcion invierte la frase recursivamente"));
//		System.out.println(invertirFraseSabin("Andoni Eguiluz es mi profesor de Programación"));
//		System.out.println(invertirFraseSabin("Las patatas fritas existen para untarse en el huevo"));
//		
//		System.out.println(invertirFraseCubillo("Esta funcion invierte la frase recursivamente", ""));
//		System.out.println(invertirFraseCubillo("Andoni Eguiluz es mi profesor de Programación", ""));
//		System.out.println(invertirFraseCubillo("Las patatas fritas existen para untarse en el huevo", ""));
		
		System.out.println(invertirPalabras("Patata;que-tal,he.a:g"));
		
//		posiblesManos(2,baraja);
		
		
	}
	
	
	
	private static void posiblesManos(int i, ArrayList<Carta> baraja) {
		if(i == 0) {
//			return "";
		} else {
			for (int i1 = 1; i1 <= baraja.size(); i++) {
				for (int i11 = 1; i11 <= baraja.size()-1; i++) {
					for (int i111 = 1; i111 <= baraja.size()-2; i++) {
						System.out.println( "" + baraja.get(i1) + baraja.get(i11) + baraja.get(i111) );
					}
				}
				
				
			}
//			return "";
		}
		
//		for (Carta c : baraja) {
//			for (Carta c2 : baraja) {
//				for (Carta c3 : baraja) {
//					System.out.println( "" + c + c2 + c3 );
//				}
//			}
//		}
		
	}



	private static void añadirCartasBaraja() {
		String[] palos = {"oros", "bastos", "espadas", "copas"};
		for(String p: palos) {
			for(int i = 1; i <= 12; i++) {
				baraja.add(new Carta(p, i));
			}
		}
	}

	private static String invertirPalabras(String fraseInicial) {
		String[] campos = fraseInicial.split("[;:-\\.]");
		System.out.println(campos.length);
		for(String c: campos ) {
			System.out.println(c);
		}
		
		fraseInicial = fraseInicial.replace("Patata", invertirFraseSabin("Patata"));
		return fraseInicial;
	}

	private static String invertirFraseCubillo(String fraseInicial, String fraseReturn) {
		if(fraseInicial.length() == 0 ) {
			return fraseReturn;
		} else {
			fraseReturn += fraseInicial.charAt(fraseInicial.length()-1);
			fraseInicial = (String) fraseInicial.subSequence(0, fraseInicial.length()-1);
			return invertirFraseCubillo(fraseInicial, fraseReturn);
		}
	}
	
	public static String invertirFraseSabin(String palabra) {
        if (palabra.length() <= 1) {
            return palabra;
        } else {
//        	System.out.println(palabra);
            return invertirFraseSabin(palabra.substring(1)) + palabra.charAt(0);
        }
    }
	
}

