package br.com.deyson.vuttr.repositories;

import br.com.deyson.vuttr.entities.ToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, UUID> {

    @Query("SELECT t FROM ToolEntity t JOIN t.tags tag WHERE tag.name = :tag")
    List<ToolEntity> findAllByTagsIn(@Param(value = "tag") String tag);
}
