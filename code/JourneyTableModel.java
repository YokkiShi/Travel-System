

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @ClassName: JourneyTableModel
 * @Description: The table model that stores information of journeys.
 * @Author: Group44
 * @Version:
 * 
 */

public class JourneyTableModel extends AbstractTableModel {

	public static String[] columnNames;
	public String routeID;
	public Object[][] values;

	public JourneyTableModel(String routeID) {
		InitialInterface.myJourney.initialize();
		this.routeID = routeID;
		setColumnNames();
		setValues();
	}

	@Override
	public int getRowCount() {
		return InitialInterface.myJourney.getRouteTrain(routeID).size();
	}

	@Override
	public int getColumnCount() {
		InitialInterface.myJourney.getRoute(routeID).size();
		return (3 + InitialInterface.myJourney.getRoute(routeID).size());
	}

	/**
	 * Set the values shown in the table
	 * 
	 */
	public void setValues() {
		InitialInterface.myJourney.initialize();
		values = new Object[InitialInterface.myJourney.getRouteTrain(routeID)
				.size()][InitialInterface.myJourney.getRoute(routeID).size() + 4];
		for (int i = 0; i < InitialInterface.myJourney.getRouteTrain(routeID)
				.size(); i++) {
			values[i][0] = InitialInterface.myJourney.getRouteTrain(routeID)
					.get(i);

			for (int j = 1; j <= InitialInterface.myJourney.getRoute(routeID)
					.size(); j++) {

				String arriveTime = String.valueOf(InitialInterface.myJourney
						.getTrainByName(
								InitialInterface.myJourney.getRouteTrain(
										routeID).get(i)).getArrivingTime()
						.get(j - 1));
				if (arriveTime.length() < 4) {

					if (arriveTime.length() == 3) {
						arriveTime = "0" + arriveTime;
					}

					if (arriveTime.length() == 2) {
						arriveTime = "00" + arriveTime;
					}
					if (arriveTime.length() == 1) {
						arriveTime = "000" + arriveTime;
					}

				}
				String hourTime = arriveTime.substring(0, 2);
				String minuteTime = arriveTime
						.substring(2, arriveTime.length());
				String newArriveTime = hourTime + ":" + minuteTime;
				values[i][j] = newArriveTime;
			}

			values[i][1 + InitialInterface.myJourney.getRoute(routeID).size()] = InitialInterface.myJourney
					.getTrainByName(
							InitialInterface.myJourney.getRouteTrain(routeID)
									.get(i)).getLocomotiveName();

			values[i][2 + InitialInterface.myJourney.getRoute(routeID).size()] = InitialInterface.myJourney
					.getTrainByName(
							InitialInterface.myJourney.getRouteTrain(routeID)
									.get(i)).getDriverName();

		}
	}

	/**
	 * Set the Column Names shown in the table
	 * 
	 */
	public void setColumnNames() {
		columnNames = new String[4 + InitialInterface.myJourney.getRoute(
				routeID).size()];
		columnNames[0] = "JourneyID";
		for (int i = 1; i <= InitialInterface.myJourney.getRoute(routeID)
				.size(); i++) {
			columnNames[i] = "Station "
					+ InitialInterface.myJourney.getRoute(routeID).get(i - 1);
		}
		columnNames[1 + InitialInterface.myJourney.getRoute(routeID).size()] = "Train";
		columnNames[2 + InitialInterface.myJourney.getRoute(routeID).size()] = "Driver";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return values[rowIndex][columnIndex];
	}

	public String getColumnName(int column) {

		return columnNames[column];
	}

}
