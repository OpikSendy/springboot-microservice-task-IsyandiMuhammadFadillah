package com.isyandi.book.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private boolean success;
    private int status;
    private String error;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
}
