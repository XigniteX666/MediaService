package com.xignitex.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
@Getter
public class VideoConfig {
    @ConfigProperty(name = "settings.video.format")
    private String format;

    @ConfigProperty(name = "settings.video.codec")
    private String videoCodec;

}
