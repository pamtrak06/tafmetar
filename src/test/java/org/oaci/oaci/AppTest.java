package org.oaci.oaci;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testNominalApp1() {
		App app1 = new App("data/admn76.kwbc.031.600_nom1.txt");
		app1.decode();
		assertTrue(app1.getIndex() == 4);
		assertTrue(app1.getMapBlocks().get(0).getOaci().equals("SAVC"));
		assertTrue(app1.getMapBlocks().get(1).getOaci().equals("SAWG"));
		assertTrue(app1.getMapBlocks().get(2).getOaci().equals("SAWE"));
		assertTrue(app1.getMapBlocks().get(3).getOaci().equals("SAWH"));
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testNominalApp2() {
		App app2 = new App("data/admn76.kwbc.031.600_nom2.txt");
		app2.decode();
		assertTrue(app2.getIndex() == 1);
		assertTrue(app2.getMapBlocks().get(0).getOaci().equals("SAVC"));
	}
}
