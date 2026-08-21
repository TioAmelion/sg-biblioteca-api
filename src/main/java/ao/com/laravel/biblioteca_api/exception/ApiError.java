package ao.com.laravel.biblioteca_api.exception;

import lombok.AllArgsConstructor;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ApiError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private List<ValidationError> errors;
}
