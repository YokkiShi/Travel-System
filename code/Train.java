

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 
 * @ClassName: Train
 * @Description: Manage a single journey in the system
 * @Author: Group44
 * @Version:
 * 
 */

public class Train {
	private String trainName; // name of the journey
	private String fileNameOfTimeTable; // file name of the timetable file
	private String fileNameOfTrainLocation;// file name of the location file
	private String routeName; // name of the route
	private String locomotiveName; // name of the train
	private String driverName; // name of the driver
	private ArrayList<String> stationName = new ArrayList<String>(); // station
																		// name
																		// list
																		// of
																		// this
																		// journey
	private ArrayList<Integer> arrivingTime = new ArrayList<Integer>(); // arrival
																		// time
																		// list
																		// of
																		// this
																		// journey

	/**
	 * Constructor: Automatically read the information of in the file by journey
	 * name
	 * 
	 * @param trainName
	 *            name of the journey
	 * @return no return value
	 */
	public Train(String trainName) { // constructor
		this.trainName = trainName;
		this.fileNameOfTimeTable = "timeTable_" + trainName + ".txt"; // file
																		// name
																		// of
																		// the
																		// time
																		// table
		this.fileNameOfTrainLocation = "trainLocation_" + trainName + ".txt"; // file
																				// name
																				// of
																				// the
																				// location
																				// table
		this.getTimeTable(); // read the file to get the time table
	}

	/**
	 * Clear the time table
	 * 
	 */
	public void clearTimeTable() {
		stationName.clear();
		arrivingTime.clear();
	}

	/**
	 * Add a new station
	 * 
	 * @param name
	 *            station name
	 * @param time
	 *            arrival time of the station
	 */
	public void addNewStation(String name, int time) {
		stationName.add(name);
		arrivingTime.add(time);
	}

	/**
	 * Get the journey name
	 * 
	 * @return Journey name
	 */
	public String getTrainName() {
		return trainName;
	}

	/**
	 * Get the name of the train
	 * 
	 * @return Train name
	 */
	public String getLocomotiveName() {
		return locomotiveName;
	}

	/**
	 * Get the name of driver
	 * 
	 * @return Driver name
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * Get the name of route
	 * 
	 * @return Route name of this journey
	 */
	public String getRouteName() {
		return routeName;
	}

	/**
	 * Get the name of all stations
	 * 
	 * @return Station name list
	 */
	public ArrayList<String> getStationName() {
		return stationName;
	}

	/**
	 * Get all the arrival time
	 * 
	 * @return Arrival time list
	 */
	public ArrayList<Integer> getArrivingTime() {
		return arrivingTime;
	}

	/**
	 * Read the file
	 * <p>
	 * Read the file to get the timetable, route name, train name and driver
	 * name
	 * 
	 */
	public void getTimeTable() {
		File file = new File(fileNameOfTimeTable);
		try {
			Scanner input = new Scanner(file);
			stationName.clear(); // clear the list
			arrivingTime.clear(); // clear the list
			while (input.hasNext()) { // read all the data in the time table
				String temp = input.next();
				if (temp.equals("*")) { // If the next is "*", it means the time
										// table is over. The followings are
										// route name, train name and driver
										// name.
					break;
				}
				stationName.add(temp);
				arrivingTime.add(input.nextInt());
			}
			routeName = input.next(); // After the time table is the route name
			locomotiveName = input.next(); // next is train name
			driverName = input.next(); // next is driver name

			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Calculate the current location of the train.
	 * <p>
	 * And save into the file
	 * <p>
	 * Old, not use now
	 * 
	 */
	public void updateTrainLocation(int nowTime) {

		File file = new File(fileNameOfTrainLocation);
		try {
			PrintWriter output = new PrintWriter(file);

			// System.out.println("Current time: " + nowTime);
			if (nowTime < arrivingTime.get(0)) { // If the current time earlier
													// than the departure time
				output.println("Not start yet.");
				// output.println(stationName.get(0) + " " + stationName.get(0)
				// + " 0"); // Still in the starting station.
				// System.out.println("Not start yet.");
			} else if (nowTime >= arrivingTime.get(arrivingTime.size() - 1)) { // If
																				// the
																				// current
																				// time
																				// later
																				// than
																				// the
																				// terminal
																				// time
				output.println("Arrived the terminus.");
				// output.println(stationName.get(arrivingTime.size() - 1) + " "
				// + stationName.get(arrivingTime.size() - 1) + " 100");
				// System.out.println("Arrived the terminus.");
			} else {
				for (int i = 0; i <= arrivingTime.size() - 2; i++) {
					if (nowTime >= arrivingTime.get(i)
							&& nowTime < arrivingTime.get(i + 1)) {
						int temp = ((nowTime / 100 * 60 + nowTime % 100) - (arrivingTime
								.get(i) / 100 * 60 + arrivingTime.get(i) % 100))
								* 100
								/ ((arrivingTime.get(i + 1) / 100 * 60 + arrivingTime
										.get(i + 1) % 100) - (arrivingTime
										.get(i) / 100 * 60 + arrivingTime
										.get(i) % 100)); // Calculate the
															// current location
															// of the train
						// output.println(stationName.get(i) + " " +
						// stationName.get(i + 1) + " " + temp); // From i
						// station to (i+1) station. 0 represents not set out;
						// 100 represents arriving
						output.println("Between " + stationName.get(i)
								+ " and " + stationName.get(i + 1)
								+ ". At a distance of " + temp + "%");
						break;
					}
				}
			}
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Read the location from the file
	 * 
	 * @return trainLocation
	 */
	public String getTrainLocation() {
		String trainLocation = "";
		File locationFile = new File(fileNameOfTrainLocation);
		try {
			Scanner input = new Scanner(locationFile);
			trainLocation = input.nextLine();
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			trainLocation = "Location information not available! ";
			// e.printStackTrace();
		}

		return trainLocation;
	}

	/**
	 * Determine whether the journey pass through a station.
	 * 
	 * @param station
	 *            the query station
	 * @return If pass through this station, return the arrival time.
	 *         <p>
	 *         If not, return -1
	 */
	public int passThroughThisStation(String station) {

		this.getTimeTable();
		if (stationName.contains(station)) {
			return arrivingTime.get(stationName.indexOf(station));
		} else {
			return -1;
		}
	}

	/**
	 * Determine whether the journey go from first station to the second
	 * station.
	 * 
	 * @param station1
	 *            First station
	 * @param station2
	 *            Second station
	 * @return If the journey go from the first to the second, return 1
	 *         <p>
	 *         If not, return -1
	 */
	public int fromFirstToSecond(String station1, String station2) {

		this.getTimeTable();

		if (stationName.contains(station1)
				&& stationName.contains(station1)
				&& stationName.indexOf(station1) < stationName
						.indexOf(station2)) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * Delete all the file of this journey
	 * 
	 */
	public void deleteTrain() {
		File timeFile = new File(fileNameOfTimeTable);
		timeFile.delete();
		File locationFiles = new File(fileNameOfTrainLocation);
		locationFiles.delete();

	}

	/**
	 * @return Journey name
	 */
	public String toString() {
		return trainName;
	}
}
