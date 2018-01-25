

import java.util.*;
import java.io.*;

import javax.swing.*;

/**
 * 
 * @ClassName: Locomotive
 * @Description: Manage all the trains in the system
 * @Author: Group44
 * @Version:
 * 
 */

public class Locomotive {
	static String name[] = new String[500];

	/**
	 * Add a new train
	 * 
	 * 
	 * 
	 */
	public void new_member() {
		String check1 = "^[1-9]{1}[0-9]{1}|[0]{1}[1-9]{1}$";
		String newLocomotiveName, b;

		File file = new File("Locomotive.txt");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			while (true) {
				newLocomotiveName = JOptionPane.showInputDialog(null,
						"Please input a new Locomotive ID",
						"Add new Locomotive", JOptionPane.PLAIN_MESSAGE);
				if (newLocomotiveName == null) {
					return;
				} else if (!newLocomotiveName.matches(check1)) {
					JOptionPane
							.showMessageDialog(
									null,
									"illegal name! \n Please input a number from 01-99",
									"error", JOptionPane.INFORMATION_MESSAGE);
					continue;

				} else {

					newLocomotiveName = "train_" + newLocomotiveName;
					int flag = 0;
					BufferedReader in = new BufferedReader(new FileReader(
							"Locomotive.txt"));
					while ((b = in.readLine()) != null) {

						String[] oldLocomotiveName = b.split("	");
						if (newLocomotiveName.equals(oldLocomotiveName[0])) {
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
			bufferWritter.write(newLocomotiveName + "	!" + "\r\n");
			bufferWritter.close();
			JOptionPane.showMessageDialog(null, "input success!", "message",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Add a journey to the train
	 * 
	 * @param LocomotiveName
	 *            Name of the train
	 * @param journeyID
	 *            Name of the journey to be added
	 */

	public void addToRoute(String LocomotiveName, String journeyID) {

		AllTrain al = new AllTrain();
		try {
			String b;
			String c;
			StringBuffer sb = new StringBuffer();
			BufferedReader in;
			in = new BufferedReader(new FileReader("Locomotive.txt"));

			while ((b = in.readLine()) != null) {
				c = "\r\n";
				String[] addToLocomotiveName = b.split("	");

				if (LocomotiveName.equals(addToLocomotiveName[0])) {
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
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"Locomotive.txt"));
			bw.write(sb.toString());
			bw.close();

		} catch (IOException e5) {
			e5.printStackTrace();
		}

	}

	/**
	 * Delete a journey from a train
	 * 
	 * @param LocomotiveName
	 *            Name of the train
	 * @param journeyID
	 *            Name of the journey to be deleted
	 */

	public void deleteJourneyFromLocomotive(String LocomotiveName,
			String journeyID) {

		try {
			String b;
			String c;
			StringBuffer sb = new StringBuffer();
			BufferedReader in;
			in = new BufferedReader(new FileReader("Locomotive.txt"));

			while ((b = in.readLine()) != null) {
				c = "\r\n";

				String[] theLocomotiveName = b.split("	");

				if (LocomotiveName.equals(theLocomotiveName[0])) {
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
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"Locomotive.txt"));
			bw.write(sb.toString());
			bw.close();

		} catch (IOException e5) {
			e5.printStackTrace();
		}

	}

	/**
	 * Delete a train
	 * 
	 * @param LocomotiveName
	 *            Name of the train to be deleted.
	 */

	public void deleteLocomotive(String LocomotiveName) {

		int ck = 0;
		try {
			String b;
			String c;
			StringBuffer sb = new StringBuffer();
			BufferedReader in;
			in = new BufferedReader(new FileReader("Locomotive.txt"));
			int unsuccess = 0;

			while ((b = in.readLine()) != null) {
				c = "\r\n";
				String[] deleteLocomotiveName = b.split("	");

				if (LocomotiveName.equals(deleteLocomotiveName[0])) {
					ck = 1;
					String strr = b.trim();
					String[] abc = strr.split("[\\p{Space}]+");

					int con = JOptionPane.showConfirmDialog(null,
							"Do you want delete this Locomotive", "confirm",
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
						"Successfully Delete the locomotive " + LocomotiveName,
						"Successful", JOptionPane.INFORMATION_MESSAGE);

				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"Locomotive.txt"));
				bw.write(sb.toString());
				bw.close();
			}

		} catch (IOException e5) {
			e5.printStackTrace();
		}

	}

	/**
	 * Get all the trains
	 * 
	 * @return Name of all the trains
	 */
	public ArrayList<String> allLocomotive() {
		ArrayList<String> LocomotiveName = new ArrayList<String>();
		String b;

		int i = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"Locomotive.txt"));

			while ((b = in.readLine()) != null) {

				String strr = b.trim();
				String[] abc = strr.split("[\\p{Space}]+");
				LocomotiveName.add(abc[0]);
				i++;

			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return LocomotiveName;
	}

	/**
	 * Get journeys belongs to a train
	 * 
	 * @param name
	 *            Name of the train to query
	 * @return All the journeys of this train
	 */
	public ArrayList<String> search_name(String name) {
		String b;
		ArrayList<String> trainName = new ArrayList<String>();

		int i = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"Locomotive.txt"));

			while ((b = in.readLine()) != null) {

				String[] oldLocomotiveName = b.split("	");

				if (name.equals(oldLocomotiveName[0])) {
					String strr = b.trim();
					String[] abc = strr.split("[\\p{Space}]+");
					for (int j = 1; j < abc.length - 1; j++) {

						trainName.add(abc[j]);

					}
				}
			}
			return trainName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the free time of a train
	 * 
	 * @param name
	 *            Name of the train to query
	 * @return Free time of this train in pairs
	 */
	public ArrayList<Integer> find_Freetime(String name) {
		AllTrain al = new AllTrain();
		al.initialize();
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
	 * Judge whether the train's busy time conflicts with a journey
	 * 
	 * @param arriveTimeList
	 *            the time list of the journey to query
	 * @param locomotiveName
	 *            Name of the train to check
	 * @return If time conflicts, return 1; if not, return -1
	 **/
	public int checkTimeConflict(ArrayList<Integer> arriveTimeList,
			String locomotiveName) {

		ArrayList<Integer> freeTime = this.find_Freetime(locomotiveName);
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
