package com.eight41.web.dao;

import com.eight41.web.model.FileMeta;

public interface FileMetaDao
{
    boolean add(FileMeta fileData);

    FileMeta get(int id);
}
