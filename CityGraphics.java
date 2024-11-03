package com.helswify.city;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.helswify.buildings.Building;
import com.helswify.buildings.Utility;
import com.helswify.buildings.publicService.Education;
import com.helswify.buildings.taxable.Commercial;
import com.helswify.buildings.taxable.Industrial;
import com.helswify.buildings.taxable.Residential;


public class CityGraphics implements Runnable {
	/** The drawing surface. */
	private final DrawingSurface drawingSurface = new DrawingSurface();
	private final ButtonPanel buttonPanel = new ButtonPanel();
	private final CityInfo cityInfo = new CityInfo();
	
	private JFrame frame = new JFrame("Java City");

	@Override
	public void run() {
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setContentPane(  new MainPanel() );
		frame.pack();
		frame.setVisible( true );
	}
	
	/**
	 * The main panel for this frame. Contains a drawing surface and a button panel.
	 */
	@SuppressWarnings("serial")
	private class MainPanel extends JPanel {
		/**
		 * Instantiates a new main panel.
		 */
		public MainPanel() {
			super(new BorderLayout());
			add(drawingSurface, BorderLayout.CENTER);
			add(buttonPanel, BorderLayout.PAGE_START);
			add(cityInfo, BorderLayout.PAGE_END);
		}
	}
	
	@SuppressWarnings("serial")
	private class ButtonPanel extends JPanel {
		/** The quit/exit game button. */
		private final JButton quitButton = new JButton("Quit");
		/** The purchase utility button. */
		private final JButton utilityButton = new JButton("Utility");
		/** The purchase residential button. */
		private final JButton residentialButton = new JButton("Residential");
		/** The purchase commercial button. */
		private final JButton commercialButton = new JButton("Commercial");
		/** The purchase industrial button. */
		private final JButton industrialButton = new JButton("Industrial");
		/** The purchase education button. */
		private final JButton educationButton = new JButton("Education");
		
		public ButtonPanel() {
			add(utilityButton);
			add(residentialButton);
			add(commercialButton);
			add(industrialButton);
			add(educationButton);
			add(quitButton);
			
			quitButton.addActionListener(evt -> System.exit(0));
			utilityButton.addActionListener(evt -> {
				JavaCity.city.purchase(new Utility());
				drawingSurface.repaint();
				cityInfo.updateLabelText();
			});
			residentialButton.addActionListener(evt -> {
				JavaCity.city.purchase(new Residential());
				drawingSurface.repaint();
				cityInfo.updateLabelText();
			});
			commercialButton.addActionListener(evt -> {
				JavaCity.city.purchase(new Commercial());
				drawingSurface.repaint();
				cityInfo.updateLabelText();
			});
			industrialButton.addActionListener(evt -> {
				JavaCity.city.purchase(new Industrial());
				drawingSurface.repaint();
				cityInfo.updateLabelText();
			});
			educationButton.addActionListener(evt -> {
				JavaCity.city.purchase(new Education());
				drawingSurface.repaint();
				cityInfo.updateLabelText();
			});
		}
	}
	
	@SuppressWarnings("serial")
	private class CityInfo extends JPanel implements ActionListener {
		Timer timer;
		private JLabel cityBalance = new JLabel("City Balance: " + String.format("%.2f", JavaCity.city.getCityBalance()));
		private JLabel citySupply = new JLabel("City Supply: " + JavaCity.city.getSupply());
		private JLabel cityDemand = new JLabel("City Demand: " + JavaCity.city.getDemand());
		private JLabel netNeed = new JLabel("Net Need: " + JavaCity.city.netNeed());
		
		public CityInfo() {
			add(cityBalance);
			add(createVerticalSeparator());
			add(citySupply);
			add(createVerticalSeparator());
			add(cityDemand);
			add(createVerticalSeparator());
			add(netNeed);
			timer = new Timer(1000, this);
    		timer.start();
		}
		
