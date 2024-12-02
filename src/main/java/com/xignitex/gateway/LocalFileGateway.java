package com.xignitex.gateway;

import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.model.FileDescription;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@ApplicationScoped
public class LocalFileGateway implements FileGateway {

    @Inject
    ApplicationConfig config;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalFileGateway.class);

    @Override
    public FileDescription getFile(FileDescription file) throws Exception {

        LOGGER.info("Trying to get file {}", file.getPath() + "/" + file.getFileName());
        try {
            File originalFile = new File(file.getPath() + "/" + file.getFileName());
            File copiedFile = new File(config.getFileLocationTemp() + file.getFileName());

            FileUtils.copyFile(originalFile, copiedFile);

            FileDescription outputFile = FileDescription
                    .builder()
                    .fileName(copiedFile.getName())
                    .path(copiedFile.getPath())
                    .build();

            return outputFile;
        } catch (Exception e) {
            throw new Exception("Unable to get the file");
        }
    }


    @Override
    public void putFile(FileDescription file) {

    }
}
