package edu.srh.bikehire.exception;

import edu.srh.bikehire.exception.util.ExceptionUtil;

public class BikeHireSystemException extends Exception {
	
	private long mlExceptionID;
	
	private String mstrErrorMessage;
	
	private String mstrDisplayMessage;
	
	public BikeHireSystemException(long pExceptionID)
	{
		mlExceptionID = pExceptionID;
		mstrErrorMessage = ExceptionUtil.getErrorMessage(mlExceptionID, null);
		mstrDisplayMessage = ExceptionUtil.getDisplayMessage(mlExceptionID, null);
	}
	
	public BikeHireSystemException(long pExceptionID, Object[] pPlaceHolderValues)
	{
		mlExceptionID = pExceptionID;
		mstrErrorMessage = ExceptionUtil.getErrorMessage(mlExceptionID, pPlaceHolderValues);
		mstrDisplayMessage = ExceptionUtil.getDisplayMessage(mlExceptionID, pPlaceHolderValues);
	}
	
	public BikeHireSystemException(long pExceptionID, Object[] pPlaceHolderValues, Throwable pRootCause)
	{
		mlExceptionID = pExceptionID;
		mstrErrorMessage = ExceptionUtil.getErrorMessage(mlExceptionID, pPlaceHolderValues);
		mstrDisplayMessage = ExceptionUtil.getDisplayMessage(mlExceptionID, pPlaceHolderValues);
		this.initCause(pRootCause);
	}
	
	public String getMessage()
	{
		return mstrErrorMessage;
	}
	
	public String getDisplayMessage()
	{
		return mstrDisplayMessage;
	}
	
}
