/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.meteo;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.model.Model;
import it.polito.tdp.meteo.model.Rilevamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	private Model model;
	ObservableList list= FXCollections.observableArrayList();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxMese"
    private ChoiceBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnUmidita"
    private Button btnUmidita; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaSequenza(ActionEvent event) {
    	txtResult.clear();
    	int mese= 0;
    	try {
    	 mese = boxMese.getValue();
    	}catch(NullPointerException npe) {
    		txtResult.setText("Errore inserire un mese nella choicebox");
    		return;
    	}
    	List<Rilevamento> a= new ArrayList<Rilevamento>();
    	long  start=System.currentTimeMillis();
    	a=model.trovaSequenza(mese);
    	long  finish=System.currentTimeMillis();
    	txtResult.appendText("Il tempo è "+(finish-start)+"ms"+"\n");
		String risultato= "";
		for(Rilevamento r:a) {
			String s="";
			s=s+r.toString()+"\n";
			txtResult.appendText(s);
		}
		System.out.println(risultato);
	}
 

    @FXML
    void doCalcolaUmidita(ActionEvent event) {
    	// mettere il controllo utente 
    	txtResult.clear();
    	int mese= 0;
    	try {
    	 mese = boxMese.getValue();
    	}catch(NullPointerException npe) {
    		txtResult.setText("Errore inserire un mese nella choicebox");
    		return;
    	}
    	Map<String,Double> risultato= new HashMap<String,Double>();
    	risultato=model.getUmiditaMedia(mese);
    	for(String s: risultato.keySet()) {
    		txtResult.appendText(s+" "+risultato.get(s)+"\n");
    		
    	}
    	
    }
    
    private void loadData() {
    	list.removeAll();
    	List<Integer> numeri= new ArrayList<Integer>();
    	for(int i=0; i<12;i++) {
    		numeri.add(i+1);
    	}
    	list.addAll(numeri);
    	boxMese.getItems().addAll(list);
//    	txtWords.setEditable(true);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        loadData();

    }
    public void setModel(Model model) {
    	this.model=model;
    }
}

