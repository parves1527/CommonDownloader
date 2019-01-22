package com.parves1527.cm.download.impl;

import com.parves1527.cm.download.info.Config;
import com.parves1527.cm.download.info.Credential;
import com.parves1527.cm.CmDownloadDetail;
import com.parves1527.cm.CmDownloadStatus;
import com.parves1527.cm.download.Downloadable;
import com.parves1527.cm.download.info.Uri;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpDownloader implements Downloadable {
    
    private static final int DEFAULT_PORT = 80;

    @Override
    public int getDefaultPort()
    {
        return DEFAULT_PORT;
    }
    
    @Override
    public CmDownloadDetail download(Uri uri, Credential credential, Config configuration) {
        try
        {
            String address = uri.getProtocol().name() + "://" + uri.getHost();
            int port = uri.getPort();
            if(port != DEFAULT_PORT)
            {
                address = address + ":" + port;
            }
            URL website = new URL(address);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());    
            FileOutputStream fos = new FileOutputStream(uri.getLocalResource());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        } catch (IOException ex) {
            return new CmDownloadDetail(CmDownloadStatus.failed, ex.getMessage());
        }
        
        return new CmDownloadDetail(CmDownloadStatus.completed);
    }
}
