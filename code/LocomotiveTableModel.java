

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @ClassName: LocomotiveTableModel
 * @Description: The table model that stores information of all the trains.
 * @Author: Group44
 * @Version:
 * 
 */

public class LocomotiveTableModel extends AbstractTableModel {

	public final static String[] columnNames = { "Locomotive Name",
			"Journeys of the Locomotive", "Free Time", };
	public Object[][] values;
	Locomotive myLocomotive = new Locomotive();

	public LocomotiveTableModel() {

		setValues();

	}

	@Override
	public int getRowCount() {

		return myLocomotive.allLocomotive().size();
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
		values = new Object[myLocomotive.allLocomotive().size()][3];
		for (int i = 0; i < myLocomotive.allLocomotive().size(); i++) {
			values[i][0] = myLocomotive.allLocomotive().get(i);
			values[i][1] = myLocomotive.search_name(myLocomotive
					.allLocomotive().get(i));
			values[i][2] = "";
			for (int j = 0; j < myLocomotive.find_Freetime(
					myLocomotive.allLocomotive().get(i)).size(); j = j + 2) {
				values[i][2] = (String) values[i][2]
						+ "From "
						+ myLocomotive.find_Freetime(
								myLocomotive.allLocomotive().get(i)).get(j)
						+ " to "
						+ myLocomotive.find_Freetime(
								myLocomotive.allLocomotive().get(i)).get(j + 1)
						+ "  //   ";
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