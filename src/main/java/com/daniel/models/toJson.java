package com.daniel.models;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.concurrent.Task;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class toJson extends Task<Object>{

	private JsonArray jsonArray;
	private boolean flag;
	private List<String> colunas;
	private List<String> valores;
	private ListView<String> listview;
	private static String gsonString;
	private int qtd;
	
	public toJson(ListView<String> listview, int qtd) {
		jsonArray = new JsonArray();
		flag = true;
		colunas = new ArrayList<String>();
		valores = new ArrayList<String>();
		flag = true;
		this.listview = listview;
		this.qtd = qtd - 1;
	}

//	public void run() {
//		
//	}

	public static String getGsonString() {
		return gsonString;
	}

	@Override
	protected Object call() throws Exception {
		int count = 1;
		Instant inicio = Instant.now();
		while(FilaValores.isTerminou()) {
			String linha = FilaValores.pegarLista();
			
			if(linha == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				if(flag) {
					flag = false;
					colunas = Arrays.asList(linha.split(","));
				}
				else {
					JsonObject obj = new JsonObject();
					valores = Arrays.asList(linha.split(","));
					for(int i = 0; i < colunas.size(); i++) {
						obj.addProperty(colunas.get(i), valores.get(i));
					}
					jsonArray.add(obj);
					updateProgress(count, qtd);
					listview.getItems().add(obj.toString());
					count++;
				}
			}
		}
		Instant fim = Instant.now();
		System.out.println("Duracao Parse: " + Duration.between(inicio, fim).toMillis() + "ms");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		gsonString =  gson.toJson(jsonArray);
		return null;
	}
}
