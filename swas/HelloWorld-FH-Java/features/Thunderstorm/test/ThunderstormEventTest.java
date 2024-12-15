package test;

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
                "moderate", "USA", new String[] {"California"});

        assertEquals("moderate", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT moderate"));
        assertEquals("../../Thunderstorm/Thunderstorm.png", e.iconPath());
    }

    @Test
    public void testMultipleProvincesThunderstorm() {
        ThunderstormEvent e = new ThunderstormEvent(
                "severe", "Canada", new String[] {"Ontario", "Quebec"});

        assertEquals("severe", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT severe"));
    }

    @Test
    public void testNoProvincesThunderstorm() {
        ThunderstormEvent e = new ThunderstormEvent(
                "low", "Germany", new String[] {});

        assertEquals("low", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT low"));
    }

    @Test
    public void testNullProvincesThunderstorm() {
        ThunderstormEvent e = new ThunderstormEvent(
                "extreme", "France", null);

        assertEquals("extreme", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT extreme"));
    }

    @Test
    public void testDatetimeInitialization() {
        ThunderstormEvent e = new ThunderstormEvent(
                "high", "Australia", new String[] {"New South Wales"});

        LocalDateTime now = LocalDateTime.now();
        assertTrue(e.toString().contains("Thunderstorm ALERT high"));
        assertTrue(e.toString().contains("high"));
        assertNotNull(e.getSeverity());
        assertTrue(e.toString().contains("high"));
    }

    @Test
    public void testSeverityEmpty() {
        ThunderstormEvent e = new ThunderstormEvent(
                "", "Brazil", new String[] {"São Paulo"});

        assertEquals("", e.getSeverity());
        assertTrue(e.toString().contains("Thunderstorm ALERT"));
    }

    @Test
    public void testNullCountry() {
        ThunderstormEvent e = new ThunderstormEvent(
                "moderate", null, new String[] {"Province1", "Province2"});

        assertEquals("moderate", e.getSeverity());
        assertNull(e.getArea());
        assertTrue(e.toString().contains("Thunderstorm ALERT moderate"));
    }

    @Test
    public void testIconPath() {
        ThunderstormEvent e = new ThunderstormEvent(
                "severe", "Japan", new String[] {"Hokkaido"});

        assertEquals("../../Thunderstorm/Thunderstorm.png", e.iconPath());
    }

    @Test
    public void testGetAreaReturnsNull() {
        ThunderstormEvent e = new ThunderstormEvent(
                "low", "Italy", new String[] {"Lombardy"});

        assertNull(e.getArea());
    }

}

