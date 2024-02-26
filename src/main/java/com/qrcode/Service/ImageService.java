package com.qrcode.Service;

import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    private static final String UPLOAD_DIR = "src/main/resources/static";
    private static final String IMAGE_FORMAT = "png"; // Define your image format here

    public String saveImage(BufferedImage image) throws IOException {
        // Generate a random file name to prevent conflicts
        String fileName = UUID.randomUUID().toString() + "." + IMAGE_FORMAT;

        // Set the path where the file will be stored
        Path uploadPath = Paths.get(UPLOAD_DIR);

        // Create the upload directory and parent directories if they don't exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Resolve the complete file path
        File outputFile = uploadPath.resolve(fileName).toFile();

        // Save the BufferedImage to the specified directory
        ImageIO.write(image, IMAGE_FORMAT, outputFile);

        // Return the file name or path as needed
        return fileName;
    }
}

