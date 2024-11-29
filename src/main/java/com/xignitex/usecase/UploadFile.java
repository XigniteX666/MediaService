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
public class UploadFile implements UseCase<FileDescription, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFile.class);

    @Inject
    VideoConversionService fileService;

    @Inject
    FileGateway fileRepo;

    @Override
    public String execute(FileDescription file) {
        LOGGER.info("File {}", file.getFileName());
        //TODO: check why the lombok builder does not work
        try {
            FileDescription copiedFile = fileRepo.getFile(file);
            fileService.encodeFile(copiedFile);
            fileRepo.putFile(file);
        } catch (Exception e) {
            //TODO: create proper exceptionhandler
            LOGGER.error("Error occured: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return "testing";
    }
}
