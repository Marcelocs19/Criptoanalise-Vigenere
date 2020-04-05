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
			
			System.out.println("nome Arquivo: " + nomeArquivo);
			
			System.out.println("TXT: " + leitura.leitura(nomeArquivo));
			
			DecifradorTxt decifrador = new DecifradorTxt();
			
			System.out.println("AQUI: " + decifrador.decodificarTexto("avelino", leitura.leitura(nomeArquivo)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
