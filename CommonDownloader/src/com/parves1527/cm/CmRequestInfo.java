package com.parves1527.cm;

import com.parves1527.cm.download.request.ConfigGenerator;
import com.parves1527.cm.download.request.UriGenerator;
import com.parves1527.cm.download.info.Config;
import com.parves1527.cm.download.info.Credential;
import com.parves1527.cm.download.info.Password;
import com.parves1527.cm.download.info.Uri;

public class CmRequestInfo
{
    private final Credential credential;
    private final Uri uri;
    private final Config config;

    private CmRequestInfo(Uri uri, Config config, String userName, Password password)
    {
        this.uri = uri;
        this.config = config;
        this.credential = new Credential(userName, password);        
    }

    public Credential getCredential()
    {
        return credential;
    }

    public Uri getUri()
    {
        return uri;
    }

    public Config getConfiguration()
    {
        return config;
    }
    
    public static String getHelpMessage() {
        String msg = "<protocol>://<host-name>/<file-name> <username(optional)> <password(optional)>";
        
        return msg;
    }
    
    public static class Builder {
        
        private String userName;
        private Password password;
        private final String address;
        private final String configFile;
        
        public static Builder newInstance(String address, String configFile) {
            return new Builder(address, configFile);
        }
        
        private Builder(String address, String configFile) {
            this.address = address;
            this.configFile = configFile;
        }                

        public Builder setUserName(String userName)
        {
            this.userName = userName;
            return this;
        }

        public Builder setPassword(Password password)
        {
            this.password = password;
            return this;
        }
        
        public CmRequestInfo build()
        {
            ConfigGenerator configGenerator = new ConfigGenerator();
            Config config = configGenerator.generate(configFile);
            
            UriGenerator uriGenerator = new UriGenerator();            
            Uri uri = uriGenerator.generate(address, config.getTempDir());
            
            return new CmRequestInfo(uri, config, userName, password);
        }
    
    }
}
