package br.pucrs.seguranca.descriptografia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.pucrs.seguranca.frequencia.FrequenciaLetras;

public class Chave {

	private String alfabeto = "abcdefghijklmnopqrstuvwxyz";
	
	private Map<Integer, Double> tamanhoChavePorIc;	
	
	private String arquivoTxt;
	
	private IndiceCoincidencia indiceCoincidencia;
	
	private FrequenciaLetras frequenciaLetras;
	

	public Chave(String arquivo) {
		arquivoTxt = arquivo;
		frequenciaLetras = new FrequenciaLetras();
		indiceCoincidencia = new IndiceCoincidencia();
		tamanhoChavePorIc = new HashMap<>();
	}
	
	public char getAlfabeto(int index) {
		return alfabeto.charAt(index);
	}
	
	public String decifrar() {
		List<String> colunas = encontraColunasCriptografadas(encontrarTamanhoChave());
		Map<Character, Double> frequenciaAlfabetoFixo = frequenciaLetras.frequenciaLetrasFixa();
		Map<Character, Double> frequenciaLetras = new HashMap<>();

		String chave = "";
		for (String textoCriptografado : colunas) {
			indiceCoincidencia.inicializarindiceDeCoincidenciaDasLetras();
			frequenciaLetras = indiceCoincidencia.verificarFrequenciaDeLetrasDoTexto(textoCriptografado);
			chave = chave + chiSquare(frequenciaAlfabetoFixo, frequenciaLetras, textoCriptografado);
		}
		return chave;
	}
	
	public int encontrarTamanhoChave() {
		int tamanhoChave = 0;
		for (int i = 1; i <= 10; i++) {
			tamanhoChave = encontrarTamanhoChave(encontraColunasCriptografadas(i), i);
		}
		return tamanhoChave;
	}
	
	public List<String> encontraColunasCriptografadas(int tamanhoChave) {
		List<String> texto = new ArrayList<>();
		int index = 0;
		while (index < arquivoTxt.length()) {
			texto.add(
					arquivoTxt.substring(index, Math.min(index + tamanhoChave, arquivoTxt.length())));
			index += tamanhoChave;
		}
		return organizarColunas(texto);
	}
	
	private int encontrarTamanhoChave(List<String> palavras, int index) {
		double[] icPalavras = { 0 };
		palavras.forEach(palavra -> {
			icPalavras[0] += indiceCoincidencia.encontrarIndiceDeCoincidencia(palavra);
		});
		
		tamanhoChavePorIc.put(index, icPalavras[0] / index);
		return tamanhoChavePorIc.entrySet().stream()
				.max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
	}

	private List<String> organizarColunas(List<String> palavras) {
		String[] colunasPalavras = new String[palavras.get(0).length()];
		Arrays.fill(colunasPalavras, "");
		
		palavras.forEach(palavra -> {
			for (int i = 0; i < palavra.length(); i++) {
				colunasPalavras[i] = colunasPalavras[i] + palavra.charAt(i);
			}
		});

		palavras.removeAll(palavras);
		for (int j = 0; j < colunasPalavras.length; j++) {
			palavras.add(colunasPalavras[j]);
		}
		return palavras;
	}
	
	
	public Character chiSquare(Map<Character, Double> frequenciaAlfabetoPtBr, Map<Character, Double> frequenciaLetras,
			String palavraCriptografada) {
		Map<Character, Double> chiSquare = new HashMap<Character, Double>();
		for (int i = 0; i < alfabeto.length(); i++) {
			double chiSquared = 0;
			for (char letra : frequenciaAlfabetoPtBr.keySet()) {
				int novoIndex = alfabeto.indexOf(letra) + i;
				if (novoIndex > 25) {
					novoIndex = novoIndex - 26;
				}

				if (frequenciaLetras.containsKey(alfabeto.charAt(novoIndex))) {
					double frequenciaDaLetra = frequenciaLetras.get(alfabeto.charAt(novoIndex));
					double frequenciaMultiplicada = frequenciaAlfabetoPtBr.get(letra) * palavraCriptografada.length();
					chiSquared += Math.pow((frequenciaDaLetra - frequenciaMultiplicada), 2) / frequenciaMultiplicada;
				}
			}
			chiSquare.put(alfabeto.charAt(i), chiSquared);
		}
		return chiSquare.entrySet().stream().min((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
				.get().getKey();
	}
	
}
