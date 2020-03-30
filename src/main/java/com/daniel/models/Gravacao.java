package com.daniel.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

public class Gravacao {
	
	private Path path;
	private BufferedWriter escrever;
	
	public Gravacao(File arq) {
		this.path = Paths.get(arq.getPath() + ".json");
		gravar();
	}
	
	public void gravar() {
		try {
			Instant inicio = Instant.now();
			Path novoDir = Files.createFile(path);
			escrever = Files.newBufferedWriter(novoDir, StandardCharsets.UTF_8);
			escrever.write(toJson.getGsonString());
			escrever.close();
			Instant fim = Instant.now();
			System.out.println("Duracao Gravacao: " + Duration.between(inicio, fim).toMillis() + "ms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
