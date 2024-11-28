package com.xignitex.services;

import com.xignitex.model.InternalFile;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface FileService {
    void getFile(InternalFile file) throws Exception;

    void encodeFile(InternalFile file);

    void putFile(InternalFile file);


}
