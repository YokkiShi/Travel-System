

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @ClassName: OnBoardScreen
 * @Description: The screen on each trains on the way.
 * @Author: Group44
 * @Version:
 * 
 */
public class OnBoardScreen extends JFrame {

	private JComboBox systemBox;
	private String journeyID;

	private JPanel informationPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JLabel titleLabel;
	private JTable journeyTable;
	private JButton backButton;
	private JScrollPane tablePanel;
	private JPanel operationPanel;

	private JButton refreshButton;
	private OnBoardTableModel onBoardTableModel;
	private Timer timer;

	public static void main(String[] args) {

	}

	public OnBoardScreen() {

		this.setSize(800, 600);
		this.setLocation(200, 50);

		this.setTitle("Travel System -> On Board System");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		initial();

	}

	public OnBoardScreen(String journeyID) {

		this.journeyID = journeyID;
		this.setSize(800, 600);
		this.setLocation(200, 50);
		this.setTitle("Travel System -> On Board System");

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
		systemBox.setSelectedIndex(2);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		menuPanel.add(systemBox);

		// this.getContentPane().setLayout(new BorderLayout());
		// this.add(menuPanel,BorderLayout.NORTH);

		BgPanel mainPanel = new BgPanel();
		this.getContentPane().add(mainPanel);
		mainPanel.setOpaque(true);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(menuPanel, BorderLayout.NORTH);

		informationPanel = new JPanel();
		mainPanel.add(informationPanel, BorderLayout.CENTER);
		informationPanel.setLayout(new BorderLayout(0, 0));

		northPanel = new JPanel();
		informationPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		titleLabel = new JLabel("The screen in the journey " + journeyID);
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		northPanel.add(titleLabel);

		onBoardTableModel = new OnBoardTableModel(journeyID);
		journeyTable = new JTable(onBoardTableModel);
		journeyTable.getColumnModel()
				.getColumn(journeyTable.getColumnCount() - 1)
				.setPreferredWidth(200);

		tablePanel = new JScrollPane(journeyTable);
		tablePanel.setBorder(new EmptyBorder(0, 10, 0, 10));

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(tablePanel);

		informationPanel.add(centerPanel, BorderLayout.CENTER);

		operationPanel = new JPanel();
		centerPanel.add(operationPanel, BorderLayout.NORTH);

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
				dispose();
				new InitialInterface();

			}
		});
		southPanel.add(backButton);

		timer = new Timer(50, new timerListener());
		timer.start();

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
					dispose();
					new TrainStopScreen();
					break;
				case 2:
					break;
				default:
					break;

				}

			}

		}

	}

	class refreshButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			onBoardTableModel.setValues();
			onBoardTableModel.fireTableDataChanged();
		}
	}

	class timerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			onBoardTableModel.setValues();
			onBoardTableModel.fireTableDataChanged();

		}

	}

}
