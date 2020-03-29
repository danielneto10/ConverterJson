package com.daniel.models;

import java.util.ArrayList;
import java.util.List;

public class FilaValores {

	private static List<String> lista = new ArrayList<String>();;
	private static boolean lendoDados = true;
	
	public FilaValores() {
		
	}
	
	public static String pegarLista() {
		if(lista.size() > 0) {
			return lista.remove(0);
		}
		return null;
	}
	public static boolean isTerminou() {
		
		return lendoDados || lista.size() > 0;
	}
	
	public static void addLinha(String linha) {
		lista.add(linha);
	}
	
	public static List<String> getLista() {
		return lista;
	}

	public static void setLendoDados(boolean lendoDados) {
		FilaValores.lendoDados = lendoDados;
	}
	
	
}
