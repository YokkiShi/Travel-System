

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @ClassName: RouteTableModel
 * @Description: The table model that stores information of all routes and the
 *               search results.
 * @Author: Group44
 * @Version:
 * 
 */
public class RouteTableModel extends AbstractTableModel {

	public final static String[] columnNames = { "RouteName",
			" By Pass Stations", "Journeys of the Route" };
	ArrayList<String> routeList = new ArrayList<String>();
	public Object[][] values;

	public RouteTableModel() {
		InitialInterface.myJourney.initialize();
		routeList.clear();
		routeList.addAll(InitialInterface.myJourney.getAllRoute());
		setValues();

	}

	public RouteTableModel(ArrayList<String> searchRouteList) {
		InitialInterface.myJourney.initialize();
		routeList.clear();
		routeList.addAll(searchRouteList);
		setValues(searchRouteList);

	}

	@Override
	public int getRowCount() {
		return routeList.size();
	}

	@Override
	public int getColumnCount() {

		return 3;
	}

	/**
	 * Set the values of search result shown in the table when
	 * 
	 */
	public void setValues(ArrayList<String> searchRouteList) {
		values = new Object[searchRouteList.size()][3];
		for (int i = 0; i < searchRouteList.size(); i++) {
			values[i][0] = searchRouteList.get(i);
			values[i][1] = InitialInterface.myJourney.getRoute(searchRouteList
					.get(i));
			values[i][2] = InitialInterface.myJourney
					.getRouteTrain(searchRouteList.get(i));
		}
	}

	/**
	 * Set the values shown in the table
	 * 
	 */
	public void setValues() {
		InitialInterface.myJourney.initialize();
		routeList.clear();
		routeList.addAll(InitialInterface.myJourney.getAllRoute());
		values = new Object[InitialInterface.myJourney.getAllRoute().size()][3];
		for (int i = 0; i < InitialInterface.myJourney.getAllRoute().size(); i++) {
			values[i][0] = InitialInterface.myJourney.getAllRoute().get(i);
			values[i][1] = InitialInterface.myJourney
					.getRoute(InitialInterface.myJourney.getAllRoute().get(i));
			values[i][2] = InitialInterface.myJourney
					.getRouteTrain(InitialInterface.myJourney.getAllRoute()
							.get(i));
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
