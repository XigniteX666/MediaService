package com.xignitex.gateway;

import com.xignitex.model.FileDescription;

public interface FileGateway {
    FileDescription getFile(FileDescription file) throws Exception;

    void putFile(FileDescription file);
}
