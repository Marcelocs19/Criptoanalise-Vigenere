package br.pucrs.seguranca.descriptografia;

import java.util.HashMap;
import java.util.Map;


public class IndiceCoincidencia {

	private Map<Character, Double> indice;

	public double encontrarIndiceDeCoincidencia(String textoCriptografado) {
		inicializarindiceDeCoincidenciaDasLetras();
		verificarFrequenciaDeLetrasDoTexto(textoCriptografado);

		double divisor = textoCriptografado.length() * (textoCriptografado.length() - 1);

		verificarIndiceDeCoincidenciaDasLetrasDoTexto();

		return verificarIndiceDeCoincidenciaDoTexto(divisor);
	}

	private double verificarIndiceDeCoincidenciaDoTexto(double divisor) {
		return (indice.values().stream().mapToDouble(Double::valueOf).sum()) / divisor;
	}

	private void verificarIndiceDeCoincidenciaDasLetrasDoTexto() {
		indice.forEach((k, v) -> {
			indice.put(k, v * (v - 1));
		});
	}
	
	public Map<Character, Double> verificarFrequenciaDeLetrasDoTexto(String texto) {
		for (int i = 0; i < texto.length(); i++) {
			indice.put(texto.charAt(i),
					indice.get(texto.charAt(i)) + 1);
		}		
		return indice;
	}
	
	public void inicializarindiceDeCoincidenciaDasLetras() {
		indice = new HashMap<>();
		indice.put('a', 0.0);
		indice.put('b', 0.0);
		indice.put('c', 0.0);
		indice.put('d', 0.0);
		indice.put('e', 0.0);
		indice.put('f', 0.0);
		indice.put('g', 0.0);
		indice.put('h', 0.0);
		indice.put('i', 0.0);
		indice.put('j', 0.0);
		indice.put('k', 0.0);
		indice.put('l', 0.0);
		indice.put('m', 0.0);
		indice.put('n', 0.0);
		indice.put('o', 0.0);
		indice.put('p', 0.0);
		indice.put('q', 0.0);
		indice.put('r', 0.0);
		indice.put('s', 0.0);
		indice.put('t', 0.0);
		indice.put('u', 0.0);
		indice.put('v', 0.0);
		indice.put('w', 0.0);
		indice.put('x', 0.0);
		indice.put('y', 0.0);
		indice.put('z', 0.0);
	}
	
	
}
