
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * 
 * @ClassName: TimeScreen
 * @Description: The screen to show the simulation time.
 * @Author: Group44
 * @Version:
 * 
 */

public class TimeScreen {

	private JFrame frame;
	public JLabel lblNowTime;
	public int timeSpeed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeScreen window = new TimeScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TimeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(62)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 302,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(70, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(75)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 99,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(87, Short.MAX_VALUE)));

		lblNowTime = new JLabel("Now time: 00:00:00");
		lblNowTime.setFont(new Font("Cambria", Font.PLAIN, 30));
		lblNowTime.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addGap(43)
						.addComponent(lblNowTime)
						.addContainerGap(44, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addGap(30)
						.addComponent(lblNowTime)
						.addContainerGap(33, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);

	}
}
