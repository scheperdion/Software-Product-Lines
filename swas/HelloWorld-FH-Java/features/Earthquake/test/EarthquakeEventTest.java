package test;

import event.EarthquakeEvent;
import event.EventLocation; 
import static org.junit.Assert.*; 

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 

import Base.Alert;

public  class  EarthquakeEventTest {
	

	@Before
	public void setUp() throws Exception {
	}

	

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testEarthquake() {
		EarthquakeEvent e = new EarthquakeEvent(
				"abcdefg", "hijklmnop", new String[] {"province"}, 
				new EventLocation[] { new EventLocation(5.0,66.0,0.0)});
		assertEquals(1, e.getArea().length);
		String s = e.toString();
		assertTrue(s.contains("66.0") && s.contains("abcdefg"));
	}
	

	@Test
	public void testEarthquake2() {
		EarthquakeEvent e = new EarthquakeEvent(
				"severity", "country", new String[] {"province"}, 
				new EventLocation[] { });
		assertTrue(e.toString().contains("severity"));
	}


}
