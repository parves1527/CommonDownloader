package com.parves1527.download;

import com.parves1527.download.impl.DummyDownloader;
import com.parves1527.download.impl.SftpDownloader;
import com.parves1527.download.impl.HttpDownloader;
import com.parves1527.download.impl.FtpDownloader;

public class DownloaderFactory
{
    public Downloadable getDownloader(Protocol protocol) {
                
        Downloadable downloadable;
        
        switch(protocol)
        {
            case http:
            case https:    
                downloadable = getHttpDownlader();
                break;
                
            case ftp:
                downloadable = getFtpDownlader();
                break;
            case sftp:
                downloadable = getSftpDownlader();
                break;
            default:
                downloadable = getDummyDownlader();
        }
        
        return downloadable;
    }
    
    private HttpDownloader getHttpDownlader() {
        return new HttpDownloader();
    }
    
    private FtpDownloader getFtpDownlader() {
        return new FtpDownloader();
    }
    
    private SftpDownloader getSftpDownlader() {
        return new SftpDownloader();
    }
    
    private DummyDownloader getDummyDownlader() {
        return new DummyDownloader();
    }     
}
