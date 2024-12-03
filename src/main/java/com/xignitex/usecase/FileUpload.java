package com.xignitex.usecase;

import com.xignitex.gateway.FileGateway;
import com.xignitex.model.FileDescription;
import com.xignitex.services.VideoConversionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@ApplicationScoped
public class FileUpload implements UseCase<File, Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUpload.class);

    @Inject
    VideoConversionService fileService;

    @Inject
    FileGateway fileRepo;

    @Override
    public Void execute(File file) {
        try {
            FileDescription copiedFile = fileRepo.putFile(file);
            fileService.encodeFile(copiedFile);
        } catch (Exception e) {
            //TODO: create proper exceptionhandler
            LOGGER.error("Error occured: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
