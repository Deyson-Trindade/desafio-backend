package br.com.deyson.vuttr.models;

import lombok.Data;

import java.util.UUID;

@Data
public class TagModel {

    private UUID toolId;

    private String name;

}
