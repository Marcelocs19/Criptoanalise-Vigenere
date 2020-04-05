package br.pucrs.seguranca.leitura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class LeituraTxt {
	
	public LeituraTxt() {}

	public String leitura(String nome) {
		String texto = "";
		String line = "";
		
		try (FileReader leitorArquivo = new FileReader(nome);
			BufferedReader leituraBuffer = new BufferedReader(leitorArquivo)){						

			while (line != null) {
				line = leituraBuffer.readLine();
				texto += line;				
			}	
			
			
		} catch (FileNotFoundException ex) {
			System.out.println("Não foi encontrado o arquivo:  " + nome);
		} catch (IOException ex) {
			System.out.println("Erro na leitura do arquivo txt: " + nome);
		}
		
		return texto;

	}


}
