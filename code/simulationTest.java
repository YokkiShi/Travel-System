


/**
 * 
 * @ClassName: simulationTest
 * @Description: Simulate the running of the train.
 * @Author: Group44
 * @Version:
 * 
 */

public class simulationTest {

	public static void main(String[] args) {
		int nowTime = 0;
		TimeScreen timeScreen = new TimeScreen();

		int counter = 0;

		while (true) {
			nowTime = nowTime + 1;
			if ((nowTime % 100) >= 60) 
			{
				nowTime = nowTime + 100 - 60;
			}
			if (((nowTime % 10000) / 100) >= 60) 
			{
				nowTime = nowTime + 10000 - 6000;
			}
			if ((nowTime / 10000) >= 24) 
			{
				nowTime = nowTime - 240000;
			}
			if (counter >= 1000) {
				InitialInterface.myJourney.initialize();
				InitialInterface.myJourney
						.updateAllTrainLocation(nowTime / 100);
				counter = 0;
			}
			String hour = "" + (nowTime / 10000);
			String minute = "" + ((nowTime % 10000) / 100);
			String second = "" + nowTime % 100;
			if (hour.length() == 1) {
				hour = "0" + hour;
			}
			if (minute.length() == 1) {
				minute = "0" + minute;
			}
			if (second.length() == 1) {
				second = "0" + second;
			}

			timeScreen.lblNowTime.setText("Now time: " + hour + ":" + minute
					+ ":" + second);

			try {
				Thread.sleep(1);
				counter++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
