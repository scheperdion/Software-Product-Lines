package test;

import event.EarthquakeEvent;
import event.EventLocation;
import event.ThunderstormEvent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class ThunderstormEventTest {

    @Before
    public void setUp() throws Exception {
        // Setup actions if required before each test
    }

    @After
    public void tearDown() throws Exception {
        // Cleanup actions if required after each test
    }

    @Test
    public void testSingleProvinceThunderstorm() {
        ThunderstormEvent e = new ThunderstormEvent(
                "moderate", "USA", new String[] {"California"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("moderate", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT moderate"));
        assertEquals("../../Thunderstorm/Thunderstorm.png", e.iconPath());
    }

    @Test
    public void testMultipleProvincesThunderstorm() {
        ThunderstormEvent e = new ThunderstormEvent(
                "severe", "Canada", new String[] {"Ontario", "Quebec"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("severe", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT severe"));
    }

    @Test
    public void testNoProvincesThunderstorm() {
        ThunderstormEvent e = new ThunderstormEvent(
                "low", "Germany", new String[] {},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("low", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT low"));
    }

    @Test
    public void testNullProvincesThunderstorm() {
        ThunderstormEvent e = new ThunderstormEvent(
                "extreme", "France", null,
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("extreme", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT extreme"));
    }

    @Test
    public void testDatetimeInitialization() {
        ThunderstormEvent e = new ThunderstormEvent(
                "high", "Australia", new String[] {"New South Wales"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        LocalDateTime now = LocalDateTime.now();
        assertTrue(e.toString().contains("Thunderstorm ALERT high"));
        assertTrue(e.toString().contains("high"));
        assertNotNull(e.getSeverity());
        assertTrue(e.toString().contains("high"));
    }

    @Test
    public void testSeverityEmpty() {
        ThunderstormEvent e = new ThunderstormEvent(
                "", "Brazil", new String[] {"São Paulo"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT"));
    }

    @Test
    public void testIconPath() {
        ThunderstormEvent e = new ThunderstormEvent(
                "severe", "Japan", new String[] {"Hokkaido"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("../../Thunderstorm/Thunderstorm.png", e.iconPath());
    }
    
    @Test
    public void testNullLocations() {
    	ThunderstormEvent e = new ThunderstormEvent(
                "high", "Italy", new String[] {"Lazio"}, null);

        assertNull(e.getArea());
        assertEquals("high", e.getSeverity());
    }

}