package com.eparlak.personeltayintalebi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String hata = ex.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "Bad Request", hata));
    }

    public static class ErrorResponse {
        public int status;
        public String error;
        public String message;

        public ErrorResponse(int status, String error, String message) {
            this.status = status;
            this.error = error;
            this.message = message;

        }
    }

    // Custom mükerrerlik hatası
    @ExceptionHandler(CustomMukerrerTayinTalebi.class)
    public ResponseEntity<ErrorResponse> handleCustomMukerrerTayinTalebi(CustomMukerrerTayinTalebi ex) {
        log.error("Mükerrerlik hatası: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, "Conflict", ex.getMessage()));
    }

    // başvuru tarihi, tercih bitiş tarihi, ve sonuç tarihi birbiri ile uyumsuz ise
    @ExceptionHandler(CustomTarihSiralamasiHatali.class)
    public ResponseEntity<ErrorResponse> handleCustomTarihSiralamasiHatali(CustomTarihSiralamasiHatali ex) {
        log.error("Tarih sıralaması hatası: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "Bad Request", ex.getMessage()));
    }

    // Diğer tüm beklenmeyen hatalar
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "Internal Server Error", "Sunucu Mesajı :Bir hata oluştu."));
    }
}