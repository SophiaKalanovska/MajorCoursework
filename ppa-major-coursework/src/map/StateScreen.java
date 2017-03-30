package map;

import java.awt.Dimension;

import javax.swing.JFrame;

public class StateScreen extends JFrame {

	private String stateShort;
	private String stateFull;

	public StateScreen(String state) {
		super();
		stateShort = state;
		stateFull = shortToFull(stateShort);
		setTitle(stateFull + " (" + stateShort + ")");
		setPreferredSize(new Dimension(500,300));
		pack();
	}

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
}
