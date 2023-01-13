package com.azl6.fileupload.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/listfiles")
public class FileExposureController {

    @Value("${app.file.upload-dir}")
    public String DIRECTORY;

    @GetMapping
    public ResponseEntity<List<File>> list() {

        List<File> files = new ArrayList<>();

        // Checks the root directory only.
        try (Stream<Path> walk = Files.walk(Paths.get(DIRECTORY), 1)) {
            List<Path> result = walk.filter(Files::isRegularFile).collect(Collectors.toList());

            for (Path p : result) {
                files.add(p.toFile().getAbsoluteFile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(files);

    }

}
