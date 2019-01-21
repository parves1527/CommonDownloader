package com.parves1527.download;

import com.parves1527.ResponseNotifier;
import com.parves1527.download.info.Configuration;
import com.parves1527.download.info.DownloadDetail;
import com.parves1527.download.info.RequestInfo;

public class Downloader
{    
    public void download(String input, ResponseNotifier notifier)
    {
        Configuration configuration = getConfiguratin();
        
        InputParser inputParser = new InputParser(configuration.getTempDir());        
        RequestInfo requestInfo = inputParser.parseInput(input);
        
        TaskHandler handler = new TaskHandler(requestInfo, configuration, notifier);
        Thread thread = new Thread(handler);
        thread.start();
    }
    
    public String getHelpMessage() {
        String msg = "<protocol>://<host-name>/<file-name> <username(optional)> <password(optional)>";
        
        return msg;
    }
        
    private Configuration getConfiguratin()
    {
        Configuration configuration = new Configuration();

        ConfigReader configReader = new ConfigReader();
        configReader.updateConfiguration(configuration);
        
        return configuration;
    }
    
    private class TaskHandler implements Runnable
    {
        private final RequestInfo requestInfo;
        private final Configuration configuration;
        private final ResponseNotifier notifier;

        public TaskHandler(RequestInfo requestInfo, Configuration configuration, ResponseNotifier notifier)
        {
            this.requestInfo = requestInfo;
            this.configuration = configuration;
            this.notifier = notifier;
        }                
        
        @Override
        public void run()
        {
            SyncDownloader downloader = new SyncDownloader();
            DownloadDetail downloadDetail = downloader.download(requestInfo, configuration);
            notifier.notify(downloadDetail);
        }        
    }
}
