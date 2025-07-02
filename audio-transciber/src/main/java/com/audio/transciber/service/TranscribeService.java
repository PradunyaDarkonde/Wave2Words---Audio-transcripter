package com.audio.transciber.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class TranscribeService {

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    public String transcribeFile(File file) throws IOException {
        // Read audio file and convert it to Base64
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        String base64Audio = Base64.getEncoder().encodeToString(fileBytes);

        // Prepare instruction part
        Map<String, Object> instruction = new HashMap<>();
        instruction.put("text", "Transcribe the following audio to text.");

        // Prepare audio part
        Map<String, Object> audio = new HashMap<>();
        Map<String, Object> inlineData = new HashMap<>();
        inlineData.put("mimeType", "audio/wav");
        inlineData.put("data", base64Audio);
        audio.put("inlineData", inlineData);

        // Combine both parts
        Map<String, Object> messageContent = new HashMap<>();
        messageContent.put("role", "user");
        messageContent.put("parts", Arrays.asList(instruction, audio));

        // Final request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(messageContent));

        // Set HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Optional: Add API key if required as header
        //headers.set("Authorization", "Bearer " + geminiApiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // Send POST request to Gemini API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(geminiApiUrl, request, Map.class);

        // Parse response to extract transcription
        Map<String, Object> body = response.getBody();
        if (body != null && body.containsKey("candidates")) {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) body.get("candidates");

            if (!candidates.isEmpty()) {
                Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

                if (!parts.isEmpty() && parts.get(0).containsKey("text")) {
                    return parts.get(0).get("text").toString();
                }
            }
        }

        return "Transcription not found in response.";
    }
}
