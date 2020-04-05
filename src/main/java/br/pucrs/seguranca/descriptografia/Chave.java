package br.pucrs.seguranca.descriptografia;

public class Chave {

	private String alfabeto = "abcdefghijklmnopqrstuvwxyz";

	public Chave() {}
	
	public char getAlfabeto(int index) {
		return alfabeto.charAt(index);
	}

}
