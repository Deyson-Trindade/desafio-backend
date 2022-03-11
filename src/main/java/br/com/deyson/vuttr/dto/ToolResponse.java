package br.com.deyson.vuttr.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ToolResponse {

    private UUID id;

    private String title;

    private String link;

    private String description;

    private List<String> tags;
}
