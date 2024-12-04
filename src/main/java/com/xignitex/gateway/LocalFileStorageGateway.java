package com.xignitex.gateway;

import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.model.FileDescription;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@ApplicationScoped
public class LocalFileStorageGateway implements StorageGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalFileStorageGateway.class);

    ApplicationConfig config;

    LocalFileStorageGateway(ApplicationConfig config) {
        this.config = config;
    }

    @Override
    public FileDescription getFile(FileDescription file) throws Exception {

        LOGGER.info("Trying to get file {}", file.getPath() + "/" + file.getFileName());
        try {
            File originalFile = new File(file.getPath() + "/" + file.getFileName());
            File copiedFile = new File(config.getFileLocationTemp() + file.getFileName());

            FileUtils.copyFile(originalFile, copiedFile);

            return FileDescription
                    .builder()
                    .fileName(copiedFile.getName())
                    .path(copiedFile.getPath())
                    .build();


        } catch (Exception e) {
            throw new Exception("Unable to get the file");
        }
    }


    @Override
    public FileDescription putFile(File file) throws Exception {
        String fileName = com.xignitex.utils.FileUtils.generateRandomFileName("tmp");
        LOGGER.info("Uploading the file {}", config.getFileLocationTemp() + "/" + fileName);
        try {


            File copiedFile = new File(config.getFileLocationTemp() + fileName);

            FileUtils.copyFile(file, copiedFile);

            return FileDescription
                    .builder()
                    .fileName(copiedFile.getName())
                    .path(copiedFile.getPath())
                    .build();

        } catch (Exception e) {
            throw new Exception("Unable to download the file");
        }

    }
}
