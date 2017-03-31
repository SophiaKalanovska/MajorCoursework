package statistics;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import api.ripley.Ripley;

public class SingleStatistic extends JLabel {
	private JButton rightButton;
	private JButton leftButton;
	private JLabel topLabel;
	private JLabel centerLabel;
	private int statisticNumber;
	private StatisticsModel statisticsModel;
	private StatisticsGui stat;
	private static ArrayList<Integer> displayedStatistics;

	public SingleStatistic(StatisticsModel statisticsModel, StatisticsGui stat) {
		super();
		setLayout(new BorderLayout());
		this.stat= stat;
		this.statisticsModel = statisticsModel;
		statisticNumber = 0;
		displayedStatistics = new ArrayList<Integer>(4);
		initWidgets();
	}

	private void initWidgets() {

		rightButton = new JButton(">");
		rightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					setStatPlus(getStat() + 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				stat.panelSave();
			}
		});
		leftButton = new JButton("<");
		leftButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					setStatMinus(getStat() - 1);
				} catch (Exception e) {
					e.printStackTrace();				
				}
				stat.panelSave();
			}
		});

		topLabel = new JLabel("Top", SwingConstants.CENTER);
		centerLabel = new JLabel("Cent", SwingConstants.CENTER);

		add(leftButton, BorderLayout.WEST);
		add(rightButton, BorderLayout.EAST);
		add(centerLabel, BorderLayout.CENTER);
		add(topLabel, BorderLayout.NORTH);
	}

	public void initializeStat(int i) throws Exception {
		updateStatistic(i);
		displayedStatistics.add(i);
	}

	public void setStatPlus(int i) throws Exception {
		int statsNumber = 8;
		if (i > statsNumber) {
			i = 1;
		} else if (i < 1) {
			i = 8;
		}
		while (displayedStatistics.contains(i)) {
			if (i >= statsNumber) {
				i = 1;
			} else if (i < 1) {
				i = 8;
			} else
				i++;
		}
		displayedStatistics.remove((Integer) statisticNumber);
		updateStatistic(i);
		displayedStatistics.add(statisticNumber);
	}

	public void setStatMinus(int i) throws Exception {
		int statsNumber = 8;
		if (i > statsNumber) {
			i = 1;
		} else if (i < 1) {
			i = 8;
		}
		while (displayedStatistics.contains(i)) {
			if (i > statsNumber) {
				i = 1;
			} else if (i <= 1) {
				i = 8;
			} else
				i--;
		}
		displayedStatistics.remove((Integer) statisticNumber);
		updateStatistic(i);
		displayedStatistics.add(statisticNumber);
	}

	public void resetDsiplayStats() {
		displayedStatistics.clear();
	}

	public int getStat() {
		return statisticNumber;
	}

	protected void updateStatistic(int i) throws Exception {
		if (i == 1) {
			statisticNumber = 1;
			topLabel.setText("Hoaxes");
			centerLabel.setText(statisticsModel.getNumberOfHoax());
		} else if (i == 2) {
			statisticNumber = 2;
			topLabel.setText("Non-US Sightings");
			centerLabel.setText(statisticsModel.getNumberNonUs());
		} else if (i == 3) {
			statisticNumber = 3;
			topLabel.setText("Likeliest State");
			centerLabel.setText(statisticsModel.getLike());
		} else if (i == 4) {
			statisticNumber = 4;
			topLabel.setText("Youtube Videos published within past week");
			centerLabel.setText(statisticsModel.getLongestDuration() + "");
		} else if (i == 5) {
			statisticNumber = 5;
			topLabel.setText("Most common shape");
			centerLabel.setText(statisticsModel.getLikeshape());
			System.out.println("Common shape called");
		} else if (i == 6) {
			statisticNumber = 6;
			topLabel.setText("Most likely time frame ");
			centerLabel.setText(statisticsModel.getMostLikelySightingTimeFrame());
		} else if (i == 7) {
			statisticNumber = 7;
			topLabel.setText("Longest Sighting");
			//centerLabel.setText(statisticsModel);
		} else if (i == 8) {
			statisticNumber = 8;
			topLabel.setText("Likeliest City");
			centerLabel.setText(statisticsModel.getCurrentCity());

		}
		repaint();
	}

}
