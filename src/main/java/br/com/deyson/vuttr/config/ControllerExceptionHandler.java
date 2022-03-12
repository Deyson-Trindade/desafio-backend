package br.com.deyson.vuttr.config;

import br.com.deyson.vuttr.dto.ErrorResponseDTO;
import br.com.deyson.vuttr.dto.ExceptionResponseDTO;
import br.com.deyson.vuttr.exceptions.ToolNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ToolNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleToolNotFound(ToolNotFoundException toolNotFoundException, HttpServletRequest request) {

        new ErrorResponseDTO( toolNotFoundException.getLocalizedMessage());
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(new Date(), request.getRequestURI(), toolNotFoundException.getMessage());

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

}
