package com.alfredTech.hospitality_management_application.services;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class GoogleCloudStorage {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public String uploadImage(MultipartFile photo) {
        try {
            byte[] bytes = photo.getBytes(); // Convert the file to a byte array
            String BUCKET_NAME = "hospitality-project-photos";
            BlobId blobId = BlobId.of(BUCKET_NAME, Objects.requireNonNull(photo.getOriginalFilename()));// Create a BlobId with bucket name and file name

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build(); // Define BlobInfo with metadata

            storage.create(blobInfo, bytes); // Upload the file to the specified bucket
            return String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, photo.getOriginalFilename()); // Return public URL of uploaded file
        } catch (IOException e) {
            throw new RuntimeException("Unable to upload image to Google Cloud Storage", e);
        }
    }
}
