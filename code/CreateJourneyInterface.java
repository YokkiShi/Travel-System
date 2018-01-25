

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

/**
 * 
 * @ClassName: CreateJourneyInterface
 * @Description: The interface for journey creating.
 * @Author: Group44
 * @Version:
 * 
 */

public class CreateJourneyInterface {

	private String routeID;
	private JFrame frmCreateANew;
	private JLabel InputLabel;
	private ArrayList<String> stationNameList = new ArrayList<String>();
	private ArrayList<Integer> arriveTimeList = new ArrayList<Integer>();
	private JTextField hourField;
	private JTextField minuteField;
	private int fromWhere;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateJourneyInterface window = new CreateJourneyInterface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the interface
	 * 
	 */

	public CreateJourneyInterface() {
		this.stationNameList.clear();
		this.arriveTimeList.clear();
		this.routeID = "01";
		this.stationNameList.addAll(InitialInterface.myJourney
				.getRoute("route_" + routeID));
		initialize();
	}

	/**
	 * Create the interface
	 * 
	 * @param routeID
	 *            Name of the route that the new created journey belongs to.
	 */
	public CreateJourneyInterface(String routeID) {
		fromWhere = 1;
		this.stationNameList.clear();
		this.arriveTimeList.clear();
		this.routeID = routeID;
		this.routeID = this.routeID.replaceAll("route_", "");
		this.stationNameList.addAll(InitialInterface.myJourney
				.getRoute(routeID));
		initialize();
	}

	/**
	 * Create the interface
	 * 
	 * @param routeID
	 *            Name of the route that the new created journey belongs to.
	 * @param newAddStationNameList
	 *            Station list of the route that the new created journey belongs
	 *            to.
	 */
	public CreateJourneyInterface(ArrayList<String> newAddStationNameList,
			String routeID) {
		fromWhere = -1;
		this.stationNameList.clear();
		this.arriveTimeList.clear();
		this.routeID = "";
		this.routeID = routeID;
		this.stationNameList.addAll(newAddStationNameList);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateANew = new JFrame();
		frmCreateANew.setTitle("Create a new new Journey");
		frmCreateANew.setBounds(100, 100, 450, 250);
		frmCreateANew.setVisible(true);
		// frmCreateANew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel north = new JPanel();
		frmCreateANew.getContentPane().add(north, BorderLayout.NORTH);

		JPanel south = new JPanel();
		frmCreateANew.getContentPane().add(south, BorderLayout.SOUTH);

		JPanel west = new JPanel();
		frmCreateANew.getContentPane().add(west, BorderLayout.WEST);

		JPanel east = new JPanel();
		frmCreateANew.getContentPane().add(east, BorderLayout.EAST);

		JPanel center = new JPanel();
		frmCreateANew.getContentPane().add(center, BorderLayout.CENTER);

		InputLabel = new JLabel("Input the arrive Time of the "
				+ (arriveTimeList.size() + 1) + "th Station  - "
				+ stationNameList.get(arriveTimeList.size()) + " -");
		InputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		InputLabel.setFont(new Font("Calibri", Font.PLAIN, 12));

		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new nextButtonListener());

		JLabel lblCreateAJourney = new JLabel("Create a Journey For the route_"
				+ routeID);
		lblCreateAJourney.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAJourney.setFont(new Font("Calibri", Font.BOLD, 14));

		JLabel lblRoute = new JLabel("Route:" + stationNameList);
		lblRoute.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoute.setFont(new Font("Calibri", Font.PLAIN, 12));

		hourField = new JTextField();
		hourField.setColumns(10);

		minuteField = new JTextField();
		minuteField.setColumns(10);

		JLabel gaplabel = new JLabel(":");
		gaplabel.setFont(new Font("Calibri", Font.BOLD, 14));

