package com.daniel.models;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AbrirArq {

	private JFileChooser arq;
	
	public AbrirArq() {
		arq = new JFileChooser();
	}
	
	public File abrir() {
		arq.setFileFilter(new FileNameExtensionFilter("TXT | CSV", "txt", "csv"));
		int resultado = arq.showOpenDialog(null);
		if(resultado == JFileChooser.APPROVE_OPTION) {
			return arq.getSelectedFile();
		}
		return null;
	}
	
	public void salvar() {
		int resultado = arq.showSaveDialog(null);
		if(resultado == JFileChooser.APPROVE_OPTION) {
			new Gravacao(arq.getSelectedFile());
		}
	}
}
