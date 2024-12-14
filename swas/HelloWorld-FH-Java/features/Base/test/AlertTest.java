package test;

import static org.junit.Assert.*; 

import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 

import Base.Alert;

public  class  AlertTest {
	

	@Before
	public void setUp() throws Exception {
	}

	

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void test() {
		Alert a = new Alert();
		assertEquals("Some message", a.toString());
	}


}
