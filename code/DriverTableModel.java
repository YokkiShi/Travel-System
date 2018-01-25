

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @ClassName: DriverTableModel
 * @Description: The table model that stores the driver information.
 * @Author: Group44
 * @Version:
 * 
 */

public class DriverTableModel extends AbstractTableModel {

	public final static String[] columnNames = { "Driver Name",
			"Journeys of the Driver", "Free Time", };
	public Object[][] values;
	Driver myDriver = new Driver();

	public DriverTableModel() {

		setValues();

	}

	@Override
	public int getRowCount() {

		return myDriver.allDriver().size();
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
		values = new Object[myDriver.allDriver().size()][3];
		for (int i = 0; i < myDriver.allDriver().size(); i++) {
			values[i][0] = myDriver.allDriver().get(i);
			values[i][1] = myDriver.search_name(myDriver.allDriver().get(i));
			myDriver.find_Freetime(myDriver.allDriver().get(i));
			values[i][2] = "";
			for (int j = 0; j < myDriver.find_Freetime(
					myDriver.allDriver().get(i)).size(); j = j + 2) {
				values[i][2] = (String) values[i][2]
						+ "From "
						+ myDriver.find_Freetime(myDriver.allDriver().get(i))
								.get(j)
						+ " to "
						+ myDriver.find_Freetime(myDriver.allDriver().get(i))
								.get(j + 1) + "  //   ";
			}
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
