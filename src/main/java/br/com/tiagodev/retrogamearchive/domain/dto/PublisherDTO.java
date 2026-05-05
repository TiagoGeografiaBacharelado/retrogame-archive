package br.com.tiagodev.retrogamearchive.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

}