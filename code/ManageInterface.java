

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * 
 * @ClassName: ManageInterface
 * @Description: The interface of management system.
 * @Author: Group44
 * @Version:
 * 
 */
public class ManageInterface extends JFrame {

	private JButton routeButton;
	private JButton driverButton;
	private JButton trainButton;
	private JButton stationButton;
	private JComboBox systemBox;

	public static void main(String[] args) {
		ManageInterface test = new ManageInterface();
	}

	public ManageInterface() {
		this.setSize(800, 600);
		this.setLocation(200, 50);
		this.setTitle("Travel System -> Management System");
		initial();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void initial() {

		JPanel menuPanel = new JPanel();
		systemBox = new JComboBox();
		// systemBox.addItem("");
		systemBox.addItem("Management System");
		systemBox.addItem("Station System");
		systemBox.addItem("OnBoard System");
		systemBox.setSelectedIndex(0);
		systemBox.addItemListener(new SystemBoxListener());
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		menuPanel.add(systemBox);

		routeButton = new JButton("Route");
		routeButton.addActionListener(new routeButtonListener());
		driverButton = new JButton("Driver");
		driverButton.addActionListener(new driverButtonListener());
		trainButton = new JButton("Train");
		trainButton.addActionListener(new trainButtonListener());
		stationButton = new JButton("station");
		stationButton.addActionListener(new stationButtonListener());

		JPanel buttonPanel = new JPanel();
		JPanel westPanel = new JPanel();
		westPanel.setOpaque(false);
		westPanel.setPreferredSize(new Dimension(
				(this.getSize().width - 300) / 2, 200));
		// westPanel.setPreferredSize(new Dimension(300, 200));
		JPanel southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(this.getSize().width, (this
				.getSize().height - 250) / 2));
		// southPanel.setPreferredSize(new Dimension(800,200));
		JPanel northPanel = new JPanel();
		northPanel.setOpaque(false);
		northPanel.setPreferredSize(new Dimension(this.getSize().width, (this
				.getSize().height - 350) / 2));
		// northPanel.setPreferredSize(new Dimension(800,200));
		JPanel eastPanel = new JPanel();
		eastPanel.setOpaque(false);
		eastPanel.setPreferredSize(new Dimension(
				(this.getSize().width - 300) / 2, 200));
		// eastPanel.setPreferredSize(new Dimension(300, 200));
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 1, 5, 5));
		centerPanel.add(routeButton);
		centerPanel.add(driverButton);
		centerPanel.add(trainButton);
		centerPanel.add(stationButton);
		centerPanel.setOpaque(false);
		centerPanel.setBorder(new EmptyBorder(0, 40, 0, 40));

		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(westPanel, BorderLayout.WEST);
		buttonPanel.add(eastPanel, BorderLayout.EAST);
		buttonPanel.add(southPanel, BorderLayout.SOUTH);
		buttonPanel.add(northPanel, BorderLayout.NORTH);
		buttonPanel.add(centerPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		BgPanel mainPanel = new BgPanel();
		this.getContentPane().add(mainPanel);
		mainPanel.setOpaque(true);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);

	}

	class SystemBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			int index = systemBox.getSelectedIndex();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				switch (index) {
				case 0:
					break;
				case 1:
					dispose();
					new TrainStopScreen();
					break;
				case 2:

					if (InitialInterface.myJourney.getAllTrain().size() == 0) {
						JOptionPane.showMessageDialog(null,
								"No route in the system", "No route ",
								JOptionPane.INFORMATION_MESSAGE);
						systemBox.setSelectedIndex(0);
						break;
					} else {

						String journeyID = "";
						InitialInterface.myJourney.initialize();
						Object[] driverOptions;
						driverOptions = new Object[InitialInterface.myJourney
								.getAllTrain().size()];
						for (int i = 0; i < InitialInterface.myJourney
								.getAllTrain().size(); i++) {
							driverOptions[i] = InitialInterface.myJourney
									.getAllTrain().get(i);
						}
						String chosenOption = (String) JOptionPane
								.showInputDialog(null,
										"please choose a journey:\n",
										"Journey Screen",
										JOptionPane.PLAIN_MESSAGE,
										new ImageIcon("icon.png"),
										driverOptions,
										InitialInterface.myJourney
												.getAllTrain().get(0));
						if (chosenOption == null) {
							systemBox.setSelectedIndex(0);
							return;
						} else
							for (int i = 0; i < InitialInterface.myJourney
									.getAllTrain().size(); i++) {
								if (InitialInterface.myJourney.getAllTrain()
										.get(i).equals(chosenOption)) {
									journeyID = InitialInterface.myJourney
											.getAllTrain().get(i);
									dispose();
									new OnBoardScreen(journeyID);
									break;
								}
							}
					}
					break;
				default:
					break;

				}

			}
		}

	}

	class routeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new RouteInterface();
		}

	}

	class driverButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new DriverInterface();
		}

	}

	class trainButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new LocomotiveInterface();
		}

	}

	class stationButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new StationInterface();
		}

	}

}
