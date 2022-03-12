package br.com.deyson.vuttr.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ToolRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String link;

    @NotBlank
    private String description;

    @NotEmpty(message = "Tag list mustn't be empty")
    private List<String> tags;

}
