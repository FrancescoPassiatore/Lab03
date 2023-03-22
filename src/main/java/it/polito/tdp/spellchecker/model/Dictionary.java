package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	List<String> dizionario = new ArrayList<String>();
	int conta;
	
	public void loadDictionary(String language) {
		conta =0;
		dizionario.clear();
		
		if (language.compareTo("Italiano")==0) {
		try {
			FileReader fr = new FileReader("C:\\Users\\frank\\git\\Lab03\\src\\main\\resources\\Italian.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while((word = br.readLine()) !=null) {
				conta++;
				dizionario.add(word);
			}
			br.close();
		} catch ( IOException e ){
			System.out.println("Errore nella lettura del file");
		}
		}
		
		if (language.compareTo("English")==0) {
		try {
			FileReader fr = new FileReader("C:\\Users\\frank\\git\\Lab03\\src\\main\\resources\\English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while((word = br.readLine()) !=null) {
				conta++;
				dizionario.add(word);
			}
			br.close();
		} catch ( IOException e ){
			System.out.println("Error while reading the file");
		}
		}	
	}
	

	public List<RichWord> spellCheckText (List<String> inputTextList) {
		
		List<RichWord> listaWord = new ArrayList<RichWord>();
		
		for (String s : inputTextList) {
			if ( this.dizionario.contains(s)) {
				listaWord.add(new RichWord (s,true));
				}
			else {
				listaWord.add(new RichWord ( s, false));
			}
		}
		
		return listaWord;
		
		
	}
	
	public List<RichWord> spellCheckTextLinear (List<String> inputTextList){
		
		List <RichWord> listaWord = new ArrayList<RichWord>();
		boolean controllo = false;
		
		for (String s : inputTextList) {
			controllo =false;
			for ( String word : dizionario) {
				if (s.compareTo(word)==0) {
					listaWord.add(new RichWord(s, true));
					controllo = true;
				}
				}
			if (controllo == false ) {
				listaWord.add(new RichWord(s,false));
			}
		}
	
		
		return listaWord;
		
	}
	
	public List<RichWord> spellCheckTextDichotomic (List<String> inputTextList){
		
		List <RichWord> listWord = new ArrayList<RichWord>();
		
		int cont = conta/2;
		
		for (String s : inputTextList) {
			
				if (s.compareTo(this.dizionario.get(cont))==0){
					listWord.add(new RichWord(s,true));
				}
				else if (s.compareTo(this.dizionario.get(cont))<0) {
					for ( int i=0 ; i<=cont ; i++) {
						if (s.compareTo(this.dizionario.get(i))==0) {
							listWord.add(new RichWord(s,true));
						}
					}
					listWord.add(new RichWord(s,false));
				}
				else if (s.compareTo(this.dizionario.get(cont))>0) {
					for (int i =cont ; i<=this.dizionario.size();i++) {
						if (this.dizionario.get(i)!=null) {
						 if (s.compareTo(this.dizionario.get(i))==0) {
							listWord.add(new RichWord(s,true));
						 }
					}
						else if (this.dizionario.get(i)==null) {
							listWord.add(new RichWord(s,false));
						}
					}
					listWord.add(new RichWord(s,false));
					}
		}
		return listWord;
	}

}
