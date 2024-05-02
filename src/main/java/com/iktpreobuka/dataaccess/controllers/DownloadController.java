package com.iktpreobuka.dataaccess.controllers;

import com.iktpreobuka.dataaccess.services.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/dataaccess")
public class DownloadController {

    @Autowired
    FileHandler fileHandler;

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        try {
            String filename = "UserEntity.csv";
            InputStreamResource file = new InputStreamResource(fileHandler.loadFile());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(file);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to import data to CSV file" + e.getMessage());
        }
    }
}
