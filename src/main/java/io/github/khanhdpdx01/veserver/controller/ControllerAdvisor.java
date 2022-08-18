package io.github.khanhdpdx01.veserver.controller;

import io.github.khanhdpdx01.veserver.dto.api.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException methodEx, WebRequest request) {
        List<ApiError> errors = new ArrayList<>();
        methodEx.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = error instanceof FieldError
                    ? ((FieldError) error).getField()
                    : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ApiError().setSource(fieldName).setMessage(errorMessage));
        });
        return new ResponseEntity(mapErrorsException(errors, request.getContextPath()), HttpStatus.BAD_REQUEST);
    }

    public Map<String, Object> mapErrorsException(List<ApiError> errors, String path) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", 400);
        body.put("path", path);
        body.put("errors", errors);
        return body;
    }
}
