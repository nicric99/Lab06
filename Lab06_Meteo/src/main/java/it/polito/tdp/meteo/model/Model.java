package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	List<Rilevamento> risultato= new ArrayList<Rilevamento>();
	
	MeteoDAO md= new MeteoDAO();
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	boolean flag =false;

	public Model() {

	}

	// of course you can change the String output with what you think works best
	public Map<String,Double> getUmiditaMedia(int mese) {
		return(md.getUmiditMedia(mese));
	}
	
	// of course you can change the String output with what you think works best
	public List<Rilevamento> trovaSequenza(int mese) {
		List<Rilevamento> parziale= new ArrayList<Rilevamento>();
		
		cerca1(parziale,1,mese);
		// qui completo la soluzione
		
		return this.risultato;
	}
	private void cerca1(List<Rilevamento> parziale,int livello,int mese){
		// condizioni di uscita
		
		// controlllo che la sol sia valida
		if(!this.soluzioneValida(parziale)) {
			return;
		}
		// condizioni siamo  arrivati al 15esimo giorno
		if(livello==16) {
		
			Double costoParziale= calcolaCosto(parziale);
			if(flag==false) {
				risultato= new ArrayList<Rilevamento>(parziale);
				flag=true;
			}else {
			if(costoParziale< calcolaCosto(risultato)) {
				risultato= new ArrayList<Rilevamento>(parziale);
			}
			return;
			}
		}
		//originale
		/*List<Rilevamento> temp= new ArrayList<Rilevamento>();
		temp=md.getRilevamentoByGiorno(mese, livello);
		for(Rilevamento r:temp) {
			parziale.add(r);
			cerca1(parziale,livello+1,mese);
			parziale.remove(r);
		}*/
		
		// qua sviluppo la query che andrà su meteo dao e questa è la soluzione bruta
		List<Rilevamento> temp= new ArrayList<Rilevamento>();
		temp=md.getRilevamentoByGiorno(mese, livello);
		for(Rilevamento r:temp) {
			parziale.add(r);
			if(livello==1) {
				Rilevamento r1;
				Rilevamento r2;
				// ipotizziamo un singolo rilevamento per citta per giorno
				r1=md.getSingoloRilevamento(mese, livello+1,r.getLocalita());
				r2=md.getSingoloRilevamento(mese, livello+2,r.getLocalita());
				if(r1.getUmidita()!=0 && r2.getUmidita()!=0) {
				parziale.add(r1);
				parziale.add(r2);
				cerca1(parziale,livello+3,mese);
				parziale.remove(r2);
				parziale.remove(r1);}
			}else {
			cerca1(parziale,livello+1,mese);
			parziale.remove(r);
			}
			
		}
		
		
		
		
	}
	
	
	public Double calcolaCosto(List<Rilevamento> parziale){
		double costo= 0;
		for(int i=0; i< parziale.size();i++) {
			costo=costo+parziale.get(i).getUmidita();
			String localita= parziale.get(i).getLocalita();
			if(i>0) {
			if(parziale.get(i-1).getLocalita().equals(localita)==false) {
				costo=costo+100;
				
			}}
			
		}
		return costo;
	}
	
	public boolean soluzioneValida(List<Rilevamento> parziale) {
		Map<String,Integer> contatore= new HashMap<String,Integer>();
		Set<Citta> posti= new HashSet<Citta>();
		posti= md.getCitta();
		int counter=1;
	
		for(int r=0; r<parziale.size();r++) {
		
			for(Citta c: posti) {
				if(parziale.get(r).getLocalita().equals(c.getNome())) {
					c.setCounter(c.getCounter()+1);
				}
			}
			if(r!=0) {
				if(parziale.get(r-1).getLocalita().equals(parziale.get(r).getLocalita())) {
				counter++;
				}
				else {
					if(counter<3){
						return false;
					}
					else{
						counter=1;
					}
				}
		}}
		for(Citta c:posti) {
			if(c.getCounter()>6) {
				return false;
			}
		}
		return true;
	}
		
	


}
