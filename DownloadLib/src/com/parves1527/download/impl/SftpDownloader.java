package com.parves1527.download.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.parves1527.download.info.Configuration;
import com.parves1527.download.info.Credential;
import com.parves1527.download.info.DownloadDetail;
import com.parves1527.download.info.DownloadStatus;
import com.parves1527.download.Downloadable;
import com.parves1527.download.info.Uri;

public class SftpDownloader implements Downloadable {
    
    @Override
    public DownloadDetail download(Uri uri, Credential credential, Configuration configuration) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            String userName = credential.getUserName();
            String password = credential.getPassword().getPassString();
            session = jsch.getSession(userName, uri.getHost(), uri.getPort());
            session.setConfig("StrictHostKeyChecking", (configuration.isVerifyHost() ? "yes" : "no"));
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get(uri.getRemoteResource(), uri.getLocalResource());
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        
        return new DownloadDetail(DownloadStatus.completed, 0, "");
    }
}
