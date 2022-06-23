package proposta.mv.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proposta.mv.desafio.exceptions.business.*;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(CityExistException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestArgument (CityExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, ex.getMessage()));
    }

    @ExceptionHandler(StateNotExistException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestArgument (StateNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage()));
    }

    @ExceptionHandler(StateExistException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestArgument (StateExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, ex.getMessage()));
    }

    @ExceptionHandler(CityNotExistException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestArgument (CityNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage()));
    }

    @ExceptionHandler(ClientNotExistException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestArgument (ClientNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage()));
    }
}
