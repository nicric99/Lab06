package it.polito.tdp.meteo.model;

import java.time.LocalDate;
import java.util.Date;

public class Rilevamento {
	
	private String localita;
	private LocalDate data;
	private int umidita;

	public Rilevamento(String localita, LocalDate data, int d) {
		this.localita = localita;
		this.data = data;
		this.umidita = d;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getUmidita() {
		return umidita;
	}

	public void setUmidita(int umidita) {
		this.umidita = umidita;
	}

	// @Override
	// public String toString() {
	// return localita + " " + data + " " + umidita;
	// }

	@Override
	public String toString() {
		return String.valueOf(umidita)+ " "+ this.getLocalita()+" "+this.getData().toString();
	}

	

}
