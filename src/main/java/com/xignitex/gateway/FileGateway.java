package com.xignitex.gateway;

import com.xignitex.model.FileDescription;

import java.io.File;

public interface FileGateway {
    FileDescription getFile(FileDescription file) throws Exception;

    FileDescription putFile(File file) throws Exception;
}
