package br.com.brlima.cursomc.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.brlima.cursomc.service.exception.FileException;

@Service
public class ImageService {

    public BufferedImage getJPGImageFromFile(MultipartFile uploadedFile) {
        String extension = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

        if (!"png".equals(extension) && !"jpg".equals(extension)) {
            throw new FileException("Somente imagens com formato JPG e PNG são permitidas");
        }

        try {
            BufferedImage image = ImageIO.read(uploadedFile.getInputStream());

            if ("png".equals(extension)) {
                image = this.pngToJpg(image);
            }

            return image;
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

    public BufferedImage pngToJpg(BufferedImage image) {
        BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage image, String extension) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

    public BufferedImage cropSquare(BufferedImage image) {
        int min = (image.getHeight() <= image.getWidth()) ? image.getHeight() : image.getWidth();
        return Scalr.crop(image, (image.getWidth() / 2) - (min / 2), (image.getHeight() / 2) - (min / 2), min, min);
    }

    public BufferedImage resize(BufferedImage image, int size) {
        return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
    }
}