package cz.muni.fi.pa165.modulepdf.rest.exceptionhandling;

import cz.muni.fi.pa165.modulepdf.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomRestGlobalExceptionHandling {

    private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();

    @ExceptionHandler({IOException.class})
    public ResponseEntity<ApiError> handleIOException(final ResourceNotFoundException ex, final HttpServletRequest request) {
        final ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getLocalizedMessage(),
                URL_PATH_HELPER.getRequestUri(request));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiError> handleResourceNotFound(final ResourceNotFoundException ex, final HttpServletRequest request) {
        final ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getLocalizedMessage(),
                URL_PATH_HELPER.getRequestUri(request));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, IllegalStateException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle all the exceptions not matched by above-mentioned definitions.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleAll(final Exception ex, HttpServletRequest request) {
        final ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                getInitialException(ex).getLocalizedMessage(),
                URL_PATH_HELPER.getRequestUri(request));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    private Exception getInitialException(Exception ex) {
        while (ex.getCause() != null) {
            ex = (Exception) ex.getCause();
        }
        return ex;
    }

}
