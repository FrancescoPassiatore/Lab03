package it.polito.tdp.spellchecker.model;

public class RichWord {

	private String parola;
	private boolean correct;
	
	
	public RichWord(String parola, boolean correct) {
		this.parola = parola;
		this.correct = correct;
	}


	public String getParola() {
		return parola;
	}


	public boolean isCorrect() {
		return correct;
	}
	

	

}
