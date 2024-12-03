package com.xignitex.resources;

import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.model.FileDescription;
import com.xignitex.usecase.EncodeFile;
import com.xignitex.usecase.FileUpload;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.apache.commons.io.FilenameUtils;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@Path("/media")
public class MediaResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaResource.class);

    @Inject
    ApplicationConfig config;

    @Inject
    EncodeFile encodeFile;

    @Inject
    FileUpload fileUpload;

    @GET
    public Response encodeFile(@RestQuery String fileName) {

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
        return Response.ok().build();
    }

    @POST
    public Response fileUpload(@RestForm("movie") File file) {
        try {
            fileUpload.execute(file);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok().build();
    }

    //TODO return an array of errors instead of a boolean
    private boolean validateUploadedFile(String fileName) {

        return FilenameUtils.getExtension(fileName).equals("mov");
    }
}
