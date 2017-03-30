package statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.JTextPane;

import api.ripley.Incident;
import api.ripley.Ripley;

public class StatisticsModel {
	private HashMap<String, Integer> hash;
	private Integer freq;
	private Ripley ripley;
	private Integer numberNonUs;
	private Integer numberOfHoax;
	private ArrayList<Incident> data;
	private ArrayList<String> state;
	//private Search search;

	public StatisticsModel(Ripley ripley) {
		this.ripley = ripley;
		numberNonUs = 0;
		numberOfHoax = 0;
		hash = new HashMap<String, Integer>();
		data = new ArrayList<Incident>();
		state = new ArrayList<String>();
	}

	public void setPanels(JTextPane nonUSjtaMessage, JTextPane hoaxesjtaMessage, String from, String to) {
		
/*		System.out.println(data.isEmpty());
		System.out.println("from: " + from);
		System.out.println("to: " + to);
		data = ripley.getIncidentsInRange("2000-01-01 00:00:00", "2017-01-01 00:00:00");
		System.out.println(data.isEmpty());  */  
		
		if (from != null && to != null) {
			
			data = ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
			
			System.out.println("Weeeesh: " + data.isEmpty());
			
			//if (data.size() == 0) {
			if (data.isEmpty()) {
				nonUSjtaMessage.setText("No data");
				hoaxesjtaMessage.setText("No data");
			} else {
				for (int i = 0; i < data.size(); i++) {

					if (data.get(i).getState().equals("Not specified.")) {
						numberNonUs += 1;
					}

					if (data.get(i).getSummary().toUpperCase().contains("HOAX")) {
						numberOfHoax += 1;
					}

				}
				nonUSjtaMessage.setText(String.valueOf(numberNonUs));
				hoaxesjtaMessage.setText(String.valueOf(numberOfHoax));

			}
		}
	}

	public void setlikely(JTextPane likeliestjtaMessage, String from, String to) {
		
		if (from != null && to != null) {
			
			data = ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
			//if (data.size() == 0) {
			if (data.isEmpty()) {
				likeliestjtaMessage.setText("No data");
			} else {
				state = new ArrayList<String>();
				for (int i = 0; i < data.size(); i++) {
					state.add(data.get(i).getState());

				}
				Integer max = 0;
				for (int i = 0; i < state.size(); i++) {
					freq = Collections.frequency(state, state.get(i));
					if (!(state.get(i).equals("Not specified."))) {
						hash.put(state.get(i), freq);
						if (freq > max) {
							max = freq;
						}
					}
				}
				if (hash.size() != 0) {
					likeliestjtaMessage.setText(getKeysByValue(hash, max));
				}else{
					likeliestjtaMessage.setText("Not enough data");
				}
			}
		}
	}
	
	public String likeliestTime(String from, String to) {
		
		
		
		data = ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
		
		double conversionTime = (100.0/60.0);

		int averageHour = 0;
		float averageMinute = 0;
		
		if (data.isEmpty()) {
			
			System.out.println("sef le bg");
			
			return "No data";
			
		} else {
			
			System.out.println("sefthi le bg");
			
			for (int i = 0; i < data.size(); i++) {
				
			//	System.out.println(data.get(i).getDateAndTime().substring(11, 13));
			//	System.out.println(data.get(i).getDateAndTime().substring(14, 16));
				System.out.println(data.get(i).getDateAndTime().substring(11, 16));
				
				averageHour += Integer.parseInt(data.get(i).getDateAndTime().substring(11, 13));
				averageMinute += ((Float.parseFloat(data.get(i).getDateAndTime().substring(14, 16))));
				
			}
			
			
		}
		
		System.out.println(averageMinute * (100/60));
		System.out.println(averageMinute);
		
		averageMinute *= conversionTime;
		
		System.out.println("Average hour: " + averageHour / data.size());
		System.out.println("Average minutes: " + averageMinute / data.size());
		
		return "pute";
		
	}

	public static <T, E> String getKeysByValue(Map<T, E> map, E value) {
		Set<T> keys = new HashSet<T>();
		String key = "";
		for (Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				keys.add(entry.getKey());
				key = keys.toString();
			}
		}
		return key;
	}

}
