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
		hashshape = new HashMap<String, Integer>();
	}

	public void setHoaxes(String from, String to) {
		
		if (from != null && to != null) {
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

	public void setLikely(String from, String to) {
		if (from != null && to != null) {
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
