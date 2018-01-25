

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

/**
 * 
 * @ClassName: RouteInterface
 * @Description: The interface for route management.
 * @Author: Group44
 * @Version:
 * 
 */
public class RouteInterface {

	private JFrame frame;
	private JComboBox systemBox;
	private JPanel informationPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JLabel goThroughLabel;
	private JLabel andLabel;
	private JTextField stationField_2;
	private JButton searchButton;
	private JTextField stationField_1;
	private JTable routeTable;
	private JButton backButton;
	private JScrollPane tablePanel;
	private JPanel operationPanel;
	private JButton addButton;
	private JButton deleteButton;
	private JButton refreshButton;
	private JButton detailButton;
	public static RouteTableModel tv;

	/**
	 * Create the application.
	 */
	public RouteInterface() {

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
		InitialInterface.myJourney.initialize();
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
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

		goThroughLabel = new JLabel("Go through station : ");
		northPanel.add(goThroughLabel);

		stationField_1 = new JTextField();
		stationField_1.setToolTipText("");
		northPanel.add(stationField_1);
		stationField_1.setColumns(10);

		andLabel = new JLabel("and station : ");
		northPanel.add(andLabel);

		stationField_2 = new JTextField();
		northPanel.add(stationField_2);
		stationField_2.setColumns(10);

		searchButton = new JButton("Search");
		searchButton.addActionListener(new searchButtonListener());

		northPanel.add(searchButton);
		northPanel.add(Box.createGlue());

		tv = new RouteTableModel();
		routeTable = new JTable(tv);
		RowSorter<RouteTableModel> sorter = new TableRowSorter<RouteTableModel>(
				tv);
		routeTable.setRowSorter(sorter);

		tablePanel = new JScrollPane(routeTable);
		tablePanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(tablePanel);

		informationPanel.add(centerPanel, BorderLayout.CENTER);

		operationPanel = new JPanel();
		centerPanel.add(operationPanel, BorderLayout.NORTH);

		addButton = new JButton("Add new route");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateRouteInterface crif = new CreateRouteInterface();
				tv.setValues();
				tv.fireTableDataChanged();
			}
		});
		operationPanel.add(addButton);

		deleteButton = new JButton("Delete this route");
		deleteButton.addActionListener(new deleteButtonListener());
		operationPanel.add(deleteButton);

		detailButton = new JButton("More Detials");
		detailButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (routeTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row",
							"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				String routeID = "";
				routeID = (String) routeTable.getValueAt(
						routeTable.getSelectedRow(), 0);

				frame.dispose();
				new JourneyInterface(routeID);

			}
		});
		operationPanel.add(detailButton);

		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new refreshButtonListener());
		operationPanel.add(refreshButton);

		southPanel = new JPanel();
		informationPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		backButton = new JButton("Go back");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ManageInterface();

			}
		});
		southPanel.add(backButton);

	}

	class searchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (stationField_1.getText().equals("")
					&& stationField_2.getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please input station information.",
						"Please input station information.",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (stationField_2.getText().equals("")) {

				ArrayList<String> singelSearch = new ArrayList<String>();
				ArrayList<String> searchRouteList = new ArrayList<String>();
				singelSearch.addAll(InitialInterface.myJourney
						.allPassThroughThisStation(stationField_1.getText()));

				if (singelSearch.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"No such route, please search again.",
							"No such route.", JOptionPane.INFORMATION_MESSAGE);
					stationField_1.setText("");
				} else {
					for (int i = 0; i < singelSearch.size(); i++) {
						if (searchRouteList.indexOf(InitialInterface.myJourney
								.getTrainByName(singelSearch.get(i))
								.getRouteName()) < 0) {
							searchRouteList.add(InitialInterface.myJourney
									.getTrainByName(singelSearch.get(i))
									.getRouteName());
						}
					}

					frame.dispose();
					new SearchInterface(searchRouteList);
				}

			} else if (!stationField_1.getText().equals("")
					&& !stationField_2.getText().equals("")) {
				ArrayList<String> doubleSearch = new ArrayList<String>();
				ArrayList<String> searchRouteList = new ArrayList<String>();
				doubleSearch.addAll(InitialInterface.myJourney
						.allFromFirstToSecond(stationField_1.getText(),
								stationField_2.getText()));
				System.out.println(doubleSearch);
				if (doubleSearch.size() == 0) {
					JOptionPane.showMessageDialog(null,
							"No such route, please search again.",
							"No such route.", JOptionPane.INFORMATION_MESSAGE);
					stationField_1.setText("");
					stationField_2.setText("");
				} else {
					for (int i = 0; i < doubleSearch.size(); i++) {
						if (searchRouteList.indexOf(InitialInterface.myJourney
								.getTrainByName(doubleSearch.get(i))
								.getRouteName()) < 0) {
							searchRouteList.add(InitialInterface.myJourney
									.getTrainByName(doubleSearch.get(i))
									.getRouteName());
						}
					}
					frame.dispose();
					new SearchInterface(searchRouteList);
				}

			}

		}

	}

	class deleteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (routeTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Please select a row",
						"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String deleteRoute = "";
			deleteRoute = (String) routeTable.getValueAt(
					routeTable.getSelectedRow(), 0);

			ArrayList<String> deleteJourneys = new ArrayList<String>();
			deleteJourneys.addAll(InitialInterface.myJourney
					.getRouteTrain(deleteRoute));

			String deleteDriver = "";
			Driver myDriver = new Driver();
			String deleteLocomotive = "";
			Locomotive myLocomotive = new Locomotive();

			InitialInterface.myJourney.getRouteTrain(deleteRoute);

			int deleteSize = InitialInterface.myJourney.getRouteTrain(
					deleteRoute).size();
			System.out.println(deleteSize);

			for (int i = 0; i < deleteSize; i++) {

				deleteDriver = InitialInterface.myJourney.getTrainByName(
						deleteJourneys.get(i)).getDriverName();
				deleteLocomotive = InitialInterface.myJourney.getTrainByName(
						deleteJourneys.get(i)).getLocomotiveName();
				String deleteJourney = deleteJourneys.get(i);
				InitialInterface.myJourney.removeTrain(deleteJourney);
				myDriver.deleteJourneyFromDriver(deleteDriver, deleteJourney);
				myLocomotive.deleteJourneyFromLocomotive(deleteLocomotive,
						deleteJourney);
			}

			JOptionPane.showMessageDialog(null, "Successfully Delete the "
					+ deleteRoute, "Successful" + deleteRoute,
					JOptionPane.INFORMATION_MESSAGE);

			tv.setValues();
			tv.fireTableDataChanged();
		}

	}

	class refreshButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			tv.setValues();
			tv.fireTableDataChanged();
		}

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

}
