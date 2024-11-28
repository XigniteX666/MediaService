package com.xignitex.services;


import com.xignitex.model.InternalFile;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FileConversionServiceImpl implements FileConversionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileConversionServiceImpl.class);

    @Override
    public void encodeFile(InternalFile file) {
        LOGGER.info("encoding file {}", file.getFileName());
    }

}
