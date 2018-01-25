

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @ClassName: AllStation
 * @Description: Manage all the stations in the system
 * @Author: Group44
 * @Version:
 * 
 */

public class AllStation {

	ArrayList<String> allStation = new ArrayList<String>();

	public static void main(String arg[]) {

	}

	/**
	 * Initialize and read all the stations from the file
	 * 
	 */
	public AllStation() {
		initialize();
	}

	/**
	 * Read all the stations from the file
	 * 
	 * @return If read the file successfully, return true; if not return false.
	 */
	public boolean initialize() {
		File file = new File("allStation.txt");
		try {
			Scanner input = new Scanner(file);
			allStation.clear();
			while (input.hasNext()) {
				allStation.add(input.next());
			}
			input.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Get all the stations
	 * 
	 * @return List of all the stations
	 */
	public ArrayList<String> getAllStation() {
		ArrayList<String> alltheStation = new ArrayList<String>();
		for (int i = 0; i <= allStation.size() - 1; i++) {
			alltheStation.add(allStation.get(i));
		}
		return alltheStation;
	}

	/**
	 * Add a new station
	 * 
	 * @param Station
	 *            Name of station to be added
	 * @return if success, return 1; if not return 0.
	 */
	public int addStation(String Station) {
		int flag = 0;
		File allFile = new File("allStation.txt");
		FileWriter allWriter;
		try {
			allWriter = new FileWriter(allFile, true);
			allWriter.write(" " + Station);
			allWriter.close();
			this.initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Determine whether the station with a certain name exits
	 * 
	 * @param name
	 *            the name to query
	 * @return If such station exits, return true; if not, return,false.
	 */
	public boolean nameExist(String name) {
		for (int i = 0; i <= allStation.size() - 1; i++) {
			if (allStation.get(i).equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Delete a station
	 * 
	 * @param name
	 *            Name of the station to be deleted.
	 * @return If success, return true; if can not find this station, return
	 *         false.
	 */
	public boolean removeStation(String name) {
		for (int i = 0; i <= allStation.size() - 1; i++) {
			if (allStation.get(i).equals(name)) {

				allStation.remove(i);

				File allFile = new File("allStation.txt");
				try {
					FileWriter allWriter = new FileWriter(allFile, false);
					String allRemove = "";
					for (int ii = 0; ii < allStation.size(); ii++) {
						allRemove = allRemove + allStation.get(ii) + " ";
					}
					allWriter.write(allRemove);
					allWriter.close();
					this.initialize();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return true;
			}
		}

		System.out.println("No such station");
		return false;
	}

}
