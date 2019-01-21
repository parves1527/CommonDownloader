package com.parves1527.download;

import com.parves1527.download.info.Uri;
import com.parves1527.download.info.Credential;
import com.parves1527.download.info.Configuration;
import com.parves1527.download.info.DownloadDetail;
import com.parves1527.download.info.RequestInfo;

public class SyncDownloader
{                            
    public DownloadDetail download(RequestInfo requestInfo, Configuration configuration) {        
        Uri uri = requestInfo.getUri();
        Credential credential = requestInfo.getCredential();
        Protocol protocol = uri.getProtocol();
        DownloaderFactory downloaderFactory = new DownloaderFactory();
        Downloadable downloadable = downloaderFactory.getDownloader(protocol);
        return downloadable.download(uri, credential, configuration);        
    }
}
