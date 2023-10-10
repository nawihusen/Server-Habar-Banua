package com.habarbanua.model.news;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
