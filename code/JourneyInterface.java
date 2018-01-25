

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

/**
 * 
 * @ClassName: JourneyInterface
 * @Description: The interface for journey management.
 * @Author: Group44
 * @Version:
 * 
 */

public class JourneyInterface {

	private JFrame frame;
	private JComboBox systemBox;

	private JPanel informationPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JLabel titleLabel;
	private JTable journeyTable;
	private JButton backButton;
	private JScrollPane tablePanel;
	private JPanel operationPanel;
	private JButton addJourneyButton;
	private JButton deleteJourneyButton;
	private JButton changeDriverButton;
	private JButton changeTrainButton;
	private JButton locationButton;
	private JPanel locationPanel;
	private JProgressBar progressbar;
	private JLabel locationLabel;
	private String selectedjourneyID;

	private String routeID;
	private JButton refreshButton;
	public static JourneyTableModel journeyTableModel;
	private Timer locationTimer;
	private JButton stopButton;
	private JLabel locationTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JourneyInterface window = new JourneyInterface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JourneyInterface() {
		routeID = "route_01";
		initialize();
	}

	public JourneyInterface(String routeID) {
		this.routeID = routeID;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(new Dimension(800, 600));
		frame.setLocation(200, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel menuPanel = new JPanel();
		systemBox = new JComboBox();

		systemBox.addItem("Management System");
		systemBox.addItem("Station System");
		systemBox.addItem("OnBoard System");
		systemBox.setSelectedIndex(0);
		systemBox.addItemListener(new SystemBoxListener());

		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		menuPanel.add(systemBox);

		// this.getContentPane().setLayout(new BorderLayout());
		// this.add(menuPanel,BorderLayout.NORTH);

		BgPanel mainPanel = new BgPanel();
		frame.getContentPane().add(mainPanel);
		mainPanel.setOpaque(true);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuPanel, BorderLayout.NORTH);

		informationPanel = new JPanel();
		mainPanel.add(informationPanel, BorderLayout.CENTER);
		informationPanel.setLayout(new BorderLayout(0, 0));

		northPanel = new JPanel();
		informationPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		titleLabel = new JLabel("The journeys information of the " + routeID);
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		northPanel.add(titleLabel);

		journeyTableModel = new JourneyTableModel(routeID);
		journeyTable = new JTable(journeyTableModel);
		// journeyTable.getColumnModel().getColumn(journeyTable.getColumnCount()-1).setPreferredWidth(200);
		RowSorter<JourneyTableModel> sorter = new TableRowSorter<JourneyTableModel>(
				journeyTableModel);
		journeyTable.setRowSorter(sorter);

		tablePanel = new JScrollPane(journeyTable);
		tablePanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		locationPanel = new JPanel();
		locationPanel.setLayout(new BorderLayout());

		locationLabel = new JLabel();
		locationLabel.setHorizontalAlignment(SwingConstants.CENTER);

		locationTitle = new JLabel("Location: ");
		locationTitle.setFont(new Font("Calibri", Font.PLAIN, 14));
		locationTitle.setHorizontalAlignment(SwingConstants.CENTER);
		progressbar = new JProgressBar();
		progressbar.setForeground(Color.BLACK);

		progressbar.setOrientation(JProgressBar.HORIZONTAL);

		progressbar.setMinimum(0);

		progressbar.setMaximum(100);

		progressbar.setValue(0);

		progressbar.setStringPainted(true);

		progressbar.setPreferredSize(new Dimension(300, 20));

		progressbar.setBorderPainted(true);

		progressbar.setBackground(Color.blue);

		locationPanel.add(locationTitle, BorderLayout.NORTH);
		locationPanel.add(locationLabel, BorderLayout.CENTER);
		locationPanel.add(progressbar, BorderLayout.SOUTH);
		locationPanel.setVisible(false);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(tablePanel, BorderLayout.CENTER);
		centerPanel.add(locationPanel, BorderLayout.SOUTH);

		informationPanel.add(centerPanel, BorderLayout.CENTER);

		operationPanel = new JPanel();
		centerPanel.add(operationPanel, BorderLayout.NORTH);

		deleteJourneyButton = new JButton("Delete this journey");
		deleteJourneyButton
				.addActionListener(new deleteJourneyButtonListener());

		addJourneyButton = new JButton("Add new journey");
		addJourneyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateJourneyInterface createJourneyInterface = new CreateJourneyInterface(
						routeID);

			}
		});
		operationPanel.add(addJourneyButton);
		operationPanel.add(addJourneyButton);
		operationPanel.add(deleteJourneyButton);

		changeDriverButton = new JButton("Change driver");
		changeDriverButton.addActionListener(new changeDriverButtonListener());

		operationPanel.add(changeDriverButton);

		changeTrainButton = new JButton("Change Locomotive");
		changeTrainButton.addActionListener(new changeTrainButtonListener());

		operationPanel.add(changeTrainButton);

		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new refreshButtonActionListener());
		operationPanel.add(refreshButton);

		locationButton = new JButton("Location");
		locationButton.addActionListener(new locationButtonActionListener());
		operationPanel.add(locationButton);

		stopButton = new JButton("Stop");
		stopButton.addActionListener(new stopButtonListener());
		operationPanel.add(stopButton);

		southPanel = new JPanel();
		informationPanel.add(southPanel, BorderLayout.SOUTH);

		backButton = new JButton("Go back");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new RouteInterface();

			}
		});
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		southPanel.add(backButton);

		locationTimer = new Timer(10, new locationTimerListener());

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
					frame.dispose();
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
									frame.dispose();
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

	class deleteJourneyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (journeyTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Please select a row",
						"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			else if (journeyTable.getRowCount() <= 1) {
				JOptionPane
						.showMessageDialog(
								null,
								"It is the last journey of the route. \n You can just delete the whole route",
								"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				new RouteInterface();
				return;

			}

			int isRun = -1;
			if (locationTimer.isRunning()) {
				locationTimer.stop();
				locationPanel.setVisible(false);
				isRun = 1;
			}
			locationTimer.stop();
			String deleteJourney = "";
			deleteJourney = (String) journeyTable.getValueAt(
					journeyTable.getSelectedRow(), 0);
			String deleteDriver = "";
			String deleteLocomotive = "";

			deleteDriver = InitialInterface.myJourney.getTrainByName(
					deleteJourney).getDriverName();
			deleteLocomotive = InitialInterface.myJourney.getTrainByName(
					deleteJourney).getLocomotiveName();
			InitialInterface.myJourney.removeTrain(deleteJourney);
			Driver myDriver = new Driver();
			myDriver.deleteJourneyFromDriver(deleteDriver, deleteJourney);

			Locomotive myLocomotiver = new Locomotive();
			myLocomotiver.deleteJourneyFromLocomotive(deleteLocomotive,
					deleteJourney);
			;

			JOptionPane.showMessageDialog(null, "Successfully Delete the "
					+ deleteJourney, "Successful" + deleteJourney,
					JOptionPane.INFORMATION_MESSAGE);

			journeyTableModel.setValues();
			journeyTableModel.fireTableDataChanged();

			if (isRun == 1) {
				locationTimer.start();
				locationPanel.setVisible(true);
			}

		}
	}

	class changeDriverButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (journeyTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Please select a row",
						"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			String journeyID = "";
			Driver myDriver = new Driver();
			journeyID = (String) journeyTable.getValueAt(
					journeyTable.getSelectedRow(), 0);
			Train changeDriverTrain = new Train(journeyID);
			String oldDriverName = changeDriverTrain.getDriverName();
			String changeDriverName = oldDriverName;

			String trainName = changeDriverTrain.getLocomotiveName();

			ArrayList<String> usableDriverName = new ArrayList<String>();
			for (int i = 0; i < myDriver.allDriver().size(); i++) {
				myDriver.checkTimeConflict(changeDriverTrain.getArrivingTime(),
						myDriver.allDriver().get(i));
				if (myDriver.checkTimeConflict(changeDriverTrain
						.getArrivingTime(), myDriver.allDriver().get(i)) == -1) {
					usableDriverName.add(myDriver.allDriver().get(i));
				}
			}

			if (usableDriverName.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								null,
								"No available driver. \n Jump to the driver interface to create a new driver",
								"No available driver",
								JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				new DriverInterface();
				return;
			}

			Object[] driverOptions;
			driverOptions = new Object[usableDriverName.size()];
			for (int i = 0; i < usableDriverName.size(); i++) {
				driverOptions[i] = usableDriverName.get(i);
			}

			String chosenOption = (String) JOptionPane.showInputDialog(null,
					"please choose a new driver:\n", "manage drivers",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"),
					driverOptions, myDriver.allDriver().get(0));
			if (chosenOption == null) {
				return;
			}
			for (int i = 0; i < myDriver.allDriver().size(); i++) {
				if (myDriver.allDriver().get(i).equals(chosenOption)) {
					changeDriverName = myDriver.allDriver().get(i);
					JOptionPane.showMessageDialog(null,
							"The driver has been changed to "
									+ changeDriverName + " .", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			myDriver.deleteJourneyFromDriver(oldDriverName, journeyID);
			myDriver.addToRoute(changeDriverName, journeyID);

			File timeFile = new File("timeTable_" + journeyID + ".txt");
			String wholeRoute = "";

			try {
				FileWriter writer = new FileWriter(timeFile, false);
				for (int i = 0; i < changeDriverTrain.getStationName().size(); i++) {
					wholeRoute = wholeRoute
							+ changeDriverTrain.getStationName().get(i) + " "
							+ changeDriverTrain.getArrivingTime().get(i) + " ";
				}

				wholeRoute = wholeRoute + "* " + routeID + " " + trainName
						+ " " + changeDriverName;

				writer.write(wholeRoute);
				writer.close();

			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

			journeyTableModel.setValues();
			journeyTableModel.fireTableDataChanged();

		}
	}

	class changeTrainButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (journeyTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Please select a row",
						"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			String journeyID = "";
			Locomotive myLocomotive = new Locomotive();
			journeyID = (String) journeyTable.getValueAt(
					journeyTable.getSelectedRow(), 0);
			Train changeLocomotiveTrain = new Train(journeyID);
			String oldLocomotiveName = changeLocomotiveTrain
					.getLocomotiveName();
			String changeLocomotiveName = oldLocomotiveName;

			String driverName = changeLocomotiveTrain.getDriverName();

			ArrayList<String> usableLocomotiveName = new ArrayList<String>();
			for (int i = 0; i < myLocomotive.allLocomotive().size(); i++) {
				myLocomotive
						.checkTimeConflict(
								changeLocomotiveTrain.getArrivingTime(),
								myLocomotive.allLocomotive().get(i));
				if (myLocomotive
						.checkTimeConflict(
								changeLocomotiveTrain.getArrivingTime(),
								myLocomotive.allLocomotive().get(i)) == -1) {
					usableLocomotiveName.add(myLocomotive.allLocomotive()
							.get(i));
				}
			}

			if (usableLocomotiveName.size() <= 0) {
				JOptionPane
						.showMessageDialog(
								null,
								"No available locomotive. \n Jump to the locomotive interface to create a new locomotive",
								"No available locomotive",
								JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				new LocomotiveInterface();
				return;
			}

			Object[] locomotiveOptions;
			locomotiveOptions = new Object[usableLocomotiveName.size()];
			for (int i = 0; i < usableLocomotiveName.size(); i++) {
				locomotiveOptions[i] = usableLocomotiveName.get(i);
			}

			String chosenOption = (String) JOptionPane.showInputDialog(null,
					"please choose a new locomotive:\n", "manage locomotives",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"),
					locomotiveOptions, myLocomotive.allLocomotive().get(0));
			if (chosenOption == null) {
				return;
			}
			for (int i = 0; i < myLocomotive.allLocomotive().size(); i++) {
				if (myLocomotive.allLocomotive().get(i).equals(chosenOption)) {
					changeLocomotiveName = myLocomotive.allLocomotive().get(i);
					JOptionPane.showMessageDialog(null,
							"The locomotive has been changed to "
									+ changeLocomotiveName + " .", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

			myLocomotive.deleteJourneyFromLocomotive(oldLocomotiveName,
					journeyID);
			myLocomotive.addToRoute(changeLocomotiveName, journeyID);

			File timeFile = new File("timeTable_" + journeyID + ".txt");
			String wholeRoute = "";

			try {
				FileWriter writer = new FileWriter(timeFile, false);
				for (int i = 0; i < changeLocomotiveTrain.getStationName()
						.size(); i++) {
					wholeRoute = wholeRoute
							+ changeLocomotiveTrain.getStationName().get(i)
							+ " "
							+ changeLocomotiveTrain.getArrivingTime().get(i)
							+ " ";
				}

				wholeRoute = wholeRoute + "* " + routeID + " "
						+ changeLocomotiveName + " " + driverName;

				writer.write(wholeRoute);
				writer.close();

			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			journeyTableModel.setValues();
			journeyTableModel.fireTableDataChanged();

		}
	}

	class refreshButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			journeyTableModel.setValues();
			journeyTableModel.fireTableDataChanged();
		}
	}

	class stopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (journeyTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Please select a row",
						"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			String stopJourneyName = "";
			stopJourneyName = (String) journeyTable.getValueAt(
					journeyTable.getSelectedRow(), 0);
			StopJourney stopjourney = new StopJourney(stopJourneyName);
			JOptionPane.showMessageDialog(null,
					"The message of stopping the journey " + stopJourneyName
							+ " has been sent successfully", "Successful",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	class locationButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (locationTimer.isRunning()) {
				locationTimer.stop();
				locationPanel.setVisible(false);
			} else {
				locationTimer.start();
				locationPanel.setVisible(true);
			}

		}

	}

	class locationTimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (journeyTable.getSelectedRow() == -1) {
				locationLabel.setText("Please selecte a journey.");
				locationTitle.setText("Location: ");

				progressbar.setValue(0);
				return;
			}

			selectedjourneyID = (String) journeyTable.getValueAt(
					journeyTable.getSelectedRow(), 0);
			String journeyLocation = InitialInterface.myJourney.getTrainByName(
					selectedjourneyID).getTrainLocation();
			locationTitle.setText("Location of " + selectedjourneyID + ": ");
			if (journeyLocation.equals("Location information not available! ")) {
				locationLabel.setText("Location information not available! ");
				progressbar.setValue(0);
				return;
			}

			else if (journeyLocation.equals("Arrived the terminus.")) {
				locationLabel.setText(journeyLocation);
				progressbar.setValue(100);
				return;
			}

			else if (journeyLocation.equals("Not start yet.")) {
				locationLabel.setText(journeyLocation);
				progressbar.setValue(0);
				return;
			} else {

				String progressNumber = (String) journeyLocation;

				String newProgressNumber = progressNumber.replaceAll("[^0-9]",
						"");

				int progressValue = Integer.parseInt(newProgressNumber);

				progressbar.setValue(progressValue);
				locationLabel.setText(journeyLocation);
			}

		}

	}

}
