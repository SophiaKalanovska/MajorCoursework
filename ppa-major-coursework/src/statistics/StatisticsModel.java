package statistics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import api.ripley.Incident;
import api.ripley.Ripley;

public class StatisticsModel {
	private static String totalResults;
	private static String thenInFormat;
	private HashMap<String, Integer> hash;
	private HashMap<String, Integer> hashshape;
	private Integer freq;
	private Ripley ripley;
	private Integer numberNonUs;
	private Integer numberOfHoax;
	private ArrayList<Incident> data;
	private ArrayList<String> state;
	private String like;
	private String likeshape;
	private ArrayList<String> shape;
	// private Search search;

	public StatisticsModel(Ripley ripley) {
		this.ripley = ripley;
		numberNonUs = 0;
		numberOfHoax = 0;
		totalResults = "";
		like = "";
		likeshape = "";
		hash = new HashMap<String, Integer>();
		data = new ArrayList<Incident>();
		state = new ArrayList<String>();
		hashshape = new HashMap<String, Integer>();
	}

	public void setPanels(JTextPane nonUSjtaMessage, JTextPane hoaxesjtaMessage, String from, String to) {
		
/*		System.out.println(data.isEmpty());
		System.out.println("from: " + from);
		System.out.println("to: " + to);
		data = ripley.getIncidentsInRange("2000-01-01 00:00:00", "2017-01-01 00:00:00");
		System.out.println(data.isEmpty());  */  
=======
	public void setHoaxes(String from, String to) {
		
		if (from != null && to != null) {
			
			data = ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
			
			System.out.println("Weeeesh: " + data.isEmpty());
			
			//if (data.size() == 0) {
			if (data.isEmpty()) {
				nonUSjtaMessage.setText("No data");
				hoaxesjtaMessage.setText("No data");
			} else {
				
			}
			data = ripley.getIncidentsInRange(from, to);
			for (int i = 0; i < data.size(); i++) {

				if (data.get(i).getSummary().toUpperCase().contains("HOAX")) {
					numberOfHoax += 1;
				}

			}
			System.out.println("number of hoaxes is" + numberOfHoax);
		}
	}

	public void setNonUS(String from, String to) {
		if (from != null && to != null) {
			data = ripley.getIncidentsInRange(from, to);


				for (int i = 0; i < data.size(); i++) {

					if (data.get(i).getState().equals("Not specified.")) {
						numberNonUs += 1;
					}
			}
		}
	}


	public void setlikely(JTextPane likeliestjtaMessage, String from, String to) {
		

	public void setLikely(String from, String to) {

		if (from != null && to != null) {

			
			data = ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
			//if (data.size() == 0) {
			if (data.isEmpty()) {
				likeliestjtaMessage.setText("No data");
			} else {

			data = ripley.getIncidentsInRange(from, to);
			

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
					like = getKeysByValue(hash, max);
				} else {
					like = "Not enough data";
				}
			}
		}
<<<<<<< HEAD
	}
	
/*	public String likeliestTime(String from, String to) {
		
		
		
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
		
	} */
	
	
	public String likeliestTime(String from, String to) {
		
		data = ripley.getIncidentsInRange(from + "-01-01 00:00:00", to + "-01-01 00:00:00");
		List<String> time = new ArrayList<String>();
		
		for (int i= 0; i < data.size(); i++) {
			
			time.add(data.get(i).getDateAndTime().substring(11, 16));
			
		}
        
		Set<String> unique = new HashSet<String>(time);

		
		if (data.isEmpty()) {
			
			System.out.println("sef le bg");
			
			return "No data";
			
		} else {
			
			System.out.println("sefthi le bg");
			
			for (int i = 0; i < data.size(); i++) {

				System.out.println(data.get(i).getDateAndTime().substring(11, 16));

			}
			
			for (String key : unique) {
			    System.out.println(key + ": " + Collections.frequency(time, key));
			}
			
			
		}
		
		return "pute";
		
	}
	
	
	public void setShape(String from, String to) {
		if (from != null && to != null) {
			data = ripley.getIncidentsInRange(from, to);
			
				shape = new ArrayList<String>();
				for (int i = 0; i < data.size(); i++) {
					shape.add(data.get(i).getShape());

				}
				Integer max = 0;
				for (int i = 0; i < shape.size(); i++) {
					freq = Collections.frequency(shape, shape.get(i));
					if (!(shape.get(i).equals("Not specified."))) {
						hashshape.put(shape.get(i), freq);
						if (freq > max) {
							max = freq;
						}
					}
				}
				if (hashshape.size() != 0) {
					likeshape = getKeysByValue(hashshape, max);
				} else {
					likeshape = "Not enough data";
				}
			}
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

	
	public static void getHTML() throws Exception {

		 DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		 	LocalDateTime now = LocalDateTime.now();
		 	LocalDateTime then = now.minusDays(7);
		 	
		 	thenInFormat = then.format(format);
	      
	      URL url = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&publishedAfter=" +thenInFormat +  "&type=video&q=UFO&key=AIzaSyCEoSb2iGC05a3th4f00-8x8iIbzQkbvR8");
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	    	Pattern pattern = Pattern.compile("totalResults");
	  		Matcher matcher = pattern.matcher(line);
	  		if (matcher.find()) {
	  			Pattern pattern1 = Pattern.compile("[0-9]+");
		  		Matcher matcher1 = pattern1.matcher(line);
		  		if (matcher1.find()) {
		  			totalResults = matcher1.group();
		  			
		  		}
		  		
	        // result.append(line);
	  		}
	      }
	      rd.close();
	     // return result.toString();
	   }
	

	public static String getTotalResults() {
		return totalResults;
		
	}

	public String getLike() {
		return like;
	}
	
	public String getNumberNonUs() {
		return numberNonUs + "";
	}

	public String getNumberOfHoax() {
		return numberOfHoax + "";
	}

	public String getLikeshape() {
		return likeshape;
	}

}
