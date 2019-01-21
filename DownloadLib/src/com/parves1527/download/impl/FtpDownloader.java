package com.parves1527.download.impl;

import com.parves1527.download.info.Configuration;
import com.parves1527.download.info.Credential;
import com.parves1527.download.info.DownloadDetail;
import com.parves1527.download.info.DownloadStatus;
import com.parves1527.download.Downloadable;
import com.parves1527.download.info.Uri;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

public class FtpDownloader implements Downloadable
{
    @Override
    public DownloadDetail download(Uri uri, Credential credential, Configuration configuration)
    {
        String server = uri.getHost();
        int port = uri.getPort();
        String user = credential.getUserName();
        String pass = credential.getPassword().getPassString();

        FTPClient ftpClient = new FTPClient();
        try
        {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
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
                boolean success = ftpClient.completePendingCommand();
            }
            inputStream.close();

        } catch (IOException ex)
        {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
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

        return new DownloadDetail(DownloadStatus.completed, 0, "");
    }
}
