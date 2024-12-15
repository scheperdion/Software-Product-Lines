package test;

import event.FloodingEvent;
import event.EventLocation;
import event.ThunderstormEvent;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class FloodingEventTest {

    @Before
    public void setUp() throws Exception {
        // Setup actions if required before each test
    }

    @After
    public void tearDown() throws Exception {
        // Cleanup actions if required after each test
    }

    @Test
    public void testSingleProvinceFlooding() {
        FloodingEvent e = new FloodingEvent(
                "moderate", "USA", new String[] {"California"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("moderate", e.getSeverity());
        assertTrue(e.toString().contains("Flooding ALERT moderate"));
        assertEquals("../../Flooding/Flooding.png", e.iconPath());
    }

    @Test
    public void testMultipleProvincesFlooding() {
    	FloodingEvent e = new FloodingEvent(
                "severe", "Canada", new String[] {"Ontario", "Quebec"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("severe", e.getSeverity());
        assertTrue(e.toString().contains("Flooding ALERT severe"));
    }

    @Test
    public void testNoProvincesFlooding() {
    	FloodingEvent e = new FloodingEvent(
                "low", "Germany", new String[] {},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("low", e.getSeverity());
        assertTrue(e.toString().contains("Flooding ALERT low"));
    }

    @Test
    public void testNullProvincesFlooding() {
    	FloodingEvent e = new FloodingEvent(
                "extreme", "France", null,
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("extreme", e.getSeverity());
        assertTrue(e.toString().contains("Flooding ALERT extreme"));
    }

    @Test
    public void testDatetimeInitialization() {
    	FloodingEvent e = new FloodingEvent(
                "high", "Australia", new String[] {"New South Wales"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        LocalDateTime now = LocalDateTime.now();
        assertTrue(e.toString().contains("Flooding ALERT high"));
        assertTrue(e.toString().contains("high"));
        assertNotNull(e.getSeverity());
        assertTrue(e.toString().contains("high"));
    }

    @Test
    public void testSeverityEmpty() {
    	FloodingEvent e = new FloodingEvent(
                "", "Brazil", new String[] {"São Paulo"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("", e.getSeverity());
        assertTrue(e.toString().contains("Flooding ALERT"));
    }

    @Test
    public void testIconPath() {
    	FloodingEvent e = new FloodingEvent(
                "severe", "Japan", new String[] {"Hokkaido"},
                new EventLocation[] { new EventLocation(52, 5, 10.0) });

        assertEquals("../../Flooding/Flooding.png", e.iconPath());
    }
    
    @Test
    public void testNullLocations() {
    	FloodingEvent e = new FloodingEvent(
                "high", "Italy", new String[] {"Lazio"}, null);

        assertNull(e.getArea());
        assertEquals("high", e.getSeverity());
    }

}