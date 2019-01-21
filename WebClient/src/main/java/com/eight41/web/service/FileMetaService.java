package com.eight41.web.service;

import com.eight41.web.model.FileMeta;

public interface FileMetaService
{
    boolean add(FileMeta fileMeta);

    FileMeta get(int id);
}
