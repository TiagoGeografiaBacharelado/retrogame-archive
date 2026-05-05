package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.PublisherDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Publisher;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherDTO createPublisher(PublisherDTO dto) {
        Publisher publisher = toEntity(dto);
        Publisher saved = publisherRepository.save(publisher);
        return toDTO(saved);
    }

    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PublisherDTO getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Publisher not found with id: " + id)
                );
        return toDTO(publisher);
    }

    public PublisherDTO updatePublisher(Long id, PublisherDTO dto) {
        Publisher existing = publisherRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Publisher not found with id: " + id)
                );
        existing.setName(dto.getName());
        Publisher updated = publisherRepository.save(existing);
        return toDTO(updated);
    }

    public void deletePublisher(Long id) {
        if (!publisherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Publisher not found with id: " + id);
        }
        publisherRepository.deleteById(id);
    }

    private PublisherDTO toDTO(Publisher publisher) {
        return PublisherDTO.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }

    private Publisher toEntity(PublisherDTO dto) {
        return Publisher.builder()
                .name(dto.getName())
                .build();
    }
}