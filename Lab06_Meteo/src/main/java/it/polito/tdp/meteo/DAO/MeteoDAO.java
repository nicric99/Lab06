package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Date data=rs.getDate("Data");
				LocalDate dataj= data.toLocalDate();

				Rilevamento r = new Rilevamento(rs.getString("Localita"), dataj, rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {

		return null;
	}
	public Map<String,Double> getUmiditMedia(int mese){
		final String sql= "SELECT AVG(situazione.Umidita) AS Media, situazione.Localita "+
				 "FROM situazione "+
				 "WHERE MONTH(situazione.data)=? "+
				 "GROUP BY situazione.Localita ";
		
		Map<String,Double> risultato= new HashMap<String,Double>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, mese);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				risultato.put(rs.getString("Localita"), rs.getDouble("Media"));
			}

			conn.close();
			return risultato;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	public List<Rilevamento> getRilevamentoByGiorno(int mese,int giorno){
		final String sql= "SELECT situazione.Localita,situazione.`Data`,situazione.Umidita "+
				 "FROM situazione "+
				 "WHERE MONTH(situazione.data)=? AND DAY(situazione.`Data`)=? ";
		List<Rilevamento> risultato= new ArrayList<Rilevamento>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, mese);
			st.setInt(2, giorno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				LocalDate d1=rs.getDate("Data").toLocalDate();
				risultato.add(new Rilevamento(rs.getString("Localita"),d1,rs.getInt("Umidita")));
			}

			conn.close();
			return risultato;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
	}
	public Rilevamento getSingoloRilevamento(int mese,int giorno,String citta){
		
		// PROVA OTTIMIZZAZIONE
		final String sql= "SELECT situazione.Localita,situazione.`Data`,situazione.Umidita "+
				 "FROM situazione "+
				 "WHERE MONTH(situazione.data)=? AND DAY(situazione.`Data`)=? AND situazione.Localita=? ";
		List<Rilevamento> risultato= new ArrayList<Rilevamento>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, mese);
			st.setInt(2, giorno);
			st.setString(3,citta);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				LocalDate d1=rs.getDate("Data").toLocalDate();
				return new Rilevamento(rs.getString("Localita"),d1,rs.getInt("Umidita"));
			}

			conn.close();
			return null;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
	}}
	
	
	
	
	public HashSet<Citta> getCitta(){
		
		final String sql= "SELECT DISTINCT "
				+ "situazione.Localita "
				+ "FROM situazione ";
		
		HashSet<Citta> citta= new HashSet<Citta>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Citta local= new Citta(rs.getString("Localita"));
				citta.add(local);
			}

			conn.close();
			return citta;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}


}
