package com.parves1527.download;

import com.parves1527.download.info.Uri;
import com.parves1527.download.info.DownloadDetail;
import com.parves1527.download.info.Credential;
import com.parves1527.download.info.Configuration;

public interface Downloadable
{
    DownloadDetail download(Uri uri, Credential credential, Configuration configuration);
}
