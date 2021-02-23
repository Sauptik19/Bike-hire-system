package edu.srh.bikehire.service.core;

import java.util.Calendar;

public interface Invoice {

    public String getInvoiceId();
    
    public int getOrderId();
    
    public Calendar getCreationTimestamp();
    
    public int getFinalAmount();
    
    public int getReturnAmount();
    
    public int getDamagedCharges();
    
    public int getWarehouseId();
    
}
