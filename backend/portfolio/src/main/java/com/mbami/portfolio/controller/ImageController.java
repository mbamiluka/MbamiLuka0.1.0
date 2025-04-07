package com.mbami.portfolio.controller;

import com.mbami.portfolio.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin(origins = {"https://mbamiluka-65b99.web.app/", "https://mbamiluka-65b99.firebaseapp.com/", "http://localhost:3000"})
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, String> response = imageService.uploadImage(file);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Image upload failed"));
        }
    }

    @DeleteMapping("/{publicId}")
    public ResponseEntity<String> deleteImage(@PathVariable String publicId) {
        try {
            imageService.deleteImage(publicId);
            return ResponseEntity.ok("Image deleted successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Image deletion failed");
        }
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<Map<String, String>> getImage(@PathVariable String publicId) {
        try {
            Map<String, String> response = imageService.getImage(publicId);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Image retrieval failed"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> getAllImages() {
        try {
            Map<String, String> response = imageService.getAllImages();
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Image retrieval failed"));
        }
    }
}