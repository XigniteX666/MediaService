package com.xignitex.gateway;

import com.xignitex.model.InternalFile;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@ApplicationScoped
public class LocalFileGateway implements FileGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalFileGateway.class);
    private static final String FILE_LOCATION = "src/main/resources/files/copiedFile.mp3";

    @Override
    public File getFile(InternalFile file) throws Exception {
        LOGGER.info("Getting file {}", file.getFileName());
        File originalFile = new File(file.getFileName());
        File copiedFile = new File(FILE_LOCATION);

        FileUtils.copyFile(originalFile, copiedFile);
        return copiedFile;
    }


    @Override
    public void putFile(InternalFile file) {

    }
}
