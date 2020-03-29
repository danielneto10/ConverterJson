package com.daniel.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class toJson implements Runnable{

	private JsonArray jsonArray;
	private boolean flag;
	private List<String> colunas;
	private List<String> valores;
	private ListView<String> listview;
	private ProgressBar pgbar;
	private static String gsonString;
	
	public toJson(ListView<String> listview, ProgressBar pgbar) {
		jsonArray = new JsonArray();
		flag = true;
		colunas = new ArrayList<String>();
		valores = new ArrayList<String>();
		flag = true;
		this.listview = listview;
		this.pgbar = pgbar;
	}

	public void run() {
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
					listview.getItems().add(obj.toString());
				}
			}
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		gsonString =  gson.toJson(jsonArray);
	}

	public static String getGsonString() {
		return gsonString;
	}
}
