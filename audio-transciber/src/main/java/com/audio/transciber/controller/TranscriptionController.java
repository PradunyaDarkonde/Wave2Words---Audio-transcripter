package com.audio.transciber.controller;

import com.audio.transciber.service.TranscribeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transcriber")
public class TranscriptionController {
    private final
    TranscribeService transcribeService;

    @PostMapping
    public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("audio", "wav");
        file.transferTo(tempFile);

        String transcription = transcribeService.transcribeFile(tempFile);

        tempFile.delete();
        return new ResponseEntity<>(transcription, HttpStatus.OK);
    }
}