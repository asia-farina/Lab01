package it.polito.tdp.parole;

import it.polito.tdp.parole.model.*;
import it.polito.tdp.parole.model.Parole;

import javafx.scene.control.Label;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Parole elenco;
	long tempo;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnInserisci;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    @FXML
    private Label txtComunicazioni;
    
    @FXML
    private TextField txtTime;

    @FXML
    void doInsert(ActionEvent event) {
    	// TODO
    	String p="";
    	p=txtParola.getText();
    	if (p.matches("(?=.*[0-9]).{1,}"))
    	{
    		txtComunicazioni.setText("ATTENZIONE: INSERISCI UNA PAROLA VALIDA");
    		return ;
    	}
    	elenco.addParola(p);
    	ComparatorParoleOrdineAlfabetico c=new ComparatorParoleOrdineAlfabetico();
    	LinkedList <String> ordineAlfabetico=(LinkedList<String>) elenco.getElenco();
    	Collections.sort(ordineAlfabetico, c);
    	String ss="";
    	for (String s:ordineAlfabetico)
    	{
    		ss+=s+"\n";
    	}
    	tempo=System.nanoTime();
    	int tempoInt=((int) tempo)*10^(-9);
    	String tempoStr=String.valueOf(tempoInt);
    	txtTime.setText(tempoStr);
     	txtResult.setText(ss);
    	txtParola.setText("");
    	txtComunicazioni.setText("");
  
    }

    @FXML
    void doReset(ActionEvent event) {
    	// TODO
    	elenco.reset();
    	txtResult.setText("");
    	tempo=System.nanoTime();
    	int tempoInt=((int) tempo)*10^(-9);
    	String tempoStr=String.valueOf(tempoInt);
    	txtTime.setText(tempoStr);
    }
    
    @FXML
    void doDelete(ActionEvent event) {
    	boolean flag=elenco.delete(txtParola.getText());
    	if (flag==false)
    		txtComunicazioni.setText("La parola non è presente nell'elenco");
    	if (flag==true)
    	{
    		ComparatorParoleOrdineAlfabetico c=new ComparatorParoleOrdineAlfabetico();
        	LinkedList <String> ordineAlfabetico=(LinkedList<String>) elenco.getElenco();
        	Collections.sort(ordineAlfabetico, c);
    		String ss="";
        	for (String s:ordineAlfabetico)
        	{
        		ss+=s+"\n";
        	}
        	txtResult.setText(ss);
    	}
    	tempo=System.nanoTime();
    	int tempoInt=((int) tempo)*10^(-9);
    	String tempoStr=String.valueOf(tempoInt);
    	txtTime.setText(tempoStr);
    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

        elenco = new Parole() ;
    }
}
