package br.com.deyson.vuttr.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDTO {

    private final String error;

    public ErrorResponseDTO(String error) {
        this.error = error;
    }

}
