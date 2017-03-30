package statistics;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.awt.Dimension;
import javax.swing.JPanel;
import api.ripley.Ripley;

public class StatisticsGui extends JPanel {
	private StatisticsModel statisticsModel;
	private SingleStatistic[] FourStatistics;
	private String[] stats;
	private Ripley ripley;
	private BufferedWriter writeInFile;
	private BufferedReader readFromFile;
	private static String savePath;

	public StatisticsGui(Ripley ripley) {
		super();
		this.ripley = ripley;
		savePath = "Saving the path";
		FourStatistics = new SingleStatistic[4];
		stats = new String[4];
		initWidgets();
	}

	public void initWidgets() {
		setLayout(new GridLayout(2, 2));
		statisticsModel = new StatisticsModel(ripley);
		for (int i = 0; i < FourStatistics.length; i++) {
			FourStatistics[i] = new SingleStatistic(statisticsModel, this);
			FourStatistics[i].setPreferredSize(new Dimension(375, 250));
			add(FourStatistics[i]);
		}
		readFromFile();
		try {
			initStats();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initStats() throws NumberFormatException, Exception {
		FourStatistics[0].resetDsiplayStats();
		for (int i = 0; i < FourStatistics.length; i++) {
			FourStatistics[i].initializeStat(Integer.parseInt(stats[i]));
		}
	}

	private void setDefaultFourPanels() {
		for (int i = 0; i < FourStatistics.length; i++) {
			stats[i] = i + 1 + "";
		}
	}
	
	public void panelSave() {
		String retString = "";
		for (int i = 0; i < FourStatistics.length; i++) {
			retString += FourStatistics[i].getStat() + " ";
		}

		try {
			writeInFile = new BufferedWriter(new FileWriter(savePath));
			writeInFile.write(retString);
			writeInFile.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void readFromFile() {
		try {
			readFromFile = new BufferedReader(new FileReader(savePath));
			String line = readFromFile.readLine();
			if (!line.isEmpty()) {
				stats = line.split(" ");
			} else {
				setDefaultFourPanels();
			}
		} catch (IOException e) {
			setDefaultSubPanels();
		}
	}
	
	private void setDefaultSubPanels() {
		for (int i = 0; i < FourStatistics.length; i++) {
			stats[i] = i + 1 + "";
		}
	}

	public void update(String from, String to) {

		from = from + "-01-01 00:00:00";
		to = to + "-12-31 00:00:00";

		statisticsModel.setHoaxes(from, to);
		statisticsModel.setNonUS(from, to);
		statisticsModel.setLikely(from, to);
		statisticsModel.setShape(from, to);
		statisticsModel.getMostLikelyTime(from, to);
		try {
			StatisticsModel.getHTML();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < FourStatistics.length; i++) {
			try {
				FourStatistics[i].updateStatistic(FourStatistics[i].getStat());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
