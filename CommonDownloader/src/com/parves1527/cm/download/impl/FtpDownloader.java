package com.parves1527.cm.download.impl;

import com.parves1527.cm.download.info.Config;
import com.parves1527.cm.download.info.Credential;
import com.parves1527.cm.CmDownloadDetail;
import com.parves1527.cm.CmDownloadStatus;
import com.parves1527.cm.download.Downloadable;
import com.parves1527.cm.download.info.Uri;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpDownloader implements Downloadable
{    
    private static final int DEFAULT_PORT = 21;

    @Override
    public int getDefaultPort()
    {
        return DEFAULT_PORT;
    }
    
    @Override
    public CmDownloadDetail download(Uri uri, Credential credential, Config configuration)
    {
        String server = uri.getHost();
        int port = uri.getPort();
        String userName = credential.getUserName();
        String password = credential.getPassword().getPassString();

        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.connect(server, port);
            ftpClient.login(userName, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            String remoteFile = uri.getRemoteResource();
            File downloadFile = new File(uri.getLocalResource());
            InputStream inputStream;
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile)))
            {
                inputStream = ftpClient.retrieveFileStream(remoteFile);
                byte[] bytesArray = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(bytesArray)) != -1)
                {
                    outputStream.write(bytesArray, 0, bytesRead);
                }
                
                ftpClient.completePendingCommand();
            }
            inputStream.close();

        } catch (IOException ex)
        {
            return new CmDownloadDetail(CmDownloadStatus.failed, ex.getMessage());
        } finally
        {
            try
            {
                if (ftpClient.isConnected())
                {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return new CmDownloadDetail(CmDownloadStatus.completed);
    }
}
