package br.pucrs.seguranca.leitura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class LeituraTxt {
	
	public LeituraTxt() {}

	public void leitura(String nome) {
		String texto = "";
		String line = "";
		try {
			FileReader fileReader = new FileReader(nome);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				texto += line;				
			}
			bufferedReader.close();
			System.out.println("Texto: " + texto);
		} catch (FileNotFoundException ex) {
			System.out.println("Não foi encontrado o arquivo:  " + nome);
		} catch (IOException ex) {
			System.out.println("Erro na leitura do arquivo txt: " + nome);
		}

	}


}
