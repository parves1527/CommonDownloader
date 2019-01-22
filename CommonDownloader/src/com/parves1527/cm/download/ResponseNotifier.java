package com.parves1527.cm.download;

import com.parves1527.cm.CmDownloadDetail;
import com.parves1527.cm.download.info.Uri;

public interface ResponseNotifier
{
    void notify(Uri uri, CmDownloadDetail downloadDetail);
}
