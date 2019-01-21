package com.parves1527.download;

import com.parves1527.download.info.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader
{
    private final String fileName = "config.properties";

    public void updateConfiguration(Configuration configuration)
    {
        Properties prop = new Properties();

        try(InputStream input = new FileInputStream(fileName))
        {
            prop.load(input);
            
            updateTempdir(configuration, prop);
            updateDownloaddir(configuration, prop);
            updateTimeout(configuration, prop);
            updateHostVerification(configuration, prop);
            
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } 
    }
    
    private void updateTempdir(Configuration configuration, Properties prop) {
        configuration.setTempDir(prop.getProperty("temporaryDirectory"));
    }
    
    private void updateDownloaddir(Configuration configuration, Properties prop) {
        configuration.setDownloadDir(prop.getProperty("downloadDirectory"));
    }
    
    private void updateTimeout(Configuration configuration, Properties prop) {
        configuration.setTimeout(Integer.parseInt(prop.getProperty("requestTimeout")));
    }
    
    private void updateHostVerification(Configuration configuration, Properties prop) {
        configuration.setVerifyHost(Boolean.parseBoolean(prop.getProperty("hostVerification")));
    }
}
