

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @ClassName: AllTrain
 * @Description: Manage all the journeys in the system
 * @Author: Group44
 * @Version:
 * 
 */

public class AllTrain {
	ArrayList<Train> allTrain = new ArrayList<Train>();

	/**
	 * Automatically read the information of all the journey from the file
	 * 
	 */
	public AllTrain() {
		initialize();
	}

	/**
	 * Initial all the information read from the file
	 * 
	 * @return if success, return true; if not success, return false.
	 */
	public boolean initialize() {
		File file = new File("allTrain.txt");
		try {
			Scanner input = new Scanner(file);
			allTrain.clear();
			while (input.hasNext()) {
				addTrain(input.next());
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
	 * Get the name of all the routes
	 * 
	 * @return all route names
	 */
	public ArrayList<String> getAllRoute() {
		ArrayList<String> allRoute = new ArrayList<String>();
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			String temp = allTrain.get(i).getRouteName();
			if (!allRoute.contains(temp)) {
				allRoute.add(temp);
			}
		}
		return allRoute;
	}

	/**
	 * Get the name of all the journey
	 * 
	 * @return all journey names
	 */

	public ArrayList<String> getAllTrain() {
		ArrayList<String> alltheTrain = new ArrayList<String>();
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			alltheTrain.add(allTrain.get(i).getTrainName());
		}
		return alltheTrain;
	}

	/**
	 * Get the station list of the route
	 * 
	 * @param routeName
	 *            Name of the route to query
	 * @return station list
	 */
	public ArrayList<String> getRoute(String routeName) {
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			String temp = allTrain.get(i).getRouteName();
			if (temp.equals(routeName)) {
				return allTrain.get(i).getStationName();
			}
		}
		return null;
	}

	/**
	 * Get all the journeys of a route
	 * 
	 * @param routeName
	 *            Name of the route to query
	 * @return All the journeys
	 */
	public ArrayList<String> getRouteTrain(String routeName) {
		ArrayList<String> routeTrain = new ArrayList<String>();
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			String temp = allTrain.get(i).getRouteName();
			if (temp.equals(routeName)) {
				routeTrain.add(allTrain.get(i).getTrainName());
			}
		}
		return routeTrain;
	}

	/**
	 * Add a new journey
	 * 
	 * @param name
	 *            Name of the journey to add
	 * @return If success, return true; if not, return false.
	 */
	public boolean addTrain(String name) {
		if (nameExist(name)) {
			System.out.println("The name of the existing train.");
			return false;
		}
		allTrain.add(new Train(name));
		return true;
	}

	/**
	 * Determine whether journey with such a name already exits.
	 * 
	 * @param name
	 *            Name of the journey to query
	 * @return If the train exits, return true; if the train does not
	 *         exit,return false.
	 */
	public boolean nameExist(String name) {
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			if (allTrain.get(i).getTrainName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Get the information of the journey by name.
	 * 
	 * @param name
	 *            Name of the journey to query
	 * @return the journey
	 */
	public Train getTrainByName(String name) {
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			if (allTrain.get(i).getTrainName().equals(name)) {
				return allTrain.get(i);
			}
		}
		return null;
	}

	/**
	 * Delete the journey with this name
	 * 
	 * @param name
	 *            Name of the journey to delete
	 * @return If success, return true; if not, return false.
	 */
	public boolean removeTrain(String name) {
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			if (allTrain.get(i).getTrainName().equals(name)) {

				allTrain.get(i).deleteTrain();
				allTrain.remove(i);

				File allFile = new File("allTrain.txt");
				try {
					FileWriter allWriter = new FileWriter(allFile, false);
					String allRemove = "";
					for (int ii = 0; ii < allTrain.size(); ii++) {
						allRemove = allRemove + allTrain.get(ii) + " ";
					}
					allWriter.write(allRemove);
					allWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return true;
			}
		}

		System.out.println("No such journey exits.");
		return false;
	}

	/**
	 * Search all the journeys pass through this station
	 * 
	 * @param station
	 *            Name of the station to query
	 * @return Name of all the journeys pass through this station
	 */
	public ArrayList<String> allPassThroughThisStation(String station) {
		ArrayList<String> trainName = new ArrayList<String>();
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			if (allTrain.get(i).passThroughThisStation(station) != -1) {
				trainName.add(allTrain.get(i).getTrainName());
			}
		}
		return trainName;
	}

	/**
	 * Search all the journeys go from station1 to station2
	 * 
	 * @param station1
	 *            First station
	 * @param station2
	 *            Second station
	 * @return all the journeys can go from first station to second station.
	 */
	public ArrayList<String> allFromFirstToSecond(String station1,
			String station2) {
		ArrayList<String> trainName = new ArrayList<String>();
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			if (allTrain.get(i).fromFirstToSecond(station1, station2) != -1) {
				trainName.add(allTrain.get(i).getTrainName());
			}
		}
		return trainName;
	}

	/**
	 * Search all the journeys pass station1 and station2
	 * 
	 * @param station1
	 *            First station
	 * @param station2
	 *            Second station
	 * @return all the journeys can pass first station and second station.
	 */
	public ArrayList<String> allThroughFirstAndSecond(String station1,
			String station2) {
		ArrayList<String> trainName = new ArrayList<String>();
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			if (allTrain.get(i).getStationName().indexOf(station1) > 0
					&& allTrain.get(i).getStationName().indexOf(station1) > 0) {
				trainName.add(allTrain.get(i).getTrainName());
			}
		}
		return trainName;
	}

	/**
	 * Determine whether the system exits a journey that pass a certain station
	 * in a certain time
	 * 
	 * @param station
	 *            Station to query
	 * @param time
	 *            Time to query
	 * @return If exits such a journey, return true; if not return false.
	 */
	public String checkTimeConflict(String station, int time) {
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			if (allTrain.get(i).passThroughThisStation(station) == time) {
				return allTrain.get(i).getTrainName();
			}
		}
		return null;
	}

	/**
	 * Update the location of all the train and write into the file
	 * 
	 * @param nowTime
	 *            the updated time
	 */
	public void updateAllTrainLocation(int nowTime) {
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			allTrain.get(i).updateTrainLocation(nowTime);
		}
	}

	/**
	 * Override the toString() method
	 * 
	 * @return Name of all trains
	 */
	public String toString() {
		String temp = "";
		for (int i = 0; i <= allTrain.size() - 1; i++) {
			temp = temp + " " + allTrain.get(i);
		}
		return temp;
	}
}
