package br.com.deyson.vuttr.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ExceptionResponseDTO {

    private final Date timestamp;
    private final String message;
    private final String path;

    public ExceptionResponseDTO(Date timestamp, String path, String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    }

}
