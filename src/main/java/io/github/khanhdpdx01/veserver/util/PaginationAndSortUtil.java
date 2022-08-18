package io.github.khanhdpdx01.veserver.util;

import io.github.khanhdpdx01.veserver.dto.pagination.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PaginationAndSortUtil {
    public static Pageable create(int page, int size, String[] sorts) {
        Sort sort = sortBy(sorts);

        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(page - 1, size, sort);
        } else {
            pageable = PageRequest.of(page - 1, size);
        }

        return pageable;
    }

    public static Sort sortBy(String[] sorts) {
        if (sorts == null || sorts.length == 0) return null;

        List<Sort.Order> orders = new ArrayList<>();

        if (sorts[0].contains(",")) {
            for (String sort : sorts) {
                String[] _sort = sort.replace("\\s", "").split(",");
                Sort.Direction direction = getSortDirection(_sort[1]);
                orders.add(new Sort.Order(direction, _sort[0]));
            }
        } else {
            String field = sorts[0].replace("\\s", "");
            String direction = sorts[1].replace("\\s", "");

            orders.add(new Sort.Order(getSortDirection(direction), field));
        }

        return Sort.by(orders);
    }

    public static <T> PaginationResponse map(Page<T> page) {
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setData(page.getContent());
        paginationResponse.setCurrentPage(page.getNumber() + 1);
        paginationResponse.setTotalItems(page.getSize());
        paginationResponse.setTotalPages(page.getTotalPages());
        return paginationResponse;
    }

    private static Sort.Direction getSortDirection(String direction) {
        if ("desc".equals(direction)) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

}
