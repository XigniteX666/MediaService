package com.xignitex.services;

import com.xignitex.model.InternalFile;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface FileConversionService {

    void encodeFile(InternalFile file);

}
