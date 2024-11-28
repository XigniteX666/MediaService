package com.xignitex.gateway;

import com.xignitex.model.InternalFile;

import java.io.File;

public interface FileGateway {
    File getFile(InternalFile file) throws Exception;

    void putFile(InternalFile file);
}
