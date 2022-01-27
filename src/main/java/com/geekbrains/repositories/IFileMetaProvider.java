package com.geekbrains.repositories;

import com.geekbrains.entities.FileMetaDTO;

import java.util.Collection;
import java.util.UUID;

public interface IFileMetaProvider {
    String checkFileExists(UUID fileHash);

    void saveFileMeta(UUID Hash, String fileName, int sybType);

    Collection<FileMetaDTO> getMetaFiles(int subtype);
}
