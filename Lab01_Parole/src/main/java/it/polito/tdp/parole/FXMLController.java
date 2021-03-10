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
	long start,end;
	
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
    	start=System.currentTimeMillis();
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
    	end=System.currentTimeMillis();
    	int tempoInt=(int) (end-start);
    	String tempoStr=String.valueOf(tempoInt);
    	txtTime.setText(tempoStr+" ms");
     	txtResult.setText(ss);
    	txtParola.setText("");
    	txtComunicazioni.setText("");
  
    }

    @FXML
    void doReset(ActionEvent event) {
    	// TODO
    	start=System.currentTimeMillis();
    	elenco.reset();
    	txtResult.setText("");
    	txtParola.setText("");
    	end=System.currentTimeMillis();
    	int tempoInt=(int) (end-start);
    	String tempoStr=String.valueOf(tempoInt);
    	txtTime.setText(tempoStr+" ms");
    }
    
    @FXML
    void doDelete(ActionEvent event) {
    	start=System.currentTimeMillis();
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
    	end=System.currentTimeMillis();
    	int tempoInt=(int) (end-start);
    	String tempoStr=String.valueOf(tempoInt);
    	txtTime.setText(tempoStr+" ms");
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
