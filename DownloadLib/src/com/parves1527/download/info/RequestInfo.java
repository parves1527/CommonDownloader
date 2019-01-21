package com.parves1527.download.info;

public class RequestInfo
{
    private final Credential credential;
    private final Uri uri;

    public RequestInfo(Uri uri, Credential credential)
    {
        this.uri = uri;
        this.credential = credential;        
    }

    public Credential getCredential()
    {
        return credential;
    }

    public Uri getUri()
    {
        return uri;
    }
    
    
}
