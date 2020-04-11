package br.pucrs.seguranca.descriptografia;

public class DecifradorTxt {
	
	public String decodificarTexto(String texto) {
		Chave chaveDecifradora = new Chave(texto);
		String chave = chaveDecifradora.decifrar();
		char[] charArray = chave.toCharArray();
		char[] textoArray = texto.toCharArray();
		
		String textoDecifrado = "";
				
		int x = 0;
		int pi = 0;
		
		for(int i = 0; i < textoArray.length; i++) {
			if(x >= charArray.length) {
				x = 0;
			} 
			pi = ((textoArray[i] - charArray[x]) + 26) % 26;
			textoDecifrado += chaveDecifradora.getAlfabeto(pi);
			x++;
		}
		
		return textoDecifrado;
	}

}
