package cz.muni.fi.pa165.moduleband.rest.exceptionhandling;

import cz.muni.fi.pa165.moduleband.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UrlPathHelper;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomRestGlobalExceptionHandling {

    private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiError> handleResourceNotFound(final ResourceNotFoundException ex, final HttpServletRequest request) {
        final ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ex.getLocalizedMessage(),
                URL_PATH_HELPER.getRequestUri(request));
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
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
