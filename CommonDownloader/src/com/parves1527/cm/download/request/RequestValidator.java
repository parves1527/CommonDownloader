package com.parves1527.cm.download.request;

import com.parves1527.cm.CmRequestException;
import com.parves1527.cm.download.info.Protocol;
import com.parves1527.cm.download.Utility;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestValidator
{
    public static void checkAddressValidity(String address)
    {
        Protocol protocol = Utility.getProtocol(address);
        
        if(protocol == null)
        {
            throw new CmRequestException("No protocol found in address");
        }
        
        String modifiedAddress = address.replaceAll(protocol.name() + "://", "http://");
        
        try
        {
            URL url = new URL(modifiedAddress);
        } catch (MalformedURLException ex)
        {
            throw new CmRequestException("Address is not in url format");
        }
    }    
}
