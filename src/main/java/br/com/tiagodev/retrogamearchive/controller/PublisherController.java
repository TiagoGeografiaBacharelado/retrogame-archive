package br.com.tiagodev.retrogamearchive.controller;

import br.com.tiagodev.retrogamearchive.domain.dto.PublisherDTO;
import br.com.tiagodev.retrogamearchive.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<PublisherDTO> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        PublisherDTO saved = publisherService.createPublisher(publisherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable Long id) {
        PublisherDTO publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable Long id, @Valid @RequestBody PublisherDTO publisherDTO) {
        PublisherDTO updated = publisherService.updatePublisher(id, publisherDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}