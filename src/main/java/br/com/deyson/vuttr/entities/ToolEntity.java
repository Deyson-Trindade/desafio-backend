package br.com.deyson.vuttr.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tools")
@Getter
@Setter
public class ToolEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @NotNull
    private UUID id;

    @Column(name = "title", nullable = false)
    @NotBlank
    private String title;

    @Column(name = "link", nullable = false)
    @NotBlank
    private String link;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tool")
    private List<TagEntity> tags;


}
