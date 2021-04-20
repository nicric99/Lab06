package it.polito.tdp.meteo.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	MeteoDAO md= new MeteoDAO();
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	public Model() {

	}

	// of course you can change the String output with what you think works best
	public Map<String,Double> getUmiditaMedia(int mese) {
		return(md.getUmiditMedia(mese));
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		
		
		
		
		
		return ;
		
	}
	

}
