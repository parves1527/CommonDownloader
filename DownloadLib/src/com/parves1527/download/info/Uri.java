package com.parves1527.download.info;

import com.parves1527.download.Protocol;

public class Uri
{
    private final Protocol protocol;
    private final String host;
    private final int port;
    private final String remoteResource;
    private final String localResource;

    public Uri(Protocol protocol, String host, int port, String remoteResource, String localResource)
    {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.remoteResource = remoteResource;
        this.localResource = localResource;
    }

    public Protocol getProtocol()
    {
        return protocol;
    }

    public String getHost()
    {
        return host;
    }

    public int getPort()
    {
        return port;
    }

    public String getRemoteResource()
    {
        return remoteResource;
    }

    public String getLocalResource()
    {
        return localResource;
    }

    @Override
    public String toString()
    {
        return "Uri{" + "protocol=" + protocol + ", host=" + host + ", port=" + port + ", remoteResource=" + remoteResource + ", localResource=" + localResource + '}';
    }                 
}
