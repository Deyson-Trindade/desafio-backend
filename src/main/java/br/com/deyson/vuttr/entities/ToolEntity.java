package br.com.deyson.vuttr.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tools")
@Data
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tool_id")
    private List<TagEntity> tags;


}
