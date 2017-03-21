package statistics;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class StatisticsGui {
	
	private JPanel statistics;
	private JTextArea hoaxesjtaMessage;
	private JButton hoaxesright;
	private JButton hoaxesleft;
	private JLabel hoaxeslabel;
	
	private JTextArea nonUSjtaMessage;
	private JButton nonUSright;
	private JButton nonUSleft;
	private JLabel nonUSlabel;
	
	private JTextArea likeliestjtaMessage;
	private JButton likeliestright;
	private JButton likeliestleft;
	private JLabel likeliestlabel;
	
	private JTextArea otherjtaMessage;
	private JButton otherright;
	private JButton otherleft;
	private JLabel otherlabel;
	
	
	
	public StatisticsGui(){
		hoaxesright = new JButton(">");
		hoaxesleft = new JButton("<");
		hoaxeslabel = new JLabel("<html> Hoaxes </html>");
		hoaxesjtaMessage = new JTextArea();
		
		nonUSright = new JButton(">");
		nonUSleft = new JButton("<");
		nonUSlabel = new JLabel("<html> Non-US Sightings </html>");
		nonUSjtaMessage = new JTextArea();
		
		likeliestright = new JButton(">");
		likeliestleft = new JButton("<");
		likeliestlabel = new JLabel("<html> Likeliest State </html>");
		likeliestjtaMessage = new JTextArea();
		
		otherright = new JButton(">");
		otherleft = new JButton("<");
		otherlabel = new JLabel("<html> Sightings via other platforms </html>");
		otherjtaMessage = new JTextArea();
			
		JPanel jpWest1 = new JPanel();
		jpWest1.setLayout(new BorderLayout());
		jpWest1.add(hoaxesleft, BorderLayout.WEST);
		jpWest1.add(hoaxesright, BorderLayout.EAST);
		jpWest1.add(hoaxesjtaMessage, BorderLayout.CENTER);
		jpWest1.add(hoaxeslabel, BorderLayout.SOUTH);
		
		JPanel jpEast1 = new JPanel();
		jpWest1.setLayout(new BorderLayout());
		jpWest1.add(nonUSleft, BorderLayout.WEST);
		jpWest1.add(nonUSright, BorderLayout.EAST);
		jpWest1.add(nonUSjtaMessage, BorderLayout.CENTER);
		jpWest1.add(nonUSlabel, BorderLayout.SOUTH);
		
		JPanel jpWest2 = new JPanel();
		jpWest1.setLayout(new BorderLayout());
		jpWest1.add(likeliestleft, BorderLayout.WEST);
		jpWest1.add(likeliestright, BorderLayout.EAST);
		jpWest1.add(likeliestjtaMessage, BorderLayout.CENTER);
		jpWest1.add(likeliestlabel, BorderLayout.SOUTH);
		
		JPanel jpEast2 = new JPanel();
		jpWest1.setLayout(new BorderLayout());
		jpWest1.add(otherleft, BorderLayout.WEST);
		jpWest1.add(otherright, BorderLayout.EAST);
		jpWest1.add(otherjtaMessage, BorderLayout.CENTER);
		jpWest1.add(otherlabel, BorderLayout.SOUTH);
				
		statistics = new JPanel();
		statistics.setLayout(new GridLayout(2,2));
		statistics.add(jpWest1);
		statistics.add(jpEast1);
		statistics.add(jpWest2);
		statistics.add(jpEast2);
			
		//Number of hoaxes
		//nonUS Sightings
		//likeliest State
		//Sightings via other platforms
			
	}
	
	public JPanel getStatisticsPanel(){
		return statistics;		
	}
	
	

	}


