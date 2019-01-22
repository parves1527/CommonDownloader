package com.parves1527;

import com.parves1527.cm.CmRequestInfo;
import com.parves1527.cm.download.info.Password;

public class InputParser
{
    public InputParser()
    {
    }
            
    public CmRequestInfo parse(String input, String configFile) {

        String parts[] = input.split(" ");
        
        if(parts.length == 0) {
            System.err.println("No uri found");
            return null;
        }
        
        CmRequestInfo.Builder builder = CmRequestInfo.Builder.newInstance(parts[0], configFile);
        
        if(parts.length > 1) {
            builder.setUserName(parts[1]);
        }
        if(parts.length > 2) {
            builder.setPassword(new Password(parts[2]));
        }
        
        return builder.build();
    }
}
