

import java.util.*;
import java.io.*;
import java.util.regex.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.lang.*;

/**
 * 
 * @ClassName: Driver
 * @Description: Manage all the Drivers in the system
 * @Author: Group44
 * @Version:
 * 
 */

public class Driver {
	static String name[] = new String[500];

	/**
	 * Add a new driver
	 * 
	 * 
	 * 
	 */
	public void new_member() {
		String check = "^[A-Za-z]+$";
		String newDriverName, b;

		File file = new File("Driver.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			while (true) {
				newDriverName = JOptionPane.showInputDialog(null,
						"Please input a new driver name", "Add new driver",
						JOptionPane.PLAIN_MESSAGE);

				if (newDriverName == null) {
					return;
				}

				else if (!newDriverName.matches(check)) {
					JOptionPane
							.showMessageDialog(
									null,
									"illegal name! \n Please input the name only with [A-Z]or[a-z]",
									"error", JOptionPane.INFORMATION_MESSAGE);
					continue;
				} else {

					BufferedReader in = new BufferedReader(new FileReader(
							"Driver.txt"));
					int flag = 0;

					while ((b = in.readLine()) != null) {

						String[] oldDriverName = b.split("	");

						if (newDriverName.equals(oldDriverName[0])) {
							JOptionPane.showMessageDialog(null,
									"this name already exist", "error",
									JOptionPane.INFORMATION_MESSAGE);
							flag = 1;
						}
					}
					if (flag == 1) {
						continue;
					} else {
						in.close();
						break;
					}
				}
			}

			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(newDriverName + "	!" + "\r\n");
			JOptionPane.showMessageDialog(null, "input success!", "message",
					JOptionPane.INFORMATION_MESSAGE);
			bufferWritter.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Add a journey to the driver
	 * 
	 * @param driverName
	 *            Name of the driver
	 * @param journeyID
	 *            Name of the journey to be added
	 */

	public void addToRoute(String driverName, String journeyID) {

		AllTrain al = new AllTrain();
		try {
			String b;
			String c;
			StringBuffer sb = new StringBuffer();
			BufferedReader in;
			in = new BufferedReader(new FileReader("Driver.txt"));

			while ((b = in.readLine()) != null) {
				c = "\r\n";
				String[] addToDriverName = b.split("	");

				if (driverName.equals(addToDriverName[0])) {
					String strr = b.trim();
					String[] abc = strr.split("[\\p{Space}]+");

					while (true) {
						if (journeyID == null) {
							return;
						} else if (al.nameExist(journeyID) == true) {

							int num = al.getTrainByName(journeyID)
									.getArrivingTime().get(0);
							int back = al
									.getTrainByName(journeyID)
									.getArrivingTime()
									.get(al.getTrainByName(journeyID)
											.getArrivingTime().size() - 1);

							if (abc.length > 2) {

								for (int numb = abc.length - 2; numb > 0; numb--) {
									if (num > al.getTrainByName(abc[numb])
											.getArrivingTime().get(0)) {
										abc[numb + 1] = journeyID;
										break;
									} else {
										abc[numb + 1] = abc[numb];
										abc[numb] = journeyID;
									}
								}
							} else {
								abc[abc.length - 1] = journeyID;
							}
							b = "";
							for (int num1 = 0; num1 < abc.length; num1++) {
								if (num1 == 0) {
									b = b + abc[num1];
								} else {
									b = b + "	" + abc[num1];
								}
							}
							b = b + "	!";
							break;
						} else {
							JOptionPane.showMessageDialog(null,
									"this route didn't exist", "error",
									JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}

				}
				sb.append(b).append(c);
			}
			in.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter("Driver.txt"));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e5) {
			e5.printStackTrace();
		}

	}

	/**
	 * Delete a journey form the driver
	 * 
	 * @param driverName
	 *            Name of the driver
	 * @param journeyID
	 *            Name of the journey to be deleted
	 */

	public void deleteJourneyFromDriver(String driverName, String journeyID) {

		try {
			String b;
			String c;
			StringBuffer sb = new StringBuffer();
			BufferedReader in;
			in = new BufferedReader(new FileReader("Driver.txt"));

			while ((b = in.readLine()) != null) {
				c = "\r\n";
				String[] theDriverName = b.split("	");

				if (driverName.equals(theDriverName[0])) {
					String strr = b.trim();
					String[] abc = strr.split("[\\p{Space}]+");

					b = "";
					String[] aa = new String[abc.length - 2];
					for (int j = 1; j < abc.length - 1; j++) {
						aa[j - 1] = abc[j];
					}
					for (int m = 0; m < abc.length; m++) {
						if (abc[m].equals(journeyID) == false) {
							if (m == 0) {
								b = b + abc[m];
							} else {
								b = b + "	" + abc[m];
							}
						}
					}

				}
				sb.append(b).append(c);
			}

			in.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter("Driver.txt"));
			bw.write(sb.toString());
			bw.close();

		} catch (IOException e5) {
			e5.printStackTrace();
		}

	}

	/**
	 * Delete a driver
	 * 
	 * @param driverName
	 *            Name of the driver to be deleted
	 */

	public void deleteDriver(String driverName) {

		try {
			String b;
			String c;
			StringBuffer sb = new StringBuffer();
			BufferedReader in;
			in = new BufferedReader(new FileReader("Driver.txt"));
			int unsuccess = 0;

			while ((b = in.readLine()) != null) {
				c = "\r\n";

				String[] deleteDriverName = b.split("	");

				if (driverName.equals(deleteDriverName[0])) {
					String strr = b.trim();
					String[] abc = strr.split("[\\p{Space}]+");

					int con = JOptionPane.showConfirmDialog(null,
							"Do you want delete this driver", "confirm",
							JOptionPane.YES_NO_OPTION);
					if (con == 0) {
						b = "";
						c = "";
					} else {
						unsuccess = 1;
						break;

					}

				}
				sb.append(b).append(c);

			}

			in.close();

			if (unsuccess == 0) {
				JOptionPane.showMessageDialog(null,
						"Successfully Delete the driver" + driverName,
						"Successful", JOptionPane.INFORMATION_MESSAGE);

				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"Driver.txt"));
				bw.write(sb.toString());
				bw.close();
			}

		} catch (IOException e5) {
			e5.printStackTrace();
		}

	}

	/**
	 * Get all the drivers
	 * 
	 * @return Name of all the drivers
	 */
	public ArrayList<String> allDriver() {
		ArrayList<String> driverName = new ArrayList<String>();
		String b;

		int i = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader("Driver.txt"));

			while ((b = in.readLine()) != null) {

				String strr = b.trim();
				String[] abc = strr.split("[\\p{Space}]+");
				driverName.add(abc[0]);
				i++;

			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return driverName;
	}

	/**
	 * Get journeys belongs to a driver
	 * 
	 * @param name
	 *            Name of the driver to query
	 * @return All the journeys of this driver
	 */
	public ArrayList<String> search_name(String name) {
		String b;
		ArrayList<String> trainName = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("Driver.txt"));

			while ((b = in.readLine()) != null) {

				String[] oldDriverName = b.split("	");

				if (name.equals(oldDriverName[0])) {
					String strr = b.trim();
					String[] abc = strr.split("[\\p{Space}]+");
					for (int j = 1; j < abc.length - 1; j++) {

						trainName.add(abc[j]);

					}
				}
			}
			in.close();
			return trainName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the free time of a driver
	 * 
	 * @param name
	 *            Name of the driver to query
	 * @return Free time of this driver in pairs
	 */
	public ArrayList<Integer> find_Freetime(String name) {
		AllTrain al = new AllTrain();
		ArrayList<String> trainName = search_name(name);
		ArrayList<Integer> freeTime = new ArrayList<Integer>();

		freeTime.add(0);
		for (int i = 0; i <= trainName.size() - 1; i++) {
			ArrayList<Integer> arrTime = al.getTrainByName(trainName.get(i))
					.getArrivingTime();
			freeTime.add(arrTime.get(0));
			freeTime.add(arrTime.get(arrTime.size() - 1));
		}
		freeTime.add(2400);

		return freeTime;

	}

	/**
	 * Judge whether the driver's busy time conflicts with a journey
	 * 
	 * @param arriveTimeList
	 *            the time list of the journey to query
	 * @param driverName
	 *            Name of the driver to check
	 * @return If time conflicts, return 1; if not, return -1
	 **/
	public int checkTimeConflict(ArrayList<Integer> arriveTimeList,
			String driverName) {

		ArrayList<Integer> freeTime = this.find_Freetime(driverName);
		int firstTime = arriveTimeList.get(0);
		int endTime = arriveTimeList.get(arriveTimeList.size() - 1);

		int temp = 1;
		for (int i = 0; i <= freeTime.size() - 2; i += 2) {
			if (firstTime >= freeTime.get(i) && endTime <= freeTime.get(i + 1)) {
				temp = -1;
				break;
			}
		}
		return temp;

	}

	public static void main(String arg[]) {

	}
}