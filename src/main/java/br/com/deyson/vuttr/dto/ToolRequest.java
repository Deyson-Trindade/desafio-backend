package br.com.deyson.vuttr.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ToolRequest {


    @NotBlank(message = "field title cannot be empty")
    private final String title;

    @NotBlank(message = "field link cannot be empty")
    private final String link;

    @NotBlank(message = "field description cannot be empty")
    private final String description;

    @NotEmpty(message = "field must have at least one tag")
    private final List<String> tags;

}
