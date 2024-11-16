package hust.project.restaurant_management.exception;

import hust.project.restaurant_management.constants.ErrorCode;
import hust.project.restaurant_management.entity.dto.response.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Resource> handleRuntimeException(Exception e) {
        log.error("Unexpected error", e);
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(new Resource(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<Resource> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(new Resource(errorCode.getCode(), errorCode.getMessage()));
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Resource> handleAccessDeniedException(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(new Resource(errorCode.getCode(), errorCode.getMessage()));
    }
}
