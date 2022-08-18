package io.github.khanhdpdx01.veserver.dto.pagination;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationResponse<T> {
    private List<T> data;
    private Integer currentPage;
    private Integer totalItems;
    private Integer totalPages;
}
