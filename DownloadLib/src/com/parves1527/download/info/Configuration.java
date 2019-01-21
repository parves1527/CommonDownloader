package com.parves1527.download.info;

import java.io.File;

public class Configuration
{
    private int timeout;
    private boolean verifyHost = false;
    private String downloadDir = ".";
    private String tempDir = "." + File.separator + "temp";

    public String getDownloadDir()
    {
        return downloadDir;
    }

    public void setDownloadDir(String downloadPath)
    {
        this.downloadDir = downloadPath;
    }

    public int getTimeout()
    {
        return timeout;
    }

    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }

    public boolean isVerifyHost()
    {
        return verifyHost;
    }

    public void setVerifyHost(boolean verifyHost)
    {
        this.verifyHost = verifyHost;
    }

    public void setTempDir(String tempDir)
    {
        this.tempDir = tempDir;
    }
    
    public String getTempDir()
    {
        return tempDir;
    }
}
