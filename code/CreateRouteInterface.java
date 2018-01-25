

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

/**
 * 
 * @ClassName: CreateRouteInterface
 * @Description: The interface for route creating.
 * @Author: Group44
 * @Version:
 * 
 */
public class CreateRouteInterface {

	private JFrame frmCreateANew;
	private ArrayList<String> stationNameList = new ArrayList<String>();
	private JComboBox stationBox;
	private JLabel currentRouteLabel;

	public static void main(String[] args) {
		CreateRouteInterface test = new CreateRouteInterface();
	}

	/**
	 * Create the application.
	 */
	public CreateRouteInterface() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateANew = new JFrame();
		frmCreateANew.setTitle("Create a new route");
		frmCreateANew.setBounds(100, 100, 450, 250);
		frmCreateANew.setVisible(true);
		// frmCreateANew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		stationNameList.clear();
		// stationNameList.add("Center");

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

		JLabel lblNewLabel = new JLabel("Add a new station: ");
		lblNewLabel.setFont(new Font("Adobe Caslon Pro Bold", Font.PLAIN, 13));

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new AddButtonListener());

		JButton finishButton = new JButton("Finish adding");
		finishButton.addActionListener(new finishButtonListener());

		AllStation myStation = new AllStation();
		stationBox = new JComboBox();
		stationBox.addItem("Please choose a Station");
		for (int i = 0; i < myStation.getAllStation().size(); i++) {
			stationBox.addItem(myStation.getAllStation().get(i));
		}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		currentRouteLabel = new JLabel("Current Route: " + "Center -> Center");
		currentRouteLabel.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_center = new GroupLayout(center);
		gl_center
				.setHorizontalGroup(gl_center
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 432,
								Short.MAX_VALUE)
						.addGroup(
								gl_center
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblNewLabel,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(18)
										.addGroup(
												gl_center
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_center
																		.createSequentialGroup()
																		.addComponent(
																				addButton,
																				GroupLayout.DEFAULT_SIZE,
																				136,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				finishButton,
																				GroupLayout.PREFERRED_SIZE,
																				131,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																stationBox,
																Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																277,
																GroupLayout.PREFERRED_SIZE))
										.addGap(19))
						.addGroup(
								Alignment.LEADING,
								gl_center
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(currentRouteLabel,
												GroupLayout.DEFAULT_SIZE, 403,
												Short.MAX_VALUE).addGap(19)));
		gl_center
				.setVerticalGroup(gl_center
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_center
										.createSequentialGroup()
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE, 56,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED, 41,
												Short.MAX_VALUE)
										.addComponent(currentRouteLabel)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_center
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																stationBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblNewLabel))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_center
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																finishButton,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																addButton,
																GroupLayout.PREFERRED_SIZE,
																23,
																GroupLayout.PREFERRED_SIZE))
										.addGap(19)));
		panel.setLayout(new BorderLayout(0, 0));

		JLabel attentionLabel = new JLabel("Attention:");
		attentionLabel.setFont(new Font("Calibri", Font.BOLD, 12));
		attentionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(attentionLabel, BorderLayout.NORTH);

		JLabel sentenceLabel_1 = new JLabel(
				"Both the departure station and the terminus is the Center station.");
		sentenceLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel.add(sentenceLabel_1, BorderLayout.CENTER);
		sentenceLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel sentenceLabel_2 = new JLabel(
				"You only need to choose the intermediate stations.");
		sentenceLabel_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		panel.add(sentenceLabel_2, BorderLayout.SOUTH);
		sentenceLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		center.setLayout(gl_center);
	}

	class AddButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			String selectedStation = (String) stationBox.getSelectedItem();

			if (stationBox.getSelectedIndex() <= 0) {
				JOptionPane.showMessageDialog(null, "Please choose a station.",
						"error ", JOptionPane.INFORMATION_MESSAGE);
				stationBox.setSelectedIndex(0);
				return;
			} else if (stationNameList.size() > 0) {

				if (selectedStation.equals(stationNameList.get(stationNameList
						.size() - 1))) {
					JOptionPane.showMessageDialog(null,
							"Do not choose two consecutive same stations.",
							"error ", JOptionPane.INFORMATION_MESSAGE);
					stationBox.setSelectedIndex(0);
					return;
				} else {

					stationNameList.add(selectedStation);
					JOptionPane.showMessageDialog(null, "New Station: "
							+ selectedStation + " has been added.",
							"Successfully added! ",
							JOptionPane.INFORMATION_MESSAGE);

					String currentRoute = "Center -> ";
					for (int i = 0; i < stationNameList.size(); i++) {
						currentRoute = currentRoute + stationNameList.get(i)
								+ " -> ";
					}
					currentRoute = currentRoute + "Center";
					currentRouteLabel.setText("Current Route: " + currentRoute);
					stationBox.setSelectedIndex(0);
					return;
				}
			} else {

				stationNameList.add(selectedStation);
				JOptionPane
						.showMessageDialog(null, "New Station: "
								+ selectedStation + " has been added.",
								"Successfully added! ",
								JOptionPane.INFORMATION_MESSAGE);

				String currentRoute = "Center -> ";
				for (int i = 0; i < stationNameList.size(); i++) {
					currentRoute = currentRoute + stationNameList.get(i)
							+ " -> ";
				}
				currentRoute = currentRoute + "Center";
				currentRouteLabel.setText("Current Route: " + currentRoute);
				stationBox.setSelectedIndex(0);
				return;
			}

		}
	}

	class finishButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (stationNameList.size() <= 0) {
				JOptionPane.showMessageDialog(null,
						"Please at least add one station", "error",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			stationNameList.add(0, "Center");
			stationNameList.add("Center");

			InitialInterface.myJourney.initialize();
			for (int i = 0; i < InitialInterface.myJourney.getAllRoute().size(); i++) {
				if (InitialInterface.myJourney.getRoute(
						InitialInterface.myJourney.getAllRoute().get(i))
						.equals(stationNameList)) {
					JOptionPane.showMessageDialog(null,
							"The route already exits", "error",
							JOptionPane.INFORMATION_MESSAGE);
					stationNameList.remove(0);
					stationNameList.remove(stationNameList.size() - 1);
					return;
				}
			}

			String checkRouteID1 = "^[1-9]{1}[0-9]{1}|[0]{1}[1-9]{1}$";
			String routeID;

			while (true) {
				routeID = JOptionPane
						.showInputDialog("Please input the route's ID. \n Only use the number from 01-99");
				if (routeID == null) {
					stationNameList.remove(0);
					stationNameList.remove(stationNameList.size() - 1);
					return;
				}
				if (!routeID.matches(checkRouteID1)) {
					JOptionPane
							.showMessageDialog(
									null,
									"illegal name! \n Please input the number form 01 to 99",
									"error", JOptionPane.INFORMATION_MESSAGE);
					continue;
				}

				else if (InitialInterface.myJourney
						.getRoute("route_" + routeID) != null) {
					JOptionPane.showMessageDialog(null,
							"Do not input the duplicate routeID", "error",
							JOptionPane.INFORMATION_MESSAGE);
					continue;
				} else {
					break;
				}
			}

			JOptionPane.showMessageDialog(null, "The new route_" + routeID
					+ " is " + stationNameList.toString() + "\n"
					+ "Please create a new journey for the route_" + routeID
					+ " just created.", "You create a new route",
					JOptionPane.INFORMATION_MESSAGE);
			CreateJourneyInterface ctif = new CreateJourneyInterface(
					stationNameList, routeID);
			frmCreateANew.dispose();

		}
	}
}
