package com.parves1527.cm.download.request;

import com.parves1527.cm.download.Utility;
import com.parves1527.cm.download.info.Protocol;
import com.parves1527.cm.download.request.RequestValidator;
import com.parves1527.cm.download.info.Uri;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UriGenerator
{    
    public Uri generate(String address, String downloadDir)
    {
        RequestValidator.checkAddressValidity(address);
        
        Protocol protocol = Utility.getProtocol(address);
        int beginIndex = address.indexOf("://") + 3;
        int endIndex = address.substring(beginIndex).indexOf("/") + beginIndex;
        String host = address.substring(beginIndex, endIndex);
        String remoteResource = address.substring(endIndex+1);        
        String localResource = downloadDir + File.separator + getFileName(address.split(" ")[0]);        

        Uri uri = new Uri(protocol, host, remoteResource, localResource);

        setPort(uri, host);        

        return uri;
    }
        
    private String getFileName(String address) {

        try {
            byte[] bytesOfMessage = address.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(bytesOfMessage);            
            return getPrintableName(digest);            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
            return address.replace(":", "_").replace("/", "_");
        }
    }
    
    private void setPort(Uri uri, String host)
    {
        int beginIndex = host.indexOf(":");                
        if(beginIndex >= 0)
        {
            int endIndex = host.indexOf("/");
            int port = Integer.parseInt(host.substring(beginIndex+1, endIndex));
            uri.setPort(port);
        }
    }
    
    private String getPrintableName(byte[] buffer) {
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : buffer) {
            stringBuilder.append(b+128);
            stringBuilder.append('_');
        }
        return stringBuilder.toString();
    }
}
