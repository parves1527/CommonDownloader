package com.parves1527.download;

import com.parves1527.download.info.Credential;
import com.parves1527.download.info.Password;
import com.parves1527.download.info.RequestInfo;
import com.parves1527.download.info.Uri;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InputParser
{
    private final String downloadDir;
    public InputParser(String downloadDir)
    {
        this.downloadDir = downloadDir;
    }
    
    private Protocol getProtocol(String address)
    {
        return Protocol.valueOf(address.substring(0, address.indexOf(':')));
    }
    
    public RequestInfo parseInput(String input) {
        if(!isValid(input)) {
            //print log
            return null;
        }
        Protocol protocol = getProtocol(input);
        if (protocol == null) {
            return null;
        }
                 
        Uri uri = getUri(input.split(" ")[0]);
        Credential credential = getCredential(input);
                
        RequestInfo requestInfo = new RequestInfo(uri, credential);
        
        return requestInfo;
    }

    private boolean isValid(String input)
    {
        //implment logic
        return true;
    }        
    
    private Uri getUri(String address)
    {
        Protocol protocol = getProtocol(address);
        int beginIndex = address.indexOf("://") + 3;
        int endIndex = address.substring(beginIndex).indexOf("/") + beginIndex;
        String host = address.substring(beginIndex, endIndex);
        String remoteResource = address.substring(endIndex+1);        
        int port = 80;
        if(protocol == Protocol.ftp)
        {
            port = 21;
        }
        else if(protocol == Protocol.sftp){
            port = 22;
        }
        
        String localResource = downloadDir + File.separator + getFileName(address.split(" ")[0]);
        
        System.out.println(host);
        System.out.println(remoteResource);
        System.out.println(localResource);
            
        Uri uri = new Uri(protocol, host, port, remoteResource, localResource);
        return uri;
    }

    private Credential getCredential(String input)
    {
        String words[] = input.split(" ");
        String username = "anonymous";
        if(words.length > 1)
        {
            username = words[1];
        }        
        Password password = new Password("password");
        if(words.length > 2)
        {
            password = new Password(words[2]);
        }
        
        return new Credential(username, password);
    }
    
    private String getFileName(String address) {
        
        try
        {
            byte[] bytesOfMessage = address.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            //return new String(thedigest);
            return address.replace(":", "_").replace("/", "_");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
            return address.replace(":", "_").replace("/", "_");
        }                        
    }
}
