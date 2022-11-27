
public class Recursividad {
	public static void main(String[] args) {
		System.out.println(invertirFraseSabin("Esta funcion invierte la frase recursivamente"));
		System.out.println(invertirFraseSabin("Andoni Eguiluz es mi profesor de Programación"));
		System.out.println(invertirFraseSabin("Las patatas fritas existen para untarse en el huevo"));
		
		System.out.println(invertirFraseCubillo("Esta funcion invierte la frase recursivamente", ""));
		System.out.println(invertirFraseCubillo("Andoni Eguiluz es mi profesor de Programación", ""));
		System.out.println(invertirFraseCubillo("Las patatas fritas existen para untarse en el huevo", ""));
		
//		System.out.println(invertirPalabras("Patata; que tal: hey - g", args));
	}

	private static String invertirPalabras(String fraseInicial, String[] camposReturn ) {
		String[] campos = fraseInicial.split("[;:-,.] \n \t");
		
		return campos[0] + "" + campos[1] + "" +campos[2] + ""+ campos[3];
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

