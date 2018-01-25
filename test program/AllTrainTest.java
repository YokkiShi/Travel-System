package operation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AllTrainTest {
	
	AllTrain allTrain = new AllTrain();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRoute() {
		assertEquals(null,allTrain.getRoute("route_01"));
	}

	@Test
	public void testNameExist() {
		assertEquals(false,allTrain.nameExist("0101"));
	}

}
