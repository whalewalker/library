package com.library.library.domain.models;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean success;
    private String message;
}
