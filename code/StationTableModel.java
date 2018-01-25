

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @ClassName: StationTableModel
 * @Description: The table model that stores information of all stations.
 * @Author: Group44
 * @Version:
 * 
 */
public class StationTableModel extends AbstractTableModel {

	public final static String[] columnNames = { "Station Name",
			"Journeys go through the station", "Other Information", };
	public Object[][] values;
	AllStation myStation = new AllStation();

	public StationTableModel() {
		InitialInterface.myJourney.initialize();
		myStation.initialize();
		setValues();

	}

	@Override
	public int getRowCount() {

		return myStation.getAllStation().size();
	}

	@Override
	public int getColumnCount() {

		return 3;
	}

	/**
	 * Set the values shown in the table
	 * 
	 */
	public void setValues() {
		InitialInterface.myJourney.initialize();
		myStation.initialize();
		values = new Object[myStation.getAllStation().size()][3];
		for (int i = 0; i < myStation.getAllStation().size(); i++) {
			values[i][0] = myStation.getAllStation().get(i);
			values[i][1] = InitialInterface.myJourney
					.allPassThroughThisStation(myStation.getAllStation().get(i));
			values[i][2] = "other information";
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
