package com.daniel.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

import javax.swing.text.DefaultEditorKit.BeepAction;

public class Leitura implements Runnable{
	private BufferedReader br;
	private String line;
	private Path path;
	
	public Leitura(File arq) {
		this.line = "";
		this.path = Paths.get(arq.getPath());
	}
	
	
	public void run() {
		try {
			Instant inicio = Instant.now();
			FilaValores.setLendoDados(true);
			br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			while((line = br.readLine()) != null) {
				FilaValores.addLinha(line.replaceAll("\"", ""));
			}
			br.close();
			FilaValores.setLendoDados(false);
			Instant fim = Instant.now();
			System.out.println("Duracao Leitura: " + Duration.between(inicio, fim).toMillis() + "ms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
