package com.app.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PageResponseDTO<T> {

    private List<T> content;

    // Offset pagination meta
    private int  pageNumber;
    private int  pageSize;
    private long totalElements;
    private int  totalPages;
    private boolean first;
    private boolean last;

    // Cursor pagination meta (null when using offset mode)
    private String nextCursor;   // base64-encoded cursor for next page
    private String prevCursor;   // base64-encoded cursor for previous page
    private boolean hasNext;
    private boolean hasPrev;
}