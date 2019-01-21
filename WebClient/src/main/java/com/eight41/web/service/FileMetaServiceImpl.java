package com.eight41.web.service;

import com.eight41.web.dao.FileMetaDao;
import com.eight41.web.model.FileMeta;
import org.springframework.beans.factory.annotation.Autowired;

public class FileMetaServiceImpl implements FileMetaService
{
    @Autowired
    private FileMetaDao fileMeta;
    
    @Override
    public boolean add(FileMeta user)
    {
        return fileMeta.add(user);
    }

    @Override
    public FileMeta get(int id)
    {
        return fileMeta.get(id);
    }    
}
