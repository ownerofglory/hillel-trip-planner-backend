package ua.ithillel.tripplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ua.ithillel.tripplanner.model.dto.ErrorDTO;

@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEntityNotFound(EntityNotFoundException e) {
        final String message = e.getMessage();
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(InconsistencyException.class)
    public ResponseEntity<ErrorDTO> handleInconsistency(InconsistencyException e) {
        final String message = e.getMessage();
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleAny(Exception e) {
        final String message = e.getMessage();
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorDTO> handleFileUploadException(FileUploadException e) {
        final String message = e.getMessage();
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(InvalidImageException.class)
    public ResponseEntity<ErrorDTO> handleInvalidImage(InvalidImageException e) {
        final String message = e.getMessage();
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

}
