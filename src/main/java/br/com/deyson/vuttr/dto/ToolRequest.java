package br.com.deyson.vuttr.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ToolRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String link;

    @NotBlank
    private String description;

}
