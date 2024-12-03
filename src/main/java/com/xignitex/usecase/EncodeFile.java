package com.xignitex.usecase;

import com.xignitex.gateway.FileGateway;
import com.xignitex.model.FileDescription;
import com.xignitex.services.VideoConversionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//TODO: check scoping
@ApplicationScoped
public class EncodeFile implements UseCase<FileDescription, Void> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncodeFile.class);

    @Inject
    VideoConversionService fileService;

    @Inject
    FileGateway fileRepo;

    @Override
    public Void execute(FileDescription file) {
        LOGGER.info("Processing file {}", file.getFileName());

        try {
            FileDescription copiedFile = fileRepo.getFile(file);
            fileService.encodeFile(copiedFile);

        } catch (Exception e) {
            //TODO: create proper exceptionhandler
            LOGGER.error("Error occured: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
