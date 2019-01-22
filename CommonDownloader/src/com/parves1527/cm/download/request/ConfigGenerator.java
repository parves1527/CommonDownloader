package com.parves1527.cm.download.request;

import com.parves1527.cm.CmRequestException;
import com.parves1527.cm.download.info.Config;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigGenerator
{
    private static final String temporaryDirectory = "temporaryDirectory";
    private static final String downloadDirectory = "downloadDirectory";
    private static final String requestTimeout = "requestTimeout";
    private static final String hostVerification = "hostVerification";    
    
    public Config generate(String fileName)
    {
        basicFileCheck(fileName);
        
        Properties prop = new Properties();

        Config config = new Config();
        
        try(InputStream input = new FileInputStream(fileName))
        {
            prop.load(input);
            
            basicPropertyCheck(prop, fileName);

            updateConfig(config, prop);            
            
        } catch (IOException ex)
        {
            throw new CmRequestException(fileName + " is not a vaild properties file");
        } 
        
        return config;
    }
    
    private String getTempDir(Properties prop) {
        String filePath = prop.getProperty(temporaryDirectory);
        File file = new File(filePath);
        
        if(!file.exists()) {
            throw new CmRequestException(temporaryDirectory + " has invalid file path");
        }
        
        return filePath;
    }
    
    private String getDownloadDir(Properties prop) {
        
        String filePath = prop.getProperty(downloadDirectory);
        File file = new File(filePath);
        
        if(!file.exists()) {
            throw new CmRequestException(downloadDirectory + " has invalid file path");
        }
        
        return filePath;
    }
    
    private int getTimeout(Properties prop) {
        
        int i = -1;
        try
        {
            return Integer.parseInt(prop.getProperty(requestTimeout));
        }
        catch(Exception ex){}
        if(i <= 0) {
            throw new CmRequestException(requestTimeout + " value needed be postive integer");
        }
        
        return i;
    }
    
    private boolean getHostVerification(Properties prop) {
                
        try
        {
            return Boolean.parseBoolean(prop.getProperty(hostVerification));
        }
        catch(Exception ex)
        {
            throw new CmRequestException(hostVerification + " Supported value: true/false");
        }
    }        

    private void basicFileCheck(String fileName)
    {
        if(fileName == null || fileName.isEmpty()) {
            throw new CmRequestException("Config file name can't be null or empty");
        }
        
        File file = new File(fileName);
        
        if(!file.exists())
        {
            throw new CmRequestException("File " + fileName + " not found");
        }
    }

    private void updateConfig(Config config, Properties prop)
    {
        String tempDir = getTempDir(prop);
        String downloadDir = getDownloadDir(prop);
        
        if(tempDir.equals(downloadDir)) {
            throw new CmRequestException(temporaryDirectory + " and " + downloadDirectory + " can't be same");
        }
        
        config.setTempDir(tempDir);
        config.setDownloadDir(downloadDir);
        config.setTimeout(getTimeout(prop));
        config.setVerifyHost(getHostVerification(prop));
    }

    private void basicPropertyCheck(Properties prop, String fileName)
    {
        String propertieNames[] = {temporaryDirectory, downloadDirectory, requestTimeout, hostVerification};

        for(String propertyName : propertieNames)
        {
            if(!prop.containsKey(propertyName))
            {
                throw new CmRequestException(propertyName + " not found in " + fileName);
            }
        }
    }
}
