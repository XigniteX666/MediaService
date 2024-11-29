package com.xignitex.services;

import com.xignitex.model.FileDescription;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface VideoConversionService {

    void encodeFile(FileDescription file);

}
