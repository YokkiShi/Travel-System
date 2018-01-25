package operation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AllStationTest {

	AllStation allStation = new AllStation();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testRemoveStation() {
		assertEquals(true,allStation.removeStation("A"));
	}

}
