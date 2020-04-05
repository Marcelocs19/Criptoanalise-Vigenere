package br.pucrs.seguranca;

import java.util.Scanner;

import br.pucrs.seguranca.leitura.LeituraTxt;

public class App {
	
	public static void main(String[] args) {
		try {
			Scanner teclado = new Scanner(System.in);

			LeituraTxt leitura = new LeituraTxt();
			
			System.out.println("Digite o no do arquivo txt: ");
			
			String nomeArquivo = teclado.nextLine();
			
			System.out.println("nome Arquivo: " + nomeArquivo);
			
			leitura.leitura(nomeArquivo);
			
			teclado.close();
		} catch (Exception e) {

		}
	}
}
