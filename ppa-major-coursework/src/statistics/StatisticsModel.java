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
	}

	public void setPanels(JTextPane nonUSjtaMessage, JTextPane hoaxesjtaMessage, String from, String to) {
		if (from != null && to != null) {
			data = ripley.getIncidentsInRange(from, to);
			if (data.size() == 0) {
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
			data = ripley.getIncidentsInRange(from, to);
			if (data.size() == 0) {
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
