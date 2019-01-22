package com.parves1527.cm.download;

import com.parves1527.cm.download.info.Uri;
import com.parves1527.cm.CmDownloadDetail;
import com.parves1527.cm.download.info.Credential;
import com.parves1527.cm.download.info.Config;

public interface Downloadable
{
    CmDownloadDetail download(Uri uri, Credential credential, Config configuration);
    
    int getDefaultPort();
}
