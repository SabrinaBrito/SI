package teste;

import java.util.ArrayList;
import java.util.Scanner;

public class NumeroEscrito {
	
	public static final String[] casas = {"", "mil", "milhoes","bilhoes", "trilhoes", "quadrilhoes"}; 
	
	public static void main(String[] args) {
		int option;
		boolean valido = false;
		Scanner s = new Scanner(System.in);
		String input = null;
		int number = 0;
		
		do{
			menu();
			option = s.nextInt();
			switch(option){
			case 1:
				do{
					input = s.nextLine();
					try{
						number = Integer.parseInt(input);
						valido = false;
					} catch (Exception e){
						valido = true;
						System.out.println("DIGITE O NUMERO DESEJADO: ");
					}
				}while(valido);
					verify(input);
					s.close();

					Integer[] parts = split(input);
				
					String[] converted = convert(parts);
					
					String result = join(converted);
					System.out.println(result);
					break;
				default:
					System.out.println("VOCE SAIU DO PROGRAMA.");
			}	
		}while(option != -1);
	}
	
	
	public static void menu(){
		System.out.println("PARA SAIR DO PROGRAMA DIGITE '-1' PARA CONTINUAR DIGITE 1");
		System.out.println("O QUE DESEJA? ");
	}

	private static boolean verify(String input) {
		return true; // TODO
	}

	private static Integer[] split(String str) {
		String temp = "";
		ArrayList<Integer> array = new ArrayList<Integer>();

		for (int i = str.length()-1; i >= 0; i--) {
			if (temp.length() >= 3) {
				array.add(0, Integer.parseInt(temp));
				temp = "";
			}
			temp = str.charAt(i) + temp;
		} 
		array.add(0, Integer.parseInt(temp));
		
		Integer[] result = new Integer[array.size()];
		return array.toArray(result);
	}
	
	private static String[] convert(Integer[] parts) {
		String[] result = new String[parts.length];
		
		for (int i = 0; i < parts.length; i++)
			result[i] = translate(parts[i]);
		
		return result;
	}

	private static String translate(Integer number) {
		String result = "";
		String piece = "";
		boolean finished = false;
		String no = number.toString();
		
		switch (no.length()) {
		case 3:
			int firstNo = no.charAt(0) - 48;
			
			String[] centenas = {"", "cento", "duzentos", "trezentos",
					"quatrocentos", "quinhentos", "seiscentos",
					"setecentos","oitocentos", "novecentos"};
			
			piece = centenas[firstNo];
			
			if (firstNo == 1) {
				if (number == 100) {
					piece = "cem";
					finished = true;
				}
			}

			result += piece;
			piece = "";

			if (finished)
				break;
			
			if (no.charAt(1) != '0'
					|| no.charAt(2) != '0')
				result += " e ";
			
		case 2:
			switch (no.charAt(no.length()-2)) {
			case '1':
				String[] dezenas = {"dez", "onze", "doze", "treze",
						"catorze", "quinze", "dezesseis","dezessete",
						"dezoito","dezenove"};
				
				piece = dezenas[no.charAt(no.length()-1) - 48]; 
				// char c = "5"; c-48 == (int) 5;
				
				finished = true;
				break;
			case '2':
				piece = "vinte";
				break;
			case '3':
				piece = "trinta";
				break;
			case '4':
				piece = "quarenta";
				break;
			case '5':
				piece = "cinquenta";
				break;
			case '6':
				piece = "sessenta";
				break;
			case '7':
				piece = "setenta";
				break;
			case '8':
				piece = "oitenta";
				break;
			case '9':
				piece = "noventa";
				break;
			case '0':
				break;
			}
			
			result += piece;
			piece = "";
			
			if (finished)
				break;
			
			if (no.charAt(no.length()-1) != '0')
				result += " e ";
			
		case 1:
			int lastNo = no.charAt(no.length()-1) - 48;
			
			String[] unidades = {"", "um", "dois", "tres", "quatro", "cinco",
					"seis", "sete", "oito", "nove"};
			
			piece = unidades[lastNo];
		}
		
		result += piece;
		
		return result;
	}

	private static String join(String[] parts) {
		StringBuilder result = new StringBuilder();
		int j = 0;
		for (int i = parts.length-1; i >= 0; i--) {
			result.append(parts[j++]);
			result.append(" " + casas[i]);
			if (i > 0)
				result.append(", ");
		}
		return result.toString();
	}

}
