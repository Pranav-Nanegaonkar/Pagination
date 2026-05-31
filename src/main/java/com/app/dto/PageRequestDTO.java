package com.app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageRequestDTO {

    @Min(value = 0, message = "Page index must be >= 0")
    private int page = 0;

    @Min(value = 1,   message = "Page size must be >= 1")
    @Max(value = 100, message = "Page size must be <= 100")   // hard cap — never let clients request 10k rows
    private int size = 20;

    private String sortBy    = "id";
    private String direction = "asc";   // "asc" | "desc"

    // Cursor-based support (optional — null = use offset mode)
    private Long   cursorId        = null;
    private String cursorCreatedAt = null;
}