

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @ClassName: EachTrainStopScreenTableModel
 * @Description: The table model that stores information of a single station.
 * @Author: Group44
 * @Version:
 * 
 */

public class EachTrainStopScreenTableModel extends AbstractTableModel {

	public static String[] columnNames = { "JourneyID",
			"Stations the journey through", "Location", "Arrival/Start Time" };
	ArrayList<String> journeyList = new ArrayList<String>();
	public String routeID;
	public Object[][] values;
	public String stationName;
	ArrayList<Integer> timeList = new ArrayList<Integer>();

	public EachTrainStopScreenTableModel(ArrayList<String> stationJourneyList,
			String stationName) {
		InitialInterface.myJourney.initialize();
		journeyList.clear();
		journeyList.addAll(stationJourneyList);
		this.stationName = stationName;

		setValues(journeyList);
	}

	@Override
	public int getRowCount() {
		return journeyList.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	/**
	 * Set the values shown in the table
	 * 
	 */
	public void setValues(ArrayList<String> stationJourneyList) {
		values = new Object[stationJourneyList.size()][4];
		for (int i = 0; i < stationJourneyList.size(); i++) {
			InitialInterface.myJourney
					.getTrainByName(stationJourneyList.get(i)).getStationName();
			values[i][0] = stationJourneyList.get(i);
			values[i][1] = InitialInterface.myJourney.getTrainByName(
					stationJourneyList.get(i)).getStationName();
			values[i][2] = InitialInterface.myJourney.getTrainByName(
					stationJourneyList.get(i)).getTrainLocation();

			int timeindex = InitialInterface.myJourney
					.getTrainByName(stationJourneyList.get(i)).getStationName()
					.indexOf(stationName);

			String arriveTime = String.valueOf(InitialInterface.myJourney
					.getTrainByName(stationJourneyList.get(i))
					.getArrivingTime().get(timeindex));
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

			values[i][3] = newArriveTime;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		return values[rowIndex][columnIndex];
	}

	public String getColumnName(int column) {

		return columnNames[column];
	}

}
