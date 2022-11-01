package io.github.khanhdpdx01.veserver.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtil {
    private static String imageLocation;

    @Value("${storage.image-location}")
    public void setImageLocation(String imageLocationArg) {
        imageLocation = imageLocationArg;
    }

    public static void save(MultipartFile file) {
        final Path savePath = Paths.get(imageLocation);
        try {
            Files.write(savePath.resolve(file.getOriginalFilename()), file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Resource load(String filename) {
        final Path savePath = Paths.get(imageLocation);
        try {
            Path file = savePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static byte[] loading(String filename) {
        final Path savePath = Paths.get(imageLocation);
        Path file = savePath.resolve(filename);
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
