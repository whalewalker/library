package com.library.library.web.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse {
        private boolean isSuccessful;
        private String message;
        private LocalDateTime timeStamp;

        public ApiResponse(boolean isSuccessful, String message) {
                this.isSuccessful = isSuccessful;
                this.message = message;
                timeStamp = LocalDateTime.now();
        }
}
