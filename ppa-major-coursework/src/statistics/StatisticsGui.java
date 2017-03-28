package statistics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import api.ripley.Ripley;
import java.util.Date;


public class StatisticsGui extends JPanel {

	
	private JTextArea PlaceHolder;
	
	private JTextPane hoaxesjtaMessage;
	private JButton hoaxesright;
	private JButton hoaxesleft;
	private JLabel hoaxeslabel;

	private JTextPane nonUSjtaMessage;
	private JButton nonUSright;
	private JButton nonUSleft;
	private JLabel nonUSlabel;

	private JTextPane likeliestjtaMessage;
	private JButton likeliestright;
	private JButton likeliestleft;
	private JLabel likeliestlabel;

	private JTextPane otherjtaMessage;
	private JButton otherright;
	private JButton otherleft;
	private JLabel otherlabel;

	private JPanel jpEast1;
	private JPanel jpWest1;
	private JPanel jpEast2;
	private JPanel jpWest2;


	private String from;
	private String to;
	
	private StatisticsModel statisticsModel;


	public StatisticsGui(JComboBox<String> jcbFrom, JComboBox<String> jcbTo, Ripley ripley) {
		statisticsModel = new StatisticsModel(ripley);
		
	/*	jcbFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (jcbFrom.getSelectedItem() != null) {

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					setFrom((String) jcbFrom.getSelectedItem() + "-01-01 00:00:00");
					try {
						Date fromAsDate = df.parse(from);
						setFrom(df.format(fromAsDate));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					statisticsModel.setPanels(nonUSjtaMessage, hoaxesjtaMessage, from, to);
					statisticsModel.setlikely(likeliestjtaMessage, from, to);
				}
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
		});*/

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
