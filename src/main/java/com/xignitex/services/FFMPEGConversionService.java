package com.xignitex.services;


import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.configuration.VideoConfig;
import com.xignitex.model.FileDescription;
import com.xignitex.utils.FileUtils;
import jakarta.enterprise.context.ApplicationScoped;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


@ApplicationScoped
public class FFMPEGConversionService implements VideoConversionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FFMPEGConversionService.class);

    VideoConfig videoConfig;
    ApplicationConfig config;

    FFMPEGConversionService(VideoConfig videoConfig, ApplicationConfig config) {
        this.videoConfig = videoConfig;
        this.config = config;
    }

    @Override
    public void encodeFile(FileDescription file) {
        String outputFile = config.getFileLocationTemp() + FileUtils.generateOutpuFileName(videoConfig.getFormat());
        LOGGER.info("encoding file {} to {}", file.getPath(), outputFile);

        try {

            FFmpeg ffmpeg = new FFmpeg("ffmpeg");
            FFprobe ffprobe = new FFprobe("ffprobe");

            FFmpegBuilder builder = new FFmpegBuilder()
                    .setInput(file.getPath())
                    .overrideOutputFiles(true)
                    .addOutput(outputFile)
                    .setFormat(videoConfig.getFormat())
                    .setVideoCodec(videoConfig.getVideoCodec())
                    .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                    .done();

            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

            executor.createJob(builder).run();
        } catch (IOException e) {
            LOGGER.error("Error encoding file, {}", e.getMessage());
        }
    }
}
