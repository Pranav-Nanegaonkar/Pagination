package com.app.service;

import com.app.dto.PageRequestDTO;
import com.app.dto.PageResponseDTO;
import com.app.exception.InvalidPageRequestException;
import com.app.model.Product;
import com.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //* Whitelist of sortable columns — prevents SQL injection via sort param
    private static final Set<String> SORTABLE_FIELDS = Set.of("id", "name", "price", "createdAt", "category");

    //* Offset Pagination

    public PageResponseDTO<Product> getProductsOffset(PageRequestDTO req) {

        validateSortField(req.getSortBy());

        Sort sort = buildSort(req.getSortBy(), req.getDirection());
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);

        Page<Product> page = productRepository.findAll(pageable);

        return PageResponseDTO.<Product>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .hasNext(page.hasNext())
                .hasPrev(page.hasPrevious())
                .build();
    }

    //* Helpers
    private Sort buildSort(String sortBy, String direction) {
        Sort.Direction dir = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return Sort.by(dir, sortBy);
    }

    private void validateSortField(String sortBy) {
        if (!SORTABLE_FIELDS.contains(sortBy)) {
            throw new InvalidPageRequestException(
                    "Invalid sort field: '" + sortBy + "'. Allowed: " + SORTABLE_FIELDS
            );
        }
    }
}

