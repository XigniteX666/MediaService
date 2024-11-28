package com.xignitex.controller;

import com.xignitex.model.InternalFile;
import com.xignitex.usecase.UploadFile;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestQuery;

@Path("/media")
public class MediaResource {

    @Inject
    UploadFile uploadFile;

    @POST
    public String uploadFile(@RestQuery String fileName) {
        InternalFile file = new InternalFile();
        file.setFileName(fileName);
        return uploadFile.execute(file);
    }

}
