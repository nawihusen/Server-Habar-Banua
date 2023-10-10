package com.habarbanua.model.news;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsResponse {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private String title;

    private String content;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("deleted_at")
    private Instant deletedAt;
//    308 241 326
}
