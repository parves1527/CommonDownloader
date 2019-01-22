package com.parves1527.cm.download;

import com.parves1527.cm.download.info.Protocol;
import com.parves1527.cm.download.impl.SftpDownloader;
import com.parves1527.cm.download.impl.HttpDownloader;
import com.parves1527.cm.download.impl.FtpDownloader;

public class DownloaderFactory
{
    public Downloadable getDownloader(Protocol protocol) {
                
        Downloadable downloadable = null;
        
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
}
