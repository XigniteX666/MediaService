package com.xignitex.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ApplicationConfig {
    @ConfigProperty(name = "settings.application.source.location")
    private String fileLocationSource;
    @ConfigProperty(name = "settings.application.temp.location")
    private String fileLocationTemp;
    @ConfigProperty(name = "settings.application.destination.location")
    private String fileLocationDestination;

    public String getFileLocationSource() {
        return fileLocationSource;
    }

    public void setFileLocationSource(String fileLocationSource) {
        this.fileLocationSource = fileLocationSource;
    }

    public String getFileLocationTemp() {
        return fileLocationTemp;
    }

    public void setFileLocationTemp(String fileLocationTemp) {
        this.fileLocationTemp = fileLocationTemp;
    }

    public String getFileLocationDestination() {
        return fileLocationDestination;
    }

    public void setFileLocationDestination(String fileLocationDestination) {
        this.fileLocationDestination = fileLocationDestination;
    }
}
