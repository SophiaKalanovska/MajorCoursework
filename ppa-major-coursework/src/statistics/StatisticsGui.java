package statistics;

import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import api.ripley.Ripley;

public class StatisticsGui extends JPanel {
	private StatisticsModel statisticsModel;
	private SingleStatistic[] FourStatistics;
	private String[] stats;
	private Ripley ripley;

	public StatisticsGui(Ripley ripley) {
		super();
		this.ripley = ripley;
		FourStatistics = new SingleStatistic[4];
		stats = new String[4];
		initWidgets();
	}

	public StatisticsGui(String from, String to, Ripley ripley) {
		
		this.from = from;
		this.to = to;
		
	public void initWidgets() {
		setLayout(new GridLayout(2, 2));

		statisticsModel = new StatisticsModel(ripley);
		for (int i = 0; i < FourStatistics.length; i++) {
			FourStatistics[i] = new SingleStatistic(statisticsModel);
			FourStatistics[i].setPreferredSize(new Dimension(375, 250));
			add(FourStatistics[i]);
		}
		setDefaultSubPanels();
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

		});
		
		

		jcbTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (jcbTo.getSelectedItem() != null) {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					setTo((String) jcbTo.getSelectedItem() + "-12-31 00:00:00");
					try {
						Date toAsDate = df.parse(to);
						setTo(df.format(toAsDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					statisticsModel.setPanels(nonUSjtaMessage, hoaxesjtaMessage, from, to);
					statisticsModel.setlikely(likeliestjtaMessage, from, to);
				}

			}
		});  */
		
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		setFrom((String) from + "-01-01 00:00:00");
		
		
//		statisticsModel.setPanels(nonUSjtaMessage, hoaxesjtaMessage, from, to);
//		statisticsModel.setlikely(likeliestjtaMessage, from, to);

		this.setLayout(new BorderLayout());
		JPanel jpNorth = new JPanel();
		JPanel jpSouth = new JPanel();
		this.add(jpNorth, BorderLayout.NORTH);
		this.add(jpSouth, BorderLayout.SOUTH);


		hoaxesright = new JButton(">");
		hoaxesleft = new JButton("<");
		hoaxeslabel = new JLabel(" Hoaxes", SwingConstants.CENTER);
		hoaxesjtaMessage = new JTextPane();
		hoaxesjtaMessage.setBackground(null);
		
		nonUSright = new JButton(">");
		nonUSleft = new JButton("<");
		nonUSlabel = new JLabel("Non-US Sightings", SwingConstants.CENTER);
		nonUSjtaMessage = new JTextPane();
		nonUSjtaMessage.setBackground(null);
        
		likeliestright = new JButton(">");
		likeliestleft = new JButton("<");
		likeliestlabel = new JLabel("Likeliest State", SwingConstants.CENTER);
		likeliestjtaMessage = new JTextPane();
		likeliestjtaMessage.setBackground(null);
        
		otherright = new JButton(">");
		otherleft = new JButton("<");
		otherlabel = new JLabel("Sightings via other platforms", SwingConstants.CENTER);
		otherjtaMessage = new JTextPane();
		otherjtaMessage.setBackground(null);
		otherjtaMessage.setEditable(false);
        
		jpWest1 = new JPanel();
		jpWest1.setLayout(new BorderLayout());
		jpWest1.setMinimumSize(new Dimension(15,15));
		jpWest1.add(hoaxesleft, BorderLayout.WEST);
		jpWest1.add(hoaxesright, BorderLayout.EAST);
		JPanel jpHoaxes = new JPanel();
		jpHoaxes.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        jpHoaxes.add(hoaxesjtaMessage, gbc1);
		jpWest1.add(jpHoaxes, BorderLayout.CENTER);
		jpWest1.add(hoaxeslabel, BorderLayout.NORTH);
        
		jpEast1 = new JPanel();
		jpEast1.setLayout(new BorderLayout());
		jpEast1.setMinimumSize(new Dimension(15,15));
		jpEast1.add(nonUSleft, BorderLayout.WEST);
		jpEast1.add(nonUSright, BorderLayout.EAST);
		JPanel jpNUSM = new JPanel();
		jpNUSM.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        jpNUSM.add(nonUSjtaMessage, gbc2);
		jpEast1.add(jpNUSM, BorderLayout.CENTER);
		jpEast1.add(nonUSlabel, BorderLayout.NORTH);
		
		jpWest2 = new JPanel();
		jpWest2.setLayout(new BorderLayout());
		jpWest2.add(likeliestleft, BorderLayout.WEST);
		jpWest2.add(likeliestright, BorderLayout.EAST);
		JPanel jpLS = new JPanel();
		jpLS.setLayout(new GridBagLayout());
        GridBagConstraints gbc3 = new GridBagConstraints();
        jpLS.add(likeliestjtaMessage, gbc3);
		jpWest2.add(jpLS, BorderLayout.CENTER);
		jpWest2.add(likeliestlabel, BorderLayout.NORTH);

		jpEast2 = new JPanel();
		jpEast2.setLayout(new BorderLayout());
		jpEast2.add(otherleft, BorderLayout.WEST);
		jpEast2.add(otherright, BorderLayout.EAST);
		jpEast2.add(otherjtaMessage, BorderLayout.CENTER);
		jpEast2.add(otherlabel, BorderLayout.NORTH);

		
		PlaceHolder = new JTextArea(15,0);
		PlaceHolder.setMaximumSize(new Dimension(15,0));
		PlaceHolder.setEditable(false);
		
//		Message.setBackground(null);
		jpWest1.setPreferredSize(new Dimension(375,250));
		jpEast1.setPreferredSize(new Dimension(375,250));
		jpNorth.setLayout(new BorderLayout());
		jpNorth.add(jpWest1, BorderLayout.WEST);
		jpNorth.add(jpEast1, BorderLayout.CENTER);
		jpNorth.add(PlaceHolder, BorderLayout.EAST);
	
		
		jpWest2.setPreferredSize(new Dimension(375,250));
		jpEast2.setPreferredSize(new Dimension(375,250));
		jpSouth.setLayout(new BorderLayout());
		jpSouth.add(jpWest2, BorderLayout.WEST);
		jpSouth.add(jpEast2, BorderLayout.CENTER);
		jpNorth.add(PlaceHolder, BorderLayout.EAST);

	}
	
	public void init(String from,String to) {
		
		statisticsModel.setPanels(nonUSjtaMessage, hoaxesjtaMessage, from, to);
		statisticsModel.setlikely(likeliestjtaMessage, from, to);
		statisticsModel.likeliestTime(from, to);

		
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;

		}

	}

}