		GroupLayout gl_center = new GroupLayout(center);
		gl_center
				.setHorizontalGroup(gl_center
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_center
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_center
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_center
																		.createSequentialGroup()
																		.addComponent(
																				lblCreateAJourney,
																				GroupLayout.DEFAULT_SIZE,
																				394,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																gl_center
																		.createSequentialGroup()
																		.addGroup(
																				gl_center
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblRoute,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								387,
																								Short.MAX_VALUE)
																						.addComponent(
																								InputLabel,
																								GroupLayout.DEFAULT_SIZE,
																								387,
																								Short.MAX_VALUE)
																						.addGroup(
																								gl_center
																										.createSequentialGroup()
																										.addComponent(
																												hourField,
																												GroupLayout.PREFERRED_SIZE,
																												34,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												gaplabel)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												minuteField,
																												GroupLayout.PREFERRED_SIZE,
																												33,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												nextButton)
																										.addGap(74)))
																		.addGap(17)))));
		gl_center
				.setVerticalGroup(gl_center
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_center
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblCreateAJourney)
										.addGap(31)
										.addComponent(lblRoute)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(InputLabel,
												GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(
												gl_center
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																nextButton)
														.addComponent(
																hourField,
																GroupLayout.PREFERRED_SIZE,
																22,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(gaplabel)
														.addComponent(
																minuteField,
																GroupLayout.PREFERRED_SIZE,
																23,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(52, Short.MAX_VALUE)));
		center.setLayout(gl_center);

	}

	class nextButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String checkHourFormat = "(([0-1][0-9])|2[0-3])";
			String checkMinuteFormat = "[0-5][0-9]";
			String time = "";
			if (!hourField.getText().matches(checkHourFormat)
					|| !minuteField.getText().matches(checkMinuteFormat)) {
				JOptionPane
						.showMessageDialog(
								null,
								"illegal time format! \n Please input the time with format like 08:05",
								"error", JOptionPane.INFORMATION_MESSAGE);
				// hourField.setText("");
				// minuteField.setText("");
				return;
			} else {
				time = hourField.getText() + minuteField.getText();
				if (arriveTimeList.size() > 0) {
					if (Integer.parseInt(time) <= arriveTimeList
							.get(arriveTimeList.size() - 1)) {
						JOptionPane
								.showMessageDialog(
										null,
										"Please input a time later than the previous one",
										"error",
										JOptionPane.INFORMATION_MESSAGE);
						// hourField.setText("");
						// minuteField.setText("");
						return;
					}
				}

				arriveTimeList.add(Integer.parseInt(time));
				if (stationNameList.size() > arriveTimeList.size()) {
					JOptionPane
							.showMessageDialog(
									null,
									"Arrival time "
											+ time
											+ " for the "
											+ (arriveTimeList.size())
											+ "th station "
											+ stationNameList
													.get(arriveTimeList.size() - 1)
											+ " is added successfully.",
									"Successfully added! ",
									JOptionPane.INFORMATION_MESSAGE);

					InputLabel.setText("Input the arrive Time of the "
							+ (arriveTimeList.size() + 1) + "th Station  "
							+ "- " + stationNameList.get(arriveTimeList.size())
							+ " -");
					// hourField.setText("");
					// minuteField.setText("");
					return;
				} else if (stationNameList.size() == arriveTimeList.size()) {

					JOptionPane.showMessageDialog(null,
							"Complete setting all the arriving time.",
							"Finish", JOptionPane.INFORMATION_MESSAGE);

					String journeyID = routeID + "00";
					String checkJourneyNumber1 = "^[1-9]{1}[0-9]{1}|[0]{1}[1-9]{1}$";

					while (true) {
						journeyID = routeID + "00";
						String journeyNumber = JOptionPane
								.showInputDialog("Please allocate a number to the journey.");
						journeyID = routeID + journeyNumber;
						if (journeyNumber == null) {
							arriveTimeList.remove(arriveTimeList.size() - 1);
							return;
						}
						if (!journeyNumber.matches(checkJourneyNumber1)) {
							JOptionPane
									.showMessageDialog(
											null,
											"illegal name! \n Only use the number from 01-99.",
											"error",
											JOptionPane.INFORMATION_MESSAGE);
							continue;
						}

						else if (InitialInterface.myJourney
								.nameExist(journeyID) == true) {
							JOptionPane.showMessageDialog(null,
									"Do not input the duplicate journeyNumber",
									"error", JOptionPane.INFORMATION_MESSAGE);
							continue;
						} else {
							break;
						}

					}

					Locomotive myLocomotive = new Locomotive();
					String locomotiveID = "#";
					myLocomotive.allLocomotive().size();
					ArrayList<String> usableLocomotiveName = new ArrayList<String>();
					for (int i = 0; i < myLocomotive.allLocomotive().size(); i++) {
						myLocomotive.checkTimeConflict(arriveTimeList,
								myLocomotive.allLocomotive().get(i));
						if (myLocomotive.checkTimeConflict(arriveTimeList,
								myLocomotive.allLocomotive().get(i)) == -1) {
							usableLocomotiveName.add(myLocomotive
									.allLocomotive().get(i));
						}
					}

					if (usableLocomotiveName.size() <= 0) {
						JOptionPane
								.showMessageDialog(
										null,
										"No available locomotive. \n Jump to the locomotive interface to create a new locomotive",
										"No available locomotive",
										JOptionPane.INFORMATION_MESSAGE);
						frmCreateANew.dispose();
						new LocomotiveInterface();
						return;
					}

					Object[] locomotiveOptions;
					locomotiveOptions = new Object[usableLocomotiveName.size()];
					for (int i = 0; i < usableLocomotiveName.size(); i++) {
						locomotiveOptions[i] = usableLocomotiveName.get(i);
					}

					String locomotiveChosenOption = (String) JOptionPane
							.showInputDialog(null, "please choose a train:\n",
									"manage trains", JOptionPane.PLAIN_MESSAGE,
									new ImageIcon("icon.png"),
									locomotiveOptions,
									usableLocomotiveName.get(0));
					if (locomotiveChosenOption == null) {
						arriveTimeList.remove(arriveTimeList.size() - 1);
						return;
					}
					for (int i = 0; i < myLocomotive.allLocomotive().size(); i++) {
						if (myLocomotive.allLocomotive().get(i)
								.equals(locomotiveChosenOption)) {
							locomotiveID = myLocomotive.allLocomotive().get(i);

						}
					}

					Driver myDriver = new Driver();
					String driverName = "#";
					myDriver.allDriver().size();

					ArrayList<String> usableDriverName = new ArrayList<String>();
					for (int i = 0; i < myDriver.allDriver().size(); i++) {
						myDriver.checkTimeConflict(arriveTimeList, myDriver
								.allDriver().get(i));
						if (myDriver.checkTimeConflict(arriveTimeList, myDriver
								.allDriver().get(i)) == -1) {
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
						frmCreateANew.dispose();
						new DriverInterface();
						return;
					}

					Object[] driverOptions;
					driverOptions = new Object[usableDriverName.size()];
					for (int i = 0; i < usableDriverName.size(); i++) {
						driverOptions[i] = usableDriverName.get(i);
					}
					String chosenOption = (String) JOptionPane.showInputDialog(
							null, "please choose a driver:\n",
							"manage drivers", JOptionPane.PLAIN_MESSAGE,
							new ImageIcon("icon.png"), driverOptions,
							usableDriverName.get(0));
					if (chosenOption == null) {
						arriveTimeList.remove(arriveTimeList.size() - 1);
						return;
					}
					for (int i = 0; i < myDriver.allDriver().size(); i++) {
						if (myDriver.allDriver().get(i).equals(chosenOption)) {
							driverName = myDriver.allDriver().get(i);

						}
					}

					File timeFile = new File("timeTable_" + journeyID + ".txt");
					File allFile = new File("allTrain.txt");
					String wholeRoute = "";

					try {
						FileWriter writer = new FileWriter(timeFile, false);
						FileWriter allWriter = new FileWriter(allFile, true);
						allWriter.write(" " + journeyID);
						allWriter.close();
						for (int i = 0; i < stationNameList.size(); i++) {
							wholeRoute = wholeRoute + stationNameList.get(i)
									+ " " + arriveTimeList.get(i) + " ";
						}

						wholeRoute = wholeRoute + "* route_" + routeID + " "
								+ locomotiveID + " " + driverName;

						writer.write(wholeRoute);
						writer.close();
						myDriver.addToRoute(driverName, journeyID);
						myLocomotive.addToRoute(locomotiveID, journeyID);
						JOptionPane
								.showMessageDialog(
										null,
										"A new Journey has been created successfully. ",
										"Successfully created! ",
										JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

					frmCreateANew.dispose();
					if (fromWhere == 1) {
						JourneyInterface.journeyTableModel.setValues();
						JourneyInterface.journeyTableModel
								.fireTableDataChanged();
					} else if (fromWhere == -1) {

						RouteInterface.tv.setValues();
						RouteInterface.tv.fireTableDataChanged();
					}

				}
			}
		}
	}
}
