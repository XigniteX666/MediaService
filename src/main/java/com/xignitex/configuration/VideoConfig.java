package com.xignitex.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
//TODO: Check why Lombok is not working for now use getters and setters
public class VideoConfig {
    @ConfigProperty(name = "settings.video.format")
    private String format;

    @ConfigProperty(name = "settings.video.codec")
    private String videoCodec;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }
}
