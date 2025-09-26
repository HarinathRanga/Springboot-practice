package com.harinath.Homework2.advices;

import com.harinath.Homework2.exceptions.DepartmentNotExistException;
import com.harinath.Homework2.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> internalServerErrorHandler(Exception exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildApiResponseForError(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundHandler(ResourceNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildApiResponseForError(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> inValidArgumentsHandler(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid Arguments")
                .subErrors(errors)
                .build();
        return buildApiResponseForError(apiError);
    }

    @ExceptionHandler(DepartmentNotExistException.class)
    public ResponseEntity<ApiResponse<?>> departmentNotFoundHandler(DepartmentNotExistException exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildApiResponseForError(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildApiResponseForError(ApiError error) {
        return new ResponseEntity<>(new ApiResponse<>(error),error.getStatus());
    }
}
