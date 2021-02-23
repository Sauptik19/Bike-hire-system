package edu.srh.bikehire.util;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.srh.bikehire.exception.BikeHireSystemException;
import edu.srh.bikehire.service.core.impl.UpcomingAppointment;

public class GetUpcomingAppointmentsTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Test
	public void testGetCalenderfromString() throws BikeHireSystemException {
	     thrown.expect(BikeHireSystemException.class);
	
	
	UpcomingAppointment Appointment = new UpcomingAppointment();
	}
	
	{
		fail("Not yet implemented");
	}
}