package com.daniel.controller;
import java.io.File;

import com.daniel.models.AbrirArq;
import com.daniel.models.Leitura;
import com.daniel.models.QtdLinhas;
import com.daniel.models.toJson;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class TelaController {
    @FXML
    private Button btnAbrir;

    @FXML
    private ListView<String> listViewRegistro;

    @FXML
    private Button btnSalvar;

    @FXML
    private ProgressBar progBarRegistros;
    
    @FXML
    void abrirArq(ActionEvent event) {
    	File arq = new AbrirArq().abrir();
    	listViewRegistro.getItems().clear();
    	if(arq != null) {
    		int totLines = new QtdLinhas(arq).total();
    		Thread thLeitura = new Thread(new Leitura(arq));
    		thLeitura.start();
    		
    		
    		Task<Object> taskToJson = new toJson(listViewRegistro, progBarRegistros, totLines);
    		progBarRegistros.progressProperty().bind(taskToJson.progressProperty());
    		Thread thToJson = new Thread(taskToJson);
    		thToJson.setDaemon(true);
    		thToJson.start();
    	}
    }

    @FXML
    void salvarArq(ActionEvent event) {
    	new AbrirArq().salvar();
    }
}
