package com.parves1527.cm;

import com.parves1527.cm.CmRequestInfo;
import com.parves1527.cm.download.Downloadable;
import com.parves1527.cm.download.DownloaderFactory;
import com.parves1527.cm.download.info.Protocol;
import com.parves1527.cm.download.info.Uri;
import com.parves1527.cm.download.info.Credential;
import com.parves1527.cm.download.info.Config;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CmSyncDownloader
{                            
    public CmDownloadDetail download(CmRequestInfo cmRequestInfo) {
        Uri uri = cmRequestInfo.getUri();
        
        Credential credential = cmRequestInfo.getCredential();
        Protocol protocol = uri.getProtocol();
        DownloaderFactory downloaderFactory = new DownloaderFactory();
        Downloadable downloadable = downloaderFactory.getDownloader(protocol);
        
        if(uri.getPort() == -1)
        {
            uri.setPort(downloadable.getDefaultPort());
        }
        
        Config configuration = cmRequestInfo.getConfiguration();
        
        long start = System.currentTimeMillis();
        
        CmDownloadDetail downloadDetail = downloadable.download(uri, credential, configuration);
        
        long end = System.currentTimeMillis();
        
        downloadDetail.setTotalTime(end - start);
        
        if(downloadDetail.getDownloadStatus() == CmDownloadStatus.completed) {
            moveToDownloadDir(cmRequestInfo);
        }
        return downloadDetail;
    }
    
    private void moveToDownloadDir(CmRequestInfo cmRequestInfo) {
        String absoluteTempPath = cmRequestInfo.getUri().getLocalResource();
        File file = new File(absoluteTempPath);
        String tempFileName = file.getName();
        Path tempPath = Paths.get(absoluteTempPath);
        String absoluteDownloadPath = cmRequestInfo.getConfiguration().getDownloadDir() + File.separator + tempFileName;        
        Path downloadPath = Paths.get(absoluteDownloadPath);
        
        try {
            Files.move(tempPath, downloadPath,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
