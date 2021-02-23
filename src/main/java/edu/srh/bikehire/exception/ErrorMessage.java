package edu.srh.bikehire.exception;

public class ErrorMessage {
	private String mstrErrorMessage;
	
	private String mstrDisplayMessage;
	
	public ErrorMessage(String pErrorMessage, String pDisplayMessage)
	{
		mstrErrorMessage = pErrorMessage;
		mstrDisplayMessage = pDisplayMessage;
	}

	public String getErrorMessage() {
		return mstrErrorMessage;
	}

	public String getDisplayMessage() {
		return mstrDisplayMessage;
	}
}
