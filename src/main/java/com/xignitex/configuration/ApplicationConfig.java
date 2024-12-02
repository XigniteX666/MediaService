package com.xignitex.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Getter
@ApplicationScoped
public class ApplicationConfig {
    @ConfigProperty(name = "settings.application.source.location")
    private String fileLocationSource;
    @ConfigProperty(name = "settings.application.temp.location")
    private String fileLocationTemp;
    @ConfigProperty(name = "settings.application.destination.location")
    private String fileLocationDestination;
}
