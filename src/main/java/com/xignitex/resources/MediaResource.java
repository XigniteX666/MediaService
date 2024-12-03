package com.xignitex.resources;

import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.model.FileDescription;
import com.xignitex.usecase.EncodeFile;
import com.xignitex.usecase.FileUpload;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;

import java.io.File;

@Path("/media")
public class MediaResource {

    ApplicationConfig config;
    EncodeFile encodeFile;
    FileUpload fileUpload;

    MediaResource(ApplicationConfig config, EncodeFile encodeFile, FileUpload fileUpload) {
        this.config = config;
        this.encodeFile = encodeFile;
        this.fileUpload = fileUpload;
    }

    @GET
    public Response encodeFile(@RestQuery String fileName) {
        try {
            if (validateUploadedFile(fileName)) {
                FileDescription file = FileDescription
                        .builder()
                        .fileName(fileName)
                        .path((config.getFileLocationSource()))
                        .build();

                encodeFile.execute(file);
            } else {
                return Response.status(400).build();
            }
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok().build();
    }

    @POST
    public Response uploadFile(@RestForm("movie") File file) {
        try {
            fileUpload.execute(file);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok().build();
    }

    //TODO return an array of errors instead of a boolean
    private boolean validateUploadedFile(String fileName) {
        //TODO: Add proper validations
        return FilenameUtils.getExtension(fileName).equals("mov");
    }
}
