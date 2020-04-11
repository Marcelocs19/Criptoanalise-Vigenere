package br.pucrs.seguranca.descriptografia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.pucrs.seguranca.frequencia.FrequenciaLetras;

public class Chave {

	private String alfabeto = "abcdefghijklmnopqrstuvwxyz";

	private Map<Integer, Double> chavesIndiceCoincidencia;

	private Map<Character, Double> frequenciaAlfabetoFixo;

	private String arquivoTxt;

	private IndiceCoincidencia indiceCoincidencia;

	private FrequenciaLetras frequenciaLetras;

	public char getAlfabeto(int index) {
		return alfabeto.charAt(index);
	}
	
	public Chave(String arquivo) {
		arquivoTxt = arquivo;
		frequenciaLetras = new FrequenciaLetras();
		indiceCoincidencia = new IndiceCoincidencia();
		chavesIndiceCoincidencia = new HashMap<>();
		frequenciaAlfabetoFixo = frequenciaLetras.frequenciaLetrasFixa();
	}
	
	public String decifrar() {
		StringBuilder chave = new StringBuilder();
		List<String> colunas = encontrarColunasCriptografadas(tamanhoChave());

		for (String textoCriptografado : colunas) {
			indiceCoincidencia.inicializarindiceFixo();
			Map<Character, Double> frequenciaIndiceCoincidencia = indiceCoincidencia.frequenciaDeLetrasDoTexto(textoCriptografado);
			chave.append(chiSquare(frequenciaAlfabetoFixo, frequenciaIndiceCoincidencia, textoCriptografado));
		}

		return chave.toString();
	}

	public int tamanhoChave() {
		int tamanhoChave = 0;
		for (int i = 1; i <= 10; i++) {
			tamanhoChave = tamanhoChave(encontrarColunasCriptografadas(i), i);
		}		
		return tamanhoChave;
	}

	public List<String> encontrarColunasCriptografadas(int tamanhoChave) {
		List<String> texto = new ArrayList<>();
		for (int i = 0; i < arquivoTxt.length(); i += tamanhoChave) {
			texto.add(arquivoTxt.substring(i, Math.min(i + tamanhoChave, arquivoTxt.length())));
		}
		return ajustaColunas(texto);
	}

	public int tamanhoChave(List<String> palavras, int index) {
		double indice = 0;
		for(int i = 0; i < palavras.size(); i++) {
			indice += indiceCoincidencia.encontrarIndiceDeCoincidencia(palavras.get(i));
		}
		chavesIndiceCoincidencia.put(index, indice / index);
		return chavesIndiceCoincidencia.entrySet().stream()
				.max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
	}

	public List<String> ajustaColunas(List<String> palavras) {
		String[] colunasPalavras = new String[palavras.get(0).length()];

		for (String palavra : palavras) {
			for (int i = 0; i < palavra.length(); i++) {
				colunasPalavras[i] = colunasPalavras[i] + palavra.charAt(i);
			}			
		}
		
		palavras.clear();
		
		for (int i = 0; i < colunasPalavras.length; i++) {
			palavras.add(colunasPalavras[i]);
		}
		
		return palavras;
	}

	public Character chiSquare(Map<Character, Double> frequenciaAlfabetoPtBr, Map<Character, Double> frequenciaLetras,
			String palavraCriptografada) {
		Map<Character, Double> chiSquare = new HashMap<>();
		for (int i = 0; i < alfabeto.length(); i++) {
			double chiSquared = 0;
			for (char letra : frequenciaAlfabetoPtBr.keySet()) {
				int novoIndex = alfabeto.indexOf(letra) + i;
				if (novoIndex > 25) {
					novoIndex = novoIndex - 26;
				}

				if (frequenciaLetras.containsKey(alfabeto.charAt(novoIndex))) {
					double frequenciaLetra = frequenciaLetras.get(alfabeto.charAt(novoIndex));
					double frequenciaMultiplicada = frequenciaAlfabetoPtBr.get(letra) * palavraCriptografada.length();
					chiSquared += Math.pow((frequenciaLetra - frequenciaMultiplicada), 2) / frequenciaMultiplicada;
				}
			}
			chiSquare.put(alfabeto.charAt(i), chiSquared);
		}
		return chiSquare.entrySet().stream().min((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
				.get().getKey();
	}

}
