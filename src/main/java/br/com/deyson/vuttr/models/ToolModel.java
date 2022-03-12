package br.com.deyson.vuttr.models;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ToolModel {

    private UUID id = UUID.randomUUID();

    private String title;

    private String link;

    private String description;

    private List<TagModel> tags;


}
