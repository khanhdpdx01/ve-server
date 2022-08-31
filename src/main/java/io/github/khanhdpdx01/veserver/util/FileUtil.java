package io.github.khanhdpdx01.veserver.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    private static final Path currentPath = Paths.get(".").normalize().toAbsolutePath();
    private static final Path savePath = currentPath.resolve("image");

    public static void save(MultipartFile file) {
        try {
            Files.write(savePath.resolve(file.getOriginalFilename()), file.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Resource load(String filename) {
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
        Path file = savePath.resolve(filename);
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
