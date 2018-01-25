

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
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
 * @ClassName: TrainStopScreen
 * @Description: The screen to choose the station that the person is at.
 * @Author: Group44
 * @Version:
 * 
 */
public class TrainStopScreen extends JFrame {

	private JComboBox systemBox;
	private AllStation myStation;
	private ArrayList<String> stationList;

	public static void main(String[] args) {

	}

	public TrainStopScreen() {

		// com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.6f);

		this.setSize(800, 600);
		this.setLocation(200, 50);

		this.setTitle("Travel System -> Station System");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		initial();

	}

	public void initial() {

		JPanel menuPanel = new JPanel();
		systemBox = new JComboBox();

		systemBox.addItem("Management System");
		systemBox.addItem("Station System");
		systemBox.addItem("OnBoard System");
		systemBox.addItemListener(new SystemBoxListener());
		systemBox.setSelectedIndex(1);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		menuPanel.add(systemBox);

		// this.getContentPane().setLayout(new BorderLayout());
		// this.add(menuPanel,BorderLayout.NORTH);

		BgPanel mainPanel = new BgPanel();
		this.getContentPane().add(mainPanel);
		mainPanel.setOpaque(true);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuPanel, BorderLayout.NORTH);

		JPanel informationPanel = new JPanel();
		// informationPanel.setOpaque(false);
		mainPanel.add(informationPanel, BorderLayout.CENTER);

		JPanel choosePanel = new JPanel();

		JLabel lblChooseAStation = new JLabel("Choose a station");
		lblChooseAStation.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAStation.setFont(new Font("Calibri", Font.BOLD, 18));
		GroupLayout gl_informationPanel = new GroupLayout(informationPanel);
		gl_informationPanel
				.setHorizontalGroup(gl_informationPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_informationPanel
										.createSequentialGroup()
										.addGroup(
												gl_informationPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_informationPanel
																		.createSequentialGroup()
																		.addGap(243)
																		.addComponent(
																				lblChooseAStation,
																				GroupLayout.PREFERRED_SIZE,
																				282,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_informationPanel
																		.createSequentialGroup()
																		.addGap(302)
																		.addComponent(
																				choosePanel,
																				GroupLayout.PREFERRED_SIZE,
																				170,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(259, Short.MAX_VALUE)));
		gl_informationPanel.setVerticalGroup(gl_informationPanel
				.createParallelGroup(Alignment.LEADING).addGroup(
						gl_informationPanel
								.createSequentialGroup()
								.addGap(76)
								.addComponent(lblChooseAStation)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(choosePanel,
										GroupLayout.PREFERRED_SIZE, 365,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(72, Short.MAX_VALUE)));
		choosePanel.setLayout(new BorderLayout(10, 10));

		JPanel buttonPanel = new JPanel();
		choosePanel.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setLayout(new BorderLayout(0, 0));

		JPanel centerButtonPanel = new JPanel();
		buttonPanel.add(centerButtonPanel, BorderLayout.NORTH);
		centerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton centerButton = new JButton("Center");
		centerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new EachTrainStopScreen("Center");
			}
		});
		centerButtonPanel.add(centerButton);

		JPanel otherButtonPanel = new JPanel();
		buttonPanel.add(otherButtonPanel);

		stationList = new ArrayList<String>();
		myStation = new AllStation();
		stationList.clear();
		stationList.addAll(myStation.getAllStation());
		for (int i = 0; i < stationList.size(); i++) {
			JButton otherButtons = new JButton(myStation.getAllStation().get(i));
			final int flag = i;
			otherButtons.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new EachTrainStopScreen(myStation.getAllStation().get(flag));

				}

			});
			otherButtonPanel.add(otherButtons);
		}

		informationPanel.setLayout(gl_informationPanel);

	}

	class SystemBoxListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			int index = systemBox.getSelectedIndex();
			if (e.getStateChange() == ItemEvent.SELECTED) {
				switch (index) {
				case 0:
					dispose();
					new ManageInterface();
					break;
				case 1:
					break;
				case 2:

					if (InitialInterface.myJourney.getAllTrain().size() == 0) {
						JOptionPane.showMessageDialog(null,
								"No route in the system", "No route ",
								JOptionPane.INFORMATION_MESSAGE);
						systemBox.setSelectedIndex(1);
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
										"please choose a Journey:\n",
										"manage drivers",
										JOptionPane.PLAIN_MESSAGE,
										new ImageIcon("icon.png"),
										driverOptions,
										InitialInterface.myJourney
												.getAllTrain().get(0));
						if (chosenOption == null) {
							systemBox.setSelectedIndex(1);
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
}
