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
	
	private String currentCity;
	// private Search search;
	private String mostLikelySightingTimeFrame;

	public StatisticsModel(Ripley ripley) {
		this.ripley = ripley;
		numberNonUs = 0;
		numberOfHoax = 0;
		totalResults = "";
		like = "";
		likeshape = "";
		currentCity = "";
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
				if (max == 1){
					likeshape = "All equally likely";
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
	


	public void getMostLikelyTime(String from, String to) {
		if(from != null && to != null){
			
			int timeFrames[] = new int[8];
			
			ArrayList<Incident> allIncidentsFromSelectedRange = ripley.getIncidentsInRange(from, to);
						
			Pattern twentyFourHourPattern = Pattern.compile("\\d\\d:\\d\\d:\\d\\d");
			for(Incident i : allIncidentsFromSelectedRange){
				Matcher twentyFourHourMatcher = twentyFourHourPattern.matcher(i.getDateAndTime());
				
				if(twentyFourHourMatcher.find()){
					
					String time = twentyFourHourMatcher.group(0).substring(0, 5);
						
					String newTime = time.substring(0, 2);
					int newTimeInt = Integer.parseInt(newTime);
					
					if(newTimeInt >= 00 && newTimeInt <= 03) timeFrames[0]++;     //00-03;
					else if(newTimeInt >= 3 && newTimeInt < 6) timeFrames[1]++;   //03-06;
					else if(newTimeInt >= 6 && newTimeInt < 9) timeFrames[2]++;   //06-09;
					else if(newTimeInt >= 9 && newTimeInt < 12) timeFrames[3]++;  //09-12;
					else if(newTimeInt >= 12 && newTimeInt < 15) timeFrames[4]++; //12-15;
					else if(newTimeInt >= 15 && newTimeInt < 18) timeFrames[5]++; //15-18;
					else if(newTimeInt >= 18 && newTimeInt < 21) timeFrames[6]++; //18-21;
					else if(newTimeInt >= 21 && newTimeInt < 24) timeFrames[7]++; //21-00;
				}
			}
			
			int largest = 0;
			int indexWithLargestSightings = 0;
			for(int k = 0; k < timeFrames.length; k++){
				if(timeFrames[k] > largest){
					largest = timeFrames[k];
					indexWithLargestSightings = k;
				}
			}
			setMostLikelyTimeString(indexWithLargestSightings, timeFrames[indexWithLargestSightings]);
		}
	}
	private void setMostLikelyTimeString(int index, int num){
		if(num != 0){
			if(index == 0) mostLikelySightingTimeFrame  = "<html><center>12 AM - 3 AM.</center><br> <center>  Number of reports: " + num+ "</center></html>";
			else if(index == 1) mostLikelySightingTimeFrame  = "<html><center>3 AM - 6 AM.</center><br> <center> Number of reports: " + num+ "</center></html>";
			else if(index == 2) mostLikelySightingTimeFrame  = "<html><center>6 AM - 9 AM.</center><br> <center>  Number of reports: " + num+ "</center></html>";
			else if(index == 3) mostLikelySightingTimeFrame  = "<html><center>9 AM - 12 PM.</center><br> <center>  Number of reports: " + num+ "</center></html>";
			else if(index == 4) mostLikelySightingTimeFrame  = "<html><center>12 PM - 3 PM.</center><br> <center>  Number of reports: " + num+ "</center></html>";
			else if(index == 5) mostLikelySightingTimeFrame  = "<html><center>3 PM - 6 PM.</center><br> <center>  Number of reports: " + num+ "</center></html>";
			else if(index == 6) mostLikelySightingTimeFrame  = "<html><center>6 PM - 9 PM.</center><br> <center> Number of reports: " + num+ "</center></html>";
			else if(index == 7) mostLikelySightingTimeFrame  = "<html><center>9 PM - 12 AM.</center><br> <center> Number of reports: " + num + "</center></html>";
		} else {
			mostLikelySightingTimeFrame = "No Data";
		}
	}
	
	public String getMostLikelySightingTimeFrame() {
		return mostLikelySightingTimeFrame;
	}
	


	/*	
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
		
	}*/
     
     public String likeliestCity(String from, String to) {
 		
 	data = ripley.getIncidentsInRange(from, to);
 		
 		List<String> city = new ArrayList<String>();
 		
 		ArrayList<Integer> count = new ArrayList<Integer>();
 		int current = 0;
 	//	String currentCity = "";
 		
 		for (int i= 0; i < data.size(); i++) {
 			
 			city.add(data.get(i).getCity());
 			
 		}
         
 		Set<String> unique = new HashSet<String>(city);
 	
 		if (data.isEmpty()) {
 			
		    System.out.println("sef le bg");
 			
 			return "No data";
 			
 		} else {
 			
 			System.out.println("sefthi le bg");
 			
 			for (int i = 0; i < data.size(); i++) {
 				

 			//	System.out.println(data.get(i).getCity());
 				
 				for (String key : unique) {
 					
 				//	System.out.println(key + ": " + Collections.frequency(city, key));
 					
 					if (Collections.frequency(city, key) == 1 && data.size() > 1) {
 						
 						count.add(Collections.frequency(city, key));
 						//System.out.println("COunt : " +count);
 						
 						//currentCity = "All cities are equally likely";
 						
 					} 
 						
 						if (Collections.frequency(city, key) > current) {
 		 			    	
 		 			    	current = Collections.frequency(city, key);
 		 			    	currentCity = key;
 		 			  //  	System.out.println("current: " + current);
 		 			  //  	System.out.println("Current city: " + currentCity);
 		 			    	
 		 			    }
 						
 						
 					
 					
 				}

 			}
 			
 			if (counting(count) && count.size() > 1) {
 				
 				
 				currentCity = "All cities had 1 occurence exactly";
 				
 			}
 			
 			

 			

 			System.out.println("GROSSE FOLLE : " + currentCity);
 			
 		} 
 		
 		return currentCity; 
 		
 	}
     
     public boolean counting(ArrayList<Integer> c) {
    	 
    	 System.out.println("ca part de la ");
    	 
 			for (int b = 0; b < c.size(); b++) {
 				
 				System.out.println(b);
 				
 				if (c.get(b) != 1) {
 					
 					System.out.println("fooooollllleee");
 					return false;
 					
 				}
 				
 			}
    	 
    	 return true;
    	 
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
	
	public String getCurrentCity() {
		
		System.out.println("connasse");
		System.out.println(currentCity);
		return currentCity;
		
	}

}
