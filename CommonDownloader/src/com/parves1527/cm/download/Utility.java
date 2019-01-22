package com.parves1527.cm.download;

import com.parves1527.cm.download.info.Protocol;

public class Utility
{
    public static Protocol getProtocol(String address)
    {
        int index = address.indexOf(':');
        if(index <= 0) {
            return null;
        }
        
        return Protocol.valueOf(address.substring(0, index));
    }                
}
