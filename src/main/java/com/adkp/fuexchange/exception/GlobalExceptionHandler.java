package com.adkp.fuexchange.exception;

import com.adkp.fuexchange.response.ResponseObject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseObject<Object> usernameNotFoundException() {
        return ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.name())
                .content("Mã số sinh viên không tồn tại hoặc chưa được đăng ký!")
                .build();
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseObject<Object> dataAccessException(UsernameNotFoundException usernameNotFoundException) {
        return ResponseObject.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .content("Lỗi trong quá trình lưu trữ dữ liệu!")
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseObject<Object> dataAccessException(BadCredentialsException badCredentialsException) {
        return ResponseObject.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(HttpStatus.FORBIDDEN.name())
                .content("Không có quyền truy cập vào trang web!")
                .build();
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseObject exception(Exception exception) {
//        return ResponseObject.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(HttpStatus.INTERNAL_SERVER_ERROR.name())
//                .content("Lỗi không xác định!")
//                .build();
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseObject<Object> entityNotFoundException(Exception exception) {
        return ResponseObject.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.name())
                .content("Thực thể không tồn tại!")
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseObject<Object> dataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseObject<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseObject<Object> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content(ex.getLocalizedMessage())
                .build();
    }
}
