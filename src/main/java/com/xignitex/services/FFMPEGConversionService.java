package com.xignitex.services;


import com.xignitex.configuration.ApplicationConfig;
import com.xignitex.configuration.VideoConfig;
import com.xignitex.model.FileDescription;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

@ApplicationScoped
public class FFMPEGConversionService implements VideoConversionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FFMPEGConversionService.class);

    @Inject
    VideoConfig videoConfig;

    @Inject
    ApplicationConfig config;

    @Override
    public void encodeFile(FileDescription file) {
        String outputFile = config.getFileLocationTemp() + generateOutpuFileName(videoConfig.getFormat());
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

    //TODO: see if it's possible to create a better solution
    private String generateOutpuFileName(String format) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString + "." + format;
    }
}
