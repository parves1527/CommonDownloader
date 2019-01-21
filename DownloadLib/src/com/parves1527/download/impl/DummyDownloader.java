package com.parves1527.download.impl;

import com.parves1527.download.Downloadable;
import com.parves1527.download.info.Uri;
import com.parves1527.download.info.DownloadDetail;
import com.parves1527.download.info.Credential;
import com.parves1527.download.info.Configuration;

public class DummyDownloader implements Downloadable
{

    @Override
    public DownloadDetail download(Uri uri, Credential credential, Configuration configuration)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
