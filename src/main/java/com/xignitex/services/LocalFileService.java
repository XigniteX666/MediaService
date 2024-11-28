package com.xignitex.services;


import com.xignitex.model.InternalFile;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@ApplicationScoped
public class LocalFileService implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalFileService.class);

    @Override
    public void getFile(InternalFile file) throws Exception {
        LOGGER.info("Getting file {}", file.getFileName());
        File originalFile = new File(file.getFileName());
        File copiedFile = new File("src/main/resources/files/copiedFile");

        FileUtils.copyFile(originalFile, copiedFile);
    }

    @Override
    public void encodeFile(InternalFile path) {

    }

    @Override
    public void putFile(InternalFile path) {

    }
}
