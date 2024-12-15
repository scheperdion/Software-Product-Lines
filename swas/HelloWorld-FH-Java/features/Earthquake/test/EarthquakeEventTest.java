package test;

import event.EarthquakeEvent;
import event.EventLocation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EarthquakeEventTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSingleLocationEarthquake() {
        EarthquakeEvent e = new EarthquakeEvent(
                "low", "Netherlands", new String[] {"Utrecht"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals(1, e.getArea().length);
        assertEquals("low", e.getSeverity());
        assertTrue(e.toString().contains("low severity"));
    }

    @Test
    public void testMultipleLocationsEarthquake() {
        EarthquakeEvent e = new EarthquakeEvent(
                "high", "Japan", new String[] {"Tokyo", "Osaka"},
                new EventLocation[] {
                        new EventLocation(35.6895, 139.6917, 15.0), // Tokyo
                        new EventLocation(34.6937, 135.5023, 20.0)  // Osaka
                });

        assertEquals(2, e.getArea().length);
        assertEquals("high", e.getSeverity());
        assertTrue(e.toString().contains("high"));
    }

    @Test
    public void testNoLocationsEarthquake() {
        EarthquakeEvent e = new EarthquakeEvent(
                "moderate", "Unknown", new String[] {"Unknown"}, new EventLocation[] {});

        assertEquals(0, e.getArea().length);
        assertEquals("moderate", e.getSeverity());
        assertTrue(e.toString().contains("moderate"));
    }

    @Test
    public void testNullProvincesEarthquake() {
        EarthquakeEvent e = new EarthquakeEvent(
                "extreme", "Indonesia", null, 
                new EventLocation[] { new EventLocation(-6.2088, 106.8456, 50.0) });

        assertEquals(1, e.getArea().length);
        assertEquals("extreme", e.getSeverity());
        assertTrue(e.toString().contains("extreme"));
    }

    @Test
    public void testEmptySeverityEarthquake() {
        EarthquakeEvent e = new EarthquakeEvent(
                "", "Chile", new String[] {"Santiago"},
                new EventLocation[] { new EventLocation(-33.4489, -70.6693, 30.0) });

        assertEquals(1, e.getArea().length);
        assertEquals("", e.getSeverity());
        assertTrue(e.toString().contains("severity at"));
    }

    @Test
    public void testIconPath() {
        EarthquakeEvent e = new EarthquakeEvent(
                "low", "USA", new String[] {"California"},
                new EventLocation[] { new EventLocation(36.7783, -119.4179, 100.0) });

        assertEquals("../../Earthquake/Earthquake.png", e.iconPath());
    }

    @Test
    public void testNullLocations() {
        EarthquakeEvent e = new EarthquakeEvent(
                "high", "Italy", new String[] {"Lazio"}, null);

        assertNull(e.getArea());
        assertEquals("high", e.getSeverity());
    }
}
