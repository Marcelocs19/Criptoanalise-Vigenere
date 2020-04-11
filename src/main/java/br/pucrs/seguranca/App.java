package br.pucrs.seguranca;

import java.util.Scanner;

import br.pucrs.seguranca.descriptografia.DecifradorTxt;
import br.pucrs.seguranca.leitura.LeituraTxt;

public class App {
	
	public static void main(String[] args) {
		try (Scanner teclado = new Scanner(System.in)){

			LeituraTxt leitura = new LeituraTxt();
			
			System.out.println("Digite o no do arquivo txt: ");
			
			String nomeArquivo = teclado.nextLine();
			
			System.out.println("Texto Original: " + leitura.leitura(nomeArquivo));
			
			DecifradorTxt decifrador = new DecifradorTxt();
			
			System.out.println("Texto Decifrado: " + decifrador.decodificarTexto(leitura.leitura(nomeArquivo)));
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
