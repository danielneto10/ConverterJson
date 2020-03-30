package com.daniel.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QtdLinhas {
	private int qtd;
	private BufferedReader br;
	private String line;
	private Path path;
	
	public QtdLinhas(File arq) {
		this.line = "";
		this.path = Paths.get(arq.getPath());
	}
	
	public int total() {
		try {
			br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			while((line = br.readLine()) != null) {
				qtd++;
			}
			br.close();
			return qtd;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}
