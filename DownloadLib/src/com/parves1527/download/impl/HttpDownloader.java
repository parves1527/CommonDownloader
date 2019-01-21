package com.parves1527.download.impl;

import com.parves1527.download.info.Configuration;
import com.parves1527.download.info.Credential;
import com.parves1527.download.info.DownloadDetail;
import com.parves1527.download.info.DownloadStatus;
import com.parves1527.download.Downloadable;
import com.parves1527.download.info.Uri;
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
    
    @Override
    public DownloadDetail download(Uri uri, Credential credential, Configuration configuration) {
        try
        {
            String address = uri.getProtocol().name() + "://" + uri.getHost();
            int port = uri.getPort();
            if(port != 80)
            {
                address = address + ":" + port;
            }
            URL website = new URL(address);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());    
            FileOutputStream fos = new FileOutputStream(uri.getLocalResource());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return new DownloadDetail(DownloadStatus.completed, 0, null);
    }
}
