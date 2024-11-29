package com.xignitex.controller;

import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.model.FileDescription;
import com.xignitex.usecase.UploadFile;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/media")
public class MediaResource {
    // private static final String SOURCE_FOLDER = "src/main/resources/files";
    @Inject
    UploadFile uploadFile;
    @Inject
    ApplicationConfig config;

    @POST
    public String uploadFile(@RestQuery String fileName) {
        //TODO: Validate the input before processing it

        FileDescription file = new FileDescription();
        file.setFileName(fileName);
        file.setPath(config.getFileLocationSource());

        uploadFile.execute(file);
        //TODO: create a return object
        return "OK";
    }

}
