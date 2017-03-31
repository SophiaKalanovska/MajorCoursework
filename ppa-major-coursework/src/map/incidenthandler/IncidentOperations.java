package map.incidenthandler;

public class IncidentOperations {
	
	public static String shortToFull(String stateShort) {
		switch (stateShort) {
		case "AZ":
			return "Arizona";
		case "TN":
			return "Tennessee";
		case "IN":
			return "Indiana";
		case "TX":
			return "Texas";
		case "WA":
			return "Washington";
		case "MN":
			return "Minnesota";
		case "AL":
			return "Alabama";
		case "OR":
			return "Oregon";
		case "WV":
			return "West Virginia";
		case "NY":
			return "New York";
		case "FL":
			return "Florida";
		case "WI":
			return "Wisconsin";
		case "NJ":
			return "New Jersey";
		case "OK":
			return "Oklahoma";
		case "MI":
			return "Michigan";
		case "CA":
			return "California";
		case "NE":
			return "Nebraska";
		case "IL":
			return "Illinois";
		case "ID":
			return "Idaho";
		case "GA":
			return "Georgia";
		case "OH":
			return "Ohio";
		case "AR":
			return "Arkansas";
		case "MA":
			return "Massachusetts";
		case "UT":
			return "Utah";
		case "LA":
			return "Louisiana";
		case "NC":
			return "North Carolina";
		case "NV":
			return "Nevada";
		case "PA":
			return "Pennsylvania";
		case "IA":
			return "Iowa";
		case "MD":
			return "Maryland";
		case "AK":
			return "Alaska";
		case "NH":
			return "New Hampshire";
		case "KY":
			return "Kentucky";
		case "CO":
			return "Colorado";
		case "SC":
			return "South Carolina";
		case "MT":
			return "Montana";
		case "CT":
			return "Connecticut";
		case "MO":
			return "Missouri";
		case "VA":
			return "Virginia";
		case "ND":
			return "North Dakota";
		case "KS":
			return "Kansas";
		case "MS":
			return "Mississippi";
		case "ME":
			return "Maine";
		case "VT":
			return "Vermont";
		case "SD":
			return "South Dakota";
		case "RI":
			return "Rhose Island";
		case "HI":
			return "Hawaii";
		case "DE":
			return "Delaware";
		case "NM":
			return "New Mexico";
		case "WY":
			return "Wyoming";
		}
		return null;
	}
	
	public static IncidentWrapper[] localIncidents(IncidentWrapper[] incidents, String state) {
		IncidentWrapper[] localIncidents = new IncidentWrapper[incidents.length]; //upper bound
		int j=0;
		for (int i=0; i<incidents.length; i++) {
			if (incidents[i] != null) {
				if (incidents[i].getState().equals(state)) {
					localIncidents[j] = incidents[i];
					j++;
				}
			}
		}
		return trim(localIncidents);
	}

	public static IncidentWrapper[] trim(IncidentWrapper[] array) {
		int occupied = 0;
		for (int i=0; i<array.length; i++) {
			if (array[i] != null) {
				occupied++;
			}
		}
		IncidentWrapper[] trimmed = new IncidentWrapper[occupied];
		int j = 0;
		while (j<occupied) {
			for (int i=0; i<array.length; i++) {
				if (array[i] != null) {
					trimmed[j] = array[i];
					j++;
				}
			}
		}
		return trimmed;
	}
}
