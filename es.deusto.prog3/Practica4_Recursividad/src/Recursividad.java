
public class Recursividad {
	public static void main(String[] args) {
//		System.out.println(invertirFrase("Esta funcion invierte la frase recursivamente", ""));
//		System.out.println(invertirFrase("Andoni Eguiluz es mi profesor de Programación", ""));
//		System.out.println(invertirFrase("Las patatas fritas existen para untarse en el huevo", ""));
		
		System.out.println(invertirPalabras("Patata; que tal: hey - g", args));
	}

	private static String invertirPalabras(String fraseInicial, String[] camposReturn ) {
		String[] campos = fraseInicial.split("[;:-,.] \n \t");
		
		return campos[0] + "" + campos[1] + "" +campos[2] + ""+ campos[3];
	}

	private static String invertirFrase(String fraseInicial, String fraseReturn) {
		if(fraseInicial.length() == 0 ) {
			return fraseReturn;
		} else {
			fraseReturn += fraseInicial.charAt(fraseInicial.length()-1);
			fraseInicial = (String) fraseInicial.subSequence(0, fraseInicial.length()-1);
			System.out.println(fraseReturn);
			System.out.println(fraseInicial);
			return invertirFrase(fraseInicial, fraseReturn);
		}
	}
	
	
}

