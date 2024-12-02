package com.xignitex.resources;

import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.model.FileDescription;
import com.xignitex.usecase.FileUpload;
import com.xignitex.usecase.UploadFile;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;

import java.io.File;

@Path("/media")
public class MediaResource {

    @Inject
    ApplicationConfig config;

    @Inject
    UploadFile uploadFile;

    @Inject
    FileUpload fileUpload;

    @GET
    public String uploadFile(@RestQuery String fileName) {
        //TODO: Validate the input before processing it

        FileDescription file = FileDescription
                .builder()
                .fileName(fileName)
                .path((config.getFileLocationSource()))
                .build();

        uploadFile.execute(file);
        //TODO: create a return object
        return "OK";
    }

    @POST
    public String fileUpload(@RestForm("movie") File file) {
        File copiedFile = new File(config.getFileLocationTemp() + "upload.mov");
        try {
            fileUpload.execute(file);
        } catch (Exception e) {

        }
        return "OK";
    }

}
