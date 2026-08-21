package ao.com.laravel.biblioteca_api.exception;

import ao.com.laravel.biblioteca_api.service.LivroService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice

public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(LivroService.class);

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ApiError> tratarLivroNaoEncontrado(
            LivroNaoEncontradoException ex,
            HttpServletRequest request) {

        ApiError error = new ApiError.ApiErrorBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ApiError> tratarRegraNegocio(
            RegraNegocioException ex,
            HttpServletRequest request) {

        ApiError erro = new ApiError.ApiErrorBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> tratarValidacao(
            MethodArgumentNotValidException ex,
            HttpServletRequest request){

        List<ValidationError> list = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field ->
                        new ValidationError(
                                field.getField(),
                                field.getDefaultMessage()))
                .toList();

        ApiError error = new ApiError.ApiErrorBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Erro de validação")
                .path(request.getRequestURI())
                .errors(list)
                .build();

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> tratarException(
            Exception ex,
            HttpServletRequest request) {

        ApiError erro = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())//"Ocorreu um erro interno no servidor."
                .path(request.getRequestURI())
                .build();

        log.error("erro interno do servidor", erro);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);

    }
}
