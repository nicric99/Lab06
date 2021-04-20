package it.polito.tdp.meteo.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.*;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		System.out.println(m.getUmiditaMedia(2));
		
//		System.out.println(m.trovaSequenza(5));
//		LocalDate myDate2 = new LocalDate(2015,Month.APRIL,2);
	/*Rilevamento r1= new Rilevamento("Milano",null,100);
		Rilevamento r2= new Rilevamento("Milano",null,100);
		Rilevamento r3= new Rilevamento("Milano",null,50);
		Rilevamento r4= new Rilevamento("Milano",null,50);
		Rilevamento r5= new Rilevamento("Milano",null,50);
		Rilevamento r6= new Rilevamento("Torino",null,50);
		Rilevamento r7= new Rilevamento("Torino",null,50);*/
		
		List<Rilevamento> a= new ArrayList<Rilevamento>();
		/*a.add(r1);
		a.add(r2);
		a.add(r3);
		a.add(r4);
		a.add(r5);
		a.add(r6);
		a.add(r7);*/
		a=m.trovaSequenza(1);
		String risultato= "";
		for(Rilevamento r:a) {
			String s="";
			s=s+r.toString()+"\n";
			risultato=risultato+s;
		}
		System.out.println(risultato);
	}

}
