package com.parves1527.cm;

public class CmDownloadDetail
{
    private final CmDownloadStatus downloadStatus;    
    private final String failureReason;
    private long totalTime;

    public CmDownloadDetail(CmDownloadStatus downloadStatus)
    {
        this(downloadStatus, null);
    }
    
    public CmDownloadDetail(CmDownloadStatus downloadStatus, String failureReason)
    {
        this.downloadStatus = downloadStatus;
        this.failureReason = failureReason;
    }

    public CmDownloadStatus getDownloadStatus()
    {
        return downloadStatus;
    }

    public long getTotalTime()
    {
        return totalTime;
    }

    public String getFailureReason()
    {
        return failureReason;
    }

    public void setTotalTime(long totalTime)
    {
        this.totalTime = totalTime;
    }       
}
