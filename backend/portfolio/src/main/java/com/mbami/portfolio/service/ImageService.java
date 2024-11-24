package com.mbami.portfolio.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mbami.portfolio.model.Content;
import com.mbami.portfolio.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@Service
public class ImageService {

    private final Cloudinary cloudinary;
    private final ContentRepository contentRepository;

    @Autowired
    public ImageService(Cloudinary cloudinary, ContentRepository contentRepository) {
        this.cloudinary = cloudinary;
        this.contentRepository = contentRepository;
    }

    public Map<String, String> uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Content content = new Content();
        content.setName(file.getOriginalFilename());
        content.setContentType("image");
        content.setContent((String) uploadResult.get("url"));

        contentRepository.save(content);

        Map<String, String> response = new HashMap<>();
        response.put("url", (String) uploadResult.get("url"));
        return response;
    }

    public void deleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}