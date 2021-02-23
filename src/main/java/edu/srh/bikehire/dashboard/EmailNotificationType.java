package edu.srh.bikehire.dashboard;

public enum EmailNotificationType {
	
	REGISTRATION_CONFIRMATION(1, "Welcome to Bike Hire System" ,"For your email address verification, use code: $SECURITY_CODE$ "),
	RESET_PASSWORD(2, "Security code for reset password", "For resetting the password, use verification code : $SECURITY_CODE$ "),
	BOOKING_CONFIRMATION(3, "Booking Confirmed!" , "YAY! your booking is confirmed! Your order id is : $ORDER_ID$"),
	BOOKING_CANCEL(4, "Booking Cancelled" ,"Your booking for order id $ORDER_ID$ has been cancelled successfully. "),
	BOOKING_INVOICE(5, "Order Invoice", "Here's your order invoice for order id $ORDER_ID$. Invoice ID: $INVOICE_ID$ Amount paid : $AMOUNT$"),
	RESET_PASSWORD_SUCCESS(6, "Password changed successfully!", "Your password has been changed successfully!");
	
	private int lNotificationType;
	
	private String lSubjectLine;
	
	private String lBodyText;
	
	private EmailNotificationType(int pType, String pSubjectLine, String pBodyText) {
		lNotificationType = pType;
		lSubjectLine = pSubjectLine;
		lBodyText = pBodyText;
	}
	

	public int getNotificationType() {
		return lNotificationType;
	}

	public String getSubjectLine() {
		return lSubjectLine;
	}

	public String getBodyText() {
		return lBodyText;
	}
	
}
