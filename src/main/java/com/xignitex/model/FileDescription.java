package com.xignitex.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDescription {
    private String path;
    private String fileName;

}
