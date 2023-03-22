package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController {
	
	
	List<RichWord> listaWord = new ArrayList<RichWord>();
	Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClearText;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private ChoiceBox<String> choiceBoxLanguage;

    @FXML
    private Label lblErrorsFound;

    @FXML
    private Label lblSpellCheckTime;

    @FXML
    private TextField txtAreaInsertWord;

    @FXML
    private TextField txtAreaWrongWords;

    @FXML
    void doClearText(ActionEvent event) {
    	
    	this.txtAreaInsertWord.clear();
    	this.txtAreaWrongWords.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	List<String> listaParole = new ArrayList<String>();
    	
    	this.txtAreaWrongWords.clear();
    	
    	this.model.loadDictionary(this.choiceBoxLanguage.getValue());
    	
    	String[] parole;
    	parole = this.txtAreaInsertWord.getText().toLowerCase().split(" ");
        for (String s : parole) {
        	s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'()\\[\\]\"]", "");
        	listaParole.add(s);
        }
        
        long start = System.nanoTime();
       // listaWord = this.model.spellCheckText(listaParole);
       // listaWord = this.model.spellCheckTextLinear(listaParole);
        listaWord = this.model.spellCheckTextDichotomic(listaParole);
        long end = System.nanoTime();
        
        this.lblSpellCheckTime.setText("Spell check completed in : "+ ((end-start)/1000000000.0));
       
        int cont = 0;
        for (RichWord rw : listaWord) {
        		if (rw.isCorrect()== false) {
        		 this.txtAreaWrongWords.appendText(rw.getParola()+",");
        		 cont++;
        		 } 
        		}
        this.lblErrorsFound.setText("Errors found :"+cont );
        }

    
    
    public void setModel(Dictionary model) {
    	this.model=model;
    }
    

    @FXML
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert choiceBoxLanguage != null : "fx:id=\"choiceBoxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrorsFound != null : "fx:id=\"lblErrorsFound\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblSpellCheckTime != null : "fx:id=\"lblSpellCheckTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaInsertWord != null : "fx:id=\"txtAreaInsertWord\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAreaWrongWords != null : "fx:id=\"txtAreaWrongWords\" was not injected: check your FXML file 'Scene.fxml'.";
        this.choiceBoxLanguage.getItems().add("Italiano");
        this.choiceBoxLanguage.getItems().add("English");

    }
    
   

}
