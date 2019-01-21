package com.parves1527.download.info;

public class DownloadDetail
{
    private final DownloadStatus downloadStatus;
    private final int totalTime;
    private final String failureReason;

    public DownloadDetail(DownloadStatus downloadStatus, int totalTime, String failureReason)
    {
        this.downloadStatus = downloadStatus;
        this.totalTime = totalTime;
        this.failureReason = failureReason;
    }

    public DownloadStatus getDownloadStatus()
    {
        return downloadStatus;
    }

    public int getTotalTime()
    {
        return totalTime;
    }

    public String getFailureReason()
    {
        return failureReason;
    }
   
    
}
