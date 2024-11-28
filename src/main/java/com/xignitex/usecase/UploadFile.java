package com.xignitex.usecase;

import com.xignitex.model.InternalFile;
import com.xignitex.services.FileService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//TODO: check scoping
@ApplicationScoped
public class UploadFile implements UseCase<String, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFile.class);
    @Inject
    FileService fileService;

    @Override
    public String execute(String filename) {
        LOGGER.info("File {}", filename);
        //TODO: check why the lombok builder does not work
        InternalFile file = new InternalFile();
        file.setFileName(filename);
        fileService.getFile(file);
        fileService.encodeFile(file);
        fileService.putFile(file);
        return "testing";
    }
}