		public JComponent createVerticalSeparator() {
	        JSeparator x = new JSeparator(SwingConstants.VERTICAL);
	        x.setPreferredSize(new Dimension(3,25));
	        return x;
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			JavaCity.city.collectTaxes();
			JavaCity.city.payOperatingCosts();
			updateLabelText();
		}
		
		public void updateLabelText() {
			cityBalance.setText("City Balance: " + String.format("%.2f", JavaCity.city.getCityBalance()));
			citySupply.setText("City Supply: " + JavaCity.city.getSupply());
			cityDemand.setText("City Demand: " + JavaCity.city.getDemand());
			netNeed.setText("Net Need: " + JavaCity.city.netNeed());
		}
	}
	
	@SuppressWarnings( "serial" )
    private class DrawingSurface extends JPanel {
		List<Building> buildings = JavaCity.city.getBuildings();
		
        public DrawingSurface() {
            Dimension   size    = new Dimension( 670, 800 );
            setPreferredSize( size );        
        }
        
        public void paintComponent( Graphics graphics ) {
        	super.paintComponent(graphics); // clear the canvas JPanel
        	Graphics2D  gtx = (Graphics2D)graphics.create();
        	final int buildingWidth = 100;
        	final int buildingHeight = 100;
        	final int margin = 30;
        	int utilityXCo = 25;
        	int utilityYCo = 25;
        	int residentialXCo = utilityXCo + margin + buildingWidth;
        	int residentialYCo = 25;
        	int commercialXCo = residentialXCo + margin + buildingWidth;
        	int commercialYCo = 25;
        	int industrialXCo = commercialXCo + margin + buildingWidth;
        	int industrialYCo = 25;
        	int educationXCo = industrialXCo + margin + buildingWidth;
        	int educationYCo = 25;
        	int yCo = 25;
        	          
        	for (Building building : buildings) {
        		if(building instanceof Utility) {
        			gtx.setColor( Color.BLACK );
        			gtx.fillRect(utilityXCo, utilityYCo, buildingWidth, buildingHeight);
        			utilityYCo += margin + buildingHeight;
        			if (yCo < utilityYCo) {
        				yCo += margin + buildingHeight;
        			}
        		} else if (building instanceof Residential) {
        			int start = 220;
                    int extent = 100;
        			gtx.setColor( Color.GREEN );
        			gtx.fillArc(residentialXCo, residentialYCo-50, buildingWidth, buildingHeight, start, extent);
        			gtx.fillRect( residentialXCo, residentialYCo + 25, buildingWidth, buildingHeight-25 );
        			residentialYCo += margin + buildingHeight;
        			if (yCo < residentialYCo) {
        				yCo += margin + buildingHeight;
        			}
        		} else if (building instanceof Commercial) {
        			int start = 0;
                    int extent = 180;
        			gtx.setColor( Color.BLUE );
        			gtx.fillArc(commercialXCo, commercialYCo, buildingWidth, buildingHeight, start, extent);
        			gtx.fillRect( commercialXCo, commercialYCo + 50, buildingWidth, buildingHeight-50 );
        			commercialYCo += margin + buildingHeight;
        			if (yCo < commercialYCo) {
        				yCo += margin + buildingHeight;
        			}
        		} else if (building instanceof Industrial) {
        			gtx.setColor( Color.ORANGE );
        			gtx.fillRoundRect(industrialXCo, industrialYCo, buildingWidth, buildingHeight, 50, 50);
        			industrialYCo += margin + buildingHeight;
        			if (yCo < industrialYCo) {
        				yCo += margin + buildingHeight;
        			}
        		} else {
        			gtx.setColor( Color.MAGENTA );
        			gtx.fillOval(educationXCo, educationYCo, buildingWidth, buildingHeight);
        			educationYCo += margin + buildingHeight;
        			if (yCo < educationYCo) {
        				yCo += margin + buildingHeight;
        			}
        		}
        	}
        }
    }
}
