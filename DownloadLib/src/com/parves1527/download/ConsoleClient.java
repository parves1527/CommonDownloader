package com.parves1527.download;

import com.parves1527.ResponseNotifier;
import com.parves1527.download.info.DownloadDetail;
import java.util.Scanner;

public class ConsoleClient implements ResponseNotifier
{
    @Override
    public void notify(DownloadDetail downloadDetail)
    {
        System.out.println(downloadDetail.getDownloadStatus().name());
    }
    
    public void start()
    {
        Scanner scanner = new Scanner(System.in);
        Downloader downloader = new Downloader();
        
        System.out.println("Simple console based file downlader");
        System.out.println("Downoad input format:");
        System.out.println(downloader.getHelpMessage());
        System.out.println("Enter q to quit");
        
        while(scanner.hasNextLine()) {
            String cmd = scanner.nextLine();
            if(cmd.equals("q")) {
                break;
            }
            
            downloader.download(cmd, this);
        }
    }
}
