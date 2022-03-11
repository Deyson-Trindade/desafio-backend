package br.com.deyson.vuttr.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "tags")
@Data
public class TagEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private UUID toolId;

}
