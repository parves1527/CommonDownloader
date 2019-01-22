package com.parves1527;

import com.parves1527.cm.CmAsyncDownloader;
import com.parves1527.cm.CmRequestInfo;
import com.parves1527.cm.download.ResponseNotifier;
import com.parves1527.cm.CmDownloadDetail;
import com.parves1527.cm.CmDownloadStatus;
import com.parves1527.cm.CmRequestException;
import com.parves1527.cm.download.info.Uri;
import java.util.Scanner;

public class ConsoleClient implements ResponseNotifier
{
    @Override
    public void notify(Uri uri, CmDownloadDetail downloadDetail)
    {
        CmDownloadStatus downloadStatus = downloadDetail.getDownloadStatus();
        StringBuilder sb = new StringBuilder();
        
        sb.append(uri);
        sb.append(System.lineSeparator());
        sb.append("Download Status: ");
        sb.append(downloadStatus.name());
        sb.append(System.lineSeparator());
        sb.append("Total time in milisecond: ");
        sb.append(downloadDetail.getTotalTime());
        sb.append(" ms");
        sb.append(System.lineSeparator());
        if(downloadStatus != CmDownloadStatus.completed) {
            sb.append(downloadDetail.getFailureReason());
        }
        
        System.out.println(sb);
    }
    
    public void start(String fileName)
    {
        InputParser inputParser = new InputParser();
        Scanner scanner = new Scanner(System.in);
        CmAsyncDownloader downloader = new CmAsyncDownloader();
        
        System.out.println("Simple console based file downlader");
        System.out.println("Downoad input format:");
        System.out.println(CmRequestInfo.getHelpMessage());
        System.out.println("Enter q to quit");
        
        while(scanner.hasNextLine()) {
            String cmd = scanner.nextLine();
            if(cmd.equals("q")) {
                break;
            }
                        
            try 
            {
                CmRequestInfo cmRequestInfo = inputParser.parse(cmd, fileName);
                downloader.download(cmRequestInfo, this);
                System.out.println("Request has been sent");
            }
            catch(CmRequestException ex)
            {
                System.err.println(ex.getMessage());
            }            
        }
    }
}
