package com.daniel.controller;
import java.io.File;

import com.daniel.models.AbrirArq;
import com.daniel.models.Leitura;
import com.daniel.models.toJson;

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
    		Thread thLeitura = new Thread(new Leitura(arq));
        	Thread thToJson = new Thread(new toJson(listViewRegistro, progBarRegistros));
        	thLeitura.start();
        	thToJson.start();
    	}
    }

    @FXML
    void salvarArq(ActionEvent event) {
    	new AbrirArq().salvar();
    }
}
