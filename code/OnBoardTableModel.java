

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @ClassName: OnBoardTableModel
 * @Description: The table model that stores information of a single journey.
 * @Author: Group44
 * @Version:
 * 
 */

public class OnBoardTableModel extends AbstractTableModel {

	public static String[] columnNames;
	public String journeyID;
	public Object[][] values;
	public Train myTrain;

	public OnBoardTableModel(String journeyID) {
		InitialInterface.myJourney.initialize();
		this.journeyID = journeyID;
		myTrain = new Train(journeyID);
		setColumnNames();
		setValues();
	}

	@Override
	public int getRowCount() {
		return 1;
	}

	@Override
	public int getColumnCount() {
		myTrain.getStationName().size();
		return (4 + myTrain.getStationName().size());
	}

	/**
	 * Set the values shown in the table
	 * 
	 */
	public void setValues() {
		values = new Object[myTrain.getStationName().size()][myTrain
				.getStationName().size() + 4];

		values[0][0] = myTrain.getTrainName();

		for (int j = 1; j <= myTrain.getStationName().size(); j++) {

			String arriveTime = String.valueOf(myTrain.getArrivingTime().get(
					j - 1));
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
			String minuteTime = arriveTime.substring(2, arriveTime.length());
			String newArriveTime = hourTime + ":" + minuteTime;

			values[0][j] = newArriveTime;
		}

		values[0][1 + myTrain.getStationName().size()] = myTrain
				.getLocomotiveName();

		values[0][2 + myTrain.getStationName().size()] = myTrain
				.getDriverName();

		values[0][3 + myTrain.getStationName().size()] = myTrain
				.getTrainLocation();

	}

	public void setColumnNames() {
		columnNames = new String[4 + myTrain.getStationName().size()];
		columnNames[0] = "JourneyID";
		for (int i = 1; i <= myTrain.getStationName().size(); i++) {
			columnNames[i] = "Station " + myTrain.getStationName().get(i - 1);
		}
		columnNames[1 + myTrain.getStationName().size()] = "Train";
		columnNames[2 + myTrain.getStationName().size()] = "Driver";
		columnNames[3 + myTrain.getStationName().size()] = "Location";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return values[rowIndex][columnIndex];
	}

	public String getColumnName(int column) {

		return columnNames[column];
	}

}
