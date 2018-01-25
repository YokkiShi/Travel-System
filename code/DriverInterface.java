

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

/**
 * 
 * @ClassName: DriverInterface
 * @Description: The interface for the driver management.
 * @Author: Group44
 * @Version:
 * 
 */
public class DriverInterface {

	private JFrame frame;
	private JComboBox systemBox;

	private JPanel informationPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JLabel titleLabel;
	private JTable driverTable;
	private JButton backButton;
	private JScrollPane tablePanel;
	private JPanel operationPanel;
	private JButton addDriverButton;
	private JButton deleteDriverButton;
	private JButton refreshButton;
	private DriverTableModel driverTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverInterface window = new DriverInterface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DriverInterface() {
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

		titleLabel = new JLabel("The driver information");
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		northPanel.add(titleLabel);

		driverTableModel = new DriverTableModel();
		driverTable = new JTable(driverTableModel);
		RowSorter<DriverTableModel> sorter = new TableRowSorter<DriverTableModel>(
				driverTableModel);
		driverTable.setRowSorter(sorter);

		tablePanel = new JScrollPane(driverTable);
		tablePanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(tablePanel);

		informationPanel.add(centerPanel, BorderLayout.CENTER);

		operationPanel = new JPanel();
		centerPanel.add(operationPanel, BorderLayout.NORTH);

		addDriverButton = new JButton("Add new driver");
		addDriverButton.addActionListener(new addDriverButtonActionListener());
		operationPanel.add(addDriverButton);

		deleteDriverButton = new JButton("Delete this driver");
		deleteDriverButton
				.addActionListener(new deleteDriverButtonActionListener());
		operationPanel.add(deleteDriverButton);

		refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new refreshButtonActionListener());
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

	class addDriverButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Driver myDriver = new Driver();
			myDriver.new_member();
			driverTableModel.setValues();
			driverTableModel.fireTableDataChanged();
		}
	}

	class deleteDriverButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (driverTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Please select a row",
						"Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String driverName = "";
			driverName = (String) driverTable.getValueAt(
					driverTable.getSelectedRow(), 0);

			Driver myDriver = new Driver();

			if (myDriver.search_name(driverName).size() > 0) {
				JOptionPane
						.showMessageDialog(
								null,
								"You can not delete the driver "
										+ driverName
										+ ". "
										+ "\n Because it has been alocated to a journey.",
								"error", JOptionPane.INFORMATION_MESSAGE);
			} else {
				myDriver.deleteDriver(driverName);
			}
			driverTableModel.setValues();
			driverTableModel.fireTableDataChanged();
		}
	}

	class refreshButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			driverTableModel.setValues();
			driverTableModel.fireTableDataChanged();

		}

	}

}
